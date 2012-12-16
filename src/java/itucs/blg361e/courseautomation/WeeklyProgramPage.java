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
import itucs.blg361e.courseautomation.model.User;
import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

/**
 *
 * @author Oguzzo
 */
public final class WeeklyProgramPage extends BasePage {

    public WeeklyProgramPage() {
        User user = ((CustomSession)getSession()).getUser();
        replace(new HeaderPanel("headerpanel", "Weekly Program of " + user.getName()));
        
        StudentCourseCollectionJDBC collectionA = new StudentCourseCollectionJDBC();
        final OpenCourseCollectionJDBC collectionB = new OpenCourseCollectionJDBC();
        List<StudentCourse> studentCourseList = collectionA.getOneStudentsCourses(user);
        ListView studentCourseListView = new ListView("crn_list", studentCourseList) {
            @Override
            protected void populateItem(ListItem li) {
                StudentCourse nStudentCourse = (StudentCourse) li.getModelObject();
                OpenCourse nOpenCourse = collectionB.getOpenCourseJoinCourseByCRN(nStudentCourse.getCRN());
                ClassRoom classRoom = new ClassRoom();
                classRoom.setId(nOpenCourse.getClassID());
                ClassRoomCollectionJDBC crCollection = new ClassRoomCollectionJDBC();
                classRoom = crCollection.getClassRoom(classRoom);
                BuildingCollectionJDBC bCollection = new BuildingCollectionJDBC();
                Building building = bCollection.getBuilding(classRoom.getBuildingID());
                li.add(new Label("CRN", nOpenCourse.getCRN().toString()));
                li.add(new Label("Name", nOpenCourse.getName()));
                li.add(new Label("Code", nOpenCourse.getCode()));
                li.add(new Label("Day", nOpenCourse.getDay()));
                li.add(new Label("Begin Time", nOpenCourse.getBeginTime().toString()));
                li.add(new Label("End Time", nOpenCourse.getEndTime().toString()));
                li.add(new Label("Building", building.getCode()));
                li.add(new Label("Class", classRoom.getName()));
            }
        };
        this.add(studentCourseListView);
    }
    
}