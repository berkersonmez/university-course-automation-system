/*
 * FooterPanel.java
 *
 * Created on 14 Ekim 2012 Pazar, 12:55
 */
 
package itucs.blg361e.courseautomation;           

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

/** 
 *
 * @author Berker
 * @version 
 */

public final class FooterPanel extends Panel {

    public FooterPanel(String id, String text, WebPage page) {
        super(id);
        Link menuPageLink = new Link("footer_menu_link") {

            @Override
            public void onClick() {
                this.setResponsePage(new MenuPage());
            }
        };
        add(menuPageLink);
        if ((page instanceof MenuPage) || (page instanceof HomePage)) {
            menuPageLink.setVisible(false);
        }
        add(new Label("footerpanel_text", text));
    }

}
