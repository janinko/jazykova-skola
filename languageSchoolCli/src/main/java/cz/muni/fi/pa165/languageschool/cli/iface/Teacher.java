package cz.muni.fi.pa165.languageschool.cli.iface;

import cz.muni.fi.pa165.languageschool.api.Language;
import cz.muni.fi.pa165.languageschool.api.dto.TeacherDto;
import java.util.Arrays;
import java.util.HashSet;

/**
 *
 * @author Honza Brázdil <jbrazdil@redhat.com>
 */
class Teacher{
	TeacherDto teacher;
	Teachers teachers;

	
	public Teacher(Teachers teachers){
		this.teachers = teachers;
	}

	public void select(long id) {
		teacher = teachers.teachers.get(id);
		while(true){
			Helper.clear();
			printTeacher();
			printHelp();
			Object resp = Helper.getResponse("Volba","q","z","u","j","p","e","r","J","E");
			if(resp == null) return;

			if ("q".equals(resp)) {
				Runtime.getRuntime().exit(0);
			} else if("z".equals(resp)) {
				return;
			} else if("u".equals(resp)) {
				update();
			} else if("j".equals(resp)) {
				editName();
				edited();
			} else if("p".equals(resp)) {
				editSurname();
				edited();
			} else if("e".equals(resp)) {
				editEmail();
				edited();
			} else if("r".equals(resp)) {
				editNative();
				edited();
			} else if("J".equals(resp)) {
				editLanguages();
				edited();
			} else if("E".equals(resp)) {
				editName();
				editSurname();
				editEmail();
				editNative();
				editLanguages();
				edited();
			}
		}
	}

	private void update(){
		TeacherDto updatetTeacher = Helper.retrieve(TeacherDto.class,"/teacher/"+teacher.getId());
		if(updatetTeacher != null){
			teacher = updatetTeacher;
			teachers.teachers.put(teacher.getId(), teacher);
		}
	}


	public void printTeacher(){
		System.out.println("ID: " + teacher.getId());
		System.out.println("Jméno: " + teacher.getFirstName());
		System.out.println("Příjmení: " + teacher.getLastName());
		System.out.println("Email: " + teacher.getEmail());
		String nat = "--";
		if(teacher.getNativeLanguage() != null) nat = teacher.getNativeLanguage().toString();
		System.out.println("Rodný jazyk: " + nat);
		printLanguages();
	}

	private void printLanguages(){
		if(teacher.getLanguages() == null) return;
		System.out.print("Jazyky: ");
		boolean first=true;
		for(Language l : teacher.getLanguages()){
			if(!first) System.out.print(", ");
			System.out.print(l);
			first = false;
		}
		System.out.println();

	}

	private void printHelp() {
		System.out.println("q - exit");
		System.out.println("z - zpet");
		System.out.println("u - nacteni aktualnich dat");
		System.out.println("E - editace všech dat");
		System.out.println("j - editace jména");
		System.out.println("p - editace příjmení");
		System.out.println("e - editace emailu");
		System.out.println("r - editace rodného jazyka");
		System.out.println("J - editace jazyků");
	}

	private void editName() {
		Object resp = Helper.getResponse("Nové jméno",String.class);
		if(resp == null || "".equals(resp)) return;
		teacher.setFirstName((String) resp);
	}

	private void editSurname() {
		Object resp = Helper.getResponse("Nové příjmení",String.class);
		if(resp == null || "".equals(resp)) return;
		teacher.setLastName((String) resp);
	}

	private void editEmail() {
		Object resp = Helper.getResponse("Nový email",String.class);
		if(resp == null || "".equals(resp)) return;
		teacher.setEmail((String) resp);
	}

	private void editNative() {
		Object resp = Helper.getResponse("Nový rodný jazyk",Language.class);
		if(resp == null) return;
		teacher.setNativeLanguage((Language) resp);
	}

	private void editLanguages() {
		printLanguages();
		Object resp = Helper.getResponse("Zadejte nové jazyky",Language[].class);
		if(resp == null||((Language[]) resp).length == 0) return;
		teacher.setLanguages(new HashSet<Language>(Arrays.asList((Language[])resp)));
	}

	private void edited() {
		// TODO
	}

	void newTeacher() {
		teacher = new TeacherDto();
		editName();
		editSurname();
		editEmail();
		editNative();
		editLanguages();
		edited();
	}
}
