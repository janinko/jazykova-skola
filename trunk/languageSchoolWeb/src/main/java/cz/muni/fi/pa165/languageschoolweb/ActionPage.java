package cz.muni.fi.pa165.languageschoolweb;

import cz.muni.fi.pa165.languageschool.api.services.GenerateDataService;
import cz.muni.fi.pa165.languageschoolweb.components.GeneratorForm;
import cz.muni.fi.pa165.languageschoolweb.components.LoginForm;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;


public class ActionPage extends BasePage {
	private static final long serialVersionUID = 1L;
    @SpringBean
    private GenerateDataService generator;

	private static boolean generated = false;
    
    public ActionPage(PageParameters parameters) {
		super(parameters);
		if(parameters == null){
			add(new Label("actionPanel", new ResourceModel("noAction")));
			return;
		}

		if(!parameters.get("generate").isNull()){
			add(new GeneratorForm("actionPanel"));
		}else if(!parameters.get("login").isNull()){
			add(new LoginForm("actionPanel"));
		}else{
			add(new Label("actionPanel", new ResourceModel("unknownAction")));
		}
    }
}
