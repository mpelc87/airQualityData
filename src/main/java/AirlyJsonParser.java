import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by marek on 15.11.17.
 */
public class AirlyJsonParser {
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String VENDOR = "vendor";
    public static final String LOCATION = "location";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    public static final String ADDRESS = "address";
    public static final String STREET_NUMBER = "streetNumber";
    public static final String ROUTE = "route";
    public static final String LOCALITY = "locality";
    public static final String COUNTRY = "country";

    public List<Map<String, String>> parseJsonString(String json) {
        List<Map<String, String>> sensors = new ArrayList<>();

        JSONArray jsonArray = new JSONArray(json);

        for(int i=0; i<jsonArray.length(); ++i) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Map<String, String> sensorParams = new HashMap<>();
            sensorParams.put(ID, String.valueOf(jsonObject.getInt(ID)));
            sensorParams.put(NAME, jsonObject.getString(NAME));
            sensorParams.put(VENDOR, jsonObject.getString(VENDOR));


            JSONObject jsonObjectLocation = jsonObject.getJSONObject(LOCATION);
            sensorParams.put(LATITUDE, String.valueOf(jsonObjectLocation.getDouble(LATITUDE)));
            sensorParams.put(LONGITUDE, String.valueOf(jsonObjectLocation.getDouble(LONGITUDE)));

            JSONObject jsonObjectAddress = jsonObject.getJSONObject(ADDRESS);
            sensorParams.put(STREET_NUMBER, getStringFromJsonIfExist(jsonObjectAddress, STREET_NUMBER));
            sensorParams.put(ROUTE, getStringFromJsonIfExist(jsonObjectAddress, ROUTE));
            sensorParams.put(LOCALITY, getStringFromJsonIfExist(jsonObjectAddress, LOCALITY));
            sensorParams.put(COUNTRY, getStringFromJsonIfExist(jsonObjectAddress, COUNTRY));

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
