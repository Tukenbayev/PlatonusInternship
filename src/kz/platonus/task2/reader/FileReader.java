package kz.platonus.task2.reader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class FileReader {

    public static Map<Integer,List<String>> readFile(String fileName) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("D:/messages.txt"),
                "UTF-16"));
        Map<Integer,List<String>> messages = new TreeMap<>();

        while (reader.ready()){
            String line = reader.readLine();
            char[] chars = line.toCharArray();
            StringBuilder id = new StringBuilder();
            StringBuilder message = new StringBuilder();
            for (char c : chars){
                if (c == '|'){
                    message.append(chars);
                    break;
                }
                id.append(c);
            }

            put(messages,Integer.valueOf(id.toString()),message.toString());
        }
        return messages;
    }

    private static void put(Map<Integer,List<String>> messages,int id,String message){
        messages.putIfAbsent(id, new ArrayList<>());
        messages.get(id).add(message);
    }

}
