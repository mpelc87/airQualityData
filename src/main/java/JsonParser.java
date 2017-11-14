

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by marek on 14.11.17.
 */
public class JsonParser {

    public List<Map<String, String>> parseJsonString(String json) {
        List<Map<String, String>> sensors = new ArrayList<>();

        JSONArray jsonArray = new JSONArray(json);

        for(int i=0; i<jsonArray.length(); ++i) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Map<String, String> sensorParams = new HashMap<>();
            sensorParams.put("id", String.valueOf(jsonObject.getInt("id")));
            sensorParams.put("name", jsonObject.getString("name"));
            sensorParams.put("vendor", jsonObject.getString("vendor"));


            JSONObject jsonObjectLocation = jsonObject.getJSONObject("location");
            sensorParams.put("latitude", String.valueOf(jsonObjectLocation.getDouble("latitude")));
            sensorParams.put("longitude", String.valueOf(jsonObjectLocation.getDouble("longitude")));

            JSONObject jsonObjectAddress = jsonObject.getJSONObject("address");
            sensorParams.put("streetNumber", getStringFromJsonIfExist(jsonObjectAddress, "streetNumber"));
            sensorParams.put("route", getStringFromJsonIfExist(jsonObjectAddress, "route"));
            sensorParams.put("locality", getStringFromJsonIfExist(jsonObjectAddress, "locality"));
            sensorParams.put("country", getStringFromJsonIfExist(jsonObjectAddress, "country"));

            sensors.add(sensorParams);
        }

        return sensors;
    }

    private String getStringFromJsonIfExist(JSONObject jsonObject, String key){
        if(jsonObject.has(key)) {
            return jsonObject.getString(key);
        }
        return "";
    }
}
