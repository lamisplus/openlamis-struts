package org.fhi360.lamis.model;
// Generated Feb 16, 2013 8:46:48 PM by Hibernate Tools 3.2.1.GA



/**
 * Oi generated by hbm2java
 */
public class Oi  implements java.io.Serializable {


     private long oiId;
     private String description;
     private String clinicStage;

    public Oi() {
    }

	
    public Oi(long oiId, String description) {
        this.oiId = oiId;
        this.description = description;
    }
    public Oi(long oiId, String description, String clinicStage) {
       this.oiId = oiId;
       this.description = description;
       this.clinicStage = clinicStage;
    }
   
    public long getOiId() {
        return this.oiId;
    }
    
    public void setOiId(long oiId) {
        this.oiId = oiId;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public String getClinicStage() {
        return this.clinicStage;
    }
    
    public void setClinicStage(String clinicStage) {
        this.clinicStage = clinicStage;
    }




}

