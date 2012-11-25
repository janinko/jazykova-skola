package cz.muni.fi.pa165.languageschoolweb.components;

import cz.muni.fi.pa165.languageschool.api.adapters.CourseDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.adapters.LessonDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.dto.LessonDto;
import cz.muni.fi.pa165.languageschoolweb.TeacherPage;
import cz.muni.fi.pa165.languageschoolweb.components.LessonList.CheckBoxModel;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author jbrazdil
 */
public class LessonList extends Panel{
	private static final long serialVersionUID = 1L;

    @SpringBean
    private LessonDtoAdapter lessonsService;
    @SpringBean
    private CourseDtoAdapter courses;

    private Map<CheckBox, LessonDto> deletes = new HashMap<CheckBox, LessonDto>();

	public LessonList(String id, Set<LessonDto> lessons){
		super(id);

        RepeatingView repeating = new RepeatingView("repeating");
        Form form = new Form("form") {
            protected void onSubmit() {
                info("Form.onSubmit executed");
            }
        };
        Button button = new Button("sign") {
            public void onSubmit() {
                boolean deleted = false;
                for (CheckBox check : deletes.keySet()) {
					if (check.getModelObject() != null && check.getModelObject() == true) {
						lessonsService.removeLesson(deletes.get(check));
						//lessons.remove(deletes.get(check));
						deleted = true;
					}
				}

                throw new RestartResponseException(this.getPage());

            }
        };

		fillRepeating(repeating,lessons);

        form.add(repeating);
        form.add(button);
        add(form);
	}

	private void fillRepeating(RepeatingView repeating, Set<LessonDto> lessons) {
		TreeSet<LessonDto> lessonsOrdered = new TreeSet<LessonDto>(new LessonComparator());
		lessonsOrdered.addAll(lessons);

        
        for (LessonDto lesson : lessonsOrdered) {
            AbstractItem item = new AbstractItem(repeating.newChildId());
		
			PageParameters params = new PageParameters();
			params.set("email", lesson.getTeacherEmail());

			Link link = new BookmarkablePageLink("link", TeacherPage.class, params);

			DateFormat dateFormat = new SimpleDateFormat("dd. MM.");
			DateFormat timeFormat = new SimpleDateFormat("HH:mm");

			CheckBoxModel model = new CheckBoxModel(false);
            CheckBox check = new CheckBox("check", new PropertyModel<Boolean>(model,"checked"));
            deletes.put(check, lesson);

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