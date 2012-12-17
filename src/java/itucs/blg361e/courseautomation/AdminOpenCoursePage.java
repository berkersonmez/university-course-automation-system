/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itucs.blg361e.courseautomation;

import itucs.blg361e.courseautomation.model.OpenCourse;


/**
 *
 * @author Oguzzo
 */
public final class AdminOpenCoursePage extends BasePage {

    public AdminOpenCoursePage() {
        replace(new HeaderPanel("headerpanel","Open Course"));
        OpenCourse nOpenCourse = new OpenCourse();
        add(new AdminOpenCourseForm("course_add", nOpenCourse));
        
    }
    
    
}
