package model.google;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;

public class Row {
    private List<Element> elements = null;

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("elements", elements).toString();
    }
}
