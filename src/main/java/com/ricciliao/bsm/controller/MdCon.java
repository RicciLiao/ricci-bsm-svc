package com.ricciliao.bsm.controller;

import com.ricciliao.bsm.common.Constants;
import com.ricciliao.bsm.pojo.UserInfoPo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
@RequestMapping("/Md")
public class MdCon {

    @Value("${spring.web.image.server.url}")
    private String imageSer;

    @PostMapping("/upLoadImg")
    @ResponseBody
    public String upLoadImg(HttpServletRequest request, HttpServletResponse response) {
        String strImgToB64 = null;
        String[] mreqType = null;
        BASE64Encoder base64Encoder = null;
        HttpSession curSession = null;
        UserInfoPo poFromSession = null;
        String ajaxResult = null;
        MultipartHttpServletRequest mReq = null;
        try {
            if (request instanceof MultipartHttpServletRequest) {

                curSession = request.getSession();
                poFromSession = (UserInfoPo) curSession.getAttribute(curSession.getId());
                if (poFromSession != null) {

                    mReq = (MultipartHttpServletRequest) request;
                    MultipartFile fileFromReq = mReq.getFile("editormd-image-file");
                    System.out.println(fileFromReq.getContentType());

                    mreqType = fileFromReq.getContentType().split("/");

                    if (mreqType[0].equals("image")) {

                        base64Encoder = new BASE64Encoder();
                        strImgToB64 = base64Encoder.encode(fileFromReq.getBytes());

                        response.setContentType("text/html;charset=utf-8");
                        URL url = new URL(imageSer);
                        HttpURLConnection httpurlconnection = (HttpURLConnection) url.openConnection();
                        httpurlconnection.setDoOutput(true);
                        httpurlconnection.setRequestMethod("POST");
                        httpurlconnection.setConnectTimeout(30000);
                        OutputStreamWriter writer = new OutputStreamWriter(httpurlconnection.getOutputStream(), "utf-8");
                        writer.write("userGuid=" + poFromSession.getUserGuid() + "&imgB64=" + strImgToB64.replaceAll(Constants.URL_PLUS, Constants.URL_PLUS_ESCAPE) + "&imgType=" + mreqType[1]);
                        writer.flush();
                        writer.close();

                        int responseCode = httpurlconnection.getResponseCode();
                        System.out.println("Response Code is: " + responseCode);
                        if (responseCode == HttpURLConnection.HTTP_OK) {
                            InputStream urlStream = httpurlconnection.getInputStream();
                            BufferedReader reader = new BufferedReader(new InputStreamReader(urlStream));
                            String line;
                            String tLine = "";
                            while ((line = reader.readLine()) != null) {
                                tLine += line;
                            }
                            ajaxResult = JSONObject.fromObject(tLine).toString();
                        } else {
                            ajaxResult = JSONObject.fromObject("{\"success\" : 0,\"message\" : \"上传失败！\"}").toString();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return ajaxResult;
        }
    }
}
