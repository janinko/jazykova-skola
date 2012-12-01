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
package cz.muni.fi.pa165.languageschoolweb.components;

import cz.muni.fi.pa165.languageschool.api.adapters.CourseDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.dto.CourseDto;
import cz.muni.fi.pa165.languageschool.api.entities.Teacher;
import cz.muni.fi.pa165.languageschoolweb.CoursesPage;
import cz.muni.fi.pa165.languageschoolweb.model.CourseFormModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.RangeValidator;

/**
 *
 * @author kelnar
 */
public final class AddCourseForm extends Panel {
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	private CourseDtoAdapter courseDtoAdapter;
	

	
	
	

	public AddCourseForm(String id) {
		super(id);
		add(new CourseInputForm("addCourseForm"));
	}
	
	class CourseInputForm extends Form<CourseFormModel>{
		
		private String selectedLanguage;
		
		public CourseInputForm(String id) {
			super(id, new CompoundPropertyModel<CourseFormModel>(new CourseFormModel()));
			
			add(new TextField<Integer>("courseLevel", Integer.class)
					.setRequired(true)
					.add(new RangeValidator<Integer>(0, 5)) 
			);
			
			add(new TextField<String>("courseName", String.class)
					.setRequired(true)
					// TODO validation
			);
			
			//add(new TextField<String>("courseLanguage", String.class)
				//	.setRequired(true)
					// TODO validation
			//);
			
			List<String> langs = new ArrayList<String>();
			for (Teacher.Language l : Teacher.Language.values()) {
				langs.add(l.toString());
			}
			
			selectedLanguage = "AJ";
			
		   
		
			 add( new DropDownChoice( "courseLanguage", 
					 new PropertyModel( this, "selectedLanguage" ), 
					 langs )
			 );
		   
			
			add(new Button("addButton"));
		}
		
		
		public String getSelectedLanguage() {
			return selectedLanguage;
		}

		public void setSelectedLanguage(String selectedLanguage) {
			this.selectedLanguage = selectedLanguage;
		}
		
		
		
		@Override
		protected void onError() {			
			PageParameters params = new PageParameters();
			params.set("error", "Chyba v zadavani modelu");
			setResponsePage(CoursesPage.class,params);
		}
		
		
		public void onSubmit()
        {
			CourseFormModel model = (CourseFormModel) getDefaultModelObject();

			PageParameters params = new PageParameters();
			try{
				CourseDto cour = new CourseDto();
				cour.setLevel(model.getCourseLevel());
				cour.setName(model.getCourseName());
				cour.setLanguage(getSelectedLanguage());
				
				courseDtoAdapter.createCourse(cour);
				params.set("message", "Vygenerováno");
			}catch(Exception ex){
				params.set("error", "Chyba generování dat: " + ex);
			}

			setResponsePage(CoursesPage.class,params);
        }

	}
}
