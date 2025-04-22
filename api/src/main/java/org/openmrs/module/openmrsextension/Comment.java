package org.openmrs.module.openmrsextension;

import org.openmrs.BaseOpenmrsObject;
import org.openmrs.User;
import org.openmrs.module.helloworld.HelloWorldMessage;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "openmrsextension.Comment")
@Table(name = "openmrsextension_comments")
public class Comment extends BaseOpenmrsObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Integer id;

    @Column(name = "text", nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "message_id", nullable = false)
    private HelloWorldMessage message;

    @ManyToOne
    @JoinColumn(name = "creator", nullable = false, updatable = false)
    private User creator;

    @Column(name = "date_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Column(name = "uuid", nullable = false, unique = true, length = 38)
    private String uuid;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public HelloWorldMessage getMessage() {
        return message;
    }

    public void setMessage(HelloWorldMessage message) {
        this.message = message;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String getUuid() {
        return uuid;
    }

    @Override
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
