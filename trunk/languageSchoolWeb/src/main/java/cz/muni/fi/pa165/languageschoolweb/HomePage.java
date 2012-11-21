/*
 * HomePage.java
 *
 * Created on 21. listopad 2012, 20:21
 */

package cz.muni.fi.pa165.languageschoolweb;           

import org.apache.wicket.markup.html.basic.Label;

public class HomePage extends BasePage {

    public HomePage() {
        add(new Label("message", "O nás!"));
    }

}
