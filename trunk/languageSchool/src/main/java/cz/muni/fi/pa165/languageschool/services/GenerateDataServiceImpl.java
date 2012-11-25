package cz.muni.fi.pa165.languageschool.services;

import cz.muni.fi.pa165.languageschool.api.entities.Course;
import cz.muni.fi.pa165.languageschool.api.entities.Lesson;
import cz.muni.fi.pa165.languageschool.api.entities.Student;
import cz.muni.fi.pa165.languageschool.api.entities.Teacher;
import cz.muni.fi.pa165.languageschool.api.entities.Teacher.Language;
import cz.muni.fi.pa165.languageschool.api.services.CourseService;
import cz.muni.fi.pa165.languageschool.api.services.GenerateDataService;
import cz.muni.fi.pa165.languageschool.api.services.LessonService;
import cz.muni.fi.pa165.languageschool.api.services.StudentService;
import cz.muni.fi.pa165.languageschool.api.services.TeacherService;
import java.sql.Date;
import java.sql.Time;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jbrazdil
 */
@Service
public class GenerateDataServiceImpl implements GenerateDataService {
	String[] names = {"Elza","Yvan","Chaim","Aloisie","Lešek","Abadon",
		"Strachota","Adrien ","Toni","Ivana","Richard","Zoran","Johana",
		"Lucie","Mlada","Ctirad","Želmír","Martin","Marina","Ingemar","Zoe",
		"Zlatomíra","Roderik","Kvido","Deana","Amanda ","Rozálie","Vavřinec",
		"Nataša ","Kateřina","Miriam","Grace","Nelu","Vilemína","Jonáš",
		"Elisabeta","Amos ","Armando ","Alvin ","Žaneta","Nidgar ",
		"Ulrika","Jiřina","Marián","Žitomíra","Marlen","Kelly",
		"Jindřich","Matěj","Jarmila"};
	
	String[] surnames = {"Liška","Malý","Müller","Kolář","Polák","Zeman",
		"Černý","Kučera","Němec","Urban","Marek","Mach","Pospíšil","Beneš",
		"Vávra","Šťastný","Doležal","Navrátil","Čermák","Havlíček","Horák",
		"Novák","Bartoš","Moravec","Janda","Kříž","Štěpánek","Valenta",
		"Sedláček","Kadlec","Mareš","Holub","Bláha","Veselý","Král",
		"Procházka","Říha","Mašek","Krejčí","Ševčík","Čech","Pokorný",
		"Kopecký","Hruška","Nguyen","Vaněk","Bureš","Hrubý","Dostál",
		"Matoušek"};

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

	private TeacherService teacherService;
	private StudentService studentService;
	private CourseService courseService;
	private LessonService lessonService;

	@Autowired
	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	@Autowired
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	@Autowired
	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	@Autowired
	public void setLessonService(LessonService lessonService) {
		this.lessonService = lessonService;
	}

	public void generateData(int courses, int lessons, int students, int techers) {
		//getFromDB();
		
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
		teacher.setFirstName(names[generator.nextInt(names.length)]);
		teacher.setLastName(surnames[generator.nextInt(surnames.length)]);
		
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
		lesson.setDate(new Date((generator.nextInt(100)+ 15700)*24*60*60*1000  )); // MAGIC!!!
		lesson.setTime(new Time((long) generator.nextInt(43200000) + 28800000L));
		lesson.setTeacher(teachers.get(generator.nextInt(teachers.size())));

		return lesson;
	}

	private Student genStudent() {
		Student student = new Student();
		student.setAge(generator.nextInt(7) + 64);
		student.setFirstName(names[generator.nextInt(names.length)]);
		student.setLastName(surnames[generator.nextInt(surnames.length)]);
		student.setEmail(genEmail(student.getFirstName(), student.getLastName()));
		return student;
	}

	private void enrollStudend(Student s) {
		int j = generator.nextInt(7) + 1;

		for(int i=0; i<j; i++){
			studentService.lessonEnroll(s, lessons.get(generator.nextInt(lessons.size())));
		}
	}

	/*private void getFromDB() {
		Set<Teacher> ts = teacherService.getAllTeachers();
		Set<Course> cs = courseService.getAllCourses();
		Set<Lesson> ls = lessonService.getAllLessons();
		//Set<Student> ss = studentService.getAllStudents();

		ts.removeAll(teachers);
		cs.removeAll(courses);
		ls.removeAll(lessons);
	}*/
}
