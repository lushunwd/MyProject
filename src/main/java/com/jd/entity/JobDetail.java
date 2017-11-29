package com.jd.entity;

/**
 * Created by lushun on 2017/11/29.
 */

public class JobDetail {
    private Long id;
    private String prjKey;
    private String prjName;
    private String buildNo;
    private String duration;
    private String totalCount;
    private String failCount;
    private String skipCount;
    private String passCount;
    private String classCount;
    private String batchNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getBatchNo() {
        return batchNo;
    }
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }





    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getPrjKey() {
        return prjKey;
    }

    public void setPrjKey(String prjKey) {
        this.prjKey = prjKey;
    }

    public String getPrjName() {
        return prjName;
    }

    public void setPrjName(String prjName) {
        this.prjName = prjName;
    }

    public String getBuildNo() {
        return buildNo;
    }

    public void setBuildNo(String buildNo) {
        this.buildNo = buildNo;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getFailCount() {
        return failCount;
    }

    public void setFailCount(String failCount) {
        this.failCount = failCount;
    }

    public String getSkipCount() {
        return skipCount;
    }

    public void setSkipCount(String skipCount) {
        this.skipCount = skipCount;
    }

    public String getPassCount() {
        return passCount;
    }

    public void setPassCount(String passCount) {
        this.passCount = passCount;
    }

    public String getClassCount() {
        return classCount;
    }

    public void setClassCount(String classCount) {
        this.classCount = classCount;
    }
}
