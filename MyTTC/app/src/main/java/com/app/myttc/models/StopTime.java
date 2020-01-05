
package com.app.myttc.models;

import java.util.HashMap;
import java.util.Map;

public class StopTime {

    private Integer departureTimestamp;
    private Integer serviceId;
    private String shape;
    private String departureTime;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getDepartureTimestamp() {
        return departureTimestamp;
    }

    public void setDepartureTimestamp(Integer departureTimestamp) {
        this.departureTimestamp = departureTimestamp;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
