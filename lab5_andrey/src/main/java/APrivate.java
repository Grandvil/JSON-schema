import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement(name = "private", namespace = "http://namespaceGrandvil.com/System")
public class APrivate {
    Date dateOfCreation;

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    @XmlElement(name = "dateOfCreation", namespace = "http://namespaceGrandvil.com/System")
    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
}
