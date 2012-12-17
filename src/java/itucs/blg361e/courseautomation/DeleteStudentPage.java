/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itucs.blg361e.courseautomation;

import itucs.blg361e.courseautomation.model.Student;
import itucs.blg361e.courseautomation.model.StudentCollectionJDBC;
import itucs.blg361e.courseautomation.model.User;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;

/**
 *
 * @author Oguzzo
 */
public final class DeleteStudentPage extends BasePage {

    class DeleteStudentForm extends Form {
        
        public DeleteStudentForm(String property) {
            super(property);
            CompoundPropertyModel model = new CompoundPropertyModel(new Student());
            this.setModel(model);
            this.add(new TextField("number").setRequired(true));
        }

        @Override
        public void onSubmit() {
            Student formResult = (Student) getModelObject();
            
            StudentCollectionJDBC nStudentCollection = new StudentCollectionJDBC();
            
            if(nStudentCollection.checkCode(formResult) == false){
                error("DELETING FAILED: Student with number: " + formResult.getNumber() + " does not exist!");
                return;
            }
            
            String student_name = nStudentCollection.getOneStudentByStudentNumber(formResult.getNumber()).getName();
            nStudentCollection.deleteStudentByStudentId(formResult);
            info("SUCCESS: Student " + student_name  + " is deleted.");
            
            nStudentCollection.close();
        }
    }
    
    public DeleteStudentPage() {
        User user = ((CustomSession)getSession()).getUser();
        replace(new HeaderPanel("headerpanel", "Delete Student " + user.getName()));
        add(new DeleteStudentForm("delete_student"));
        
        
    }
    
    
}
