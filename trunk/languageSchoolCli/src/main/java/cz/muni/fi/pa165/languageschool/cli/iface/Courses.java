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
	Application app;
	Course course;
	
	public Courses(Application app){
		this.app = app;
		course = new Course(this);
	}

	void update(){
		CourseDto[] updatetCourses = Helper.read(CourseDto[].class,"course");
		if(updatetCourses != null){
			courses.clear();
			for(CourseDto course : updatetCourses){
				courses.put(course.getId(), course);
			}
		}
	}

	public void select(){
		update();
		while(app.running){
			Helper.clear();
			printCourses();
			printHelp();
			Object resp = Helper.getResponse("Volba","q","z","n","u",Integer.class);
			if(resp == null) return;
			if(resp instanceof String){
				if("q".equals(resp)){
					app.running = false;
				}else if("z".equals(resp)){
					return;
				}else if("n".equals(resp)){
					course.newCourse();
				}else if("u".equals(resp)){
					update();
				}
			}else{
				Integer id = (Integer) resp;
				if(courses.containsKey(id.longValue())){
					course.select(id);
				}
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
		if(name != null && name.length() > 40) name = name.substring(0, 38) + "…";
		// "#   1 Novotný Pavel             novotnyp@example.com      AJ  2
		System.out.printf("#%4d %-40s %2s %2d\n",
				          course.getId(),
						  name,
						  course.getLanguage(),
						  course.getLevel());
	}

	private void printHelp() {
		System.out.println("_____");
		System.out.println("q - exit");
		System.out.println("z - zpet");
		System.out.println("n - nový kurz");
		System.out.println("u - načtení aktuálních dat");
		System.out.println("CISLO - přejde na kurz s id CISLO");
	}
}
