package kz.platonus.task2;

import kz.platonus.task2.pojo.Message;
import kz.platonus.task2.reader.MessageReader;
import kz.platonus.task2.writer.MessageWriter;

import java.io.IOException;
import java.util.List;


public class Main {

    private static final String INPUT_FILE_NAME = "D:/messages.txt";
    private static final String OUTPUT_FILE_NAME = "D:/new-messages.txt";
    private static final String CHARSET = "UTF-16";

    public static void main(String[] args) throws IOException {

        MessageReader messageReader = new MessageReader();
        List<Message> messages = messageReader.readFile(INPUT_FILE_NAME);

        MessageWriter messageWriter = new MessageWriter();
        messageWriter.sortAndWriteToFile(messages,OUTPUT_FILE_NAME,CHARSET);

    }
}
