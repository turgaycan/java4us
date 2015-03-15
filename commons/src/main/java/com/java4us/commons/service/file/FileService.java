package com.java4us.commons.service.file;

import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Service
public class FileService {

    private final static Charset ENCODING = StandardCharsets.UTF_8;

    public void write(String source, String path) {

        char buffer[] = new char[source.length()];
        source.getChars(0, source.length(), buffer, 0);
        try {
            FileWriter writer = new FileWriter(path);
            for (int i = 0; i < buffer.length; i += 2) {
                writer.write(buffer[i]);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
