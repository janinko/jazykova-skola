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
		
		// curl -i -H "Content-Type: application/json" -H "Accept: application/json" -X POST -d '{"id":2,"name":"Kurz FJ pro experty - konverzace I","language":"FJ","level":5}' http://localhost:8084/languageSchoolWeb/TeacherAPI
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
			// curl -X DELETE ../TeacherAPI/{id}
			teachers.deleteTeacher(teachers.readTeacher(Integer.parseInt(ApiHelper.getFirstArg(pathInfo))));
			mapper.writeValue(response.getOutputStream(), teachers.readTeacher(Integer.parseInt(ApiHelper.getFirstArg(pathInfo))));
		}
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		TeacherDto teacher = mapper.readValue(request.getInputStream(), TeacherDto.class);
		
		// TODO isn't supported
		teachers.deleteTeacher(teacher);
		teachers.createTeacher(teacher);
	}
	
	@Override
    public void init(ServletConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
        super.init(config);
    }
	
	
}
