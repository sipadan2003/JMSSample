package jms2;

import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

@Named(value = "messageProducerBean")
@RequestScoped
public class MessageProducerBean {
    @Resource(mappedName = "jms/queue1")
    private Queue queue1;
    @Inject
    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    private JMSContext context;
    private String message;

    public MessageProducerBean() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void send(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        sendJMSMessageToQueue1(message);
        FacesMessage facesMessage = new FacesMessage("Message sent: " + message);
        facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
        facesContext.addMessage(null, facesMessage);
    }

    private void sendJMSMessageToQueue1(String messageData) {
        context.createProducer().send(queue1, messageData);
    }
}
