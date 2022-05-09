package com.gbx.pojo;

import java.util.Date;

/**
 * @Author A_xiba
 * @Date 2022/5/9 20:04
 * @Version 1.0
 */
public class FileMessage {
    private Integer id;
    private String realName;
    private String pathName;
    private Date uploadTime;
    private double fileSize;
    private Integer fid;
    private FileType fileType;

    public FileMessage() {
    }

    public FileMessage(Integer id, String realName, String pathName, Date uploadTime, double fileSize, Integer fid,FileType fileType) {
        this.id = id;
        this.realName = realName;
        this.pathName = pathName;
        this.uploadTime = uploadTime;
        this.fileSize = fileSize;
        this.fid = fid;
        this.fileType = fileType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public double getFileSize() {
        return fileSize;
    }

    public void setFileSize(double fileSize) {
        this.fileSize = fileSize;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

}