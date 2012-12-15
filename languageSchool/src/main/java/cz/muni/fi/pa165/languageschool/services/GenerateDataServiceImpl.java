package cz.muni.fi.pa165.languageschool.services;

import cz.muni.fi.pa165.languageschool.api.Language;
import cz.muni.fi.pa165.languageschool.api.entities.Course;
import cz.muni.fi.pa165.languageschool.api.entities.Lesson;
import cz.muni.fi.pa165.languageschool.api.entities.Student;
import cz.muni.fi.pa165.languageschool.api.entities.Teacher;
import cz.muni.fi.pa165.languageschool.api.services.CourseService;
import cz.muni.fi.pa165.languageschool.api.services.LessonService;
import cz.muni.fi.pa165.languageschool.api.services.StudentService;
import cz.muni.fi.pa165.languageschool.api.services.TeacherService;
import cz.muni.fi.pa165.languageschool.api.utilservices.GenerateDataService;
import java.sql.Date;
import java.sql.Time;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xbrazdi1, xchrastk, xkelnar
 */
@Service
public class GenerateDataServiceImpl implements GenerateDataService {
	String[] namesM = {"Yvan","Chaim","Lešek","Abadon","Strachota","Adrien",
		"Toni","Richard","Zoran","Ctirad","Želmír","Martin","Ingemar",
		"Roderik","Kvido","Vavřinec","Jonáš","Amos","Armando","Alvin",
		"Nidgar","Marián","Marlen","Kelly","Jindřich","Matěj"};

	String[] namesF = {"Elza","Aloisie","Ivana","Johana","Lucie","Mlada",
		"Marina","Zoe","Zlatomíra","Deana","Amanda ","Rozálie","Nataša ",
		"Kateřina","Miriam","Grace","Nelu","Vilemína","Elisabeta","Žaneta",
		"Ulrika","Jiřina","Žitomíra","Jarmila"};

	String[] surnamesM = {"Liška","Malý","Müller","Kolář","Polák","Zeman",
		"Černý","Kučera","Němec","Urban","Marek","Mach","Pospíšil","Beneš",
		"Vávra","Šťastný","Doležal","Navrátil","Čermák","Havlíček","Horák",
		"Novák","Bartoš","Moravec","Janda","Kříž","Štěpánek"};

	String[] surnamesF = {"Lišková","Malá","Müllerová","Kolářová","Poláková",
		"Zemanová","Černá","Kučerová","Němcová","Urbanová","Marková","Machová",
		"Pospíšilová","Benešová","Vávrová","Šťastná","Doležalová","Navrátilová",
		"Čermáková","Havlíčková","Horáková","Nováková","Bartošová","Moravcová",
		"Jandová","Křížová","Štěpánková"};

	String[] courseNamePrefix = {"Fajnový ", "Zábavný ", "Expresní ",
		"Lingvistický ", "Nepříjemný "};
	String   courseNameCourseM = "kurz ";
	String   courseNameCourseF = "Kurz ";
	String[] courseNameLevel = {" pro začátečníky", " pro mírně pokročilé",
		" pro středně pokročilé", " pro pokročilé", " pro experty"};
	String[] courseNameSufix = {" - konverzace", " - gramatika", " - právnický",
		"", ""};
	String[] courseNameNumber = {" I", " II", " III", " IV", "V"};

	Language[] langs = Language.values();

	Random generator = new Random();

	HashSet<String> emails = new HashSet<String>();
	HashSet<String> courseNames = new HashSet<String>();
	ArrayList<Teacher> teachers = new ArrayList<Teacher>();
	ArrayList<Course> courses = new ArrayList<Course>();
	ArrayList<Lesson> lessons = new ArrayList<Lesson>();
	ArrayList<Student> students = new ArrayList<Student>();

	@Autowired private TeacherService teacherService;
	@Autowired private StudentService studentService;
	@Autowired private CourseService courseService;
	@Autowired private LessonService lessonService;

	@Override
	public void generateData(int courses, int lessons, int students, int techers) {		
		genTeachers(techers);
		genCourses(courses);
		genLessons(lessons);
		genStudents(students);
	}

	private void genTeachers(int teachers){
		for(int i=0; i < teachers; i++){
			Teacher t = genTeacher();
			teacherService.createTeacher(t);
			this.teachers.add(t);
		}
	}

