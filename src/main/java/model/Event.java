package model;

import java.sql.Timestamp;

public class Event {

    private Long id;
    private Long userId;
    private Long fileId;
    private Timestamp created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", useId=" + userId +
                ", fileId=" + fileId +
                ", created=" + created +
                '}';
    }
}
