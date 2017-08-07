package kz.platonus.task2.writer;

import kz.platonus.task2.pojo.Message;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class MessageWriter {

    public void sortAndWriteToFile(List<Message> messages, String fileName, String charSet){

        Collections.sort(messages);
        try(PrintWriter writer = new PrintWriter(fileName,charSet)){

            Set<Message> duplicateMessages = new LinkedHashSet<>();
            Message previousMessage = null;
            for(Message message : messages){
                if (previousMessage != null && previousMessage.getId() + 1 < message.getId() ){
                    writer.println();
                }
                if (previousMessage != null && previousMessage.getId() == message.getId()){
                    duplicateMessages.add(previousMessage);
                    duplicateMessages.add(message);
                }else if (!duplicateMessages.isEmpty()){
                    System.out.println("Duplicate ids found:");
                    for (Message message1 : duplicateMessages){
                        System.out.println(message1.getContent());
                    }
                    duplicateMessages.clear();
                }
                writer.println(message.getContent());
                previousMessage = message;
            }

        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            System.err.println("File not found | Unsupported encoding !");
        }
    }

}
