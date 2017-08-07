package kz.platonus.task2.reader;

import kz.platonus.task2.pojo.Message;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MessageReader {



    public List<Message> readFile(String fileName) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("D:/messages.txt"),
                "UTF-16"));
        List<Message> messages = new ArrayList<>();

        while (reader.ready()){
            String line = reader.readLine();
            char[] chars = line.toCharArray();
            StringBuilder id = new StringBuilder();
            StringBuilder content = new StringBuilder();
            for (char c : chars){
                if (c == '|'){
                    content.append(chars);
                    break;
                }
                id.append(c);
            }

            Message message = new Message(Integer.valueOf(id.toString()), content.toString());
            messages.add(message);
        }

        return messages;
    }



}
