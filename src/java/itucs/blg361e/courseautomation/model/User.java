package itucs.blg361e.courseautomation.model;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Berker
 */
public class User {

    
    protected Integer id = null;
    protected String name;
    protected String username;
    protected String password;
    private String email;
    protected Integer type;
    private Long phone;
    public static final int TYPE_ADMIN= 1;
    public static final int TYPE_TEACHER= 2;
    public static final int TYPE_STUDENT= 3;
    
    public User(){
        
    }
    
    public User(String name, String username, String password, String email, Integer type, Long phone) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.type = type;
        this.phone = phone;
    }
    
    public User(String nName, String nUsername, String nPassword) {
        username = nUsername;
        name = nName;
        password = nPassword;
    }

    public void setId(Integer newId) {
        this.id = newId;
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setUsername(String newUsername) {
        this.username = newUsername;
    }

    public String getUsername() {
        return this.username;
    }
    
    public void setName(String newName) {
        this.name = newName;
    }

    public String getName() {
        return this.name;
    }
    
    public void setPasswordDirectly(String newPassword){
        this.password = newPassword;
    }
    
    public void setPassword(String newPassword){
        this.password = newPassword;
    }

    public String getPassword() {
        return this.password;
    }
    
    public String preparePassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest mdEnc = MessageDigest.getInstance("MD5"); // Encryption algorithm
        mdEnc.update(password.getBytes("UTF-8"), 0, password.length());
        String md5 = new BigInteger(1, mdEnc.digest()).toString(16); // Encrypted string
        return md5;
    }
    
    public void setType(int i){
        type = i;
    }
    
    public int getType(){
        return type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }
}
