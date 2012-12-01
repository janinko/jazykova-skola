package cz.muni.fi.pa165.languageschool.adapters;

import cz.muni.fi.pa165.languageschool.api.adapters.LessonDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.dto.CourseDto;
import cz.muni.fi.pa165.languageschool.api.dto.LessonDto;
import cz.muni.fi.pa165.languageschool.api.dto.StudentDto;
import cz.muni.fi.pa165.languageschool.api.entities.Lesson;
import cz.muni.fi.pa165.languageschool.api.entities.Student;
import cz.muni.fi.pa165.languageschool.api.entities.Teacher;
import cz.muni.fi.pa165.languageschool.api.services.LessonService;
import cz.muni.fi.pa165.languageschool.api.services.TeacherService;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xbrazdi1, xchrastk, xkelnar
 */
@Service
public class LessonDtoAdapterImpl implements LessonDtoAdapter {
    @Autowired
    private LessonService lessonService;
    @Autowired
    private TeacherService teacherService;
	@Autowired
	private CourseDtoAdapterImpl courseDtoAdapter;
	@Autowired
	private StudentDtoAdapterImpl studentDtoAdapter;

    public LessonDto read(Long lessonId) {
        return (e2dto(lessonService.read(lessonId)));
    }
	
	@Override
    public void removeLesson(LessonDto lesson){
            lessonService.removeLesson(dto2e(lesson));
    }
    
	@Override
    public void removeStudent(LessonDto lesson,StudentDto student){
            lessonService.removeStudent(dto2e(lesson),studentDtoAdapter.dto2e(student));
    }
    
	@Override
    public void addStudent(LessonDto lesson,StudentDto student){
            lessonService.addStudent(dto2e(lesson), studentDtoAdapter.dto2e(student));
    }  
    
	@Override
    public Set<StudentDto> findStudentsByLesson(LessonDto lesson){
            Set<StudentDto> studentTOs = new HashSet<StudentDto>();
            for(Student s : lessonService.findStudentsByLesson(dto2e(lesson))){
                    studentTOs.add(studentDtoAdapter.e2dto(s));
            }
            return studentTOs; 
    }
		
	@Override
    public Set<LessonDto> getAllLessons(){
            Set<LessonDto> lessonTOs = new HashSet<LessonDto>();
            for(Lesson l : lessonService.getAllLessons()){
                    lessonTOs.add(e2dto(l));
            }
            return lessonTOs;
    }

	@Override
	public Set<LessonDto> getUpcomingLessons(Date date) {
            Set<LessonDto> lessonTOs = new HashSet<LessonDto>();
            for(Lesson l : lessonService.getUpcomingLessons(date)){
                    lessonTOs.add(e2dto(l));
            }
            return lessonTOs;
	}

	@Override
	public Set<LessonDto> getLessonsByCourse(CourseDto course) {
            Set<LessonDto> lessonTOs = new HashSet<LessonDto>();
            for(Lesson l : lessonService.getLessonsByCourse(courseDtoAdapter.dto2e(course))){
                    lessonTOs.add(e2dto(l));
            }
            return lessonTOs;
	}


	LessonDto e2dto(Lesson entity) {
		if(entity == null) return null;
		LessonDto dto = new LessonDto();
		dto.setId(entity.getId());

		Calendar date = new GregorianCalendar(); date.setTimeInMillis(entity.getDate().getTime() + entity.getTime().getTime());
		dto.setDate(date);

		dto.setCourse(courseDtoAdapter.e2dto(entity.getCourse()));

		dto.setTeacherName(entity.getTeacher().getFirstName() + " " + entity.getTeacher().getLastName());
		dto.setTeacherEmail(entity.getTeacher().getEmail());
		return dto;
	}

	Lesson dto2e(LessonDto dto) {
		if(dto == null) return null;
		Lesson entity = new Lesson();
		entity.setId(dto.getId());

		if(dto.getCourse() != null){
			entity.setCourse(courseDtoAdapter.dto2e(dto.getCourse()));
		}
		if(dto.getDate() != null){
			Calendar time = (Calendar) dto.getDate().clone();

			time.set(1970,Calendar.JANUARY,1);
			entity.setTime(new Time(time.getTimeInMillis()));

			entity.setDate(new Date(dto.getDate().getTimeInMillis() - time.getTimeInMillis()));
		}
		Teacher teacher = teacherService.readTeacher(dto.getTeacherEmail());
		if(teacher == null) throw new IllegalArgumentException("Teacher with email " + dto.getTeacherEmail() + " doesn't exist");
		entity.setTeacher(teacher);

		// studends are not setted, when lesson is created, students are epmty,
		// students can themselve enroll and cancel
		// lesson isn't updatable

		return entity;
	}
}
