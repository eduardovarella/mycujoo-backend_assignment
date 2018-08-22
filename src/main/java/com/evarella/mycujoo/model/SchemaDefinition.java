package com.evarella.mycujoo.model;

/**
 * Created by Eduardo on 22/08/2018.
 * Model class to hold schema definition data
 */
public class SchemaDefinition {

    private String subject;
    private int version;
    private int id;
    private Schema schema;

    public SchemaDefinition(){

    }

    public SchemaDefinition(String subject, int version, int id, Schema schema) {
        this.subject = subject;
        this.version = version;
        this.id = id;
        this.schema = schema;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Schema getSchema() {
        return schema;
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }

    // Method to create database table name
    public String getDatabaseTableName() {
        return this.getSchema().getName().replaceAll("-", "_");
    }
}
