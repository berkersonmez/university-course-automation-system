/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itucs.blg361e.courseautomation;

import itucs.blg361e.courseautomation.model.Course;
import itucs.blg361e.courseautomation.model.CourseCollectionJDBC;
import itucs.blg361e.courseautomation.model.Options;
import itucs.blg361e.courseautomation.model.OptionsCollectionJDBC;
import itucs.blg361e.courseautomation.utility.SelectOption;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 *
 * @author Berker
 */
public final class OptionsPage extends BasePage {
    
    class OptionsForm extends Form {
        
        private DropDownChoice<Course> ddc;
        public OptionsForm(String property) {
            super(property);
            OptionsCollectionJDBC oCollection = new OptionsCollectionJDBC();
            Options options = oCollection.getOptions();
            CompoundPropertyModel model = new CompoundPropertyModel(options);
            this.setModel(model);
            add(new CheckBox("isAddDrop"));
        }

        @Override
        public void onSubmit() {
            Options formResult = (Options) getModelObject();
            OptionsCollectionJDBC oCollection = new OptionsCollectionJDBC();
            oCollection.updateOptions(formResult);
            setResponsePage(new MenuPage());
        }
    }

    public OptionsPage() {
        replace(new HeaderPanel("headerpanel","Options"));
        OptionsForm form = new OptionsForm("options");
        add(form);
        
    }
}
