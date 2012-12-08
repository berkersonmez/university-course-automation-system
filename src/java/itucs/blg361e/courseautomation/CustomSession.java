/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itucs.blg361e.courseautomation;

/**
 *
 * @author Oguzzo
 */

import itucs.blg361e.courseautomation.model.User;
import org.apache.wicket.request.Request;
import org.apache.wicket.protocol.http.WebSession;

public class CustomSession extends WebSession {
    private User user;
    
    public CustomSession(Request request){
        super(request);
    }
    
    public User getUser(){
        return user;
    }
    
    public void setUser(User user){
        this.user = user;
    }
    
}
