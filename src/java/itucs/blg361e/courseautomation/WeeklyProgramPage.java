/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itucs.blg361e.courseautomation;

import itucs.blg361e.courseautomation.model.StudentCourse;
import itucs.blg361e.courseautomation.model.StudentCourseCollectionJDBC;
import itucs.blg361e.courseautomation.model.User;
import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

/**
 *
 * @author Oguzzo
 */
public final class WeeklyProgramPage extends BasePage {

    public WeeklyProgramPage() {
        User user = ((CustomSession)getSession()).getUser();
        replace(new HeaderPanel("headerpanel", "Weekly Program" + user.getName()));
        
        StudentCourseCollectionJDBC collectionA = new StudentCourseCollectionJDBC();
        List<StudentCourse> studentCourseList = collectionA.getOneStudentsCourses(user);
        ListView studentCourseListView = new ListView("student_courses", studentCourseList) {
            @Override
            protected void populateItem(ListItem li) {
                StudentCourse nStudentCourse = (StudentCourse) li.getModelObject();
                li.add(new Label("CRN", nStudentCourse.getCRN().toString()));
            }
        };
        this.add(studentCourseListView);
    }
    
}