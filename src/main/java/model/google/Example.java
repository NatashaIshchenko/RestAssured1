package model.google;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;

public class Example {
    private List<String> destination_addresses = null;
    private List<String> origin_addresses = null;
    private List<Row> rows = null;
    private String status;

    public List<String> getDestination_addresses() {
        return destination_addresses;
    }

    public void setDestination_addresses(List<String> destination_addresses) {
        this.destination_addresses = destination_addresses;
    }

    public List<String> getOrigin_addresses() {
        return origin_addresses;
    }

    public void setOrigin_addresses(List<String> origin_addresses) {
        this.origin_addresses = origin_addresses;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("destination_addresses", destination_addresses).append("originAddresses", origin_addresses).append("rows", rows).append("status", status).toString();
    }
}
