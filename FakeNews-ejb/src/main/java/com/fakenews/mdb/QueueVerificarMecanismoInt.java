package com.fakenews.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.fakenews.datatypes.DTHechoMecanismo;
import com.fakenews.ejb.NewsEJBLocal;

/**
 * Message-Driven Bean implementation class for: QueueVerificarMecanismoInt
 */

@MessageDriven(name = "QueueVerificarMecanismoInt", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "queue/QueueVerificarMecanismoInt"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
public class QueueVerificarMecanismoInt implements MessageListener {

	@EJB
	private NewsEJBLocal newsEJB;
	
	public void onMessage(Message rcvMessage) {
        TextMessage msg = null;
        try {
            if (rcvMessage instanceof TextMessage) {
                msg = (TextMessage) rcvMessage;
                System.out.println("Received Message from queue: " + msg.getText());
                //Parseo msg
                String pipe = "[|]";
                String[] attributes = msg.getText().split(pipe);
                DTHechoMecanismo hechoMecanismo = new DTHechoMecanismo(Long.parseLong(attributes[0]), 
                		Long.parseLong(attributes[1]), attributes[2]);
                newsEJB.verificarMecanismoIntAsync(hechoMecanismo);
                
            } else {
            	System.out.println("Message of wrong type: " + rcvMessage.getClass().getName());
            }
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

}

