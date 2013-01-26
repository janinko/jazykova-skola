    package cz.muni.fi.pa165.languageschoolweb.components;

import cz.muni.fi.pa165.languageschoolweb.HomePage;
import cz.muni.fi.pa165.languageschoolweb.model.LoginModel;
import cz.muni.fi.pa165.languageschoolweb.security.SpringAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author xbrazdi1, xchrastk, xkelnar
 */
public class LoginForm extends Panel{
	private static final long serialVersionUID = 1L;

	/*@SpringBean
	private StudentDtoAdapter students;
	@SpringBean
	private TeacherDtoAdapter teachers;*/

	public LoginForm(String id){
		super(id);
		add(new InputForm("generateForm"));
	}

	class InputForm extends Form<LoginModel>{
		private static final long serialVersionUID = 1L;

        public InputForm(String id){
			super(id, new CompoundPropertyModel<LoginModel>(new LoginModel()));

            add(new TextField<String>("email", String.class).setRequired(true));
            add(new PasswordTextField("password").setRequired(true));

            add(new Button("loginButton"));
		}

		@Override
		protected void onError() {			
			PageParameters params = new PageParameters();
			getSession().error("Chyba v zadavani modelu");
			setResponsePage(HomePage.class,params);
		}

		@Override
        public void onSubmit()
        {
			setResponsePage(HomePage.class);
			
			LoginModel model = (LoginModel) getDefaultModelObject();
			SpringAuthenticatedWebSession session = (SpringAuthenticatedWebSession)AuthenticatedWebSession.get();
			if(session.authenticate(model.getEmail(), model.getPassword())){
				session.fillRoles();
                getSession().info("Přihlášen!");// jako student: " + student.getFirstName() + student.getLastName());
                
			}else{
				getSession().error("Špatné email nebo heslo");
			}
/*

			SecurityContextHolder.getContext().setAuthentication(res);

			for(GrantedAuthority authority : res.getAuthorities()){
				if("ROLE_TEACHER".equals(authority.getAuthority())){
					TeacherDto teacher = teachers.readTeacher(model.getEmail());
					getSession().info("Přihlášen jako učitel: " + teacher.getFirstName() + teacher.getLastName());
				}else if("ROLE_STUDENT".equals(authority.getAuthority())){
					StudentDto student = students.read(model.getEmail());
					getSession().info("Přihlášen jako student: " + student.getFirstName() + student.getLastName());
				}else{
					getSession().info("!!!!! Přihlášen jako: " + authority.getAuthority());
				}
			}*/
        }
	}
}
