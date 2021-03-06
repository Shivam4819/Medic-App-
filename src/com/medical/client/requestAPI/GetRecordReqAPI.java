package com.medical.client.requestAPI;

import com.medical.client.responseAPI.GetRecordResAPI;
import com.medical.client.service.ExtraFunctions;
import org.json.simple.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "GetRecordReqAPI", urlPatterns = {"/readRecord"})
public class GetRecordReqAPI extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            GetRecordResAPI resAPI = new GetRecordResAPI();
            String hospitalUserName = null;

            Cookie[] cookies = request.getCookies();
            Cookie cookie;
            if (cookies != null) {
                for (Cookie value : cookies) {
                    cookie = value;
                    if (cookie.getName().equals("loginStatus")) {
                        hospitalUserName = cookie.getValue();
                        break;
                    }
                }
            }

            String patientId = request.getParameter("patientID");
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("patientId", Long.parseLong(patientId));
            jsonObject.put("hospitalUserName", hospitalUserName);
            System.out.println("id:"+patientId+" `hos:"+hospitalUserName);
            URL url = new URL("http://localhost:8082/getChain");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            OutputStream output = conn.getOutputStream();
            OutputStreamWriter outputWriter = new OutputStreamWriter(output, StandardCharsets.UTF_8);
            outputWriter.write(jsonObject.toString());
            outputWriter.flush();
            outputWriter.close();

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                System.out.println("Server hit waiting for error");
                resAPI.readResponse(conn,response);
            } else {
                System.out.println("something went wrong");
            }
        }catch (Exception ex){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("statusCode",400);
            PrintWriter printWriter = response.getWriter();
            printWriter.println(jsonObject.toString());
            ex.printStackTrace();
        }
    }
}
