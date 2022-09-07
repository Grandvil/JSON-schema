import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "admins", namespace = "http://namespaceGrandvil.com/System")
public class Admins {
    Integer idref;

    public Integer getIdref() {
        return idref;
    }

    @XmlAttribute(name = "idref")
    public void setIdref(Integer idref) {
        this.idref = idref;
    }
}
