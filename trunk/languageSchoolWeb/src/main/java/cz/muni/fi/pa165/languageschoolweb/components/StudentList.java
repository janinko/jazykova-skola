package cz.muni.fi.pa165.languageschoolweb.components;

import cz.muni.fi.pa165.languageschool.api.adapters.StudentDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.dto.StudentDto;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * @author xbrazdi1, xchrastk, xkelnar
 */
public class StudentList extends Panel{
	private static final long serialVersionUID = 1L;

    @SpringBean
    private StudentDtoAdapter studentsService;

	public StudentList(String id, Set<StudentDto> students){
		super(id);

        RepeatingView repeating = new RepeatingView("repeating");

		fillRepeating(repeating,students);

        //form.add(repeating);
        add(repeating);
	}

	private void fillRepeating(RepeatingView repeating, Set<StudentDto> students) {
		TreeSet<StudentDto> studentsOrdered = new TreeSet<StudentDto>(new StudentComparator());
		studentsOrdered.addAll(students);
        
        
        for (StudentDto student : studentsOrdered) {
            AbstractItem item = new AbstractItem(repeating.newChildId());

			item.add(new Label("age", Integer.toString(student.getAge())));
			item.add(new Label("firstname", student.getFirstName()));
			item.add(new Label("lastname", student.getLastName()));
			item.add(new Label("email", student.getEmail()));

            repeating.add(item);
        }
	}

	private static class StudentComparator implements Comparator<StudentDto> {

		@Override
		public int compare(StudentDto o1, StudentDto o2) {
		  int ret;
		  ret = o1.getLastName().compareTo(o2.getLastName());
		  if(ret != 0) return ret;
		  ret = o1.getFirstName().compareTo(o2.getFirstName());
		  return ret;
		}
	}
}