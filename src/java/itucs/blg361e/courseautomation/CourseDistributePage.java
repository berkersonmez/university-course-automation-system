/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itucs.blg361e.courseautomation;

import itucs.blg361e.courseautomation.model.Course;
import itucs.blg361e.courseautomation.model.CourseCollectionJDBC;
import itucs.blg361e.courseautomation.utility.SelectOption;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.markup.html.WebPage;
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
public final class CourseDistributePage extends BasePage {
    
    class CourseSelectForm extends Form {
        
        private DropDownChoice<Course> ddc;
        public CourseSelectForm(String property) {
            super(property);
        }

        @Override
        public void onSubmit() {
            
            info("Courses are distributed!");
        }
    }

    public CourseDistributePage() {
        replace(new HeaderPanel("headerpanel","Course Distribution"));
        CourseSelectForm form = new CourseSelectForm("course_distribute");
        add(form);
        
    }
}
