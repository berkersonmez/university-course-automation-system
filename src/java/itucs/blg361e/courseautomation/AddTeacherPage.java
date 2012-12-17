/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itucs.blg361e.courseautomation;

import itucs.blg361e.courseautomation.model.Student;
import itucs.blg361e.courseautomation.model.StudentCollectionJDBC;
import itucs.blg361e.courseautomation.model.Teacher;
import itucs.blg361e.courseautomation.model.TeacherCollectionJDBC;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;

/**
 *
 * @author Berker
 */
public final class AddTeacherPage extends BasePage {
    
    class AddTeacherForm extends Form {
        
        public AddTeacherForm(String property) {
            super(property);
            CompoundPropertyModel model = new CompoundPropertyModel(new Teacher());
            this.setModel(model);
            this.add(new TextField("name").setRequired(true));
            this.add(new TextField("username").setRequired(true));
            this.add(new TextField("password").setRequired(true));
        }

        @Override
        public void onSubmit() {
            Teacher formResult = (Teacher) getModelObject();
            
            TeacherCollectionJDBC nTeacherCollection = new TeacherCollectionJDBC();
            
            nTeacherCollection.addTeacher(formResult);
            getSession().info("New teacher is added!");
            setResponsePage(new MenuPage());
            
            nTeacherCollection.close();
        }
    }

    public AddTeacherPage() {
        replace(new HeaderPanel("headerpanel","Add Teacher"));
        add(new AddTeacherForm("add_teacher"));
    }
}
