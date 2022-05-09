package com.gbx.dao;

import com.gbx.pojo.FileType;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author A_xiba
 * @Date 2022/5/9 20:06
 * @Version 1.0
 */
public interface FileTypeMapper {
    /**
     * 根据id查询fileType
     * @param id 文件类型编号
     * @return  FileType对象
     */
    @Select("select * from filetype where id = #{fid}")
    @Results(value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "name",property = "name"),
    })
    FileType getFileTypeById(@Param("fid") int id);

    /**
     * 查询所有filetype
     * @return  FileType列表
     */
    @Select("select * from filetype")
    List<FileType> getFileType();

}
