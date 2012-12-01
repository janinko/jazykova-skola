package cz.muni.fi.pa165.languageschoolweb.model;

import org.apache.wicket.util.io.IClusterable;

/**
 * @author xbrazdi1, xchrastk, xkelnar
 */
public class LanguagesModel implements IClusterable {
	private static final long serialVersionUID = 1L;

	String languages;

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }	
}
