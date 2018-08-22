package com.evarella.mycujoo.service;

import com.evarella.mycujoo.Configuration;
import com.evarella.mycujoo.model.*;
import com.evarella.mycujoo.utils.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eduardo on 22/08/2018.
 * Service class that implements all the SQL command generation
 */
public class SQLCommandService {

    private Configuration configuration;

    public SQLCommandService() {

    }

    public SQLCommandService(Configuration configuration) {
        this.configuration = configuration;
    }

    // Service
    public void generateCreateTableCommandsFromSubjects() throws Exception {

        String sqlCommand = "";
        // Loading subject list from first endpoint
        List<Subject> subjects = this.loadSubjectsList();
        for (Subject subject : subjects) {
            // For each subject, load and parse JSON schema definition
            SchemaDefinition schemaDefinition = this.getSchemaDefinitionFromSubject(subject);
            sqlCommand += this.buildCreateTableCommand(schemaDefinition);
        }

        this.writeCommandToFile(sqlCommand);
    }

    void writeCommandToFile(String command) throws Exception {
        String filePath = this.configuration.getOutputFile();
        System.out.print("Writting output to " + filePath);
        PrintWriter out = null;
        try {
            out = new PrintWriter(filePath);
        } catch (FileNotFoundException e) {
            throw new Exception("Couldn´t write to " + filePath, e);
        }
        out.println(command);
        out.close();
        System.out.println(": Done!");
    }

    String buildCreateTableCommand(SchemaDefinition schemaDefinition) {

        String command = "CREATE TABLE `" + schemaDefinition.getDatabaseTableName() + "` (\n";
        for (Field field : schemaDefinition.getSchema().getFields()) {
            command += "\t`" + field.getName() + "` " + field.getDatabaseType() + "" + (field.isNullable() ? "" : " NOT NULL") + ",\n";
        }

        command += "\tPRIMARY KEY (`" + schemaDefinition.getSchema().getFields().get(0).getName() + "`)\n) ENGINE=InnoDB;\n\n";
        return command;
    }

    SchemaDefinition getSchemaDefinitionFromSubject(Subject subject) throws Exception {
        System.out.print("Parsing subject '" + subject.getName() + "'");
        String jsonString = loadSchemaDefinitionFromAPI(subject);
        SchemaDefinition schemaDefinition = parseSchemaDefinitionFromJsonString(jsonString);
        System.out.println(": Done!");
        return schemaDefinition;
    }

    SchemaDefinition parseSchemaDefinitionFromJsonString(String jsonString) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(SchemaDefinition.class, new SchemaDefinitionDeserializer());
        mapper.registerModule(module);

        try {
            return mapper.readValue(jsonString, SchemaDefinition.class);
        } catch (IOException e) {
            throw new Exception("Couldn´t parse schema definition from json string: " + jsonString, e);
        }
    }

    String loadSchemaDefinitionFromAPI(Subject subject) throws Exception {
        String url = configuration.getSchemaURLPrefix() + subject.getName() + ".json";
        try {
            return HttpUtils.get(url);
        } catch (HttpUtilsException e) {
            throw new Exception("Couldn´t load schema definition from " + url, e);
        }
    }

    String[] getSubjectNamesArray() throws Exception {
        String jsonString = this.loadSubjectListFromAPI();
        return this.parseSubjectListFromJsonString(jsonString);
    }

    String[] parseSubjectListFromJsonString(String jsonString) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(jsonString, String[].class);
        } catch (IOException e) {
            throw new Exception("Couldn´t parse subject list json string: " + jsonString, e);
        }
    }

    String loadSubjectListFromAPI() throws Exception {
        try {
            return HttpUtils.get(configuration.getSubjectsURL());
        } catch (HttpUtilsException e) {
            throw new Exception("Couldn´t load subject list from " + configuration.getSubjectsURL(), e);
        }
    }

    List<Subject> loadSubjectsList() throws Exception {
        System.out.print("Loading subjects list");
        String[] subjectNames = getSubjectNamesArray();

        List<Subject> subjects = new ArrayList<Subject>();
        for (String subjectName : subjectNames) {
            subjects.add(new Subject(subjectName));
        }
        System.out.println(": Done!");
        return subjects;
    }
}
