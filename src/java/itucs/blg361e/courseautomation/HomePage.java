/*
 * HomePage.java
 *
 * Created on 14 Ekim 2012 Pazar, 12:55
 */

package itucs.blg361e.courseautomation;           

import itucs.blg361e.courseautomation.model.Student;
import itucs.blg361e.courseautomation.model.StudentCollectionJDBC;
import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

public class HomePage extends BasePage {

    public HomePage() {
        StudentCollectionJDBC collection = new StudentCollectionJDBC();
        List<Student> students = collection.getStudents();
        ListView studentListView = new ListView("student_list", students) {
            @Override
            protected void populateItem(ListItem li) {
                Student student = (Student) li.getModelObject();
                li.add(new Label("number", student.getNumber().toString()));
                li.add(new Label("name", student.getName()));
            }
        };
        this.add(studentListView);
        
    }

}
