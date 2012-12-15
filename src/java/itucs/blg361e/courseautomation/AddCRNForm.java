/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itucs.blg361e.courseautomation;

import itucs.blg361e.courseautomation.model.AdminCollectionJDBC;
import itucs.blg361e.courseautomation.model.OpenCourse;
import itucs.blg361e.courseautomation.model.OpenCourseCollectionJDBC;
import itucs.blg361e.courseautomation.model.StudentCollectionJDBC;
import itucs.blg361e.courseautomation.model.StudentCourse;
import itucs.blg361e.courseautomation.model.StudentCourseCollectionJDBC;
import itucs.blg361e.courseautomation.model.TeacherCollectionJDBC;
import itucs.blg361e.courseautomation.model.User;
import itucs.blg361e.courseautomation.model.UserCollectionJDBC;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;

/**
 *
 * @author Oguzzo
 */
public class AddCRNForm extends Form {

    public AddCRNForm(String id, StudentCourse iStudentCourse) {
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
        OpenCourseCollectionJDBC nOpenCourseCollectionJDBC = new OpenCourseCollectionJDBC();
        User nUser = ((CustomSession)getSession()).getUser();
        nStudentCourse.setUserID(nUser.getId());
        
        if(nStudentCourseCollectionJDBC.checkCode(nStudentCourse) == true){
            error("Selected CRN is already added");
            return;
        }
        else if(nOpenCourseCollectionJDBC.checkCode(nStudentCourse.getCRN()) == false){
            error("Selected CRN does not exist");
            return;
        }
        
        nStudentCourseCollectionJDBC.addStudentCourse(nStudentCourse);
           
        }
    }
