package com.evarella.mycujoo;

import java.util.Map;

/**
 * Created by Eduardo on 22/08/2018.
 */
public class Configuration {

    private String subjectsURL;
    private String schemaURLPrefix;
    private String outputFile;

    public Configuration() {
    }

    public String getSubjectsURL() {
        return subjectsURL;
    }

    public void setSubjectsURL(String subjectsURL) {
        this.subjectsURL = subjectsURL;
    }

    public String getSchemaURLPrefix() {
        return schemaURLPrefix;
    }

    public void setSchemaURLPrefix(String schemaURLPrefix) {
        this.schemaURLPrefix = schemaURLPrefix;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }
}
