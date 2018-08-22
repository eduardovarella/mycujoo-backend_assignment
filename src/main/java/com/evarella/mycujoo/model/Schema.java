package com.evarella.mycujoo.model;

import java.util.List;

/**
 * Created by Eduardo on 22/08/2018.
 * Model class to hold schema data
 */
public class Schema {
    private String type;
    private String name;
    private List<Field> fields;

    public Schema(){

    }

    public Schema(String type, String name, List<Field> fields) {
        this.type = type;
        this.name = name;
        this.fields = fields;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
}
