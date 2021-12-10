package qrjson.input;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;

import javax.swing.JProgressBar;

import com.google.gson.FieldAttributes;
import com.google.gson.Gson;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import org.json.simple.parser.JSONParser;

public class stringToJson {
    public String intToJson[] = { "round", "inner", "outer", "bottom", "miss", "missAver", "InAver" };

    public void makeJson(String data, String dir) throws IOException, ParseException {
        // Gson gson = new Gson();
        String A[] = data.split("-");
        JSONParser parser = new JSONParser();
        for (int j = 0; j < A.length / 6; j++) {
            String fName = dir.replace('\'', '/') + "/t" + A[j * 6] + ".json";
            File file = new File(fName);
            if (file.createNewFile()) {
                System.out.println("file created");
                FileWriter fw = new FileWriter(file);
                fw.write("[]");
                fw.flush();
                fw.close();
            }
            try {
                JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(fName));
                JSONObject obj = new JSONObject();
                for (int i = 0; i < 7; i++) {
                    switch (i) {
                        case 5:
                            obj.put(intToJson[i], (Float.parseFloat(A[5 + (j * 6)]) / (Float.parseFloat(A[5 + (j * 6)])+ Float.parseFloat(A[3 + (j * 6)]) + Float.parseFloat(A[2 + (j * 6)]))));
                            break;
                        case 6:
                            obj.put(intToJson[i], (Float.parseFloat(A[2 + (j * 6)]) / (Float.parseFloat(A[2 + (j * 6)]) + Float.parseFloat(A[3 + (j * 6)]))));
                            break;
                        default:
                            obj.put(intToJson[i], Integer.parseInt(A[i + (j * 6) + 1]));
                            break;
                    }

                }
                jsonArray.add(obj);
                FileWriter fw = new FileWriter(file);
                fw.write(jsonArray.toJSONString());
                fw.flush();
                fw.close();
                
            } catch (IOException e) {

            }
        }

    }
}
