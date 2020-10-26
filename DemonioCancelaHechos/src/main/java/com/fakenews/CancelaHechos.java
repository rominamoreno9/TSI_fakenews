package com.fakenews;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.fakenews.ejb.NewsEJBRemote;

//import javax.jms.Connection;
//import javax.jms.ConnectionFactory;
//import javax.jms.JMSException;
//import javax.jms.MessageProducer;
//import javax.jms.Queue;
//import javax.jms.Session;
//import javax.jms.TextMessage;

public class CancelaHechos 
{
    public static void main( String[] args )
    {	 
    	NewsEJBRemote newsEJB;
		try {
			newsEJB = lookupNewsEJBRemote();
			newsEJB.cancelaHechosDia();
		} catch (NamingException e) {
			
			e.printStackTrace();
		}
    	
    }
              
    private static NewsEJBRemote lookupNewsEJBRemote() throws NamingException {
        final Hashtable<String, String> jndiProperties = new Hashtable<>();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        final Context context = new InitialContext(jndiProperties);
        return (NewsEJBRemote) context.lookup("ejb:FakeNews-ear/FakeNews-ejb/NewsEJB!com.fakenews.ejb.NewsEJBRemote");
    }

}
