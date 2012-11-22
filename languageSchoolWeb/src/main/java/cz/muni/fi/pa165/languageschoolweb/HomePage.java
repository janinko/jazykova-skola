/*
 * HomePage.java
 *
 * Created on 21. listopad 2012, 20:21
 */
package cz.muni.fi.pa165.languageschoolweb;

import cz.muni.fi.pa165.languageschool.api.adapters.CourseDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.adapters.LessonDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.dto.CourseDto;
import cz.muni.fi.pa165.languageschool.api.dto.LessonDto;
import cz.muni.fi.pa165.languageschool.api.services.GenerateDataService;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;


public class HomePage extends BasePage {
    @SpringBean
    private LessonDtoAdapter lessons;
    @SpringBean
    private CourseDtoAdapter courses;
    @SpringBean
    private GenerateDataService generator;
    
    public HomePage() {
        /*generator.generateData(2, 8, 5, 2);
        Set<LessonDto> set = lessons.getAllLessons();*/
        
        
        
        
        CourseDto course1 = new CourseDto();
        course1.setName("Fránina");
        LessonDto lesson1 = new LessonDto();
        lesson1.setCourse(course1);
        lesson1.setTeacherName("Učák učitel");
        Calendar date = (Calendar.getInstance());
        date.set(2012, 12, 12);
        lesson1.setDate(date);
        List<LessonDto> set = new ArrayList<LessonDto>();
        set.add(lesson1);
        CourseDto course2 = new CourseDto();
        course2.setName("Ájina");
        LessonDto lesson2 = new LessonDto();
        lesson2.setCourse(course2);
        lesson2.setTeacherName("Učák učitel");
        date = (Calendar.getInstance());
        date.set(2022, 22, 22);
        lesson2.setDate(date);
        set.add(lesson2);

        Form form = new Form("form") {
            protected void onSubmit() {
                info("Form.onSubmit executed");
            }
        };

        
        
        RepeatingView repeating = new RepeatingView("repeating");
        add(repeating);

        
        for (LessonDto lesson : set) {
            AbstractItem item = new AbstractItem(repeating.newChildId());

            
            
            DateFormat dateFormat = new SimpleDateFormat("dd.MM");
            DateFormat timeFormat = new SimpleDateFormat("HH:mm");

            item.add(new Label("date", dateFormat.format(lesson.getDate().getTime())));
            item.add(new Label("time", timeFormat.format(lesson.getDate().getTime())));
            item.add(new Label("name", lesson.getCourse().getName()));
            item.add(new Label("teacher", lesson.getTeacherName()));
            item.add(new CheckBox("bool",Model.of(Boolean.TRUE)));

            repeating.add(item);
        }

        form.add(repeating);

        Button button = new Button("sign") {
            public void onSubmit() {
                info("button1.onSubmit executed");
            }
        };
        
        form.add(button);
        add(form);
    }
}
