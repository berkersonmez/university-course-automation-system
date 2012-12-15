/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itucs.blg361e.courseautomation;

import itucs.blg361e.courseautomation.model.StudentCourse;
import itucs.blg361e.courseautomation.model.StudentCourseCollectionJDBC;
import itucs.blg361e.courseautomation.model.User;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;

/**
 *
 * @author Oguzzo
 */
public class DropCRNForm extends Form {

    public DropCRNForm(String id, StudentCourse iStudentCourse) {
        super(id);

        CompoundPropertyModel model = new CompoundPropertyModel(iStudentCourse);
        this.setModel(model);

        this.add(new TextField("CRN"));
    }

    @Override
    public void onSubmit() {
        StudentCourse nStudentCourse = (StudentCourse) this.getModelObject();
        Application app = (Application) this.getApplication();
        StudentCourseCollectionJDBC nStudentCourseCollectionJDBC = new StudentCourseCollectionJDBC();
        User nUser = ((CustomSession)getSession()).getUser();
        nStudentCourse.setUserID(nUser.getId());
     
        nStudentCourseCollectionJDBC.deleteStudentCourse(nStudentCourse);
          
        }
    }
