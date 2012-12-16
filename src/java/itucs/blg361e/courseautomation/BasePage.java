/*
 * WicketExamplePage.java
 *
 * Created on 14 Ekim 2012 Pazar, 12:55
 */
 
package itucs.blg361e.courseautomation;           

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

/** 
 *
 * @author Berker
 * @version 
 */

public abstract class BasePage extends WebPage {

    public BasePage() { 
        super(); 
        add(new HeaderPanel("headerpanel", "Welcome To Wicket")); 
        add(new FeedbackPanel("feedbackPanel"));
        add(new FooterPanel("footerpanel", "Powered by Wicket and the NetBeans Wicket Plugin", this));
    } 

}
