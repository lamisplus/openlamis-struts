package org.fhi360.lamis.model;
// Generated Feb 16, 2013 8:46:48 PM by Hibernate Tools 3.2.1.GA

import java.util.Date;

/**
 * Clinic generated by hbm2java
 */
public class Clinic implements java.io.Serializable {

    private long clinicId;
    private Patient patient;
    private long patientId;
    private long facilityId;
    private Date dateVisit;
    private String clinicStage;
    private String funcStatus;
    private String tbStatus;
    private Double viralLoad;
    private Double cd4;
    private Double cd4p;
    private String regimentype;
    private String regimen;
    private Double bodyWeight;
    private Double height;
    private Double waist;
    private String bp;
    private Integer pregnant;
    private Date lmp;
    private Integer breastfeeding;
    private String gestationalAge;
    private String maternalStatusArt;
    private String oiScreened;
    private String oiIds;
    private String stiTreated;
    private String stiIds;
    private String adrScreened;
    private String adrIds;
    private String adherenceLevel;
    private String adhereIds;
    private Integer commence;
    private Date nextAppointment;
    private String notes;
    private Date timeStamp;
    private Long userId;
    private Integer uploaded;
    private Date timeUploaded;
    private Long deviceconfigId;
    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Clinic() {
    }

    public Clinic(long clinicId, Patient patient, long facilityId, Date dateVisit) {
        this.clinicId = clinicId;
        this.patient = patient;
        this.facilityId = facilityId;
        this.dateVisit = dateVisit;
    }

    public Clinic(long clinicId, Patient patient, long patientId, long facilityId, Date dateVisit, String clinicStage, String funcStatus, String tbStatus, Double viralLoad, Double cd4, Double cd4p, String regimentype, String regimen, Double bodyWeight, Double height, Double waist, String bp, Integer pregnant, Date lmp, Integer breastfeeding, String gestationalAge, String maternalStatusArt, String oiScreened, String oiIds, String stiTreated, String stiIds, String adrScreened, String adrIds, String adherenceLevel, String adhereIds, Integer commence, Date nextAppointment, String notes, Date timeStamp, Long userId, Integer uploaded, Date timeUploaded, Long deviceconfigId, String uuid) {
        this.clinicId = clinicId;
        this.patient = patient;
        this.patientId = patientId;
        this.facilityId = facilityId;
        this.dateVisit = dateVisit;
        this.clinicStage = clinicStage;
        this.funcStatus = funcStatus;
        this.tbStatus = tbStatus;
        this.viralLoad = viralLoad;
        this.cd4 = cd4;
        this.cd4p = cd4p;
        this.regimentype = regimentype;
        this.regimen = regimen;
        this.bodyWeight = bodyWeight;
        this.height = height;
        this.waist = waist;
        this.bp = bp;
        this.pregnant = pregnant;
        this.lmp = lmp;
        this.breastfeeding = breastfeeding;
        this.gestationalAge = gestationalAge;
        this.maternalStatusArt = maternalStatusArt;
        this.oiScreened = oiScreened;
        this.oiIds = oiIds;
        this.stiTreated = stiTreated;
        this.stiIds = stiIds;
        this.adrScreened = adrScreened;
        this.adrIds = adrIds;
        this.adherenceLevel = adherenceLevel;
        this.adhereIds = adhereIds;
        this.commence = commence;
        this.nextAppointment = nextAppointment;
        this.notes = notes;
        this.timeStamp = timeStamp;
        this.userId = userId;
        this.uploaded = uploaded;
        this.timeUploaded = timeUploaded;
        this.deviceconfigId = deviceconfigId;
        this.uuid = uuid;
    }

    
    public long getClinicId() {
        return clinicId;
    }

