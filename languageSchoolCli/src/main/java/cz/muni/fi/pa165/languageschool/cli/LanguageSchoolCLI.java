package cz.muni.fi.pa165.languageschool.cli;

import cz.muni.fi.pa165.languageschool.cli.iface.Application;

/**
 *
 * @author Honza Brázdil <jbrazdil@redhat.com>
 */
public class LanguageSchoolCLI {
	public static void main(String[] args) {
		Application app = new Application();
		app.select();
	}
}
