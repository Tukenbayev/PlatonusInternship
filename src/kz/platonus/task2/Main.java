package kz.platonus.task2;

import kz.platonus.task2.reader.FileReader;
import kz.platonus.task2.writer.FileWriter;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public class Main {

    private static final String INPUT_FILE_NAME = "D:/messages.txt";
    private static final String OUTPUT_FILE_NAME = "D:/new-messages.txt";
    private static final String CHARSET = "UTF-16";

    public static void main(String[] args) throws IOException {

        Map<Integer,List<String>> messages = FileReader.readFile(INPUT_FILE_NAME);
        FileWriter.sortAndWriteToFile(messages,OUTPUT_FILE_NAME,CHARSET);

    }
}
