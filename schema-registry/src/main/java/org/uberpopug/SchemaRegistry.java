package org.uberpopug;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import io.vertx.core.json.JsonObject;
import org.uberpopug.event.Event;
import org.uberpopug.exception.ValidationException;

import java.util.Set;

public class SchemaRegistry {

    public static void validate(Event event) throws ValidationException {
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V202012);
        JsonSchema jsonSchema = factory
                .getSchema(Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("/" + event.getMetaData().getEventName() + "_" + event.getMetaData().getEventVersion() + ".json"));
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = mapper.readTree(event.getData().toString());
        } catch (Exception e) {

        }
        Set<ValidationMessage> errors = jsonSchema.validate(jsonNode);
        if (!errors.isEmpty()) {
            throw new ValidationException("Event is not valid");
        }
    }

}
