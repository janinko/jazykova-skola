package cz.muni.fi.pa165.languageschoolweb;

import cz.muni.fi.pa165.languageschool.api.adapters.TeacherDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.dto.TeacherDto;
import java.util.Set;
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

		Set<TeacherDto> set = teachers.getAllTeachers();

		RepeatingView repeating = new RepeatingView("repeating");
        add(repeating);
		
		for (TeacherDto teacherDto : set) {
			AbstractItem item = new AbstractItem(repeating.newChildId());
			
			
			PageParameters params = new PageParameters();
			params.set("email", teacherDto.getEmail());

			Link link = new BookmarkablePageLink("link", TeacherPage.class, params);
			
			
			item.add(new Label("email", teacherDto.getEmail()));
			item.add(new Label("nativlang", teacherDto.getNativeLanguage().toString()));
			link.add(new Label("firstname", teacherDto.getFirstName()));
			link.add(new Label("lastname", teacherDto.getLastName()));
			item.add(link);
			
			
			repeating.add(item);
		}
		
		add(repeating);
	}
}
