package cz.muni.fi.pa165.languageschool.cli.iface;

import cz.muni.fi.pa165.languageschool.api.dto.TeacherDto;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Honza Brázdil <jbrazdil@redhat.com>
 */
public class Teachers{
	Map<Long,TeacherDto> teachers = new HashMap<Long, TeacherDto>();
	Teacher teacher;
	Application app;

	public Teachers(Application app){
		this.app = app;
		teacher = new Teacher(this);
	}

	void update(){
		TeacherDto[] updatetTeachers = Helper.read(TeacherDto[].class,"teacher");
		if(updatetTeachers != null){
			teachers.clear();
			for(TeacherDto teacher : updatetTeachers){
				teachers.put(teacher.getId(), teacher);
			}
		}
	}

	public void select(){
		update();
		while(app.running){
			Helper.clear();
			printTeachers();
			printHelp();
			Object resp = Helper.getResponse("Volba","q","z","n","u",Integer.class);
			if(resp == null) return;
			if(resp instanceof String){
				if("q".equals(resp)){
					app.running = false;
				}else if("z".equals(resp)){
					return;
				}else if("n".equals(resp)){
					teacher.newTeacher();
				}else if("u".equals(resp)){
					update();
				}
			}else{
				Integer id = (Integer) resp;
				if(teachers.containsKey(id.longValue())){
					teacher.select(id);
				}
			}
		}
	}

	private void printTeachers(){
		for(TeacherDto teacher : teachers.values()){
			printTeacher(teacher);
		}
	}

	private void printTeacher(TeacherDto teacher){
		String lang = "--";
		if(teacher.getNativeLanguage() != null) lang = teacher.getNativeLanguage().toString();
		// "#   1 Novotný Pavel             novotnyp@example.com      AJ  2
		System.out.printf("#%4d %-25s %-25s %2s %2d\n",
				          teacher.getId(),
						  teacher.getLastName()+" "+teacher.getFirstName(),
						  teacher.getEmail(),
						  lang,
						  teacher.getLanguages().size());
	}

	private void printHelp() {
		System.out.println("_____");
		System.out.println("q - exit");
		System.out.println("z - zpet");
		System.out.println("n - nový učitel");
		System.out.println("u - načtení aktuálních dat");
		System.out.println("CISLO - přejde na učitele s id CISLO");
	}
}
