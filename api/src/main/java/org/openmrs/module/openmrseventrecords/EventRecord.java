package org.openmrs.module.openmrseventrecords;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "event_records") // Map to the actual database table name
public class EventRecord { // Removed 'extends BaseOpenmrsData'

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // Matches the 'id' column in your event_records table
    private Integer id;

    @Column(name = "uuid", unique = true, nullable = false, length = 38)
    private String uuid;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "timestamp", nullable = false)
    private Date timestamp;

    @Column(name = "uri") // Can be null as per your table data
    private String uri;

    @Column(name = "object", columnDefinition = "TEXT") // Use TEXT for potentially large JSON strings
    private String object;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "date_created", nullable = false) // Directly maps to 'date_created' column
    private Date dateCreated;

    @Column(name = "tags") // Can be null
    private String tags;

    // --- Getters and Setters ---
    // These are now explicit, as they are no longer inherited from BaseOpenmrsData

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDateCreated() { // Renamed from getEventDateCreated to match column name directly
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) { // Renamed from setEventDateCreated
        this.dateCreated = dateCreated;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}