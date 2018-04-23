/*
 * All Rights Reserved.FileName: User.java@author: Hacket@date: 18-4-9 下午1:36@version: 1.0
 */

/*
 * All Rights Reserved.FileName: User.java@author: Hacket@date: 18-4-9 上午12:07@version: 1.0
 */

package com.example.a10146.demo2;

import java.io.Serializable;

public class User implements Serializable {
    private int Id;
    private int UserType;
    private String NickName = "";
    private transient String Password;
    private String EmailAddress = "";
    private String PhoneNumber;

    public int getId() {return Id;}
    public void setId(int id) {this.Id = id;}
    public String getNickName() {return NickName;}
    public void setNickName(String nickName) {this.NickName = nickName;}
    public String getPassword() {return Password;}
    public void setPassword(String password) {this.Password = password;}
    public String getEmailAddress() {return EmailAddress;}
    public void setEmailAddress(String emailAddress) {EmailAddress = emailAddress;}
    public String getPhoneNumber() {return PhoneNumber;}
    public void setPhoneNumber(String phoneNumber) {this.PhoneNumber = phoneNumber;}
    public int getUserType() {return UserType;}
    public void setUserType(int userType) {this.UserType = userType;}
}
