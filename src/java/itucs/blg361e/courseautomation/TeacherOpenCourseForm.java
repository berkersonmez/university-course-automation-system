/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itucs.blg361e.courseautomation;

import itucs.blg361e.courseautomation.model.BuildingCollectionJDBC;
import itucs.blg361e.courseautomation.model.ClassRoom;
import itucs.blg361e.courseautomation.model.ClassRoomCollectionJDBC;
import itucs.blg361e.courseautomation.model.Course;
import itucs.blg361e.courseautomation.model.CourseCollectionJDBC;
import itucs.blg361e.courseautomation.model.Faculty;
import itucs.blg361e.courseautomation.model.FacultyCollectionJDBC;
import itucs.blg361e.courseautomation.model.OpenCourse;
import itucs.blg361e.courseautomation.model.OpenCourseCollectionJDBC;
import itucs.blg361e.courseautomation.model.TeacherCollectionJDBC;
import itucs.blg361e.courseautomation.model.User;
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
        private final DropDownChoice<ClassRoom> classRoomSelect;
        User user = ((CustomSession)getSession()).getUser();
        
        public TeacherOpenCourseForm(String property, OpenCourse iOpenCourse) {
            super(property);
            CompoundPropertyModel model = new CompoundPropertyModel(iOpenCourse);
            this.setModel(model);
            this.add(new TextField("CRN").setRequired(true));
            this.add(new TextField("quota").setRequired(true));
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
            
            
            
            ClassRoomCollectionJDBC crCollection = new ClassRoomCollectionJDBC();
            BuildingCollectionJDBC bCollection = new BuildingCollectionJDBC();
            List<SelectOption> selectChoices2 = new ArrayList<SelectOption>();
            for (ClassRoom nClassRoom : crCollection.getClassRooms()) {
                SelectOption selection2 = new SelectOption(nClassRoom.getId().toString() ,bCollection.getBuilding(nClassRoom.getBuildingID()).getCode() + " - " + nClassRoom.getName());
                selectChoices2.add(selection2);
                if (iOpenCourse.getClass_roomID() == nClassRoom.getId()){
                    iOpenCourse.setClass_room(selection2);
                }
            }
            ChoiceRenderer cr2 = new ChoiceRenderer("value", "key");
            // TODO: Selectbox default value
            classRoomSelect = (DropDownChoice<ClassRoom>) new DropDownChoice("class_room", 
                    new PropertyModel(iOpenCourse,"class_room"), 
                    selectChoices2, 
                    cr2).setRequired(true);
            add(classRoomSelect);
        }

        @Override
        public void onSubmit() {
            OpenCourse formResult = (OpenCourse) getModelObject();
            formResult.setCourseID(Integer.parseInt(formResult.getCourse().getKey()));
            formResult.setClass_roomID(Integer.parseInt(formResult.getCourse().getKey()));
            
            TeacherCollectionJDBC tCollection = new TeacherCollectionJDBC();
            formResult.setTeacherID(tCollection.getTeacher(user.getId()).getTeacherID());
            
            OpenCourseCollectionJDBC oCollection = new OpenCourseCollectionJDBC();
            oCollection.updateOpenCourse(formResult);
            setResponsePage(new MenuPage());
        }
    }