/*
 * Copyright 2012 jbrazdil.
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
package cz.muni.fi.pa165.languageschoolweb.components;

import cz.muni.fi.pa165.languageschool.api.dto.LessonDto;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;

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

        for (LessonDto lesson : lessonsOrdered) {
            AbstractItem item = new AbstractItem(repeating.newChildId());

            DateFormat dateFormat = new SimpleDateFormat("dd. MM.");
            DateFormat timeFormat = new SimpleDateFormat("HH:mm");

            item.add(new Label("date", dateFormat.format(lesson.getDate().getTime())));
            item.add(new Label("time", timeFormat.format(lesson.getDate().getTime())));
            item.add(new Label("name", lesson.getCourse().getName()));
            item.add(new Label("teacher", lesson.getTeacherName()));
            item.add(new CheckBox("bool",Model.of(Boolean.TRUE)));

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
