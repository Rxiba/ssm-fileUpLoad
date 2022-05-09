package com.gbx.service.impl;

import com.gbx.dao.FileTypeMapper;
import com.gbx.pojo.FileType;
import com.gbx.service.FileTypeService;

import java.util.List;

/**
 * @Author A_xiba
 * @Date 2022/5/9 20:11
 * @Version 1.0
 */
public class FileTypeServiceImpl implements FileTypeService {

    private FileTypeMapper mapper;

    public void setFileTypeMapper(FileTypeMapper fileTypeMapper) {
        this.mapper = fileTypeMapper;
    }

    public FileType getFileTypeById(int id) {
        return mapper.getFileTypeById(id);
    }

    public List<FileType> getFileType() {
        return mapper.getFileType();
    }
}
