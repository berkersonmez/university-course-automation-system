/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itucs.blg361e.courseautomation;

import itucs.blg361e.courseautomation.model.AdminCollectionJDBC;
import itucs.blg361e.courseautomation.model.StudentCollectionJDBC;
import itucs.blg361e.courseautomation.model.TeacherCollectionJDBC;
import itucs.blg361e.courseautomation.model.User;
import itucs.blg361e.courseautomation.model.UserCollectionJDBC;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;

public class LoginForm extends Form {

    public LoginForm(String id, User iUser) {
        super(id);

        CompoundPropertyModel model = new CompoundPropertyModel(iUser);
        this.setModel(model);

        this.add(new TextField("username"));
        this.add(new PasswordTextField("password"));
    }

    @Override
    public void onSubmit() {
        User user = (User) this.getModelObject();
        Application app = (Application) this.getApplication();
        UserCollectionJDBC collection = new UserCollectionJDBC();
        user = collection.checkUserByUsernameAndPassword(user);
        
        
        if(user.getId() != null){
            StudentCollectionJDBC sCollection = new StudentCollectionJDBC();
            TeacherCollectionJDBC tCollection = new TeacherCollectionJDBC();
            AdminCollectionJDBC aCollection = new AdminCollectionJDBC();
            
            if(aCollection.isAdmin(user)){
                user.setType(1);
            }
            else if(tCollection.isTeacher(user)){
                user.setType(2);
            }
            else if(sCollection.isStudent(user)){
                user.setType(3);
            }
            
            ((CustomSession)getSession()).setUser(user);
            this.setResponsePage(new MenuPage());
            
            
        }
    }
    
    public CustomSession getCustomSession(){
        return (CustomSession)getSession();
    }
}