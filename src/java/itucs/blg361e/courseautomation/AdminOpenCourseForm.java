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
import itucs.blg361e.courseautomation.model.OpenCourse;
import itucs.blg361e.courseautomation.model.OpenCourseCollectionJDBC;
import itucs.blg361e.courseautomation.model.TeacherCollectionJDBC;
import itucs.blg361e.courseautomation.model.User;
import itucs.blg361e.courseautomation.utility.SelectOption;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;

/**
 *
 * @author Oguzzo
 */
class AdminOpenCourseForm extends Form {
        private final DropDownChoice<Course> courseSelect;
        private final DropDownChoice<ClassRoom> classRoomSelect;
        private final DropDownChoice<String> daySelect;
        User user = ((CustomSession)getSession()).getUser();
        
        public AdminOpenCourseForm(String property, OpenCourse iOpenCourse) {
            super(property);
            CompoundPropertyModel model = new CompoundPropertyModel(iOpenCourse);
            this.setModel(model);
            this.add(new TextField("CRN").setRequired(true));
            this.add(new TextField("quota").setRequired(true));
            this.add(new TextField("teacherID").setRequired(true));
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
                    selectChoices, 
                    cr).setRequired(true);
            add(courseSelect);
            
            
            List<SelectOption> selectChoices3 = new ArrayList<SelectOption>();
            selectChoices3.add(new SelectOption("Mon" ,"Monday"));
            selectChoices3.add(new SelectOption("Tue" ,"Tuesday"));
            selectChoices3.add(new SelectOption("Wed" ,"Wednesday"));
            selectChoices3.add(new SelectOption("Thu" ,"Thursday"));
            selectChoices3.add(new SelectOption("Fri" ,"Friday"));
            // TODO: Selectbox default value
            daySelect = (DropDownChoice<String>) new DropDownChoice("daySelect",
                    selectChoices3, 
                    cr).setRequired(true);
            add(daySelect);
            
            
            this.add(new TextField("beginTimeInString").setRequired(true));
            this.add(new TextField("endTimeInString").setRequired(true));
            
            ClassRoomCollectionJDBC crCollection = new ClassRoomCollectionJDBC();
            BuildingCollectionJDBC bCollection = new BuildingCollectionJDBC();
            List<SelectOption> selectChoices2 = new ArrayList<SelectOption>();
            for (ClassRoom nClassRoom : crCollection.getClassRooms()) {
                SelectOption selection2 = new SelectOption(nClassRoom.getId().toString() ,bCollection.getBuilding(nClassRoom.getBuildingID()).getCode() + " - " + nClassRoom.getName());
                selectChoices2.add(selection2);
                if (iOpenCourse.getClassID() == nClassRoom.getId()){
                    iOpenCourse.setClass_room(selection2);
                }
            }
            ChoiceRenderer cr2 = new ChoiceRenderer("value", "key");
            // TODO: Selectbox default value
            classRoomSelect = (DropDownChoice<ClassRoom>) new DropDownChoice("class_room",
                    selectChoices2, 
                    cr2).setRequired(false);
            add(classRoomSelect);
            cCollection.close();
            crCollection.close();
            bCollection.close();
        }

        @Override
        public void onSubmit() {
            OpenCourse formResult = (OpenCourse) getModelObject();
            formResult.setCourseID(Integer.parseInt(formResult.getCourse().getKey()));
            if (formResult.getClass_room() == null) {
                formResult.setClassID(0);
            } else {
                formResult.setClassID(Integer.parseInt(formResult.getClass_room().getKey()));
            }
            formResult.setBeginTime(Time.valueOf(formResult.getBeginTimeInString()));
            formResult.setEndTime(Time.valueOf(formResult.getEndTimeInString()));
            formResult.setDay(formResult.getDaySelect().getKey());
            
            TeacherCollectionJDBC tCollection = new TeacherCollectionJDBC();
            
            OpenCourseCollectionJDBC oCollection = new OpenCourseCollectionJDBC();
            if(oCollection.checkCode(formResult.getCRN())){
                error("(CRN: " + formResult.getCRN().toString() + ") already exists");
                return;
            }
            if (!oCollection.isTeacherAvailable(formResult)) {
                error(formResult.getTeacherID().toString() + "(" + tCollection.getTeacherByTeacherId(formResult.getTeacherID()) + ") is not available for given timespan!");
                return;
            }
            
            if (formResult.getClassID() != 0) {
                ClassRoom selectedClassroom = new ClassRoom();
                selectedClassroom.setId(formResult.getClassID());
                ClassRoomCollectionJDBC crCollection = new ClassRoomCollectionJDBC();
                selectedClassroom = crCollection.getClassRoom(selectedClassroom);
                if (!(selectedClassroom.getQuota() >= formResult.getQuota())) {
                    error("Classroom quota is not high enough!");
                    return;
                }
                if (!crCollection.isClassRoomAvailable(formResult)) {
                    error("Classroom is not available for given timespan!");
                    return;
                }
                crCollection.close();
            }
               
            oCollection.addOpenCourse(formResult);
            tCollection.close();
            oCollection.close();
            getSession().info("Course is opened successfully!");
            setResponsePage(new MenuPage());
        }
    }