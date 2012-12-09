package cz.muni.fi.pa165.languageschoolweb.rest;

import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import cz.muni.fi.pa165.languageschool.api.adapters.StudentDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.adapters.TeacherDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.dto.StudentDto;
import cz.muni.fi.pa165.languageschool.api.dto.TeacherDto;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * Processes the information got from the rest messages.
 * It can search the database for students and teachers and
 * manipulate them.
 * 
 * @author zabka
 */
public class MessageProcessor {
    @SpringBean
    TeacherDtoAdapter teachers;
    
    @SpringBean
    StudentDtoAdapter students;
    
    /*
     * Creates new object.
     * @param object kind of object - either "teacher" or "student"
     * @param json json representation of the object to create
     * @throws JsonSyntaxException if the json string cannot be translated to the object
     */
    public void post(String object, String json) throws JsonSyntaxException {
        if (object.equals("teacher")) {
            JsonTranslator translator = new JsonTranslator();
            TeacherDto teacher = translator.jsonToObject(json, TeacherDto.class);
            teachers.createTeacher(teacher);
        }
        
        if (object.equals("student")) {
            JsonTranslator translator = new JsonTranslator();
            StudentDto student = translator.jsonToObject(json, StudentDto.class);
            students.createStudent(student);
        }
    }
    
    /*
     * Updates the object.
     * @param object kind of object - either "teacher" or "student"
     * @param json json representation of the updated object
     * @throws JsonSyntaxException if the json string cannot be translated to the object
     */
    public void put(String object, String json) throws JsonSyntaxException {
        if (object.equals("teacher")) {
            JsonTranslator translator = new JsonTranslator();
            TeacherDto teacher = translator.jsonToObject(json, TeacherDto.class);
            teachers.updateTeacher(teacher);
        }
        
        if (object.equals("student")) {
            JsonTranslator translator = new JsonTranslator();
            StudentDto student = translator.jsonToObject(json, StudentDto.class);
            students.update(student);
        }
    }
    
    /*
     * Retrieves all objects of the kind specified with object param.
     * @param object kind of object - either "teacher" or "student"
     * @return json representation of collection of all object of the specified kind
     */
    public String get(String object) {
        if (object.equals("teacher")) {
            JsonTranslator translator = new JsonTranslator();
            List<TeacherDto> allTeachers = new ArrayList<TeacherDto>();
            allTeachers.addAll(teachers.getAllTeachers());
            Type setType = new TypeToken<Set<TeacherDto>>() {}.getType();
            String all = translator.objectToJson(allTeachers);
            return all;
        } 
        
        if (object.equals("student")) {
            JsonTranslator translator = new JsonTranslator();
            List<StudentDto> allStudents = new ArrayList<StudentDto>();
            //allStudents.addAll(students.());
            Type setType = new TypeToken<Set<StudentDto>>() {}.getType();
            String all = translator.objectToJson(allStudents);
            return all;
        }        
        return "";
    }
    
    /*
     * Retrieves a json representation of the object with the id
     * @param object kind of object - either "teacher" or "student"
     * @param id id of the object    
     * @return json representation of the object with given id or null if it does not exist
     * @throws IllegalArgumentException if the the id is not a valid Long
     */
    public String get(String object, String id) throws IllegalArgumentException {
        Long idValue = Long.valueOf(id);
        if (id == null) {   //TODO Long.valueOf
            throw new IllegalArgumentException("Id of "+object+" is not a number.");
        }
        
        if (object.equals("teacher")) {
            JsonTranslator translator = new JsonTranslator();
            TeacherDto t = teachers.readTeacher(idValue);
            String teacher = translator.objectToJson(t);
            return teacher;
        }
        
        if (object.equals("student")) {
            JsonTranslator translator = new JsonTranslator();
            StudentDto s = students.read(idValue);
            String student = translator.objectToJson(s);
            return student;
        }        
        return null;
    }
    
    /*
     * Deletes the object with the id.
     * @param object kind of object - either "teacher" or "student"
     * @param id id of the object 
     * @throws IllegalArgumentException if the the id is not a valid Long
     */
    public void delete(String object, String id) throws IllegalArgumentException {
        Long idValue = Long.valueOf(id);
        if (id == null) {   //TODO Long.valueOf
            throw new IllegalArgumentException("Id of "+object+" is not a number.");
        }
        
        if (object.equals("teacher")) {
            JsonTranslator translator = new JsonTranslator();
            teachers.deleteTeacher(teachers.readTeacher(idValue));
        }
        
        if (object.equals("student")) {
            JsonTranslator translator = new JsonTranslator();
            students.removeStudent(students.read(idValue));
        }
    }
}
