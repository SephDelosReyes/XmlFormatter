package com.formatter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FormatterApplication {
    public static void main(String[] args) throws IOException {

        // File sanity stuff
        System.out.println(args[0].toString());
        byte[] fileContent = Files.readAllBytes(Paths.get(args[0].toString()));
        System.out.println(new String(fileContent));

        // The good stuff:
        // XmlFormatter formatter = new XmlFormatter();
        // formatter.format();
    }
}