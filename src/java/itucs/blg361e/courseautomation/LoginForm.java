/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itucs.blg361e.courseautomation;

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
//        collection.addUser(user);
//        this.setResponsePage(new MovieDisplayPage(movie));
    }
}