/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itucs.blg361e.courseautomation;

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
        }
    }

    public CourseDistributePage() {
        replace(new HeaderPanel("headerpanel","Course Distribution"));
        CourseDistributeForm form = new CourseDistributeForm("course_distribute");
        add(form);
        
    }
}
