/*
 * HomePage.java
 *
 * Created on 21. listopad 2012, 20:21
 */
package cz.muni.fi.pa165.languageschoolweb;

import cz.muni.fi.pa165.languageschool.api.adapters.CourseDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.adapters.LessonDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.dto.LessonDto;
import cz.muni.fi.pa165.languageschool.api.services.GenerateDataService;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;


public class LessonsPage extends BasePage {
    @SpringBean
    private LessonDtoAdapter lessons;
    @SpringBean
    private CourseDtoAdapter courses;
    @SpringBean
    private GenerateDataService generator;

    public LessonsPage() {
        generator.generateData(2, 3, 3, 2);
        Set<LessonDto> set = lessons.getAllLessons();

        Form form = new Form("form") {
            protected void onSubmit() {
                info("Form.onSubmit executed");
            }
        };


        RepeatingView repeating = new RepeatingView("repeating");
        add(repeating);
        
        final Map<CheckBox, LessonDto> deletes = new HashMap<CheckBox, LessonDto>();
        final List<CheckBoxModel> deleteModels = new ArrayList<CheckBoxModel>();
        
        int i = 0;
        for (LessonDto lesson : set) {
            AbstractItem item = new AbstractItem(repeating.newChildId());

            CheckBoxModel model = new CheckBoxModel(false);
            deleteModels.add(model);
            CheckBox check = new CheckBox("check", new PropertyModel<Boolean>(deleteModels.get(i),"checked"));
            deletes.put(check, lesson);
            i++;

            DateFormat dateFormat = new SimpleDateFormat("dd.MM");
            DateFormat timeFormat = new SimpleDateFormat("HH:mm");

            item.add(new Label("date", dateFormat.format(lesson.getDate().getTime())));
            item.add(new Label("time", timeFormat.format(lesson.getDate().getTime())));
            item.add(new Label("name", lesson.getCourse().getName()));
            item.add(new Label("teacher", lesson.getTeacherName()));
            item.add(check);

            repeating.add(item);
        }

        form.add(repeating);

        Button button = new Button("sign") {
            public void onSubmit() {                
                /*for (CheckBox check : deletes.keySet()) {
                    if (check.getModelObject() == null) {
                        
                    } else 
                            if (check.getModelObject() == true) {                        
                        lessons.removeLesson(deletes.get(check));
                    }
                }*/
                
            }
        };
        
        form.add(button); 
        addOrReplace(form);
    }
}

class CheckBoxModel implements Serializable
{private static final long serialVersionUID = 1L;
  private boolean checked;

  CheckBoxModel(Boolean checked)
  {
    this.checked = checked;
  }

  boolean isChecked()
  {
    return checked;
  }
}
