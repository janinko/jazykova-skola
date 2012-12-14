package cz.muni.fi.pa165.languageschool.cli.iface;

import cz.muni.fi.pa165.languageschool.api.Language;
import cz.muni.fi.pa165.languageschool.api.dto.CourseDto;
import java.util.Arrays;
import java.util.HashSet;

/**
 *
 * @author Honza Brázdil <jbrazdil@redhat.com>
 */
class Course{
	CourseDto course;
	Courses courses;

	
	public Course(Courses courses){
		this.courses = courses;
	}

	public void select(long id) {
		course = courses.courses.get(id);
		while(courses.app.running){
			Helper.clear();
			printCourse();
			printHelp();
			Object resp = Helper.getResponse("Volba","q","z","u","E","D","n","j","o");
			if(resp == null) return;

			if ("q".equals(resp)) {
				courses.app.running = false;
			} else if("z".equals(resp)) {
				return;
			} else if("u".equals(resp)) {
				update();
			} else if("n".equals(resp)) {
				editName();
				edited();
			} else if("j".equals(resp)) {
				editLanguage();
				edited();
			} else if("o".equals(resp)) {
				editLevel();
				edited();
			} else if("E".equals(resp)) {
				editName();
				editLanguage();
				editLevel();
				edited();
			} else if("D".equals(resp)) {
				if(Helper.delete(course.getId(), "course")){
					courses.courses.remove(course.getId());
					System.out.println("Kurz odebrán.");
				}else{
					System.out.println("Chyba při odebírání kurzu.");
				}
				return;
			}
		}
	}

	private void update(){
		CourseDto updatetCourse = Helper.read(CourseDto.class,"course/"+course.getId());
		if(updatetCourse != null){
			course = updatetCourse;
			courses.courses.put(course.getId(), course);
		}
	}


	public void printCourse(){
		System.out.println("ID: " + course.getId());
		System.out.println("Název: " + course.getName());
		System.out.println("Jazyk: " + course.getLanguage());
		System.out.println("Obtížnost: " + course.getLevel());
	}

	private void printHelp() {
		System.out.println("_____");
		System.out.println("q - exit");
		System.out.println("z - zpet");
		System.out.println("u - načtení aktuálních dat");
		System.out.println("E - editace všech dat");
		System.out.println("D - odstranění kurzu");
		System.out.println("n - editace názvu");
		System.out.println("j - editace jazyka");
		System.out.println("o - editace obtížnosti");
	}

	private void editName() {
		Object resp = Helper.getResponse("Nový název","",String.class);
		if(resp == null || "".equals(resp)) return;
		course.setName((String) resp);
	}

	private void editLevel() {
		Object resp = Helper.getResponse("Nová obtížnost","",Integer.class);
		if(resp == null || "".equals(resp)) return;
		course.setLevel((Integer) resp);
	}


	private void editLanguage() {
		Object resp = Helper.getResponse("Nový jazyk","",Language.class);
		if(resp == null|| "".equals(resp)) return;
		course.setLanguage((Language) resp);
	}

	private void edited() {
		if(course.getId() == null){
			CourseDto t = Helper.create(course, "course");
			if(t!= null){
				courses.courses.put(t.getId(),t);
				System.out.println("Kurz vytvořen.");
			}else{
				System.out.println("Chyba při vytváření kurzu.");
			}
		}else{
			if(Helper.update(course, "course")){
				System.out.println("Kurz aktualizován.");
			}else{
				System.out.println("Chyba při aktualizaci kurzu.");
				update();
			}
		}
	}

	void newCourse() {
		course = new CourseDto();
		editName();
		editLanguage();
		editLevel();
		edited();
	}
}
