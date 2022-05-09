package com.gbx.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.gbx.pojo.FileMessage;
import com.gbx.pojo.FileType;
import com.gbx.service.FileMessageService;
import com.gbx.service.FileTypeService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * @Author A_xiba
 * @Date 2022/5/9 20:08
 * @Version 1.0
 */
@Controller
@RequestMapping("/file")
public class FileMessageController {
    @Autowired
    private FileMessageService fileMessageService;
    @Autowired
    private FileTypeService fileTypeService;

    @RequestMapping("/upLoad")
    public String upLoad(MultipartFile file, int fid, String filesize, Model model) {
        String picPath = null;
        //判断文件是否为空
        if (!file.isEmpty()) {
            //获取上传文件大小
            double fileSize = Double.valueOf(filesize.substring(0,filesize.length()-1));
            //获取选择的文件结构
            FileType fileType = fileTypeService.getFileTypeById(fid);
            //定义上传目标路径,File.separator:自适应路径分隔符;
            String path = "C:\\maven_courses\\exp\\fileUpLoad\\src\\main\\webapp\\pic\\";
            //获取原文件名
            String oldFileName = file.getOriginalFilename();
            //获取原文件名的后缀
            String prefix = FilenameUtils.getExtension(oldFileName);
            //判断上传文件格式和选择的文件类型是否相同
            if (fileType.getName().equalsIgnoreCase(prefix)) {
                File targetFile = new File(path,oldFileName); //新建文件对象
                //判断是否有此文件
                if (!targetFile.exists()) {
                    //文件不存在就创建
                    targetFile.mkdirs();
                }
                try {
                    //接收用户上传文件流,输出到指定文件里去
                    file.transferTo(targetFile);
                } catch (IOException e) {
                    e.printStackTrace();
                    model.addAttribute("uploadFileError", "上传失败！");
                    return "forward:/add.jsp";
                }
                picPath = path  + oldFileName;
                //然后把picPath路径值保存在数据库
                FileMessage newFile = new FileMessage(null,oldFileName,picPath,new Date(),fileSize,fid,fileType);
                fileMessageService.addFile(newFile);
                //提交事务
                /*sqlSession.commit();*/
                return "redirect:/index.jsp";
            } else {
                model.addAttribute("uploadFileError", "上传文件格式和选择的文件类型不正确！");
                return "forward:/add.jsp";
            }
        }else{
            model.addAttribute("uploadFileError", "上传文件不能为空！");
            return "forward:/add.jsp";
        }
    }

    @RequestMapping("/downLoad")
    public ResponseEntity<byte[]> downLoad(String filename) throws IOException {
        //下载文件的路径(这里绝对路径)
        String filepath= "C:\\maven_courses\\exp\\fileUpLoad\\src\\main\\webapp\\pic\\"+filename;
        File file =new File(filepath);
        //创建字节输入流，这里不实用Buffer类
        InputStream in = new FileInputStream(file);
        //available:获取输入流所读取的文件的最大字节数
        byte[] bytes = new byte[in.available()];
        //把字节读取到数组中
        in.read(bytes);
        //设置请求头
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Content-Disposition", "attchement;filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
        in.close();
        //使用ResponseEntity作为controller的返回值，我们可以方便地处理响应的header，状态码以及body。
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

    @RequestMapping("/readImage")
    public void readImage(HttpServletResponse response, String pathName){
        System.out.println("pathName="+pathName);
        try {
            FileInputStream picture = new FileInputStream(pathName);
            int i = picture.available();
            byte[] data = new byte[i];
            picture.read(data);
            picture.close();
            response.setContentType("img/*");
            response.setCharacterEncoding("utf-8");
            ServletOutputStream out = response.getOutputStream();
            out.write(data);
            out.flush();
            out.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/getFileList",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getFileList() {
        List<FileMessage> fileList = fileMessageService.getAllFiles();
        return JSON.toJSONStringWithDateFormat(fileList,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
    }

    @RequestMapping(value = "/getFileType",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    private String getFileType(){
        List<FileType> fileTypeList = fileTypeService.getFileType();
        return JSON.toJSONString(fileTypeList);
    }

    @RequestMapping(value = "/getFileListByType",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getFileListByType(int fid) {
        List<FileMessage> fileList = fileMessageService.getFileByType(fid);
        return JSON.toJSONStringWithDateFormat(fileList,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
    }
}
