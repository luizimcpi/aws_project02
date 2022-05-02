package br.com.luizimcpi.aws_project02.model;

import br.com.luizimcpi.aws_project02.enums.EventType;

public class Envelope {
    private EventType eventType;
    private String data;

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Envelope{" +
                "eventType=" + eventType +
                ", data='" + data + '\'' +
                '}';
    }
}
