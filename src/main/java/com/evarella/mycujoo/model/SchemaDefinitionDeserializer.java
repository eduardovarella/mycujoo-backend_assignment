package com.evarella.mycujoo.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.IntNode;
import jdk.nashorn.internal.runtime.regexp.joni.ast.StringNode;

import java.io.IOException;

/**
 * Created by Eduardo on 22/08/2018.
 */
public class SchemaDefinitionDeserializer extends StdDeserializer<SchemaDefinition> {

    public SchemaDefinitionDeserializer() {
        this(null);
    }

    public SchemaDefinitionDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public SchemaDefinition deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);

        String subject = node.get("subject").asText();
        int version = (Integer) ((IntNode) node.get("version")).numberValue();
        int id = (Integer) ((IntNode) node.get("id")).numberValue();

        String schemaString = node.get("schema").asText();

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Field.class, new FieldDeserializer());
        mapper.registerModule(module);

        Schema schema = mapper.readValue(schemaString, Schema.class);

        return new SchemaDefinition(subject, version, id, schema);
    }
}