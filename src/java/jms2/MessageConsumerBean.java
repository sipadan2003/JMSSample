package jms2;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(mappedName = "jms/queue1", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class MessageConsumerBean implements MessageListener {
    
    public MessageConsumerBean() {
    }
    
    @Override
    public void onMessage(Message message) {
        try {
            TextMessage tm = (TextMessage) message;
            System.out.println("Consumed message: " + tm.getText());
        } catch (JMSException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
