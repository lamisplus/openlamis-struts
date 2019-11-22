package org.fhi360.lamis.model;
// Generated Feb 16, 2013 8:46:48 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Regimen generated by hbm2java
 */
public class Regimen  implements java.io.Serializable {


     private long regimenId;
     private Regimentype regimentype;
     private String description;
     private String composition;
     private Set<Regimendrug> regimendrugs = new HashSet<Regimendrug>(0);

    public Regimen() {
    }

	
    public Regimen(long regimenId, Regimentype regimentype, String description, String composition) {
        this.regimenId = regimenId;
        this.regimentype = regimentype;
        this.description = description;
        this.composition = composition;
    }
    public Regimen(long regimenId, Regimentype regimentype, String description, String composition, Set<Regimendrug> regimendrugs) {
       this.regimenId = regimenId;
       this.regimentype = regimentype;
       this.description = description;
       this.composition = composition;
       this.regimendrugs = regimendrugs;
    }
   
    public long getRegimenId() {
        return this.regimenId;
    }
    
    public void setRegimenId(long regimenId) {
        this.regimenId = regimenId;
    }
    public Regimentype getRegimentype() {
        return this.regimentype;
    }
    
    public void setRegimentype(Regimentype regimentype) {
        this.regimentype = regimentype;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public String getComposition() {
        return this.composition;
    }
    
    public void setComposition(String composition) {
        this.composition = composition;
    }
    public Set<Regimendrug> getRegimendrugs() {
        return this.regimendrugs;
    }
    
    public void setRegimendrugs(Set<Regimendrug> regimendrugs) {
        this.regimendrugs = regimendrugs;
    }




}

