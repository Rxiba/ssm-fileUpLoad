package com.gbx.service;

import com.gbx.pojo.FileType;

import java.util.List;

/**
 * @Author A_xiba
 * @Date 2022/5/9 20:09
 * @Version 1.0
 */
public interface FileTypeService {

    FileType getFileTypeById(int id);

    List<FileType> getFileType();
}
