package cz.muni.fi.pa165.languageschoolweb.components;

import cz.muni.fi.pa165.languageschool.api.dto.LessonDto;
import cz.muni.fi.pa165.languageschoolweb.TeacherPage;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 *
 * @author jbrazdil
 */
public class LessonList extends Panel{
	private static final long serialVersionUID = 1L;

	public LessonList(String id, Set<LessonDto> lessons){
		super(id);

		Label heading = new Label("heading","Lekce");
        RepeatingView repeating = new RepeatingView("repeating");
        Form form = new Form("form") {
            protected void onSubmit() {
                info("Form.onSubmit executed");
            }
        };
        Button button = new Button("sign") {
            public void onSubmit() {
                info("button1.onSubmit executed");
            }
        };

		fillRepeating(repeating,lessons);

        form.add(repeating);
        form.add(button);
        add(form);
		add(heading);
	}

	private void fillRepeating(RepeatingView repeating, Set<LessonDto> lessons) {
		TreeSet<LessonDto> lessonsOrdered = new TreeSet<LessonDto>(new LessonComparator());
		lessonsOrdered.addAll(lessons);

        final Map<CheckBox, LessonDto> deletes = new HashMap<CheckBox, LessonDto>();
        final List<CheckBoxModel> deleteModels = new ArrayList<CheckBoxModel>();
        
        int i = 0;
        for (LessonDto lesson : lessonsOrdered) {
            AbstractItem item = new AbstractItem(repeating.newChildId());
		
			PageParameters params = new PageParameters();
			params.set("email", lesson.getTeacherEmail());

			Link link = new BookmarkablePageLink("link", TeacherPage.class, params);

			DateFormat dateFormat = new SimpleDateFormat("dd. MM.");
			DateFormat timeFormat = new SimpleDateFormat("HH:mm");

			CheckBoxModel model = new CheckBoxModel(false);
            deleteModels.add(model);
            CheckBox check = new CheckBox("check", new PropertyModel<Boolean>(deleteModels.get(i),"checked"));
            deletes.put(check, lesson);
            i++;




			item.add(new Label("date", dateFormat.format(lesson.getDate().getTime())));
			item.add(new Label("time", timeFormat.format(lesson.getDate().getTime())));
            item.add(new Label("name", lesson.getCourse().getName()));
            link.add(new Label("teacherName", lesson.getTeacherName()));
			item.add(link);
            //item.add(new CheckBox("bool",Model.of(Boolean.TRUE)));
            item.add(check);

            repeating.add(item);
        }
	}

	private static class LessonComparator implements Comparator<LessonDto> {

		@Override
		public int compare(LessonDto o1, LessonDto o2) {
			return o1.getDate().compareTo(o2.getDate());
		}
	}

	class CheckBoxModel implements Serializable {

		private static final long serialVersionUID = 1L;
		private boolean checked;

		CheckBoxModel(Boolean checked) {
			this.checked = checked;
		}

		boolean isChecked() {
			return checked;
		}
	}
}

/*
 * public class LessonsList extends BasePage {
    @SpringBean
    private LessonDtoAdapter lessons;
    @SpringBean
    private CourseDtoAdapter courses;
    @SpringBean
    private GenerateDataService generator;

    public LessonsList() {
        generator.generateData(2, 3, 3, 2);
        final Set<LessonDto> set = lessons.getAllLessons();

        final Form form = new Form("form") {
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
                boolean deleted = false;
                for (CheckBox check : deletes.keySet()) {
                    if (check.getModelObject() == null) {
                        ;
                    } else 
                        if (check.getModelObject() == true) {                        
                        lessons.removeLesson(deletes.get(check));
                        set.remove(deletes.get(check));
                        deleted = true;
                    }
                }
                
                throw new RestartResponseException(this.getPage());
                
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

 */
