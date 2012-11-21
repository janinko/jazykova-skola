package cz.muni.fi.pa165.languageschool.adapters;

import cz.muni.fi.pa165.languageschool.api.adapters.LessonDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.dto.LessonDto;
import cz.muni.fi.pa165.languageschool.api.dto.StudentDto;
import cz.muni.fi.pa165.languageschool.api.entities.Lesson;
import cz.muni.fi.pa165.languageschool.api.entities.Student;
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
 *
 * @author jbrazdil
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
	
    public void removeLesson(LessonDto lesson){
            lessonService.removeLesson(dto2e(lesson));
    }
    
    public void removeStudent(LessonDto lesson,StudentDto student){
            lessonService.removeStudent(dto2e(lesson),studentDtoAdapter.dto2e(student));
    }
    
    public void addStudent(LessonDto lesson,StudentDto student){
            lessonService.addStudent(dto2e(lesson), studentDtoAdapter.dto2e(student));
    }  
    
    public Set<StudentDto> findStudentsByLesson(LessonDto lesson){
            Set<StudentDto> studentTOs = new HashSet<StudentDto>();
            for(Student s : lessonService.findStudentsByLesson(dto2e(lesson))){
                    studentTOs.add(studentDtoAdapter.e2dto(s));
            }
            return studentTOs; 
    }
		
    public Set<LessonDto> getAllLessons(){
            Set<LessonDto> lessonTOs = new HashSet<LessonDto>();
            for(Lesson l : lessonService.getAllLessons()){
                    lessonTOs.add(e2dto(l));
            }
            return lessonTOs;
    }


	LessonDto e2dto(Lesson entity) {
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
		entity.setTeacher(teacherService.readTeacher(dto.getTeacherEmail()));


		// studends are not setted, when lesson is created, students are epmty,
		// students can themselve enroll and cancel
		// lesson isn't updatable

		return entity;
	}	
}
