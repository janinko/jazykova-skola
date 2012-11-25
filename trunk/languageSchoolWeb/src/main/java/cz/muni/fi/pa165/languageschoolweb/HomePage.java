/*
 * HomePage.java
 *
 * Created on 21. listopad 2012, 20:21
 */
package cz.muni.fi.pa165.languageschoolweb;

import cz.muni.fi.pa165.languageschool.api.services.GenerateDataService;
import org.apache.wicket.spring.injection.annot.SpringBean;


public class HomePage extends BasePage {
    @SpringBean
    private GenerateDataService generator;

	private static boolean generated = false;
    
    public HomePage() {
        if(!generated){
			generator.generateData(4, 20, 10, 4);
			generated = true;
		}
    }
}
