package com.evarella.mycujoo;

import com.evarella.mycujoo.service.SQLCommandService;
import com.evarella.mycujoo.utils.HttpUtilsException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

/**
 * Created by Eduardo on 22/08/2018.
 * Main application file
 * Loads configuration file that most be passed as first argument in the command line
 */
public class App {
    public static void main(String[] args) {

        if( args.length != 1 ) {
            System.out.println( "Usage: <file.yml>" );
            return;
        }

        String confFileName = args[0];

        // Loading configuration data using Jackson
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        Configuration configuration = null;
        try {
            configuration = mapper.readValue(new File(confFileName), Configuration.class);
        } catch (IOException e) {
            System.out.println("Couldn´t parse configuration file " + confFileName);
            e.printStackTrace();
            return;
        }

        // Executing main process
        SQLCommandService service = new SQLCommandService(configuration);
        try {
            service.generateCreateTableCommandsFromSubjects();
            System.out.println("Process finished succesfully");
        } catch (Exception e) {
            System.out.println("Process exited with an error: " + e.getMessage());
            e.printStackTrace();
            return;
        }

    }

}
