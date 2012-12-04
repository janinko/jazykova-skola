package cz.muni.fi.pa165.languageschoolweb.components;

import cz.muni.fi.pa165.languageschool.api.Language;
import cz.muni.fi.pa165.languageschool.api.adapters.CourseDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.dto.CourseDto;
import cz.muni.fi.pa165.languageschoolweb.CoursesPage;
import cz.muni.fi.pa165.languageschoolweb.model.CourseFormModel;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.RangeValidator;

/**
 * @author xbrazdi1, xchrastk, xkelnar
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
		private static final long serialVersionUID = 1L;
		
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
			
			List<String> langs = new ArrayList<String>();
			for (Language l : Language.values()) {
				langs.add(l.toString());
			}

			selectedLanguage = Language.AJ.toString();

			add( new DropDownChoice<String>( "courseLanguage",
			         new PropertyModel<String>( this, "selectedLanguage" ),
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
		public void onSubmit()
        {
			CourseFormModel model = (CourseFormModel) getDefaultModelObject();

			try{
				CourseDto cour = new CourseDto();
				cour.setLevel(model.getCourseLevel());
				cour.setName(model.getCourseName());
				cour.setLanguage(getSelectedLanguage());
				
				courseDtoAdapter.createCourse(cour);
				getSession().error(getString("courseCreated"));
			}catch(Exception ex){
				getSession().error(getString("courseNotCreated"));
				getSession().error(ex);
			}

			setResponsePage(CoursesPage.class);
        }

	}
}
