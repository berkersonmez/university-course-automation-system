/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itucs.blg361e.courseautomation;

import itucs.blg361e.courseautomation.model.Course;
import itucs.blg361e.courseautomation.model.CourseCollectionJDBC;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 *
 * @author Berker
 */
public final class CourseEditPage extends BasePage {

    public CourseEditPage(final PageParameters parameters) {
        replace(new HeaderPanel("headerpanel","Edit Course"));
        Integer courseID = Integer.parseInt(parameters.get("id").toString());
        Course course = new Course();
        course.setId(courseID);
        CourseCollectionJDBC cCollection = new CourseCollectionJDBC();
        course = cCollection.getCourse(course);
        add(new CourseForm("course_edit", course));
    }
}
