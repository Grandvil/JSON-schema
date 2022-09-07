import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement(name = "message", namespace = "http://namespaceGrandvil.com/System")
public class Message {
    Sender sender;
    Receivers receivers;
    Date time;
    Content content;

    @XmlElement(name = "sender", namespace = "http://namespaceGrandvil.com/System")
    public void setSender(Sender sender) {
        this.sender = sender;
    }

    @XmlElement(name = "receivers", namespace = "http://namespaceGrandvil.com/System")
    public void setReceivers(Receivers receivers) {
        this.receivers = receivers;
    }

    @XmlElement(name = "content", namespace = "http://namespaceGrandvil.com/System")
    public void setContent(Content content) {
        this.content = content;
    }

    @XmlElement(name = "time", namespace = "http://namespaceGrandvil.com/System")
    public void setTime(Date time) {
        this.time = time;
    }

    public Sender getSender() {
        return sender;
    }

    public Receivers getReceivers() {
        return receivers;
    }

    public Date getTime() {
        return time;
    }

    public Content getContent() {
        return content;
    }
}