/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itucs.blg361e.courseautomation;

import itucs.blg361e.courseautomation.model.Course;
import itucs.blg361e.courseautomation.model.CourseCollectionJDBC;

/**
 *
 * @author Berker
 */
public final class CourseAddPage extends BasePage {

    public CourseAddPage() {
        replace(new HeaderPanel("headerpanel","Add Course"));
        Course course = new Course();
        add(new CourseForm("course_add", course) {

            @Override
            public void onSubmit() {
                Course formResult = (Course) getModelObject();
                formResult.setFacultyID(Integer.parseInt(formResult.getFaculty().getKey()));
                CourseCollectionJDBC cCollection = new CourseCollectionJDBC();
                if (cCollection.checkCode(formResult)) {
                    error("Code must be unique!");
                } else {
                    cCollection.addCourse(formResult);
                    setResponsePage(new MenuPage());
                }
                cCollection.close();
            }
            
        });
    }
}
