package com.ahnlab.vagent.utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServerUtils {

    private static final Logger LOGGER = LogManager.getLogger(ServerUtils.class);

    private static final String hostUrl = "http://172.20.9.130:8809";

    public static String sendServer(String connectUrl, String jsonValue) {
        HttpURLConnection conn = null;
        String inputLine = null;
        StringBuffer outResult = new StringBuffer();
        try {
            URL url = new URL(connectUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Encoding", "utf-8");
            conn.setRequestProperty("Content-Type", "application/json");

            OutputStream os = conn.getOutputStream();
            os.write(jsonValue.getBytes("UTF-8"));
            os.flush();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            while ((inputLine = in.readLine()) != null) {
                outResult.append(inputLine);
            }
            conn.disconnect();
        } catch (Exception e) {
            LOGGER.warn("server send fail");
        }
        return outResult.toString();
    }
}
