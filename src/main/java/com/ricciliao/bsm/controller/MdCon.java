package com.ricciliao.bsm.controller;

import com.ricciliao.bsm.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/Md")
public class MdCon {

    @Autowired
    private HttpServletRequest g_request;

    @PostMapping("/upLoadImg")
    @ResponseBody
    public Map upLoadImg(HttpServletRequest request, HttpServletResponse response) {
        String strImgToB64 = null;
        BASE64Encoder b64er = null;
        Map<String, Object> ajaxResult = null;
        MultipartHttpServletRequest mreq = null;
        try {
            if (request instanceof MultipartHttpServletRequest) {
                mreq = (MultipartHttpServletRequest) request;
                MultipartFile fileFromReq = mreq.getFile("editormd-image-file");
                System.out.println(fileFromReq.getOriginalFilename());
                b64er = new BASE64Encoder();
                strImgToB64 = b64er.encode(fileFromReq.getBytes());
                ajaxResult = new HashMap<>();
                ajaxResult.put(Constants.SUCCESS, 1);   // 0 表示上传失败，1 表示上传成功
                ajaxResult.put(Constants.MESSAGE, "");
                ajaxResult.put(Constants.URL, "");



                g_request.setCharacterEncoding("utf-8");
                response.setContentType("text/html;charset=utf-8");
                String http="http://localhost:8090/writeImg";
                URL url=new URL(http);
                HttpURLConnection httpurlconnection=(HttpURLConnection) url.openConnection();
                httpurlconnection.setDoOutput(true);
                httpurlconnection.setRequestMethod("POST");
                httpurlconnection.setConnectTimeout(30000);
                OutputStreamWriter writer=new OutputStreamWriter(httpurlconnection.getOutputStream(), "utf-8");
                writer.write("username=123&password=456");
                writer.flush();
                writer.close();
                int responseCode=httpurlconnection.getResponseCode();
                if(responseCode==HttpURLConnection.HTTP_OK){
                    System.out.println("OK"+responseCode);
                    InputStream urlstream=httpurlconnection.getInputStream();
                    BufferedReader reader=new BufferedReader(new InputStreamReader(urlstream));
                    String line;
                    String tline="";
                    while((line=reader.readLine())!=null){
                        tline+=line;
                    }
                    System.out.println(tline);
                }else{
                    System.out.println("ERR"+responseCode);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return ajaxResult;
        }
    }
}
