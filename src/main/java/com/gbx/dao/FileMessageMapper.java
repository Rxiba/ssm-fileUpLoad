package com.gbx.dao;

import com.gbx.pojo.FileMessage;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @Author A_xiba
 * @Date 2022/5/9 20:05
 * @Version 1.0
 */
public interface FileMessageMapper {
    /**
     * 上传FileMessage，附带文件类型
     * @param file 对象
     * @return  影响行数
     */
    @Insert("insert into filemessage(real_name,path_name,upload_time,file_size,filetype_id) values(#{realName},#{pathName},#{uploadTime},#{fileSize},#{fid})")
    int addFile(FileMessage file);

    /**
     * 查询所有FileMessage，附带文件类型
     * @return  FileMessage列表
     */
    @Select("select * from filemessage")
    @Results(id = "fileTypeUserMap",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "real_name",property = "realName"),
            @Result(column = "path_name",property = "pathName"),
            @Result(column = "upload_time",property = "uploadTime"),
            @Result(column = "file_size",property = "fileSize"),
            @Result(column = "filetype_id",property = "fid"),
            @Result(property = "fileType",column = "filetype_id",one = @One(select = "com.gbx.dao.FileTypeMapper.getFileTypeById",fetchType = FetchType.DEFAULT)),
    })
    List<FileMessage> getAllFiles();

    /**
     * 根据编号查询一个FileMessage
     * @return  FileMessage对象
     */
    @Select("select * from filemessage where id=#{id}")
    @ResultMap("fileTypeUserMap")
    FileMessage getFileById(@Param("id") int id);

    /**
     * 查询所有FileMessage，附带文件类型（按照文件类型查询）
     * @return  FileMessage列表
     */
    @Select("select * from filemessage where filetype_id=#{fid}")
    @ResultMap("fileTypeUserMap")
    List<FileMessage> getFileByType(@Param("fid") int fid);

}
