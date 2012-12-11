/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itucs.blg361e.courseautomation;

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
        
        public MenuObject(String label, BasePage link) {
            this.label = label;
            this.link = link;
        }
        
        public String getLabel() {
            return this.label;
        }
        
        public BasePage getLink() {
            return this.link;
        }
    }

    public MenuPage() {
        User user = ((CustomSession)getSession()).getUser();
        replace(new HeaderPanel("headerpanel", "Welcome " + user.getName()));
        
        List<MenuObject> menuList= new LinkedList<MenuObject>();
        if (user.getType() == User.TYPE_ADMIN) {
            menuList.add(new MenuObject("Edit Course", new CourseEditPage()));
        } else if (user.getType() == User.TYPE_STUDENT) {
            // Student menu
        } else if (user.getType() == User.TYPE_TEACHER) {
            // Teacher menu
        }
        

        ListView menuListView = new ListView("menu", menuList) {
            @Override
            protected void populateItem(ListItem li) {
                final MenuObject menuObj = (MenuObject) li.getModelObject();
                Link link = new Link("menu_link") {

                    @Override
                    public void onClick() {
                        this.setResponsePage(menuObj.getLink());
                    }
                };
                li.add(link.add(new Label("menu_label", menuObj.getLabel())));
            }
        };
        this.add(menuListView);
    }
    
    
}
