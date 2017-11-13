/**
 * Created by marek on 13.11.17.
 */
public class Sensor {
    private String id;
    private String name;
    private String vendor;
    private String latitude;
    private String longitude;
    private String streetNumber;
    private String route;
    private String locality;
    private String country;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getVendor() {
        return vendor;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getRoute() {
        return route;
    }

    public String getLocality() {
        return locality;
    }

    public String getCountry() {
        return country;
    }

    public Sensor(String id, String name, String vendor, String latitude, String longitude, String streetNumber,
                  String route, String locality, String country) {
        this.id = id;
        this.name = name;
        this.vendor = vendor;
        this.latitude = latitude;
        this.longitude = longitude;
        this.streetNumber = streetNumber;
        this.route = route;
        this.locality = locality;
        this.country = country;
    }

    public static class SensorBuilder {
        private String nestedId;
        private String nestedName;
        private String nestedVendor;
        private String nestedLatitude;
        private String nestedLongitude;
        private String nestedStreetNumber;
        private String nestedRoute;
        private String nestedLocality;
        private String nestedCountry;



        public SensorBuilder(String id, String name, String latitude, String longitude) {
            this.nestedId = id;
            this.nestedName = name;
            this.nestedLatitude = latitude;
            this.nestedLongitude = longitude;
        }

        public SensorBuilder vendor(String vendor) {
            this.nestedVendor = vendor;
            return this;
        }


        public SensorBuilder street_number(String streetNumber) {
            this.nestedStreetNumber = streetNumber;
            return this;
        }

        public SensorBuilder route(String route) {
            this.nestedRoute = route;
            return this;
        }

        public SensorBuilder locality(String locality) {
            this.nestedLocality = locality;
            return this;
        }

        public SensorBuilder country(String country) {
            this.nestedCountry = country;
            return this;
        }

        public Sensor build() {
            return new Sensor(nestedId, nestedName, nestedVendor, nestedLatitude, nestedLongitude, nestedStreetNumber,
                    nestedRoute, nestedLocality, nestedCountry);
        }
    }



}

