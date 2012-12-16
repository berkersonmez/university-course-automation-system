/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itucs.blg361e.courseautomation;

import itucs.blg361e.courseautomation.model.Course;
import itucs.blg361e.courseautomation.model.CourseCollectionJDBC;
import itucs.blg361e.courseautomation.model.Faculty;
import itucs.blg361e.courseautomation.model.FacultyCollectionJDBC;
import itucs.blg361e.courseautomation.utility.SelectOption;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;

/**
 *
 * @author Berker
 */
class CourseForm extends Form {
        private final DropDownChoice<Faculty> facultySelect;
        
        public CourseForm(String property, Course course) {
            super(property);
            CompoundPropertyModel model = new CompoundPropertyModel(course);
            this.setModel(model);
            this.add(new TextField("name").setRequired(true));
            this.add(new TextField("code").setRequired(true));
            this.add(new TextField("credits").setRequired(true));
            final FacultyCollectionJDBC fCollection = new FacultyCollectionJDBC();
            List<SelectOption> selectChoices = new ArrayList<SelectOption>();
            for (Faculty fcl : fCollection.getFaculties()) {
                SelectOption selection = new SelectOption(fcl.getId().toString(), fcl.getName() + " (" + fcl.getCode() + ")");
                selectChoices.add(selection);
                if (course.getFacultyID() == fcl.getId()){
                    course.setFaculty(selection);
                }
            }
            ChoiceRenderer cr = new ChoiceRenderer("value", "key");
            // TODO: Selectbox default value
            facultySelect = (DropDownChoice<Faculty>) new DropDownChoice("faculty", 
                    new PropertyModel(course,"faculty"), 
                    selectChoices, 
                    cr).setRequired(true);
            add(facultySelect);
            this.add(new TextField("length").setRequired(true));
            fCollection.close();
        }

        @Override
        public void onSubmit() {
            Course formResult = (Course) getModelObject();
            formResult.setFacultyID(Integer.parseInt(formResult.getFaculty().getKey()));
            CourseCollectionJDBC cCollection = new CourseCollectionJDBC();
            cCollection.updateCourse(formResult);
            setResponsePage(new CourseSelectPage());
            cCollection.close();
            
        }
    }