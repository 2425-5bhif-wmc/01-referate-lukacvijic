package at.htl.leonding.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LocationDTO {

    @JsonProperty("_type")
    private String type;
    @JsonProperty("BSSID")
    private String BSSID;
    @JsonProperty("SSID")
    private String SSID;
    @JsonProperty("acc")
    private int acc;
    @JsonProperty("alt")
    private int alt;
    @JsonProperty("batt")
    private int batt;
    @JsonProperty("bs")
    private int bs;
    @JsonProperty("conn")
    private String conn;
    @JsonProperty("created_at")
    private long created_at;
    @JsonProperty("lat")
    private double lat;
    @JsonProperty("lon")
    private double lon;
    @JsonProperty("m")
    private int m;
    @JsonProperty("t")
    private String t;
    @JsonProperty("tid")
    private String tid;
    @JsonProperty("tst")
    private long tst;
    @JsonProperty("vac")
    private int vac;
    @JsonProperty("vel")
    private int vel;

    public LocationDTO(String type,
                       String BSSID,
                       String SSID,
                       int acc,
                       int alt,
                       int batt,
                       int bs,
                       String conn,
                       long created_at,
                       double lat,
                       double lon,
                       int m,
                       String t,
                       String tid,
                       long tst,
                       int vac,
                       int vel) {
        this.type = type;
        this.BSSID = BSSID;
        this.SSID = SSID;
        this.acc = acc;
        this.alt = alt;
        this.batt = batt;
        this.bs = bs;
        this.conn = conn;
        this.created_at = created_at;
        this.lat = lat;
        this.lon = lon;
        this.m = m;
        this.t = t;
        this.tid = tid;
        this.tst = tst;
        this.vac = vac;
        this.vel = vel;
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

    public String getBSSID() {
        return BSSID;
    }

    public void setBSSID(String BSSID) {
        this.BSSID = BSSID;
    }

    public String getSSID() {
        return SSID;
    }

    public void setSSID(String SSID) {
        this.SSID = SSID;
    }

    public int getAcc() {
        return acc;
    }

    public void setAcc(int acc) {
        this.acc = acc;
    }

    public int getAlt() {
        return alt;
    }

    public void setAlt(int alt) {
        this.alt = alt;
    }

    public int getBatt() {
        return batt;
    }

    public void setBatt(int batt) {
        this.batt = batt;
    }

    public int getBs() {
        return bs;
    }

    public void setBs(int bs) {
        this.bs = bs;
    }

    public String getConn() {
        return conn;
    }

    public void setConn(String conn) {
        this.conn = conn;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
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

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public long getTst() {
        return tst;
    }

    public void setTst(long tst) {
        this.tst = tst;
    }

    public int getVac() {
        return vac;
    }

    public void setVac(int vac) {
        this.vac = vac;
    }

    public int getVel() {
        return vel;
    }

    public void setVel(int vel) {
        this.vel = vel;
    }

    // endregion
}
