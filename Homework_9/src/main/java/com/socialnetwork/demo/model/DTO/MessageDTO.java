package com.socialnetwork.demo.model.DTO;

import com.socialnetwork.demo.model.Message;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MessageDTO {
    private LocalDateTime dateTime;
    private String sender;
    private String receiver;
    private String text;


    public MessageDTO(Message message){
        this.dateTime = message.getDateTime();
        this.sender = message.getSender().getFirstName() + " " + message.getSender().getLastName();
        this.receiver = message.getReceiver().getFirstName() + " " + message.getReceiver().getLastName();
        this.text = message.getText();
    }

    public MessageDTO(LocalDateTime dateTime, String from, String to, String text) {
        this.dateTime = dateTime;
        this.sender = from;
        this.receiver = to;
        this.text = text;
    }

    public MessageDTO() {
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "dateTime=" + dateTime +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
