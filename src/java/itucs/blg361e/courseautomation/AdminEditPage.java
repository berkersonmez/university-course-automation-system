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
public final class AdminEditPage extends BasePage {
    
    class AdminEditForm extends Form {
        private String oldPassword;
        
        public AdminEditForm(String property, Admin admin) {
            super(property);
            oldPassword = admin.getPassword();
            admin.setPassword("");
            CompoundPropertyModel model = new CompoundPropertyModel(admin);
            this.setModel(model);
            this.add(new TextField("name").setRequired(true));
            this.add(new TextField("password").setRequired(false));
        }

        @Override
        public void onSubmit() {
            Admin formResult = (Admin) getModelObject();
            AdminCollectionJDBC aCollection = new AdminCollectionJDBC();
            if (formResult.getPassword() == null) {
                formResult.setPassword(oldPassword);
            } else {
                try {
                    formResult.setPassword(formResult.preparePassword(formResult.getPassword()));
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(AdminEditPage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(AdminEditPage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            aCollection.updateAdminPreparedPassword(formResult);
            setResponsePage(new MenuPage());
        }
    }

    public AdminEditPage() {
        replace(new HeaderPanel("headerpanel","Edit Personal Info"));
        User user = ((CustomSession)getSession()).getUser();
        AdminCollectionJDBC aCollection = new AdminCollectionJDBC();
        Admin admin = new Admin("", "", "");
        admin.setId(user.getId());
        admin = aCollection.getAdmin(admin);
        add(new AdminEditForm("admin_edit", admin));
    }
}
