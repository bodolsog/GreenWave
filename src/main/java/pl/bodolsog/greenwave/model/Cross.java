package pl.bodolsog.greenwave.model;

import java.util.Map;

public class Cross {
    private long id;
    private Double lat;
    private Double lng;

    public Cross(){}

    public Cross(double lat, double lng){
        this.lat = lat;
        this.lng = lng;
    }

    public Cross(long id, Map<String, Object> properties){
        this.id = id;
        if(properties.containsKey("lat"))
            this.lat = Double.valueOf(properties.get("lat").toString());
        if(properties.containsKey("lng"))
            this.lng = Double.valueOf(properties.get("lng").toString());
    }

    /**
     * Getter for property 'id'.
     *
     * @return Value for property 'id'.
     */
    public long getId() {
        return id;
    }

    /**
     * Setter for property 'id'.
     *
     * @param id Value to set for property 'id'.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Getter for property 'lat'.
     *
     * @return Value for property 'lat'.
     */
    public double getLat() {
        return lat;
    }

    /**
     * Setter for property 'lat'.
     *
     * @param lat Value to set for property 'lat'.
     */
    public void setLat(double lat) {
        this.lat = lat;
    }

    /**
     * Getter for property 'lng'.
     *
     * @return Value for property 'lng'.
     */
    public double getLng() {
        return lng;
    }

    /**
     * Setter for property 'lng'.
     *
     * @param lng Value to set for property 'lng'.
     */
    public void setLng(double lng) {
        this.lng = lng;
    }
}
