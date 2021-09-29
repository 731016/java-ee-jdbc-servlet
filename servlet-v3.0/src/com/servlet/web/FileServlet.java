package com.servlet.web;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.servlet.service.EmployeeService;
import com.servlet.service.impl.EmployeeServiceImpl;
import lombok.Data;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
/*
    处理图片名称，上传到服务器和数据库，删除原文件
    
    请求方式：post
    请求地址：/file_upload
    请求参数：enctype="multipart/form-data"

    重定向：/employee/employee_select
 */
@WebServlet(name = "FileServlet", urlPatterns = "/file_upload")
public class FileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("utf-8");
            SmartUpload smartUpload = new SmartUpload();
            smartUpload.initialize(this.getServletConfig(), request, response);
            // 创建服务器upload目录
            File file = new File(getServletContext().getRealPath("/upload"));
            if (!file.exists()) {
                if (!file.mkdir()) {
                    throw new Exception("目录创建失败");
                }
            }
            smartUpload.upload();
            smartUpload.save("/upload");
            // 原始路径
//            String hdFilePath = smartUpload.getRequest().getParameter("hd");
            // 截取后的路径
//            String[] splitFilePath = hdFilePath.split("\\.");
            // 处理路径
            Date date = new Date();
            String time = date.getTime() + "";
            Random random = new Random();
            int randomNum = random.nextInt(100000000);
//            String editFilePath = time + randomNum + "." + splitFilePath[splitFilePath.length - 1];
            String editFilePath = time + randomNum + "." + smartUpload.getFiles().getFile(0).getFileExt();
            smartUpload.getFiles().getFile(0).saveAs(getServletContext().getRealPath("/") + "upload/" + editFilePath);
            String eidStr = String.valueOf(request.getSession().getAttribute("eid"));
            Integer eid = Integer.parseInt(eidStr);
            EmployeeService service = new EmployeeServiceImpl();
            Integer imageFlag = service.addUserImage(eid, editFilePath);
            if (imageFlag > 0) {
                System.out.println("添加图像名称成功！");
            }
            // 上传的原始图像
            String serviceFilePath = getServletContext().getRealPath("/") + "upload/" + smartUpload.getFiles().getFile(0).getFilePathName();
            File serviceFile = new File(serviceFilePath);

            // 数据库之前的图像
            String originFile = getServletContext().getRealPath("/")+"upload/"+request.getSession().getAttribute("originFile");
            File originFileObject = new File(originFile);

            if (serviceFile.isFile()){
                serviceFile.delete();
                originFileObject.delete();
                System.out.println("源图像已删除！");
            }else{
                System.out.println("源图像删除失败！");
            }
            response.sendRedirect("/employee/employee_select");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
