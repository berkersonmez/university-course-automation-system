/*
 * HomePage.java
 *
 * Created on 14 Ekim 2012 Pazar, 12:55
 */

package itucs.blg361e.courseautomation;           

import org.apache.wicket.markup.html.basic.Label;

public class HomePage extends BasePage {

    public HomePage() {
        add(new Label("message", "Hello, World!"));
    }

}
