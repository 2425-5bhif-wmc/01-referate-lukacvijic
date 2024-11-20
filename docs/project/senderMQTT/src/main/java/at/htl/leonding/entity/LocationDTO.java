package at.htl.leonding.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LocationDTO {

    @JsonProperty("_type")
    private String type;
    @JsonProperty("lat")
    private double lat;
    @JsonProperty("lon")
    private double lon;
    private String tid;

    public LocationDTO(String type, double lat, double lon, String tid) {
        this.type = type;
        this.lat = lat;
        this.lon = lon;
        this.tid = tid;
    }

    public LocationDTO() {
    }

    // region getter und setter

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    // endregion
}
