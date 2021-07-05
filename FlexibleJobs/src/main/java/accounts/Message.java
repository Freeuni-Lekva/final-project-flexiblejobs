package accounts;

import java.time.LocalDateTime;

public class Message implements Comparable{

    private String from;
    private String to;
    private String text;
    private String timeCreated;

    public Message(String from,String to, String text,String timeCreated){
        this.from=from;
        this.to=to;
        this.text=text;
        this.timeCreated=timeCreated;
    }

    public Message(String from,String to, String text){
        this(from,to,text,LocalDateTime.now().toString());
    }

    public String getSender() {
        return from;
    }

    public String getReceiver() {
        return to;
    }

    public String getText() {
        return text;
    }

    public String getTime() {
        return timeCreated;
    }

    public String toString(){
        return text;
    }

    @Override
    public int compareTo(Object o) {
        return timeCreated.compareTo(((Message)o).getTime());
    }
}
