package org.fhi360.lamis.model;
// Generated Feb 16, 2013 8:46:48 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * Delivery generated by hbm2java
 */
public class Delivery  implements java.io.Serializable {

     private long deliveryId;
     private Patient patient;
     private long facilityId;
     private long patientId;
     private long ancId;
     private Integer bookingStatus;
     private Date dateDelivery;
     private String romDeliveryInterval;
     private String modeDelivery;
     private String episiotomy;
     private String vaginalTear;
     private String maternalOutcome;
     private String timeHivDiagnosis;
     private String hepatitisBStatus;
     private String hepatitisCStatus;
     private int gestationalAge;
     private String sourceReferral;
     private Integer screenPostPartum;
     private String arvRegimenPast;
     private String arvRegimenCurrent;
     private Date dateArvRegimenCurrent;
     private Date dateConfirmedHiv;
     private String clinicStage;
     private String cd4Ordered;
     private Double cd4;
     private Long userId;
     private Date timeStamp;
     private Integer uploaded;
     private Date timeUploaded;
      private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

   

    public Delivery() {
    }

	
    public Delivery(Patient patient, long facilityId) {
        this.patient = patient;
        this.facilityId = facilityId;
    }

    public Delivery(long deliveryId, Patient patient, long facilityId, long patientId, long ancId, Integer bookingStatus, Date dateDelivery, String romDeliveryInterval, String modeDelivery, String episiotomy, String vaginalTear, String maternalOutcome, String timeHivDiagnosis, String hepatitisBStatus, String hepatitisCStatus, int gestationalAge, String sourceReferral, Integer screenPostPartum, String arvRegimenPast, String arvRegimenCurrent, Date dateArvRegimenCurrent, Date dateConfirmedHiv, String clinicStage, String cd4Ordered, Double cd4, Long userId, Date timeStamp, Integer uploaded, Date timeUploaded, String uuid) {
        this.deliveryId = deliveryId;
        this.patient = patient;
        this.facilityId = facilityId;
        this.patientId = patientId;
        this.ancId = ancId;
        this.bookingStatus = bookingStatus;
        this.dateDelivery = dateDelivery;
        this.romDeliveryInterval = romDeliveryInterval;
        this.modeDelivery = modeDelivery;
        this.episiotomy = episiotomy;
        this.vaginalTear = vaginalTear;
        this.maternalOutcome = maternalOutcome;
        this.timeHivDiagnosis = timeHivDiagnosis;
        this.hepatitisBStatus = hepatitisBStatus;
        this.hepatitisCStatus = hepatitisCStatus;
        this.gestationalAge = gestationalAge;
        this.sourceReferral = sourceReferral;
        this.screenPostPartum = screenPostPartum;
        this.arvRegimenPast = arvRegimenPast;
        this.arvRegimenCurrent = arvRegimenCurrent;
        this.dateArvRegimenCurrent = dateArvRegimenCurrent;
        this.dateConfirmedHiv = dateConfirmedHiv;
        this.clinicStage = clinicStage;
        this.cd4Ordered = cd4Ordered;
        this.cd4 = cd4;
        this.userId = userId;
        this.timeStamp = timeStamp;
        this.uploaded = uploaded;
        this.timeUploaded = timeUploaded;
        this.uuid = uuid;
    }

    public long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public long getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(long facilityId) {
        this.facilityId = facilityId;
    }

    public long getAncId() {
        return ancId;
    }

    public void setAncId(long ancId) {
        this.ancId = ancId;
    }

    public Integer getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(Integer bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public Date getDateDelivery() {
        return dateDelivery;
    }

    public void setDateDelivery(Date dateDelivery) {
        this.dateDelivery = dateDelivery;
    }

    public String getRomDeliveryInterval() {
        return romDeliveryInterval;
    }

    public void setRomDeliveryInterval(String romDeliveryInterval) {
        this.romDeliveryInterval = romDeliveryInterval;
    }

    public String getModeDelivery() {
        return modeDelivery;
    }

    public void setModeDelivery(String modeDelivery) {
        this.modeDelivery = modeDelivery;
    }

    public String getEpisiotomy() {
        return episiotomy;
    }

    public void setEpisiotomy(String episiotomy) {
        this.episiotomy = episiotomy;
    }

    public String getVaginalTear() {
        return vaginalTear;
    }

    public void setVaginalTear(String vaginalTear) {
        this.vaginalTear = vaginalTear;
    }

    public String getMaternalOutcome() {
        return maternalOutcome;
    }

    public void setMaternalOutcome(String maternalOutcome) {
        this.maternalOutcome = maternalOutcome;
    }

    public String getTimeHivDiagnosis() {
        return timeHivDiagnosis;
    }

    public void setTimeHivDiagnosis(String timeHivDiagnosis) {
        this.timeHivDiagnosis = timeHivDiagnosis;
    }

    public String getHepatitisBStatus() {
        return hepatitisBStatus;
    }

    public void setHepatitisBStatus(String hepatitisBStatus) {
        this.hepatitisBStatus = hepatitisBStatus;
    }

    public String getHepatitisCStatus() {
        return hepatitisCStatus;
    }

    public void setHepatitisCStatus(String hepatitisCStatus) {
        this.hepatitisCStatus = hepatitisCStatus;
    }

    public int getGestationalAge() {
        return gestationalAge;
    }

    public void setGestationalAge(int gestationalAge) {
        this.gestationalAge = gestationalAge;
    }

    public String getSourceReferral() {
        return sourceReferral;
    }

    public void setSourceReferral(String sourceReferral) {
        this.sourceReferral = sourceReferral;
    }

    public Integer getScreenPostPartum() {
        return screenPostPartum;
    }

    public void setScreenPostPartum(Integer screenPostPartum) {
        this.screenPostPartum = screenPostPartum;
    }

    public String getArvRegimenPast() {
        return arvRegimenPast;
    }

    public void setArvRegimenPast(String arvRegimenPast) {
        this.arvRegimenPast = arvRegimenPast;
    }

    public String getArvRegimenCurrent() {
        return arvRegimenCurrent;
    }

    public void setArvRegimenCurrent(String arvRegimenCurrent) {
        this.arvRegimenCurrent = arvRegimenCurrent;
    }

    public Date getDateArvRegimenCurrent() {
        return dateArvRegimenCurrent;
    }

    public void setDateArvRegimenCurrent(Date dateArvRegimenCurrent) {
        this.dateArvRegimenCurrent = dateArvRegimenCurrent;
    }

    public Date getDateConfirmedHiv() {
        return dateConfirmedHiv;
    }

    public void setDateConfirmedHiv(Date dateConfirmedHiv) {
        this.dateConfirmedHiv = dateConfirmedHiv;
    }

    public String getClinicStage() {
        return clinicStage;
    }

    public void setClinicStage(String clinicStage) {
        this.clinicStage = clinicStage;
    }

    public String getCd4Ordered() {
        return cd4Ordered;
    }

    public void setCd4Ordered(String cd4Ordered) {
        this.cd4Ordered = cd4Ordered;
    }

    public Double getCd4() {
        return cd4;
    }

    public void setCd4(Double cd4) {
        this.cd4 = cd4;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
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
    
    
}