	private void genCourses(int courses){
		for(int i=0; i < courses; i++){
			Course c = genCourse();
			courseService.createCourse(c);
			this.courses.add(c);
		}
	}

	private void genLessons(int lessons) {
		for(int i=0; i < lessons; i++){
			Lesson l = genLesson();
			courseService.addLessonToCourse(courses.get(generator.nextInt(courses.size())), l);
			this.lessons.add(l);
		}

	}

	private void genStudents(int students) {
		for(int i=0; i < students; i++){
			Student s = genStudent();
			studentService.createStudent(s);
			enrollStudend(s);
			this.students.add(s);
		}
	}

	private Teacher genTeacher(){
		Teacher teacher = new Teacher();
		String[] name = genName();
		teacher.setFirstName(name[0]);
		teacher.setLastName(name[1]);
		
		HashSet<Language> languages = new HashSet<Language>();
		do{
			languages.add(langs[generator.nextInt(langs.length)]);
		}while(generator.nextInt(3)==0);
		if(generator.nextInt(4) == 0){
			Language l = (new ArrayList<Language>(languages)).get(generator.nextInt(languages.size()));
			teacher.setNativeLanguage(l);
		}
		teacher.setLanguages(languages);

		teacher.setEmail(genEmail(teacher.getFirstName(), teacher.getLastName()));

		return teacher;
	}

	private String[] genName(){
		String[] ret = new String[2];
		boolean female = generator.nextBoolean();
		if(female){
			ret[0]=namesF[generator.nextInt(namesF.length)];
			ret[1]=surnamesF[generator.nextInt(surnamesF.length)];
		}else{
			ret[0]=namesM[generator.nextInt(namesM.length)];
			ret[1]=surnamesM[generator.nextInt(surnamesM.length)];
		}
		return ret;
	}



	private String genEmail(String jmeno, String prijmeni){
		String jmn = Normalizer.normalize(jmeno, Normalizer.Form.NFD)
				.replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
		String pjmn = Normalizer.normalize(prijmeni, Normalizer.Form.NFD)
				.replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();

		int end = 7;
		if(pjmn.length() < 7){
			end = pjmn.length();
		}
		String mejl = jmn.charAt(0) + pjmn.substring(0, end);
		String email = mejl;
		int count=1;

		while(emails.contains(email)){
			email = mejl + count;
			count++;
		}
		emails.add(email);
		return email + "@example.com";
	}

	private Course genCourse() {
		Course course = new Course();
		course.setLanguage(langs[generator.nextInt(langs.length)]);
		course.setLevel(generator.nextInt(5)+1);

		Boolean prefix = generator.nextBoolean();
		String nejm = (prefix? courseNamePrefix[generator.nextInt(courseNamePrefix.length)]:"") +
				      (prefix? courseNameCourseM : courseNameCourseF) +
				      course.getLanguage().toString() +
				      courseNameLevel[course.getLevel() - 1] +
				      courseNameSufix[generator.nextInt(courseNameSufix.length)];

		String name = nejm + courseNameNumber[0];
		int count = 1;
		while(courseNames.contains(name)){
			if(count < courseNameNumber.length){
				name = nejm + courseNameNumber[count];
			}else{
				name = nejm + (count - courseNameNumber.length);
			}
		}
		courseNames.add(name);
		course.setName(name);
		return course;
	}

	private Lesson genLesson() {
		Lesson lesson = new Lesson();
		lesson.setDate(new Date((new java.util.Date()).getTime() + generator.nextInt(100)*24*60*60*1000  )); // MAGIC!!!
		lesson.setTime(new Time((long) generator.nextInt(43200000) + 28800000L));
		lesson.setTeacher(teachers.get(generator.nextInt(teachers.size())));

		return lesson;
	}

	private Student genStudent() {
		Student student = new Student();
		student.setAge(generator.nextInt(64) + 7);
		String[] name = genName();
		student.setFirstName(name[0]);
		student.setLastName(name[1]);
		student.setEmail(genEmail(student.getFirstName(), student.getLastName()));
        student.setPassword("pass");
		return student;
	}

	private void enrollStudend(Student s) {
		int j = generator.nextInt(7) + 1;

		for(int i=0; i<j; i++){
			studentService.lessonEnroll(s, lessons.get(generator.nextInt(lessons.size())));
		}
	}
}
