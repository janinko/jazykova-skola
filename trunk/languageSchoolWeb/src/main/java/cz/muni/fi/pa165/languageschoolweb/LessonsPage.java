package cz.muni.fi.pa165.languageschoolweb;

import cz.muni.fi.pa165.languageschool.api.adapters.LessonDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.adapters.StudentDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.dto.LessonDto;
import cz.muni.fi.pa165.languageschool.api.dto.StudentDto;
import cz.muni.fi.pa165.languageschoolweb.components.LessonList;
import cz.muni.fi.pa165.languageschoolweb.security.SpringAuthenticatedWebSession;
import java.sql.Date;
import java.util.Set;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class LessonsPage extends BasePage {

    private static final long serialVersionUID = 1L;
    @SpringBean
    private LessonDtoAdapter lessons;
    @SpringBean
    private StudentDtoAdapter students;

    public LessonsPage(PageParameters parameters) {
        super(parameters);
        String act = parameters.get("act").toString();
        Long lessonId = parameters.get("id").toLong(-1);
        Boolean my = false;
        SpringAuthenticatedWebSession session = (SpringAuthenticatedWebSession) AuthenticatedWebSession.get();
        String email;
        if (session.getLogged() == null) {
            email = "";
        } else {
            email = session.getLogged();
        }
        if (!parameters.get("my").isNull() && !parameters.get("email").isNull()) {
            email = parameters.get("email").toString();
        }
        Set<LessonDto> lsns;


        if ((act != null) && (lessonId != null)) {
            if (act.equals("delete")) {
                lessons.removeLesson(lessons.read(lessonId));
            } else {
                if (students.read(email) != null) {
                    StudentDto s = students.read(email);
                    if (students.getAllLessons(s).contains(lessons.read(lessonId))) {
                        students.lessonCancel(students.read(email), lessons.read(lessonId));
                    } else {
                        students.lessonEnroll(students.read(email), lessons.read(lessonId));
                    }
                }
            }
        }


        if (!parameters.get("my").isNull() && !parameters.get("email").isNull() && students.read(email) != null) {
            lsns = students.getAllLessons(students.read(email));
            my = true;
        } else {
            lsns = lessons.getUpcomingLessons(new Date((new java.util.Date()).getTime() + 86400000 * 21));
        }
        LessonList lessonList = new LessonList("lekce", lsns, my, email);

        add(lessonList);
    }
}
