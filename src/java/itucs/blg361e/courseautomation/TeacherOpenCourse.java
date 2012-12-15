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
        replace(new HeaderPanel("headerpanel","Add Course"));
        OpenCourse nOpenCourse = new OpenCourse();
        add(new TeacherOpenCourseForm("course_add", nOpenCourse) {

            @Override
            public void onSubmit() {
                OpenCourse formResult = (OpenCourse) getModelObject();
                formResult.setCourseID(Integer.parseInt(formResult.getCourse().getKey()));
                OpenCourseCollectionJDBC oCollection = new OpenCourseCollectionJDBC();
                if (oCollection.checkCode(formResult)) {
                    error("Code must be unique!");
                } else {
                    oCollection.addOpenCourse(formResult);
                    setResponsePage(new MenuPage());
                }
            }
            
        });
        
    }
    
    
}
