package ssf.day15_ws.models;

import jakarta.validation.constraints.*;

public class Contact {

    private String id;
    private String name;

    @Size(min=7, message="Phone number has to be at least 7 digits")
    @Pattern(regexp="\\d+", message="Phone number should only contain digits")
    private String phoneNum;

    @Email
    private String email;

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getPhoneNum() {return phoneNum;}
    public void setPhoneNum(String phoneNum) {this.phoneNum = phoneNum;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    
    @Override
    public String toString() {
        return "Contact [id=" + id + ", name=" + name + ", phoneNum=" + phoneNum + ", email=" + email + "]";
    }
   
    
}
