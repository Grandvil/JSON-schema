import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "content", namespace = "http://namespaceGrandvil.com/System")
public class Content {
    String type;
    Boolean messageEncoded;
    String body;

    public String getType() {
        return type;
    }

    public Boolean getMessageEncoded() {
        return messageEncoded;
    }

    @XmlAttribute(name = "type")
    public void setType(String type) {
        this.type = type;
    }

    @XmlAttribute(name = "messageEncoded")
    public void setMessageEncoded(Boolean messageEncoded) {
        this.messageEncoded = messageEncoded;
    }

    @XmlElement(name = "body", namespace = "http://namespaceGrandvil.com/System")
    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }
}
