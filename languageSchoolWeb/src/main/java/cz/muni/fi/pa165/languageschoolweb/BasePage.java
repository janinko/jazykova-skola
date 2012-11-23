/*
 * WicketExamplePage.java
 *
 * Created on 21. listopad 2012, 20:21
 */
 
package cz.muni.fi.pa165.languageschoolweb;           

import org.apache.wicket.markup.html.WebPage;

/** 
 *
 * @author kelnar
 * @version 
 */

public abstract class BasePage extends WebPage {

    public BasePage() { 
        super(); 
        add(new HeaderPanel("headerpanel", "Welcome To Wicket")); 
        add(new FooterPanel("footerpanel", "Powered by Wicket1"));
    } 

}
