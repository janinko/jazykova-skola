package cz.muni.fi.pa165.languageschoolweb;

import cz.muni.fi.pa165.languageschool.api.adapters.TeacherDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.dto.TeacherDto;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * @author xbrazdi1, xchrastk, xkelnar
 */
public class TeachersPage extends BasePage {
	private static final long serialVersionUID = 1L;
	@SpringBean
    private TeacherDtoAdapter teachers;
	
	public TeachersPage(PageParameters parameters) {
		super(parameters);


		RepeatingView repeating = new RepeatingView("repeating");
        add(repeating);
	
       TreeSet<TeacherDto> teachersOrdered = new TreeSet<TeacherDto>(new TeacherComparator());
       teachersOrdered.addAll(teachers.getAllTeachers());


		for (TeacherDto teacherDto : teachersOrdered) {
			AbstractItem item = new AbstractItem(repeating.newChildId());
			
			
			PageParameters params = new PageParameters();
			params.set("email", teacherDto.getEmail());

			Link link = new BookmarkablePageLink("link", TeacherPage.class, params);
			
			
			item.add(new Label("email", teacherDto.getEmail()));
			String nat = "";
			if(teacherDto.getNativeLanguage() != null) nat = teacherDto.getNativeLanguage().toString();
			item.add(new Label("nativlang", nat));
			link.add(new Label("firstname", teacherDto.getFirstName()));
			link.add(new Label("lastname", teacherDto.getLastName()));
			item.add(link);
			
			
			repeating.add(item);
		}
		
		add(repeating);
	}

	private static class TeacherComparator implements Comparator<TeacherDto> {

	 @Override
	 public int compare(TeacherDto o1, TeacherDto o2) {
		  int ret;
		  ret = o1.getLastName().compareTo(o2.getLastName());
		  if(ret != 0) return ret;
		  ret = o1.getFirstName().compareTo(o2.getFirstName());
		  return ret;
	 }
	}
}
