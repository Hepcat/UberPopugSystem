package org.uberpopug.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.vertx.core.json.JsonObject;

public class Event {

    @JsonProperty(value = "meta_data")
    private MetaData metaData;

    @JsonProperty(value = "data")
    private JsonObject data;

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public JsonObject getData() {
        return data;
    }

    public void setData(JsonObject data) {
        this.data = data;
    }
}
