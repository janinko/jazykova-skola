package cz.muni.fi.pa165.languageschoolweb;           

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

/** 
 * @author xbrazdi1, xchrastk, xkelnar
 */
public final class FooterPanel extends Panel {
	private static final long serialVersionUID = 1L;

    public FooterPanel(String id, String text) {
        super(id);
        add(new Label("footerpanel_text", text));
    }

}
