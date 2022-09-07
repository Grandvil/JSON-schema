import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

@XmlRootElement(name = "group", namespace = "http://namespaceGrandvil.com/System")
@XmlType(propOrder = {"dateOfCreation", "title", "admins"})
public class Group {
    Date dateOfCreation;
    String title;
    Admins admins;

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    @XmlElement(name = "dateOfCreation", namespace = "http://namespaceGrandvil.com/System")
    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public String getTitle() {
        return title;
    }

    public Admins getAdmins() {
        return admins;
    }

    @XmlElement(name = "title", namespace = "http://namespaceGrandvil.com/System")
    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement(name = "admins", namespace = "http://namespaceGrandvil.com/System")
    public void setAdmins(Admins admins) {
        this.admins = admins;
    }


}
