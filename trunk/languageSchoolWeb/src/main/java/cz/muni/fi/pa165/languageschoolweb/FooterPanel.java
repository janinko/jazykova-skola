/*
 * FooterPanel.java
 *
 * Created on 21. listopad 2012, 20:21
 */
 
package cz.muni.fi.pa165.languageschoolweb;           

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

/** 
 *
 * @author kelnar
 * @version 
 */

public final class FooterPanel extends Panel {
	private static final long serialVersionUID = 1L;

    public FooterPanel(String id, String text) {
        super(id);
        add(new Label("footerpanel_text", text));
    }

}
