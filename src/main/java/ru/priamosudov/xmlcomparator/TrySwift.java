package ru.priamosudov.xmlcomparator;

import com.prowidesoftware.swift.model.mt.AbstractMT;

import java.io.File;
import java.io.IOException;

public class TrySwift {
    public static void main(String[] args) {
        AbstractMT msg = null;
        try {
            msg = AbstractMT.parse(new File("D://originalMessage_568_0.swt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String sender = msg.getSender();
        String messageType = msg.getMessageType();
        String receiver = msg.getReceiver();

        StringBuilder builder = new StringBuilder();
        builder.append("Sender: ").append(sender).append("\n");
        builder.append("Message type: ").append(messageType).append("\n");
        builder.append("Receiver: ").append(receiver).append("\n");
        builder.append("Message as string: ").append(msg.getSwiftMessage().toXml());

        System.out.println(builder.toString());
    }
}
