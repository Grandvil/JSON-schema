import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "chatHistory", namespace = "http://namespaceGrandvil.com/System")
public class MessageList {
    List<Message> messageList;

    public List<Message> getMessageList() {
        return messageList;
    }

    @XmlElement(name = "message", namespace = "http://namespaceGrandvil.com/System")
    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }
}
