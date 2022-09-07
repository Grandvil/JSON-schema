import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "chat", namespace = "http://namespaceGrandvil.com/System")
public class Chat {
    Integer id;
    MessageList messageList;
    Type type;

    public MessageList getMessageList() {
        return messageList;
    }

    public Integer getId() {
        return id;
    }

    @XmlAttribute(name = "id")
    public void setId(Integer id) {
        this.id = id;
    }

    @XmlElement(name = "messageList", namespace = "http://namespaceGrandvil.com/System")
    public void setMessageList(MessageList messageList) {
        this.messageList = messageList;
    }

    public Type getType() {
        return type;
    }

    @XmlElement(name = "type", namespace = "http://namespaceGrandvil.com/System")
    public void setType(Type type) {
        this.type = type;
    }


}
