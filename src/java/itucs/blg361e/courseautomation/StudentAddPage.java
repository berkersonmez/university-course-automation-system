/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itucs.blg361e.courseautomation;

import itucs.blg361e.courseautomation.model.Course;
import itucs.blg361e.courseautomation.model.CourseCollectionJDBC;
import itucs.blg361e.courseautomation.model.Faculty;
import itucs.blg361e.courseautomation.model.FacultyCollectionJDBC;
import itucs.blg361e.courseautomation.model.Student;
import itucs.blg361e.courseautomation.model.StudentCollectionJDBC;
import itucs.blg361e.courseautomation.utility.SelectOption;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 *
 * @author Berker
 */
public final class StudentAddPage extends BasePage {
    
    class StudentAddForm extends Form {
        
        public StudentAddForm(String property) {
            super(property);
            CompoundPropertyModel model = new CompoundPropertyModel(new Student("", "", "", null));
            this.setModel(model);
            this.add(new TextField("name").setRequired(true));
            this.add(new TextField("username").setRequired(true));
            this.add(new TextField("password").setRequired(true));
            this.add(new TextField("number").setRequired(true));
            this.add(new TextField("creditLimit").setRequired(true));
        }

        @Override
        public void onSubmit() {
            Student formResult = (Student) getModelObject();
            
            StudentCollectionJDBC sCollection = new StudentCollectionJDBC();
            if (sCollection.checkUsernameAndNumber(formResult)) {
                error("Username and number must be unique!");
            } else {
                sCollection.addStudent(formResult);
                setResponsePage(new MenuPage());
            }
        }
    }

    public StudentAddPage() {
        replace(new HeaderPanel("headerpanel","Add Student"));
        add(new StudentAddForm("student_add"));
    }
}
