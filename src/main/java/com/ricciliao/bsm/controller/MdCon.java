package com.ricciliao.bsm.controller;

import com.ricciliao.bsm.common.Constants;
import com.ricciliao.bsm.pojo.UserInfoPo;
import net.sf.json.JSONObject;
import org.apache.catalina.User;
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
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/Md")
public class MdCon {

    @Autowired
    private HttpServletRequest g_request;

    @PostMapping("/upLoadImg")
    @ResponseBody
    public String upLoadImg(HttpServletRequest request, HttpServletResponse response) {
        String strImgToB64 = null;
        String[] mreqType = null;
        BASE64Encoder base64Encoder = null;
        HttpSession curSession = null;
        UserInfoPo poFromSession = null;
        String ajaxResult = null;
        MultipartHttpServletRequest mreq = null;
        try {
            if (request instanceof MultipartHttpServletRequest) {

                curSession = request.getSession();
                poFromSession = (UserInfoPo) curSession.getAttribute(curSession.getId());
                if (poFromSession != null) {

                    mreq = (MultipartHttpServletRequest) request;
                    MultipartFile fileFromReq = mreq.getFile("editormd-image-file");
                    System.out.println(fileFromReq.getContentType());

                    mreqType = fileFromReq.getContentType().split("/");

                    if (mreqType[0].equals("image")) {

                        base64Encoder = new BASE64Encoder();
                        strImgToB64 = base64Encoder.encode(fileFromReq.getBytes());

                        response.setContentType("text/html;charset=utf-8");
                        String http = "http://localhost:8090/bsmImgSer/writeImg";
                        URL url = new URL(http);
                        HttpURLConnection httpurlconnection = (HttpURLConnection) url.openConnection();
                        httpurlconnection.setDoOutput(true);
                        httpurlconnection.setRequestMethod("POST");
                        httpurlconnection.setConnectTimeout(30000);
                        OutputStreamWriter writer = new OutputStreamWriter(httpurlconnection.getOutputStream(), "utf-8");
                        writer.write("userGuid=" + poFromSession.getUserGuid() + "&imgB64=" + strImgToB64.replaceAll(Constants.URL_PLUS, Constants.URL_PLUS_ESCAPE) + "&imgType=" + mreqType[1]);
                        writer.flush();
                        writer.close();

                        int responseCode = httpurlconnection.getResponseCode();
                        if (responseCode == HttpURLConnection.HTTP_OK) {
                            InputStream urlStream = httpurlconnection.getInputStream();
                            BufferedReader reader = new BufferedReader(new InputStreamReader(urlStream));
                            String line;
                            String tLine = "";
                            while ((line = reader.readLine()) != null) {
                                tLine += line;
                            }
                            System.out.println(tLine);

                            ajaxResult = JSONObject.fromObject(tLine).toString();

                        } else {
                            System.out.println("ERR" + responseCode);
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
