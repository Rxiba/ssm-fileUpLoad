package com.gbx.service.impl;

import com.gbx.dao.FileMessageMapper;
import com.gbx.pojo.FileMessage;
import com.gbx.service.FileMessageService;

import java.util.List;

/**
 * @Author A_xiba
 * @Date 2022/5/9 20:11
 * @Version 1.0
 */
public class FileMessageServiceImpl implements FileMessageService {

    private FileMessageMapper mapper;

    public void setFileMessageMapper(FileMessageMapper fileMessageMapper) {
        this.mapper = fileMessageMapper;
    }

    public int addFile(FileMessage file) {
        return mapper.addFile(file);
    }

    public List<FileMessage> getAllFiles() {
        return mapper.getAllFiles();
    }

    public FileMessage getFileById(int id) {
        return mapper.getFileById(id);
    }

    public List<FileMessage> getFileByType(int fid) {
        return mapper.getFileByType(fid);
    }
}
