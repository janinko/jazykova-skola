package cz.muni.fi.pa165.languageschoolweb.components;

import cz.muni.fi.pa165.languageschool.api.services.GenerateDataService;
import cz.muni.fi.pa165.languageschoolweb.HomePage;
import cz.muni.fi.pa165.languageschoolweb.model.GeneratorFormModel;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.RangeValidator;

/**
 * @author
 */
public class GeneratorForm extends Panel{
	private static final long serialVersionUID = 1L;

	@SpringBean
	private GenerateDataService generator;

	public GeneratorForm(String id){
		super(id);
		add(new InputForm("generateForm"));
	}

	class InputForm extends Form<GeneratorFormModel>{
		private static final long serialVersionUID = 1L;

        public InputForm(String id){
			super(id, new CompoundPropertyModel<GeneratorFormModel>(new GeneratorFormModel()));

            add(new TextField<Integer>("teacherCount", Integer.class).setRequired(true).add(
                new RangeValidator<Integer>(0, 100)));
            add(new TextField<Integer>("courseCount", Integer.class).setRequired(true).add(
                new RangeValidator<Integer>(0, 100)));
            add(new TextField<Integer>("lessonCount", Integer.class).setRequired(true).add(
                new RangeValidator<Integer>(0, 1000)));
            add(new TextField<Integer>("studentCount", Integer.class).setRequired(true).add(
                new RangeValidator<Integer>(0, 100)));

            add(new Button("generateButton"));
		}

		@Override
        public void onSubmit()
        {
			GeneratorFormModel model = (GeneratorFormModel) getDefaultModelObject();

			PageParameters params = new PageParameters();
			try{
				generator.generateData(model.getCourseCount(),
									   model.getLessonCount(),
									   model.getStudentCount(),
									   model.getTeacherCount());
				getSession().info("Vygenerováno");
			}catch(Exception ex){
				getSession().error(ex);
			}

			setResponsePage(HomePage.class,params);
        }
	}
}
