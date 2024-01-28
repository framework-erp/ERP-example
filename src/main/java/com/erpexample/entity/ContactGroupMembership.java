package com.erpexample.entity;

public class ContactGroupMembership {
    private long id;
    private long contactId;
    private long contactGroupId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
    }

    public long getContactGroupId() {
        return contactGroupId;
    }

    public void setContactGroupId(long contactGroupId) {
        this.contactGroupId = contactGroupId;
    }
}
