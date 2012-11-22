/*
 * HomePage.java
 *
 * Created on 21. listopad 2012, 20:21
 */

package cz.muni.fi.pa165.languageschoolweb;           

import cz.muni.fi.pa165.languageschool.api.dto.CourseDto;
import cz.muni.fi.pa165.languageschool.api.dto.LessonDto;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.repeater.RepeatingView;

public class HomePage extends BasePage {

    public HomePage() {
        CourseDto course1 = new CourseDto();
        course1.setName("Fránina");
        LessonDto lesson1 = new LessonDto();
        lesson1.setCourse(course1);
        lesson1.setTeacherName("Učák učitel");
        Calendar date = (Calendar.getInstance());
        date.set(2012,12,12);
        lesson1.setDate(date);        
        List<LessonDto> list = new ArrayList<LessonDto>();
        list.add(lesson1);
        CourseDto course2 = new CourseDto();
        course2.setName("Ájina");
        LessonDto lesson2 = new LessonDto();
        lesson2.setCourse(course2);
        lesson2.setTeacherName("Učák učitel");
        date = (Calendar.getInstance());
        date.set(2022,22,22);
        lesson2.setDate(date);           
        list.add(lesson2);
        
        RepeatingView repeating = new RepeatingView("repeating");
        add(repeating);
        
        for(LessonDto lesson: list) {
            AbstractItem item = new AbstractItem(repeating.newChildId());
            
            DateFormat dateFormat = new SimpleDateFormat("dd.MM");            
            DateFormat timeFormat = new SimpleDateFormat("HH:mm");

            item.add(new Label("date",dateFormat.format(lesson1.getDate().getTime()))) ;
            item.add(new Label("time",timeFormat.format(lesson1.getDate().getTime()))) ;
            item.add(new Label("name",lesson1.getCourse().getName())) ;
            item.add(new Label("teacher", lesson1.getTeacherName())) ;
            
            repeating.add(item);
        }
        
        
    }

}
