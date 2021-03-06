package org.fhi360.lamis.model;
// Generated Feb 16, 2013 8:46:48 PM by Hibernate Tools 3.2.1.GA

import java.util.Date;

/**
 * Monitor generated by hbm2java
 */
public class Monitor implements java.io.Serializable {

    private long monitorId;
    private Long facilityId;
    private String entityId;
    private String tableName;
    private Integer operationId;
    private Long userId;
    private Date timeStamp;
    private Integer uploaded;
    private Date timeUploaded;
      private String uuid;

    public Monitor() {
    }

    public Monitor(long monitorId, Long facilityId, String entityId, String tableName, Integer operationId) {
        this.monitorId = monitorId;
        this.facilityId = facilityId;
        this.entityId = entityId;
        this.tableName = tableName;
        this.operationId = operationId;
    }

    public Monitor(long monitorId, Long facilityId, String entityId, String tableName, Integer operationId, Long userId, Date timeStamp, Integer uploaded, Date timeUploaded, String uuid) {
        this.monitorId = monitorId;
        this.facilityId = facilityId;
        this.entityId = entityId;
        this.tableName = tableName;
        this.operationId = operationId;
        this.userId = userId;
        this.timeStamp = timeStamp;
        this.uploaded = uploaded;
        this.timeUploaded = timeUploaded;
        this.uuid = uuid;
    }

    public long getMonitorId() {
        return this.monitorId;
    }

    public void setMonitorId(long monitorId) {
        this.monitorId = monitorId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getFacilityId() {
        return this.facilityId;
    }

    public void setFacilityId(Long facilityId) {
        this.facilityId = facilityId;
    }

    public String getEntityId() {
        return this.entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setOperationId(Integer operationId) {
        this.operationId = operationId;
    }

    public Integer getOperationId() {
        return this.operationId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * @return the uploaded
     */
    public Integer getUploaded() {
        return uploaded;
    }

    /**
     * @param uploaded the uploaded to set
     */
    public void setUploaded(Integer uploaded) {
        this.uploaded = uploaded;
    }

    /**
     * @return the timeUploaded
     */
    public Date getTimeUploaded() {
        return timeUploaded;
    }

    /**
     * @param timeUploaded the timeUploaded to set
     */
    public void setTimeUploaded(Date timeUploaded) {
        this.timeUploaded = timeUploaded;
    }

}
