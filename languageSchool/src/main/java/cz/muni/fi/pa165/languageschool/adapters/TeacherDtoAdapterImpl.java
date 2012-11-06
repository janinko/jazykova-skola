package cz.muni.fi.pa165.languageschool.adapters;

import cz.muni.fi.pa165.languageschool.dto.CourseDto;
import cz.muni.fi.pa165.languageschool.entities.Lesson;
import cz.muni.fi.pa165.languageschool.dto.LessonDto;
import cz.muni.fi.pa165.languageschool.dto.TeacherDto;
import cz.muni.fi.pa165.languageschool.entities.Course;
import cz.muni.fi.pa165.languageschool.entities.Teacher;
import cz.muni.fi.pa165.languageschool.services.TeacherService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author xchrastk
 */
public class TeacherDtoAdapterImpl implements TeacherDtoAdapter {
	
	TeacherService teacherService;
	
	@Autowired
    public void setTeacherService(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    public void createTeacher(TeacherDto teacher) {
        teacherService.createTeacher(teacher.adaptToEntity());
    }

    public TeacherDto readTeacher(String email) {
        return new TeacherDto(teacherService.readTeacher(email));
    }

    public void updateTeacher(TeacherDto teacher) {
        teacherService.updateTeacher(teacher.adaptToEntity());
    }

    public void deleteTeacher(TeacherDto teacher) {
        teacherService.deleteTeacher(teacher.adaptToEntity());
    }

    public Set<LessonDto> getTeachersLessons(TeacherDto teacher) {
        Set<Lesson> lessons = teacherService.getTeachersLessons(teacher.adaptToEntity());
        Set<LessonDto> lessonsDto = new HashSet<LessonDto>();
        for(Lesson l : lessons) {
            lessonsDto.add(new LessonDto(l));
        }
        return lessonsDto;
    }

    public Set<CourseDto> getTeachersCourses(TeacherDto teacher) {
        Set <Course> courses = teacherService.getTeachersCourses(teacher.adaptToEntity());
        Set <CourseDto> coursesDto = new HashSet<CourseDto>();
        for(Course c: courses) {
            coursesDto.add(new CourseDto(c));
        }
        return coursesDto;
    }

    public Set<TeacherDto> getAllTeachers() {
        Set<Teacher> teachers = teacherService.getAllTeachers();
        Set<TeacherDto> teachersDto = new HashSet<TeacherDto>();
        for(Teacher t: teachers) {
            teachersDto.add(new TeacherDto(t));
        }
        return teachersDto;
    }
}
