package cz.muni.fi.pa165.languageschoolweb.components;

import cz.muni.fi.pa165.languageschool.api.adapters.LessonDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.dto.LessonDto;
import cz.muni.fi.pa165.languageschoolweb.LessonPage;
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

        add(repeating);
	}

	private void fillRepeating(RepeatingView repeating, Set<LessonDto> lessons) {
		TreeSet<LessonDto> lessonsOrdered = new TreeSet<LessonDto>(new LessonComparator());
		lessonsOrdered.addAll(lessons);
        
        
        for (LessonDto lesson : lessonsOrdered) {
            AbstractItem item = new AbstractItem(repeating.newChildId());
		
			PageParameters teacherParams = new PageParameters();
			teacherParams.set("email", lesson.getTeacherEmail());
			
			PageParameters lessonParams = new PageParameters();
			lessonParams.set("id", lesson.getId());

            PageParameters deleteParams = new PageParameters();
            if (my) {deleteParams.set("my",true);}
            deleteParams.set("id",lesson.getId());
            deleteParams.set("act","delete");

			Link teacherLink = new BookmarkablePageLink("teacherLink", TeacherPage.class, teacherParams);
			Link lessonLink  = new BookmarkablePageLink("lessonLink", LessonPage.class, lessonParams);
			Link deleteLink  = new BookmarkablePageLink("deleteLink", LessonsPage.class, deleteParams);

			DateFormat dateFormat = new SimpleDateFormat("dd. MM.");
			DateFormat timeFormat = new SimpleDateFormat("HH:mm");

			item.add(new Label("date", dateFormat.format(lesson.getDate().getTime())));
			item.add(new Label("time", timeFormat.format(lesson.getDate().getTime())));
            lessonLink.add(new Label("name", lesson.getCourse().getName()));
            teacherLink.add(new Label("teacherName", lesson.getTeacherName()));
			item.add(teacherLink);
            item.add(lessonLink);
            item.add(deleteLink);

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