import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlRootElement(name = "System", namespace = "http://namespaceGrandvil.com/System")
@XmlType(propOrder = {"personList", "chatHistory", "result"})
public class SystemXML {
    PersonList personList;
    ChatHistory chatHistory;
    int result;

    public PersonList getPersonList() {
        return personList;
    }

    public int getResult() {
        result = personList.getResult();
        return result;
    }

    public ChatHistory getChatHistory() {
        return chatHistory;
    }

    @XmlElement(name = "personList", namespace = "http://namespaceGrandvil.com/System")
    public void setPersonList(PersonList personList) {
        this.personList = personList;
    }

    @XmlElement(name = "result", namespace = "http://namespaceGrandvil.com/System")
    public void setResult(int result) {
        this.result = result;
    }

    @XmlElement(name = "chatHistory", namespace = "http://namespaceGrandvil.com/System")
    public void setChatHistory(ChatHistory chatHistory) {
        this.chatHistory = chatHistory;
    }
}

