package com.mars.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by mars on 2015/8/25.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class Alert {

    private int status = 0;
    private Date created = new Date();
    private int createdBy = 72;
    private Date updated = new Date();
    private int updatedBy = 72;
    private int sender = 72;
    private int userContact;
    private int type = 0;
    private Date sendDate = new Date();
    private String subject = "From Hr Alert";
    private String description;
    private String hasRead = "Y";

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alert alert = (Alert) o;
        return Objects.equals(status, alert.status) &&
                Objects.equals(sender, alert.sender) &&
                Objects.equals(userContact, alert.userContact) &&
                Objects.equals(type, alert.type) &&
                Objects.equals(sendDate, alert.sendDate) &&
                Objects.equals(subject, alert.subject) &&
                Objects.equals(hasRead, alert.hasRead);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, sender, userContact, type, sendDate, subject, hasRead);
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("status     ", status);
        map.put("created    ", created);
        map.put("createdBy  ", createdBy);
        map.put("updated    ", updated);
        map.put("updatedBy  ", updatedBy);
        map.put("sender     ", sender);
        map.put("userContact", userContact);
        map.put("type       ", type);
        map.put("sendDate   ", sendDate);
        map.put("subject    ", subject);
        map.put("description", description);
        map.put("hasRead    ", hasRead);
        return map;
    }

    public static AlertBuilder builder() {
        return new Alert.AlertBuilder();
    }

    public static class AlertBuilder {
        private int status = 0;
        private Date created = new Date();
        private int createdBy = 72;
        private Date updated = new Date();
        private int updatedBy = 72;
        private int sender = 72;
        private int userContact;
        private int type = 0;
        private Date sendDate = new Date();
        private String subject = "From Hr Alert";
        private String description;
        private String hasRead = "Y";

        AlertBuilder() {
        }

        public Alert.AlertBuilder status(int status) {
            this.status = status;
            return this;
        }

        public Alert.AlertBuilder created(Date created) {
            this.created = created;
            return this;
        }

        public Alert.AlertBuilder createdBy(int createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public Alert.AlertBuilder updated(Date updated) {
            this.updated = updated;
            return this;
        }

        public Alert.AlertBuilder updatedBy(int updatedBy) {
            this.updatedBy = updatedBy;
            return this;
        }

        public Alert.AlertBuilder sender(int sender) {
            this.sender = sender;
            return this;
        }

        public Alert.AlertBuilder userContact(int userContact) {
            this.userContact = userContact;
            return this;
        }

        public Alert.AlertBuilder type(int type) {
            this.type = type;
            return this;
        }

        public Alert.AlertBuilder sendDate(Date sendDate) {
            this.sendDate = sendDate;
            return this;
        }

        public Alert.AlertBuilder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public Alert.AlertBuilder description(String description) {
            this.description = description;
            return this;
        }

        public Alert.AlertBuilder hasRead(String hasRead) {
            this.hasRead = hasRead;
            return this;
        }

        public Alert build() {
            return new Alert(this.status, this.created, this.createdBy, this.updated, this.updatedBy, this.sender, this.userContact, this.type, this.sendDate, this.subject, this.description, this.hasRead);
        }

        public String toString() {
            return "Alert.AlertBuilder(status=" + this.status + ", created=" + this.created + ", createdBy=" + this.createdBy + ", updated=" + this.updated + ", updatedBy=" + this.updatedBy + ", sender=" + this.sender + ", userContact=" + this.userContact + ", type=" + this.type + ", sendDate=" + this.sendDate + ", subject=" + this.subject + ", description=" + this.description + ", hasRead=" + this.hasRead + ")";
        }
    }
}