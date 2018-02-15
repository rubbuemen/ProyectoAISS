package aiss.myAnimeList;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class VerifyCredentials {

    @XmlElement
    private String id;

    @XmlElement
    private String username;
    
    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

}
