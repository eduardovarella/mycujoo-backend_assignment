package com.evarella.mycujoo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Eduardo on 22/08/2018.
 */
public class HttpUtils {

    public static String get(String url) throws HttpUtilsException {

        URL obj = null;
        StringBuffer response = null;
        try {
            obj = new URL(url);

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
            throw new HttpUtilsException(e);
        } catch (ProtocolException e) {
            throw new HttpUtilsException(e);
        } catch (IOException e) {
            throw new HttpUtilsException(e);
        }

        return response.toString();
    }
}
