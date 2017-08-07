package kz.platonus.task2.writer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public class FileWriter {

    public static void sortAndWriteToFile(Map<Integer,List<String>> messages, String fileName, String charSet){

        try(PrintWriter writer = new PrintWriter(fileName,charSet)){

            int previousId = 0;
            for(Map.Entry<Integer,List<String>> message : messages.entrySet()){

                if (previousId != 0 && previousId + 1 != message.getKey() ){
                    writer.println();
                }

                if (message.getValue().size() > 1){
                    System.err.println("Found duplicate IDs:");
                    for (String duplicateId : message.getValue()){
                        System.out.println(duplicateId);
                        writer.println(duplicateId);
                    }
                    previousId = message.getKey();
                    continue;
                }

                writer.println(message.getValue().get(0));
                previousId = message.getKey();
            }

        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            System.err.println("File not found | Unsupported encoding !");
        }
    }

}
