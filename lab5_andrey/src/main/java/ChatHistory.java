import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "chatHistory", namespace = "http://namespaceGrandvil.com/System")
public class ChatHistory {
    List<Chat> chatList;

    public List<Chat> getChatList() {
        return chatList;
    }

    @XmlElement(name = "chat", namespace = "http://namespaceGrandvil.com/System")
    public void setChatList(List<Chat> chatList) {
        this.chatList = chatList;
    }


}
