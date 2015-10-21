package org.vr.messenger.model;


import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class Profile {
    private long id;
    private String profileName;
    private Date created;

    public Profile() {
    }

    public Profile(long id, String profileName) {
        this.id = id;
        this.profileName = profileName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
