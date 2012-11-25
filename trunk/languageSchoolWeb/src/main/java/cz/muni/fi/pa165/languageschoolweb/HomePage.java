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
    
    public HomePage() {
        generator.generateData(2, 8, 5, 2);
    }
}
