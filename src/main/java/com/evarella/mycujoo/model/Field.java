package com.evarella.mycujoo.model;

/**
 * Created by Eduardo on 22/08/2018.
 */
public class Field {
    private String name;
    private String type;
    private boolean nullable;

    public Field(){

    }

    public Field(String name, String type, boolean nullable) {
        this.name = name;
        this.type = type;
        this.nullable = nullable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public String getDatabaseType() {
        switch (this.getType()){
            case "string":
                return "varchar(255)";
            default:
                return this.getType();
        }
    }
}
