/*
 * HomePage.java
 *
 * Created on 14 Ekim 2012 Pazar, 12:55
 */

package itucs.blg361e.courseautomation;           

import itucs.blg361e.courseautomation.model.Admin;
import itucs.blg361e.courseautomation.model.AdminCollectionJDBC;
import itucs.blg361e.courseautomation.model.Student;
import itucs.blg361e.courseautomation.model.StudentCollectionJDBC;
import itucs.blg361e.courseautomation.model.Teacher;
import itucs.blg361e.courseautomation.model.TeacherCollectionJDBC;
import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

public class HomePage extends BasePage {

    public HomePage() {
        replace(new HeaderPanel("headerpanel", "Example Page")); 
        StudentCollectionJDBC collection = new StudentCollectionJDBC();
        List<Student> students = collection.getStudents();
        ListView studentListView = new ListView("student_list", students) {
            @Override
            protected void populateItem(ListItem li) {
                Student student = (Student) li.getModelObject();
                li.add(new Label("number", student.getNumber().toString()));
                li.add(new Label("name", student.getName()));
                li.add(new Label("username", student.getUsername()));
                li.add(new Label("password", student.getPassword()));
            }
        };
        this.add(studentListView);
        
        TeacherCollectionJDBC collectionT = new TeacherCollectionJDBC();
        List<Teacher> teachers = collectionT.getTeachers();
        ListView teacherListView = new ListView("teacher_list", teachers) {
            @Override
            protected void populateItem(ListItem li) {
                Teacher teacher = (Teacher) li.getModelObject();
                li.add(new Label("t_name", teacher.getName()));
                li.add(new Label("t_username", teacher.getUsername()));
                li.add(new Label("t_password", teacher.getPassword()));
            }
        };
        this.add(teacherListView);
        
        AdminCollectionJDBC collectionA = new AdminCollectionJDBC();
        List<Admin> admins = collectionA.getAdmins();
        ListView adminListView = new ListView("admin_list", admins) {
            @Override
            protected void populateItem(ListItem li) {
                Admin admin = (Admin) li.getModelObject();
                li.add(new Label("a_name", admin.getName()));
                li.add(new Label("a_username", admin.getUsername()));
                li.add(new Label("a_password", admin.getPassword()));
            }
        };
        this.add(adminListView);
        
    }

}
