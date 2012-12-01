package cz.muni.fi.pa165.languageschoolweb.components;

import cz.muni.fi.pa165.languageschool.api.adapters.LessonDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.dto.LessonDto;
import cz.muni.fi.pa165.languageschoolweb.LessonsPage;
import cz.muni.fi.pa165.languageschoolweb.TeacherPage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * @author xbrazdi1, xchrastk, xkelnar
 */
public class LessonList extends Panel{
	private static final long serialVersionUID = 1L;

    @SpringBean
    private LessonDtoAdapter lessonsService;

    private boolean my;

	public LessonList(String id, Set<LessonDto> lessons, boolean my){
		super(id);
        this.my = my;

        RepeatingView repeating = new RepeatingView("repeating");

		fillRepeating(repeating,lessons);

        //form.add(repeating);
        add(repeating);
	}

	private void fillRepeating(RepeatingView repeating, Set<LessonDto> lessons) {
		TreeSet<LessonDto> lessonsOrdered = new TreeSet<LessonDto>(new LessonComparator());
		lessonsOrdered.addAll(lessons);
        
        
        for (LessonDto lesson : lessonsOrdered) {
            AbstractItem item = new AbstractItem(repeating.newChildId());
		
			PageParameters params = new PageParameters();
			params.set("email", lesson.getTeacherEmail());
            PageParameters idParams = new PageParameters();
            if (my) {idParams.set("my",true);}
            idParams.set("id",lesson.getId());
            idParams.set("act","delete");

			Link link = new BookmarkablePageLink("link", TeacherPage.class, params);

			DateFormat dateFormat = new SimpleDateFormat("dd. MM.");
			DateFormat timeFormat = new SimpleDateFormat("HH:mm");

			item.add(new Label("date", dateFormat.format(lesson.getDate().getTime())));
			item.add(new Label("time", timeFormat.format(lesson.getDate().getTime())));
            item.add(new Label("name", lesson.getCourse().getName()));
            link.add(new Label("teacherName", lesson.getTeacherName()));
			item.add(link);
            item.add(new BookmarkablePageLink("deleteLink", LessonsPage.class, idParams));

            repeating.add(item);
        }
	}

	private static class LessonComparator implements Comparator<LessonDto> {

		@Override
		public int compare(LessonDto o1, LessonDto o2) {
			return o1.getDate().compareTo(o2.getDate());
		}
	}
}