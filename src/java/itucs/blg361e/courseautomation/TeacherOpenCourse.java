/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itucs.blg361e.courseautomation;

import itucs.blg361e.courseautomation.model.Course;
import itucs.blg361e.courseautomation.model.CourseCollectionJDBC;
import itucs.blg361e.courseautomation.model.OpenCourse;
import itucs.blg361e.courseautomation.model.OpenCourseCollectionJDBC;
import itucs.blg361e.courseautomation.model.StudentCourse;
import itucs.blg361e.courseautomation.model.User;


/**
 *
 * @author Can
 */
public final class TeacherOpenCourse extends BasePage {

    public TeacherOpenCourse() {
        replace(new HeaderPanel("headerpanel","Open Course"));
        OpenCourse nOpenCourse = new OpenCourse();
        add(new TeacherOpenCourseForm("course_add", nOpenCourse));
        
    }
    
    
}
