/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itucs.blg361e.courseautomation;

import itucs.blg361e.courseautomation.model.CourseCollectionJDBC;
import itucs.blg361e.courseautomation.model.OpenCourse;
import itucs.blg361e.courseautomation.model.OpenCourseCollectionJDBC;
import itucs.blg361e.courseautomation.model.StudentCourse;
import itucs.blg361e.courseautomation.model.StudentCourseCollectionJDBC;
import itucs.blg361e.courseautomation.model.User;
import org.apache.wicket.markup.html.form.Form;
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

        this.add(new TextField("CRN").setRequired(true));
    }

    @Override
    public void onSubmit() {
        StudentCourse nStudentCourse = (StudentCourse) this.getModelObject();
        Application app = (Application) this.getApplication();
        StudentCourseCollectionJDBC nStudentCourseCollectionJDBC = new StudentCourseCollectionJDBC();
        OpenCourseCollectionJDBC nOpenCourseCollectionJDBC = new OpenCourseCollectionJDBC();
        CourseCollectionJDBC nCourseCollectionJDBC = new CourseCollectionJDBC();
        User nUser = ((CustomSession)getSession()).getUser();
        nStudentCourse.setUserID(nUser.getId());
        
        if(nStudentCourseCollectionJDBC.checkCode(nStudentCourse) == true){
            error("ADDING FAILED: (CRN:" + nStudentCourse.getCRN().toString() + ") is already added");
            return;
        }
        else if(nOpenCourseCollectionJDBC.checkCode(nStudentCourse.getCRN()) == false){
            error("ADDING FAILED: (CRN:" + nStudentCourse.getCRN().toString() + ") does not exist");
            return;
        }
        else if(nOpenCourseCollectionJDBC.getOpenCourseByCRN(nStudentCourse.getCRN()).getCurrentStudentCount() >= nOpenCourseCollectionJDBC.getOpenCourseByCRN(nStudentCourse.getCRN()).getQuota()){
            error("ADDING FAILED: (CRN:" + nStudentCourse.getCRN().toString() + ") is FULL");
            return;
        }
        //This Part Checks if the selected time is empty
        String beginTimeString = nOpenCourseCollectionJDBC.getOpenCourseByCRN(nStudentCourse.getCRN()).getBeginTime().toString();
        String endTimeString = nOpenCourseCollectionJDBC.getOpenCourseByCRN(nStudentCourse.getCRN()).getEndTime().toString();
        String dayString = nOpenCourseCollectionJDBC.getOpenCourseByCRN(nStudentCourse.getCRN()).getDay();
        for(StudentCourse sStudentCourse : nStudentCourseCollectionJDBC.getOneStudentsCourses(nUser)){
            String listItemBeginTime = nOpenCourseCollectionJDBC.getOpenCourseByCRN(sStudentCourse.getCRN()).getBeginTime().toString();
            String listItemEndTime = nOpenCourseCollectionJDBC.getOpenCourseByCRN(sStudentCourse.getCRN()).getEndTime().toString();
            String listItemDay = nOpenCourseCollectionJDBC.getOpenCourseByCRN(sStudentCourse.getCRN()).getDay();
            if((dayString.equals(listItemDay)) && (listItemBeginTime.compareTo(beginTimeString) <= 0 && listItemEndTime.compareTo(beginTimeString) > 0 )){
                   error("ADDING FAILED: (CRN:" + nStudentCourse.getCRN().toString() + ") shares same hours with (CRN:" + sStudentCourse.getCRN().toString() + ")");
                   return;
            }
            else if((dayString.equals(listItemDay)) && listItemBeginTime.compareTo(endTimeString) < 0 && listItemEndTime.compareTo(endTimeString) >= 0 ){
                   error("ADDING FAILED: (CRN:" + nStudentCourse.getCRN().toString() + ") shares same hours with (CRN:" + sStudentCourse.getCRN().toString() + ")");
                   return;
            }
        }
        
        //Add new CRN
        OpenCourse openCourse = nOpenCourseCollectionJDBC.getOpenCourseByCRN(nStudentCourse.getCRN());
        openCourse.setCurrentStudentCount(openCourse.getCurrentStudentCount()+1);
        nOpenCourseCollectionJDBC.updateOpenCourse(openCourse);
        nStudentCourseCollectionJDBC.addStudentCourse(nStudentCourse);
        info("(CRN:" + nStudentCourse.getCRN().toString() + ") is successfully added");
        nStudentCourseCollectionJDBC.close();
        nOpenCourseCollectionJDBC.close();
        nCourseCollectionJDBC.close();
        }
    }
