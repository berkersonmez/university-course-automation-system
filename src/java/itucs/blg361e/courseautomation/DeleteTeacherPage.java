/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itucs.blg361e.courseautomation;

import itucs.blg361e.courseautomation.model.StudentCourse;
import itucs.blg361e.courseautomation.model.Teacher;
import itucs.blg361e.courseautomation.model.TeacherCollectionJDBC;
import itucs.blg361e.courseautomation.model.User;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;

/**
 *
 * @author Oguzzo
 */
public final class DeleteTeacherPage extends BasePage {

    class DeleteTeacherForm extends Form {
        
        public DeleteTeacherForm(String property) {
            super(property);
            CompoundPropertyModel model = new CompoundPropertyModel(new Teacher());
            this.setModel(model);
            this.add(new TextField("teacherID").setRequired(true));
        }

        @Override
        public void onSubmit() {
            Teacher formResult = (Teacher) getModelObject();
            
            TeacherCollectionJDBC nTeacherCollection = new TeacherCollectionJDBC();
            
            if(nTeacherCollection.checkCode(formResult) == false){
                error("DELETING FAILED: Teacher id: " + formResult.getTeacherID() + " does not exist!");
                return;
            }
            
            String teacher_name = nTeacherCollection.getTeacherByTeacherId(formResult.getTeacherID()).getName();
            nTeacherCollection.deleteTeacherByTeacherId(formResult);
            info("SUCCESS: Teacher " + teacher_name  + " is deleted.");
            
            nTeacherCollection.close();
        }
    }
    
    public DeleteTeacherPage() {
        User user = ((CustomSession)getSession()).getUser();
        replace(new HeaderPanel("headerpanel", "Delete Teacher " + user.getName()));
        add(new DeleteTeacherForm("delete_teacher"));
        
        
    }
    
    
}
