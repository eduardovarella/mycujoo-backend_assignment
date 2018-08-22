package com.evarella.mycujoo.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;

import java.io.IOException;

/**
 * Created by Eduardo on 22/08/2018.
 */
public class FieldDeserializer extends StdDeserializer<Field> {

    public FieldDeserializer() {
        this(null);
    }

    public FieldDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Field deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);

        String name = node.get("name").asText();
        String type = node.get("type").asText();
        boolean nullable = false;

        if(node.get("type").isArray()){
            type = node.get("type").get(0).asText();
            if(type.equals("null"))
            {
                if(node.get("type").get(1).isObject()){
                    type = "string";
                }
                else {
                    type = node.get("type").get(1).asText();
                }
            }
            nullable = true;
        }
        else if(node.get("type").isObject()){
            type = "string";
            nullable = false;
        }

        return new Field(name, type, nullable);
    }
}