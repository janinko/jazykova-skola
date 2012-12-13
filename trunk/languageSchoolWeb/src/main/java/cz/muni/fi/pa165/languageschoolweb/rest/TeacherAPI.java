package cz.muni.fi.pa165.languageschoolweb.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pa165.languageschool.api.adapters.TeacherDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.dto.TeacherDto;
import java.io.IOException;
import java.util.Locale;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateOptimisticLockingFailureException;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 *
 * @author kelnar
 */
public class TeacherAPI extends HttpServlet {

    @Autowired
    private TeacherDtoAdapter teachers;
    private ObjectMapper mapper = new ObjectMapper();

    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Locale lock = request.getLocale();
        request.setCharacterEncoding("utf-8");
        String pathInfo = request.getPathInfo();
        response.setContentType("application/json");

        if (ApiHelper.isNoArgument(pathInfo)) {
            mapper.writeValue(response.getOutputStream(), teachers.getAllTeachers());
        } else if (ApiHelper.isNumeric(ApiHelper.getFirstArg(pathInfo))) {
            if (teachers.readTeacher(Long.valueOf(ApiHelper.getFirstArg(pathInfo))) != null){
                mapper.writeValue(response.getOutputStream(), teachers.readTeacher(Long.valueOf(ApiHelper.getFirstArg(pathInfo))));
            } else {
                response.setStatus(404);    //teacher not found
            } 
        } else {
            response.setStatus(400);    //parameter is not a Long
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        
        TeacherDto teacher = mapper.readValue(request.getInputStream(), TeacherDto.class);
        teacher.setId(null);
        try {
        
            teachers.createTeacher(teacher);
        } catch (DataIntegrityViolationException ex) {
            response.setStatus(400);                //teacher with the email exists
        }
    } 

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String pathInfo = request.getPathInfo();

        if (ApiHelper.isNoArgument(pathInfo) || !ApiHelper.isNumeric((ApiHelper.getFirstArg(pathInfo)))) {
            response.setStatus(400);
        } else {
            try {
                // curl -X DELETE ../api/teacher/{id}
                teachers.deleteTeacher(teachers.readTeacher(Long.valueOf(ApiHelper.getFirstArg(pathInfo))));
            } catch (IllegalArgumentException ex) {
                response.setStatus(404);
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        TeacherDto teacher = mapper.readValue(request.getInputStream(), TeacherDto.class);
        
        try {
            teachers.updateTeacher(teacher);
        } catch (HibernateOptimisticLockingFailureException ex) {
            response.setStatus(404);
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
        super.init(config);
    }
}