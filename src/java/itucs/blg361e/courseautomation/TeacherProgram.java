/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itucs.blg361e.courseautomation;

import itucs.blg361e.courseautomation.model.Building;
import itucs.blg361e.courseautomation.model.BuildingCollectionJDBC;
import itucs.blg361e.courseautomation.model.ClassRoom;
import itucs.blg361e.courseautomation.model.ClassRoomCollectionJDBC;
import itucs.blg361e.courseautomation.model.OpenCourse;
import itucs.blg361e.courseautomation.model.OpenCourseCollectionJDBC;
import itucs.blg361e.courseautomation.model.StudentCourse;
import itucs.blg361e.courseautomation.model.StudentCourseCollectionJDBC;
import itucs.blg361e.courseautomation.model.Teacher;
import itucs.blg361e.courseautomation.model.TeacherCollectionJDBC;
import itucs.blg361e.courseautomation.model.User;
import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

/**
 *
 * @author Oguzzo
 */
public final class TeacherProgram extends BasePage {

    public TeacherProgram() {
        User user =((CustomSession)getSession()).getUser();
        Teacher teacher = new Teacher("", "", "");
        teacher.setId(user.getId());
         
        replace(new HeaderPanel("headerpanel", "Weekly Program of " + user.getName()));
        
        TeacherCollectionJDBC tCollection = new TeacherCollectionJDBC();
        teacher = tCollection.getTeacher(teacher.getId());
        final OpenCourseCollectionJDBC collectionB = new OpenCourseCollectionJDBC();
        List<OpenCourse> teacherCoursesList = collectionB.getOneTeacherCourses(teacher);
        ListView studentCourseListView = new ListView("crn_list", teacherCoursesList) {
            @Override
            protected void populateItem(ListItem li) {
                OpenCourse nTeacherCourse = (OpenCourse) li.getModelObject();
                
                li.add(new Label("CRN", nTeacherCourse.getCRN().toString()));
                li.add(new Label("Name", nTeacherCourse.getName()));
                li.add(new Label("Code", nTeacherCourse.getCode()));
                li.add(new Label("Day", nTeacherCourse.getDay()));
                li.add(new Label("Begin Time", nTeacherCourse.getBeginTime().toString()));
                li.add(new Label("End Time", nTeacherCourse.getEndTime().toString()));
                
                if (nTeacherCourse.getClassID() == 0) {
                    li.add(new Label("Building", "*NOT ASSIGNED*"));
                    li.add(new Label("Class", "*NOT ASSIGNED*"));
                } else {
                    ClassRoom classRoom = new ClassRoom();
                    classRoom.setId(nTeacherCourse.getClassID());
                    ClassRoomCollectionJDBC crCollection = new ClassRoomCollectionJDBC();
                    classRoom = crCollection.getClassRoom(classRoom);
                    BuildingCollectionJDBC bCollection = new BuildingCollectionJDBC();
                    Building building = bCollection.getBuilding(classRoom.getBuildingID());
                    li.add(new Label("Building", building.getCode()));
                    li.add(new Label("Class", classRoom.getName()));
                }
            }
        };
        this.add(studentCourseListView);
    }
    
}