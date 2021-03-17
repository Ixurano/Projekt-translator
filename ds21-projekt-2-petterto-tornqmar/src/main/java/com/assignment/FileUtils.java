package com.assignment;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


public class FileUtils {
    public static String readTextFile(String fileName) {
        StringBuilder retStr = new StringBuilder();
        try {
            FileInputStream inputStream = new FileInputStream(fileName);
            InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(reader);

            // Då vi använder bufferedReader kan vi ta en hel rad i gången.
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                retStr.append(line);
            }

            bufferedReader.close();
            reader.close();
        } catch (FileNotFoundException e) {

            return fileName + " not found";
        } catch (IOException e) {
            e.printStackTrace();
        }

        return retStr.toString();
    }
}