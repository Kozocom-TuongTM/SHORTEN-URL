package models.mongo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.PrePersist;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Common {
    // auto-generated, if not set (see ObjectId)
    @Id
    protected ObjectId id = null;
    protected LocalDateTime created;
    protected LocalDateTime updated;

    public Common() {
        this.id = new ObjectId();
    }

    public void setCreated(LocalDateTime date) {
        this.created = date;
    }

    public LocalDateTime getCreated() {
        return this.created;
    }

    public LocalDateTime getUpdated() {
        return this.updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public void setId(String id) {
        if (id.equals("")) {
            this.id = new ObjectId();
        } else {
            this.id = new ObjectId(id);
        }
    }

    public String getId() {
        return this.id.toString();
    }

    @JsonIgnore
    public ObjectId getObjectId() {
        return this.id;
    }

    @PrePersist
    private void prePersist() {
        if (this.created == null) {
            this.created = LocalDateTime.now();
        }
        this.updated = LocalDateTime.now();
    }

}
