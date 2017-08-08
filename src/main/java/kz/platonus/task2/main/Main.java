package kz.platonus.task2.main;

import kz.platonus.task2.entity.Message;
import kz.platonus.task2.reader.MessageReader;
import kz.platonus.task2.writer.MessageWriter;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class Main {

    private static final String INPUT_FILE_NAME = "D:"+ File.separator+"messages.txt";
    private static final String OUTPUT_FILE_NAME = "D:"+File.separator+"new-messages.txt";
    private static final String CHARSET = "UTF-16";

    public static void main(String[] args) throws IOException {

        MessageReader messageReader = new MessageReader();
        List<Message> messages = messageReader.readFile(INPUT_FILE_NAME);

        MessageWriter messageWriter = new MessageWriter();
        messageWriter.sortAndWriteToFile(messages,OUTPUT_FILE_NAME,CHARSET);

    }
}
