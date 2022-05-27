package cuit.pymjl.core.servlet.user.file;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.resource.ResourceUtil;
import cuit.pymjl.core.exception.AppException;
import cuit.pymjl.core.result.R;
import lombok.SneakyThrows;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/5/27 14:54
 **/
@WebServlet(name = "AvatarServlet", value = "/upload")
public class AvatarServlet extends HttpServlet {
    /**
     * 根路径
     */
    private String rootPath;
    /**
     * 内存阈值
     */
    private int memoryThreshold;
    /**
     * 最大文件大小
     */
    private int maxFileSize;
    /**
     * 最大请求大小
     */
    private int maxRequestSize;

    @SneakyThrows
    @Override
    public void init() throws ServletException {
        Properties properties = new Properties();
        properties.load(ResourceUtil.getStream("file.setting"));
        this.rootPath = properties.getProperty("rootPath");
        this.memoryThreshold = Integer.parseInt(properties.getProperty("MEMORY_THRESHOLD"));
        this.maxFileSize = Integer.parseInt(properties.getProperty("MAX_FILE_SIZE"));
        this.maxRequestSize = Integer.parseInt(properties.getProperty("MAX_REQUEST_SIZE"));
    }

    /**
     * 上传数据及保存文件
     */
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        // 检测是否为多媒体上传
        if (!ServletFileUpload.isMultipartContent(request)) {
            // 如果不是则停止
            throw new AppException("上传的不是文件");
        }

        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(memoryThreshold);
        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        // 设置最大文件上传值
        upload.setFileSizeMax(maxFileSize);

        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(maxRequestSize);

        // 中文处理
        upload.setHeaderEncoding("UTF-8");

        // 构造临时路径来存储上传的文件
        // 这个路径相对当前应用的目录
        String uploadPath = request.getServletContext().getRealPath("/") + File.separator + rootPath;

        // 如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            // 解析请求的内容提取文件数据
            List<FileItem> formItems = upload.parseRequest(request);

            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        // 在控制台输出文件的上传路径
                        System.out.println(filePath);
                        // 保存文件到硬盘
                        item.write(storeFile);
                    }
                }
            }
        } catch (Exception ex) {
            throw new AppException(ex.getMessage());
        }
        response.getWriter().println(R.success());
    }
}
