/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itucs.blg361e.courseautomation;

import itucs.blg361e.courseautomation.model.Course;
import itucs.blg361e.courseautomation.model.CourseCollectionJDBC;
import itucs.blg361e.courseautomation.model.Faculty;
import itucs.blg361e.courseautomation.model.FacultyCollectionJDBC;
import itucs.blg361e.courseautomation.model.OpenCourse;
import itucs.blg361e.courseautomation.model.OpenCourseCollectionJDBC;
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
 * @author Oguzzo
 */
class TeacherOpenCourseForm extends Form {
        private final DropDownChoice<Course> courseSelect;
        
        public TeacherOpenCourseForm(String property, OpenCourse iOpenCourse) {
            super(property);
            CompoundPropertyModel model = new CompoundPropertyModel(iOpenCourse);
            this.setModel(model);
            this.add(new TextField("name").setRequired(true));
            this.add(new TextField("code").setRequired(true));
            this.add(new TextField("credits").setRequired(true));
            final CourseCollectionJDBC cCollection = new CourseCollectionJDBC();
            List<SelectOption> selectChoices = new ArrayList<SelectOption>();
            for (Course nCourse : cCollection.getCourses()) {
                SelectOption selection = new SelectOption(nCourse.getId().toString(), nCourse.getName() + " (" + nCourse.getCode() + ")");
                selectChoices.add(selection);
                if (iOpenCourse.getCourseID() == nCourse.getId()){
                    iOpenCourse.setCourse(selection);
                }
            }
            ChoiceRenderer cr = new ChoiceRenderer("value", "key");
            // TODO: Selectbox default value
            courseSelect = (DropDownChoice<Course>) new DropDownChoice("course", 
                    new PropertyModel(iOpenCourse,"course"), 
                    selectChoices, 
                    cr).setRequired(true);
            add(courseSelect);
            this.add(new TextField("length").setRequired(true));
        }

        @Override
        public void onSubmit() {
            OpenCourse formResult = (OpenCourse) getModelObject();
            formResult.setCourseID(Integer.parseInt(formResult.getCourse().getKey()));
            OpenCourseCollectionJDBC oCollection = new OpenCourseCollectionJDBC();
            oCollection.updateOpenCourse(formResult);
            setResponsePage(new MenuPage());
        }
    }