/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itucs.blg361e.courseautomation;

import itucs.blg361e.courseautomation.model.User;
import org.apache.wicket.markup.html.WebPage;

/**
 *
 * @author Oguzzo
 */
public final class MenuPage extends BasePage {

    public MenuPage() {
        User user = ((CustomSession)getSession()).getUser();
        replace(new HeaderPanel("headerpanel", "Welcome " + user.getName()));
        
    }
    
    
}
