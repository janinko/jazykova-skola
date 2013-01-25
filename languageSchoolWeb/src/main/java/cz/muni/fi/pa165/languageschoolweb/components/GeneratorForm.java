package cz.muni.fi.pa165.languageschoolweb.components;

import cz.muni.fi.pa165.languageschool.api.utilservices.GenerateDataService;
import cz.muni.fi.pa165.languageschoolweb.HomePage;
import cz.muni.fi.pa165.languageschoolweb.model.GeneratorFormModel;
import cz.muni.fi.pa165.languageschoolweb.security.SpringAuthenticatedWebSession;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.RangeValidator;

/**
 * @author xbrazdi1, xchrastk, xkelnar
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
				// simulating admin before generating
				AuthenticatedWebSession session = AuthenticatedWebSession.get();
				if(session.authenticate("admin@admin.com", "admin")){
					getSession().info("Přihlášen!");// jako student: " + student.getFirstName() + student.getLastName());
				}else{
					getSession().error("Špatné email nebo heslo 2134");
				}
				
				generator.generateData(model.getCourseCount(),
									   model.getLessonCount(),
									   model.getStudentCount(),
									   model.getTeacherCount());
				getSession().info(getString("generated"));
				
				// delete simulated admin after generating
				//session.logout();
				
			}catch(Exception ex){
				getSession().error(getString("notGenerated"));
				getSession().error(ex);
				Logger.getLogger(InputForm.class.getName()).log(Level.WARNING, "Failed to generate data", ex);
			}

			setResponsePage(HomePage.class,params);
        }
	}
}
