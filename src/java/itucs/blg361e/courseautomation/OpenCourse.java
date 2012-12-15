/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itucs.blg361e.courseautomation;

import itucs.blg361e.courseautomation.model.StudentCourse;
import itucs.blg361e.courseautomation.model.User;


/**
 *
 * @author Can
 */
public final class OpenCourse extends BasePage {

    public OpenCourse() {
        User user = ((CustomSession)getSession()).getUser();
        replace(new HeaderPanel("headerpanel", "Add / Drop " + user.getName()));
        add(new AddCRNForm("add_drop", new StudentCourse()));
        
        
    }
    
    
}
