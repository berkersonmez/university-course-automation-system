/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itucs.blg361e.courseautomation;

import itucs.blg361e.courseautomation.model.Building;
import itucs.blg361e.courseautomation.model.BuildingCollectionJDBC;
import itucs.blg361e.courseautomation.model.ClassRoom;
import itucs.blg361e.courseautomation.model.ClassRoomCollectionJDBC;
import itucs.blg361e.courseautomation.model.Course;
import itucs.blg361e.courseautomation.model.OpenCourse;
import itucs.blg361e.courseautomation.model.OpenCourseCollectionJDBC;
import itucs.blg361e.courseautomation.utility.SelectOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 *
 * @author Berker
 */
public final class CourseDistributePage extends BasePage {
    
    class CourseDistributeForm extends Form {
        
        private DropDownChoice<Course> ddc;
        public CourseDistributeForm(String property) {
            super(property);
        }

        @Override
        public void onSubmit() {
            OpenCourseCollectionJDBC ocCollection = new OpenCourseCollectionJDBC();
            List<OpenCourse> openCourseList = ocCollection.getOpenCoursesJoinCourses();
            ClassRoomCollectionJDBC crCollection = new ClassRoomCollectionJDBC();
            Map<OpenCourse, ClassRoom> successfulCourses = new HashMap<OpenCourse, ClassRoom>();
            List<OpenCourse> failedCourses = new ArrayList<OpenCourse>();
            for (OpenCourse oc : openCourseList) {
                ClassRoom classroom = crCollection.findEmptyClassroomOnSameFaculty(oc);
                if (classroom != null) {
                    oc.setClassID(classroom.getId());
                    ocCollection.updateOpenCourseClass(oc);
                    successfulCourses.put(oc, classroom);
                    continue;
                }
                classroom = crCollection.findEmptyClassroomOnDifferentFaculty(oc);
                if (classroom != null) {
                    oc.setClassID(classroom.getId());
                    ocCollection.updateOpenCourseClass(oc);
                    successfulCourses.put(oc, classroom);
                    continue;
                }
                failedCourses.add(oc);
            }
            if (successfulCourses.isEmpty()) {
                warn("No class is distributed successfully.");
            } else {
                for (Map.Entry<OpenCourse, ClassRoom> entry : successfulCourses.entrySet()) {
                    info("Course '("+entry.getKey().getCode()+") "+entry.getKey().getName()+"' is given the classroom "
                            + "'" + entry.getValue().getName() + "'");
                }
            }
            if (failedCourses.isEmpty()) {
                warn("No class is failed to be distributed.");
            } else {
                for (OpenCourse entry : failedCourses) {
                    error("Course '("+entry.getCode()+") "+entry.getName()+"' could not be placed to a classroom!");
                }
            }
            refreshList();
        }
    }

    public CourseDistributePage() {
        replace(new HeaderPanel("headerpanel","Course Distribution"));
        CourseDistributeForm form = new CourseDistributeForm("course_distribute");
        add(form);
        
        OpenCourseCollectionJDBC ocCollection = new OpenCourseCollectionJDBC();
        List<OpenCourse> openCourses = ocCollection.getAllOpenCourses();
        ListView openCourseListView = new ListView("crn_list", openCourses) {
            @Override
            protected void populateItem(ListItem li) {
                OpenCourse openCourse = (OpenCourse) li.getModelObject();
                
                li.add(new Label("CRN", openCourse.getCRN().toString()));
                li.add(new Label("Name", openCourse.getName()));
                li.add(new Label("Code", openCourse.getCode()));
                li.add(new Label("Day", openCourse.getDay()));
                li.add(new Label("Begin Time", openCourse.getBeginTime().toString()));
                li.add(new Label("End Time", openCourse.getEndTime().toString()));
                
                if (openCourse.getClassID() == 0) {
                    li.add(new Label("Building", "*NOT ASSIGNED*"));
                    li.add(new Label("Class", "*NOT ASSIGNED*"));
                } else {
                    ClassRoom classRoom = new ClassRoom();
                    classRoom.setId(openCourse.getClassID());
                    ClassRoomCollectionJDBC crCollection = new ClassRoomCollectionJDBC();
                    classRoom = crCollection.getClassRoom(classRoom);
                    BuildingCollectionJDBC bCollection = new BuildingCollectionJDBC();
                    Building building = bCollection.getBuilding(classRoom.getBuildingID());
                    li.add(new Label("Building", building.getCode()));
                    li.add(new Label("Class", classRoom.getName()));
                }
            }
        };
        this.add(openCourseListView);
    }
    
    public void refreshList() {
        OpenCourseCollectionJDBC ocCollection = new OpenCourseCollectionJDBC();
        List<OpenCourse> openCourses = ocCollection.getAllOpenCourses();
        ListView openCourseListView = new ListView("crn_list", openCourses) {
            @Override
            protected void populateItem(ListItem li) {
                OpenCourse openCourse = (OpenCourse) li.getModelObject();
                
                li.add(new Label("CRN", openCourse.getCRN().toString()));
                li.add(new Label("Name", openCourse.getName()));
                li.add(new Label("Code", openCourse.getCode()));
                li.add(new Label("Day", openCourse.getDay()));
                li.add(new Label("Begin Time", openCourse.getBeginTime().toString()));
                li.add(new Label("End Time", openCourse.getEndTime().toString()));
                
                if (openCourse.getClassID() == 0) {
                    li.add(new Label("Building", "*NOT ASSIGNED*"));
                    li.add(new Label("Class", "*NOT ASSIGNED*"));
                } else {
                    ClassRoom classRoom = new ClassRoom();
                    classRoom.setId(openCourse.getClassID());
                    ClassRoomCollectionJDBC crCollection = new ClassRoomCollectionJDBC();
                    classRoom = crCollection.getClassRoom(classRoom);
                    BuildingCollectionJDBC bCollection = new BuildingCollectionJDBC();
                    Building building = bCollection.getBuilding(classRoom.getBuildingID());
                    li.add(new Label("Building", building.getCode()));
                    li.add(new Label("Class", classRoom.getName()));
                }
            }
        };
        this.replace(openCourseListView);
    }
}
