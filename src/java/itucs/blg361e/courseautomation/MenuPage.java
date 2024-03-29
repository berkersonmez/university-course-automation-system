/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itucs.blg361e.courseautomation;

import itucs.blg361e.courseautomation.model.Options;
import itucs.blg361e.courseautomation.model.OptionsCollectionJDBC;
import itucs.blg361e.courseautomation.model.User;
import java.util.LinkedList;
import java.util.List;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

/**
 *
 * @author Oguzzo
 */
public final class MenuPage extends BasePage {
    
    public class MenuObject {

        private String label;
        private BasePage link;
        private boolean disabled;
        
        public MenuObject(String label, BasePage link) {
            this.label = label;
            this.link = link;
            this.disabled = false;
        }
        
        public MenuObject(String label, BasePage link, boolean disabled) {
            this.label = label;
            this.link = link;
            this.disabled = disabled;
        }
        
        public String getLabel() {
            return this.label;
        }
        
        public BasePage getLink() {
            return this.link;
        }
        
        public boolean isDisabled() {
            return disabled;
        }
    }

    public MenuPage() {
        User user = ((CustomSession)getSession()).getUser();
        replace(new HeaderPanel("headerpanel", "Welcome " + user.getName()));
        
        List<MenuObject> menuList= new LinkedList<MenuObject>();
        OptionsCollectionJDBC oCollection = new OptionsCollectionJDBC();
        Options options = oCollection.getOptions();
        if (user.getType() == User.TYPE_ADMIN) {
            menuList.add(new MenuObject("Add Student", new StudentAddPage()));
            menuList.add(new MenuObject("Delete Student", new DeleteStudentPage()));
            menuList.add(new MenuObject("Add Teacher", new AddTeacherPage()));
            menuList.add(new MenuObject("Delete Teacher", new DeleteTeacherPage()));
            menuList.add(new MenuObject("Add Course", new CourseAddPage()));
            menuList.add(new MenuObject("Add Open Course", new AdminOpenCoursePage()));
            menuList.add(new MenuObject("Edit / Delete Course", new CourseSelectPage()));
            menuList.add(new MenuObject("List / Distribute Courses", new CourseDistributePage()));
            menuList.add(new MenuObject("Edit Admin", new UserEditPage()));
            menuList.add(new MenuObject("Options", new OptionsPage()));
            
        } else if (user.getType() == User.TYPE_STUDENT) {
           menuList.add(new MenuObject("Add / Drop", new AddDropPage(), !options.isIsAddDrop()));
           menuList.add(new MenuObject("Weekly Program", new WeeklyProgramPage()));
           menuList.add(new MenuObject("Edit Student", new UserEditPage()));
           
        } else if (user.getType() == User.TYPE_TEACHER) {
            menuList.add(new MenuObject("Open Course", new TeacherOpenCourse()));
            menuList.add(new MenuObject("Weekly Program", new TeacherProgram()));
            menuList.add(new MenuObject("Edit Teacher", new UserEditPage()));
            
        }
        oCollection.close();
        

        ListView menuListView = new ListView("menu", menuList) {
            @Override
            protected void populateItem(ListItem li) {
                final MenuObject menuObj = (MenuObject) li.getModelObject();
                Link link = new Link("menu_link") {

                    @Override
                    public void onClick() {
                        if (menuObj.isDisabled()) {
                            getSession().error("You can't do that at this time!");
                        } else {
                            this.setResponsePage(menuObj.getLink());
                        }
                    }
                };
                
                li.add(link.add(new Label("menu_label", menuObj.getLabel())));
            }
        };
        this.add(menuListView);
        
        Link logoutLink = new Link("logout_link") {

            @Override
            public void onClick() {
                getSession().invalidateNow();
                this.setResponsePage(HomePage.class);
            }
        
        };
        this.add(logoutLink);
    }
    
    
}
