import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "type", namespace = "http://namespaceGrandvil.com/System")
public class Type {
    Group group;
    APrivate aPrivate;

    public Group getGroup() {
        return group;
    }

    public APrivate getaPrivate() {
        return aPrivate;
    }

    @XmlElement(name = "group", namespace = "http://namespaceGrandvil.com/System")
    public void setGroup(Group group) {
        this.group = group;
    }

    @XmlElement(name = "private", namespace = "http://namespaceGrandvil.com/System")
    public void setaPrivate(APrivate aPrivate) {
        this.aPrivate = aPrivate;
    }
}
