package kz.platonus.task2.pojo;

public class Message implements Comparable<Message> {

    private int id;
    private String content;

    public Message(int id, String message){
        this.id = id;
        this.content = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String message) {
        this.content = message;
    }

    @Override
    public int compareTo(Message o) {
        return id - o.getId();
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
