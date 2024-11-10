package common;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JsonDataParsing {
    public JSONObject parseRequestJson(HttpServletRequest request) {
        BufferedReader rd = null;
        StringBuilder sb = null;
        try {
            rd = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
            sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(rd != null) {
                try {
                    rd.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //System.out.println("rd = " + rd.toString());
        System.out.println("request jsonData(sb) = " + sb.toString());

        JSONObject jsonObject = new JSONObject();
        try {
            JSONParser parser = new JSONParser();
            jsonObject = (JSONObject)parser.parse(sb.toString());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
