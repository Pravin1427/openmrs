package org.openmrs.module.helloworld;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.openmrs.BaseOpenmrsObject;
import org.openmrs.User;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "helloworld.HelloWorldMessage")
@Table(name = "helloworld_messages_v2")
public class HelloWorldMessage extends BaseOpenmrsObject {

    @Id
    @GeneratedValue(generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "message_id")
    private Integer id;

    @Column(name = "message", length = 255, nullable = false)
    private String message;

    @ManyToOne
    @JoinColumn(name = "creator", nullable = false, updatable = false)
    private User creator;

    @Column(name = "date_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @ManyToOne
    @JoinColumn(name = "changed_by")
    private User changedBy;

    @Column(name = "date_changed")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateChanged;

    @Column(name = "voided", nullable = false)
    @Type(type = "yes_no")
    private Boolean voided = false;

    @ManyToOne
    @JoinColumn(name = "voided_by")
    private User voidedBy;

    @Column(name = "date_voided")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateVoided;

    @Column(name = "void_reason", length = 255)
    private String voidReason;

    @Column(name = "uuid", length = 38, nullable = false, unique = true)
    private String uuid;



    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
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

    public User getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(User changedBy) {
        this.changedBy = changedBy;
    }

    public Date getDateChanged() {
        return dateChanged;
    }

    public void setDateChanged(Date dateChanged) {
        this.dateChanged = dateChanged;
    }

    public Boolean getVoided() {
        return voided;
    }

    public void setVoided(Boolean voided) {
        this.voided = voided;
    }

    public User getVoidedBy() {
        return voidedBy;
    }

    public void setVoidedBy(User voidedBy) {
        this.voidedBy = voidedBy;
    }

    public Date getDateVoided() {
        return dateVoided;
    }

    public void setDateVoided(Date dateVoided) {
        this.dateVoided = dateVoided;
    }

    public String getVoidReason() {
        return voidReason;
    }

    public void setVoidReason(String voidReason) {
        this.voidReason = voidReason;
    }

    @Override
    public String getUuid() {
        return uuid;
    }

    @Override
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "HelloWorldMessage [id=" + id + ", message=" + message + ", uuid=" + uuid + "]";
    }
}