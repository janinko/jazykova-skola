package cz.muni.fi.pa165.languageschoolweb.components;

import cz.muni.fi.pa165.languageschool.api.dto.CourseDto;
import cz.muni.fi.pa165.languageschoolweb.CoursePage;
import cz.muni.fi.pa165.languageschoolweb.HomePage;
import java.util.Comparator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 *
 * @author jbrazdil
 */
public class CourseList extends Panel{
	private static final long serialVersionUID = 1L;

	public CourseList(String id, Set<CourseDto> courses){
		super(id);

        RepeatingView repeating = new RepeatingView("repeating");
		Label deleteLabel = new Label("deleteLabel", "Smazat");
		Button addButton = new Button("addButton");
		Form form = new Form("form") {
			private static final long serialVersionUID = 1L;
			@Override
            protected void onSubmit() {
                info("Form.onSubmit executed");
            }
        };

		Set<CourseDto> orderedCourses = new TreeSet<CourseDto>(new CourseComparator());
		orderedCourses.addAll(courses);

		for (CourseDto course : orderedCourses) {
			fillRepeatingLine(repeating, course);
		}

		deleteLabel.setVisible(isLogged());
		addButton.setVisible(isLogged());

		form.add(deleteLabel);
        form.add(repeating);
		form.add(addButton);
		add(form);
	}

	private boolean logged = new Random().nextBoolean();
	private boolean isLogged(){
		return logged;
	}

	private void fillRepeatingLine(RepeatingView repeating, CourseDto course) {
		AbstractItem item = new AbstractItem(repeating.newChildId());

		PageParameters params = new PageParameters();
		params.set("courseid", course.getId());

		Link link = new BookmarkablePageLink("link", CoursePage.class, params);

		Button button = new Button("delete");
		button.setVisible(isLogged());

		link.add(new Label("name", course.getName()));
		item.add(link);
		item.add(new Label("language", course.getLanguage()));
		item.add(new Label("level", String.valueOf(course.getLevel())));
		item.add(button);
		repeating.add(item);
	}

	private class CourseComparator implements Comparator<CourseDto>{

		@Override
		public int compare(CourseDto o1, CourseDto o2) {
			int ret = o1.getLanguage().compareTo(o2.getLanguage());
			if(ret != 0) return ret;
			ret = Integer.valueOf(o1.getLevel()).compareTo(o2.getLevel());
			if(ret != 0) return ret;
			ret = o1.getName().compareTo(o2.getName());
			return ret;
		}
	}
}
