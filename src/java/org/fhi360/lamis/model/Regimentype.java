package org.fhi360.lamis.model;
// Generated Feb 16, 2013 8:46:48 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Regimentype generated by hbm2java
 */
public class Regimentype  implements java.io.Serializable {


     private long regimentypeId;
     private String description;
     private Set<Regimen> regimens = new HashSet<Regimen>(0);

    public Regimentype() {
    }

	
    public Regimentype(long regimentypeId, String description) {
        this.regimentypeId = regimentypeId;
        this.description = description;
    }
    public Regimentype(long regimentypeId, String description, Set<Regimen> regimens) {
       this.regimentypeId = regimentypeId;
       this.description = description;
       this.regimens = regimens;
    }
   
    public long getRegimentypeId() {
        return this.regimentypeId;
    }
    
    public void setRegimentypeId(long regimentypeId) {
        this.regimentypeId = regimentypeId;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public Set<Regimen> getRegimens() {
        return this.regimens;
    }
    
    public void setRegimens(Set<Regimen> regimens) {
        this.regimens = regimens;
    }




}


