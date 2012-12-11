package cz.muni.fi.pa165.languageschoolweb.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pa165.languageschool.api.adapters.TeacherDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.dto.TeacherDto;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
		request.setCharacterEncoding("utf-8");
		String pathInfo = request.getPathInfo();
		response.setContentType("application/json");
		
		if (ApiHelper.isNoArgument(pathInfo)) {
            mapper.writeValue(response.getOutputStream(), teachers.getAllTeachers());
		} else if (ApiHelper.isNumeric(ApiHelper.getFirstArg(pathInfo))) {
			mapper.writeValue(response.getOutputStream(), teachers.readTeacher(Long.valueOf(ApiHelper.getFirstArg(pathInfo))));
		} else {response.sendError(400, "Wrong parameter "+pathInfo+". Provide id(number) or no parameters.");}
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
		teachers.createTeacher(teacher);
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String pathInfo = request.getPathInfo();
		
		if (ApiHelper.isNoArgument(pathInfo)) {
			// TODO isn't supported
		} else {
			// curl -X DELETE ../api/teacher/{id}
			teachers.deleteTeacher(teachers.readTeacher(Long.valueOf(ApiHelper.getFirstArg(pathInfo))));
		}
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		TeacherDto teacher = mapper.readValue(request.getInputStream(), TeacherDto.class);
		
		teachers.updateTeacher(teacher);
	}
	
	@Override
    public void init(ServletConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
        super.init(config);
    }
}