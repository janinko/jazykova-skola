package cz.muni.fi.pa165.languageschool.cli.iface;

/**
 * @author Honza Brázdil <jbrazdil@redhat.com>
 */
public class Application {
	Teachers teachers = new Teachers();
	Courses courses = new Courses();

	public void select(){
		while(true){
			Helper.clear();
			printWelcome();
			printHelp();
			Object resp = Helper.getResponse("Volba", "q", "t","T", "c", "C");
			if(resp == null) return;
			if("q".equals(resp)){
				return;
			}else if("t".equals(resp) || "T".equals(resp)){
				teachers.select();
			}else if("c".equals(resp) || "C".equals(resp)){
				courses.select();
			}
		}
	}
	
	
	private void printHelp() {
		System.out.println("q - exit");
		System.out.println("t - Práce s učitely");
		System.out.println("c - Práce s kurzy");
	}

	private void printWelcome() {
		System.out.println("PA165 Languageschool REST api client");
	}

}
