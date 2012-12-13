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
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 *
 * @author xchrastk
 */
public class TeacherAPI extends HttpServlet {

    @Autowired
    private TeacherDtoAdapter teachers;
    private ObjectMapper mapper = new ObjectMapper();

    /**
	 * GET.
	 * 
	 * Returns error 404 if teacher does not exist
	 * Returns error 400 if id param is not number
	 * 
	 * Example of curl:
	 * curl http://localhost:8080/pa165/api/teacher
	 * curl http://localhost:8080/pa165/api/teacher/1
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
	 * CREATE.
	 * 
	 * Returns error 400 if bad object is pasted or a teacher with the same email already exists
	 * 
	 * Example of curl:
	 * curl -i -H "Content-Type: application/json" -H "Accept: application/json" -X POST -d '{"id":6,"firstName":"Abadon","lastName":"Šťastný","email":"astastny@example.com","languages":["RU","NJ"],"nativeLanguage":"FJ"}' http://localhost:8080/pa165/api/teacher
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
        } catch (/*DataIntegrityViolation*/Exception ex) {  //teacher with the email exists
            response.setStatus(400);                
        }
    } 
    
    /**
	 * UPDATE.
	 * 
	 * Returns error 400 if bad object is pasted.
	 * 
	 * Example of curl: 
	 * curl -i -H "Content-Type: application/json" -H "Accept: application/json" -X PUT -d '{"id":6,"firstName":"Abadon","lastName":"Šťastný","email":"astastny@example.com","languages":["RU","NJ"],"nativeLanguage":"FJ"}' http://localhost:8080/pa165/api/teacher
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException 
	 */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        TeacherDto teacher = mapper.readValue(request.getInputStream(), TeacherDto.class);
        
        try {
            teachers.updateTeacher(teacher);
        } catch (/*HibernateOptimisticLockingFailure*/Exception ex) {
            response.setStatus(404);
        }
    }

    /**
	 * DELETE.
	 * 
	 * Returns error 404 if teacher does not exist
	 * Returns error 400 if bad argument is pasted or no argument is pasted
	 * 
	 * Example of curl:
	 * curl -X DELETE http://localhost:8084/languageSchoolWeb/pa165/api/course/1
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException 
	 */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String pathInfo = request.getPathInfo();

        if (ApiHelper.isNoArgument(pathInfo) || !ApiHelper.isNumeric((ApiHelper.getFirstArg(pathInfo)))) {
            response.setStatus(400);
        } else {
            try {
                teachers.deleteTeacher(teachers.readTeacher(Long.valueOf(ApiHelper.getFirstArg(pathInfo))));
            } catch (/*IllegalArgument*/Exception ex) {
                response.setStatus(404);        //teacher with the id not found
            }
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
        super.init(config);
    }
}