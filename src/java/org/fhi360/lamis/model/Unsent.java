package org.fhi360.lamis.model;
// Generated Feb 16, 2013 8:46:48 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * Unsent generated by hbm2java
 */
public class Unsent  implements java.io.Serializable {


     private long unsentId;
     private String phone;
     private String message;
     private Date expire;

    public Unsent() {
    }

	
    public Unsent(long unsentId, String phone, String message, Date expire) {
       this.unsentId = unsentId;
       this.phone = phone;
       this.message = message;
       this.expire = expire;
    }
   
    public long getUnsentId() {
        return this.unsentId;
    }
    
    public void setUnsentId(long unsentId) {
        this.unsentId = unsentId;
    }
    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    public Date getExpire() {
        return this.expire;
    }
    
    public void setExpire(Date expire) {
        this.expire = expire;
    }




}

