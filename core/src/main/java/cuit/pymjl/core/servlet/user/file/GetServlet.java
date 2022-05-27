//package cuit.pymjl.core.servlet.user.file;
///**
// * @author Pymjl
// * @date 2022/5/27 19:38
// * @version 1.0
// **/
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;
//import java.io.IOException;
//
//@WebServlet(name = "GetServlet", value = "/getAvatar")
//public class GetServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("文件下载...");
//
//        //设置请求编码格式
//        req.setCharacterEncoding("UTF-8");
//        resp.setContentType("text/html;charset=UTF-8");
////        //获取参数  （得到要下载的文件名）
////        String fileName = req.getParameter("fileName");
////        //参数的非空判断  trim:去除字符串的前后空格
////        if(fileName == null || "".equals(fileName.trim())){
////            resp.getWriter().write("请输入要下载的文件名！");
////            resp.getWriter().close();
////            return;
////        }
//        //得到图片存放的路径
//        String Path = req.getServletContext().getRealPath("/download/");
//        //通过路径得到file对象
//        File file = new File(Path+fileName);
//        //判断文件对象是否存在并且是否为标准文件
//        if(file.exists() && file.isFile()){
//            //设置响应类型（浏览器无法使用某种方式或激活某个程序来处理的MIME类型）
//            resp.setContentType("application/octet-stream");
//            //设置响应头信息
//            resp.setHeader("Content-Disposition","attachment;filename=" + fileName);
//            //得到file文件的输入流
//            InputStream in = new FileInputStream(file);
//            //得到字节输出流
//            ServletOutputStream out = resp.getOutputStream();
//            //定义byte数组
//            byte[] bytes = new byte[1024];
//            //定义长度
//            int len =0;
//            //循环输出
//            while((len = in.read(bytes))!= -1){
//                //输出
//                out.write(bytes,0,len);
//            }
//
//            //关闭资源
//            out.close();
//            in.close();
//
//        }else{
//            resp.getWriter().write("文件不存在，请重试！");
//            resp.getWriter().close();
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
//}
