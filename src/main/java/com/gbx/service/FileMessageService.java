package com.gbx.service;

import com.gbx.pojo.FileMessage;

import java.util.List;

/**
 * @Author A_xiba
 * @Date 2022/5/9 20:09
 * @Version 1.0
 */
public interface FileMessageService {
    int addFile(FileMessage file);

    List<FileMessage> getAllFiles();

    FileMessage getFileById(int id);

    List<FileMessage> getFileByType(int fid);

}
