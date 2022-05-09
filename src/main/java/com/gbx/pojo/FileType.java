package com.gbx.pojo;

/**
 * @Author A_xiba
 * @Date 2022/5/9 20:05
 * @Version 1.0
 */
public class FileType {
    private Integer id;
    private String name;

    public FileType() {
    }

    public FileType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}