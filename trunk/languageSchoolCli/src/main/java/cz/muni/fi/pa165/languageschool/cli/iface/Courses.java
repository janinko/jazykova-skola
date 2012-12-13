package cz.muni.fi.pa165.languageschool.cli.iface;

import cz.muni.fi.pa165.languageschool.api.dto.CourseDto;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Honza Brázdil <jbrazdil@redhat.com>
 */
public class Courses {

	Map<Long,CourseDto> courses = new HashMap<Long,CourseDto>();

	void update(){
		CourseDto[] updatetCourses = Helper.retrieve(CourseDto[].class,"/course");
		if(updatetCourses != null){
			courses.clear();
			for(CourseDto course : updatetCourses){
				courses.put(course.getId(), course);
			}
		}
	}

	public void select(){
		update();
		while(true){
			Helper.clear();
			printCourses();
			printHelp();
			Object resp = Helper.getResponse("Volba","q","z",Integer.class);
			if(resp == null) return;
			if(resp instanceof String){
				if("q".equals(resp)){
					Runtime.getRuntime().exit(0);
				}else{
					return;
				}
			}else{
				Integer id = (Integer) resp;
				//Teacher.get(id).select();
			}
		}
	}

	private void printCourses(){
		for(CourseDto course : courses.values()){
			printCourses(course);
		}
	}

	private void printCourses(CourseDto course){
		String name = course.getName();
		if(name.length() > 30) name = name.substring(0, 28) + "…";
		// "#   1 Novotný Pavel             novotnyp@example.com      AJ  2
		System.out.printf("#%4d %-30s %2s %2d\n",
				          course.getId(),
						  name,
						  course.getLanguage(),
						  course.getLevel());
	}

	private void printHelp() {
		System.out.println("q - exit");
		System.out.println("z - zpet");
		System.out.println("CISLO - prejde na ucitele s id CISLO");
	}
}