    public void setClinicId(long clinicId) {
        this.clinicId = clinicId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public long getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(long facilityId) {
        this.facilityId = facilityId;
    }

    public Date getDateVisit() {
        return dateVisit;
    }

    public void setDateVisit(Date dateVisit) {
        this.dateVisit = dateVisit;
    }

    public String getClinicStage() {
        return clinicStage;
    }

    public void setClinicStage(String clinicStage) {
        this.clinicStage = clinicStage;
    }

    public String getFuncStatus() {
        return funcStatus;
    }

    public void setFuncStatus(String funcStatus) {
        this.funcStatus = funcStatus;
    }

    public String getTbStatus() {
        return tbStatus;
    }

    public void setTbStatus(String tbStatus) {
        this.tbStatus = tbStatus;
    }

    public Double getViralLoad() {
        return viralLoad;
    }

    public void setViralLoad(Double viralLoad) {
        this.viralLoad = viralLoad;
    }

    public Double getCd4() {
        return cd4;
    }

    public void setCd4(Double cd4) {
        this.cd4 = cd4;
    }

    public Double getCd4p() {
        return cd4p;
    }

    public void setCd4p(Double cd4p) {
        this.cd4p = cd4p;
    }

    public String getRegimentype() {
        return regimentype;
    }

    public void setRegimentype(String regimentype) {
        this.regimentype = regimentype;
    }

    public String getRegimen() {
        return regimen;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    public Double getBodyWeight() {
        return bodyWeight;
    }

    public void setBodyWeight(Double bodyWeight) {
        this.bodyWeight = bodyWeight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWaist() {
        return waist;
    }

    public void setWaist(Double waist) {
        this.waist = waist;
    }

    public String getBp() {
        return bp;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }

    public Integer getPregnant() {
        return pregnant;
    }

    public void setPregnant(Integer pregnant) {
        this.pregnant = pregnant;
    }

    public Date getLmp() {
        return lmp;
    }

    public void setLmp(Date lmp) {
        this.lmp = lmp;
    }

    public Integer getBreastfeeding() {
        return breastfeeding;
    }

    public void setBreastfeeding(Integer breastfeeding) {
        this.breastfeeding = breastfeeding;
    }

    public String getGestationalAge() {
        return gestationalAge;
    }

    public void setGestationalAge(String gestationalAge) {
        this.gestationalAge = gestationalAge;
    }

    public String getMaternalStatusArt() {
        return maternalStatusArt;
    }

    public void setMaternalStatusArt(String maternalStatusArt) {
        this.maternalStatusArt = maternalStatusArt;
    }

    public String getOiScreened() {
        return oiScreened;
    }

    public void setOiScreened(String oiScreened) {
        this.oiScreened = oiScreened;
    }

    public String getOiIds() {
        return oiIds;
    }

    public void setOiIds(String oiIds) {
        this.oiIds = oiIds;
    }

    public String getStiTreated() {
        return stiTreated;
    }

    public void setStiTreated(String stiTreated) {
        this.stiTreated = stiTreated;
    }

    public String getStiIds() {
        return stiIds;
    }

    public void setStiIds(String stiIds) {
        this.stiIds = stiIds;
    }

    public String getAdrScreened() {
        return adrScreened;
    }

    public void setAdrScreened(String adrScreened) {
        this.adrScreened = adrScreened;
    }

    public String getAdrIds() {
        return adrIds;
    }

    public void setAdrIds(String adrIds) {
        this.adrIds = adrIds;
    }

    public String getAdherenceLevel() {
        return adherenceLevel;
    }

    public void setAdherenceLevel(String adherenceLevel) {
        this.adherenceLevel = adherenceLevel;
    }

    public String getAdhereIds() {
        return adhereIds;
    }

    public void setAdhereIds(String adhereIds) {
        this.adhereIds = adhereIds;
    }

    public Integer getCommence() {
        return commence;
    }

    public void setCommence(Integer commence) {
        this.commence = commence;
    }

    public Date getNextAppointment() {
        return nextAppointment;
    }

    public void setNextAppointment(Date nextAppointment) {
        this.nextAppointment = nextAppointment;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getUploaded() {
        return uploaded;
    }

    public void setUploaded(Integer uploaded) {
        this.uploaded = uploaded;
    }

    public Date getTimeUploaded() {
        return timeUploaded;
    }

    public void setTimeUploaded(Date timeUploaded) {
        this.timeUploaded = timeUploaded;
    }

    public Long getDeviceconfigId() {
        return deviceconfigId;
    }

    public void setDeviceconfigId(Long deviceconfigId) {
        this.deviceconfigId = deviceconfigId;
    }

}