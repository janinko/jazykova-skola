/*
 * Copyright 2012 kelnar.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cz.muni.fi.pa165.languageschoolweb.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pa165.languageschool.api.adapters.CourseDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.dto.CourseDto;
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

public class CourseAPI extends HttpServlet {

	@Autowired
    private CourseDtoAdapter courses;
	
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
            mapper.writeValue(response.getOutputStream(), courses.getAllCourses());
		} else if (ApiHelper.isNumeric(ApiHelper.getFirstArg(pathInfo))) {
			mapper.writeValue(response.getOutputStream(), courses.read(Long.valueOf(ApiHelper.getFirstArg(pathInfo))));
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
		
		// curl -i -H "Content-Type: application/json" -H "Accept: application/json" -X POST -d '{"id":2,"name":"Kurz FJ pro experty - konverzace I","language":"FJ","level":5}' http://localhost:8084/languageSchoolWeb/pa165/api/course
		CourseDto course = mapper.readValue(request.getInputStream(), CourseDto.class);
		if (course.getId() != null) {
			course.setId(null);
		}
		courses.createCourse(course);
	}
	
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String pathInfo = request.getPathInfo();
		
		// curl -X DELETE ../CourseAPI/{id}
		courses.deleteCourse(courses.read(Long.valueOf(ApiHelper.getFirstArg(pathInfo))));
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		CourseDto course = mapper.readValue(request.getInputStream(), CourseDto.class);
		courses.updateCourse(course);
	}
	
	@Override
    public void init(ServletConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
        super.init(config);
    }	
	
}