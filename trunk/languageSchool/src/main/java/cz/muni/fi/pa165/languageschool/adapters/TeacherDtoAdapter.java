package cz.muni.fi.pa165.languageschool.adapters;

import cz.muni.fi.pa165.languageschool.dto.CourseDto;
import cz.muni.fi.pa165.languageschool.dto.LessonDto;
import cz.muni.fi.pa165.languageschool.dto.TeacherDto;
import java.util.Set;

/**
 *
 * @author xchrastk
 */
public interface TeacherDtoAdapter {

    void createTeacher(TeacherDto teacher);
    
    TeacherDto readTeacher(String email);
    
    void updateTeacher(TeacherDto teacher);    
    
    void deleteTeacher(TeacherDto teacher);
    
    Set<LessonDto> getTeachersLessons(TeacherDto teacher);
    
    Set<CourseDto> getTeachersCourses(TeacherDto teacher);
    
    Set<TeacherDto> getAllTeachers();    
}
