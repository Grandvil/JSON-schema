import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "person", namespace = "http://namespaceGrandvil.com/System")
public class Person {
    String firstName;
    String lastName;
    String pnoneNumber;
    int id;

    @XmlElement(name = "firstName", namespace = "http://namespaceGrandvil.com/System")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @XmlElement(name = "lastName", namespace = "http://namespaceGrandvil.com/System")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @XmlElement(name = "pnoneNumber", namespace = "http://namespaceGrandvil.com/System")
    public void setPnoneNumber(String pnoneNumber) {
        this.pnoneNumber = pnoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPnoneNumber() {
        return pnoneNumber;
    }

    public int getId() {
        return id;
    }

    @XmlAttribute(name = "id")
    public void setId(int id) {
        this.id = id;
    }
}