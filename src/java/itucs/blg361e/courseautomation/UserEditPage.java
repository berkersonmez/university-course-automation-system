/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itucs.blg361e.courseautomation;

import itucs.blg361e.courseautomation.model.Admin;
import itucs.blg361e.courseautomation.model.AdminCollectionJDBC;
import itucs.blg361e.courseautomation.model.Course;
import itucs.blg361e.courseautomation.model.CourseCollectionJDBC;
import itucs.blg361e.courseautomation.model.Faculty;
import itucs.blg361e.courseautomation.model.FacultyCollectionJDBC;
import itucs.blg361e.courseautomation.model.User;
import itucs.blg361e.courseautomation.model.UserCollectionJDBC;
import itucs.blg361e.courseautomation.utility.SelectOption;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public final class UserEditPage extends BasePage {
    
    class UserEditForm extends Form {
        private String oldPassword;
        
        public UserEditForm(String property, User user) {
            super(property);
            oldPassword = user.getPassword();
            user.setPassword("");
            CompoundPropertyModel model = new CompoundPropertyModel(user);
            this.setModel(model);
            this.add(new TextField("name").setRequired(true));
            this.add(new TextField("password").setRequired(false));
        }

        @Override
        public void onSubmit() {
            User formResult = (User) getModelObject();
            UserCollectionJDBC uCollection = new UserCollectionJDBC();
            if (formResult.getPassword() == null) {
                formResult.setPassword(oldPassword);
            } else {
                try {
                    formResult.setPassword(formResult.preparePassword(formResult.getPassword()));
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(UserEditPage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(UserEditPage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            uCollection.updateUserPreparedPassword(formResult);
            setResponsePage(new MenuPage());
        }
    }

    public UserEditPage() {
        replace(new HeaderPanel("headerpanel","Edit Personal Info"));
        User user = ((CustomSession)getSession()).getUser();
        add(new UserEditForm("user_edit", user));
    }
}
