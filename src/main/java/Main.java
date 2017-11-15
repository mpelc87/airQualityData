
public class Main {

    public static void main(String[] args) {
        double southWestLatitude = 40;
        double southWestLongitude = 25;
        double northEastLatitude = 55;
        double northEastLongitude = 10;

        AirlySensorsListHttpRequest request = new AirlySensorsListHttpRequest(southWestLatitude, southWestLongitude, northEastLatitude, northEastLongitude);
    }
}
