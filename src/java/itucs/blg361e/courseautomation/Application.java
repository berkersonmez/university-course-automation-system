/*
 * Application.java
 *
 * Created on 14 Ekim 2012 Pazar, 12:55
 */
 
package itucs.blg361e.courseautomation;           

import org.apache.wicket.request.*;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
/** 
 *
 * @author Berker
 * @version 
 */

public class Application extends WebApplication {

    public Application() {
    }

    public Class getHomePage() {
        return HomePage.class;
    }
    
    @Override
    public Session newSession(Request request, Response response){
        return new CustomSession(request);
    }

}
