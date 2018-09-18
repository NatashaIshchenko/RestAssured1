package model.google;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Element {
    private Distance distance;
    private Duration duration;
    private String status;

    public Distance getDistance() {
        return distance;
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("distance", distance).append("duration", duration).append("status", status).toString();
    }
}
