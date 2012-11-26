/*
 * Copyright 2012 kelnar.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
 *
 * @author kelnar
 */
public class TeachersPage extends BasePage {
	@SpringBean
    private TeacherDtoAdapter teachers;
	
	public TeachersPage() {
		super();

		Set<TeacherDto> set = teachers.getAllTeachers();
		
		Form form = new Form("form") {
            protected void onSubmit() {
                info("Form.onSubmit executed");
            }
        };
		
		RepeatingView repeating = new RepeatingView("repeating");
        add(repeating);
		
		for (TeacherDto teacherDto : set) {
			AbstractItem item = new AbstractItem(repeating.newChildId());
			
			
			PageParameters params = new PageParameters();
			params.set("email", teacherDto.getEmail());

			Link link = new BookmarkablePageLink("link", TeacherPage.class, params);
			
			
			item.add(new Label("email", teacherDto.getEmail()));
			item.add(new Label("nativlang", teacherDto.getNativeLanguage()));
			link.add(new Label("firstname", teacherDto.getFirstName()));
			link.add(new Label("lastname", teacherDto.getLastName()));
			item.add(link);
			
			
			repeating.add(item);
		}
		
		form.add(repeating);
		addOrReplace(form);
	}
}
