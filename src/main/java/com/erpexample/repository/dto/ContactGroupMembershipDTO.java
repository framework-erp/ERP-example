package com.erpexample.repository.dto;

import com.erpexample.entity.ContactGroupMembership;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("contactgroupmembership")
public class ContactGroupMembershipDTO {
    @Id
    private long id;
    @Column("contactId")
    private long contactId;
    @Column("contactGroupId")
    private long contactGroupId;

    public ContactGroupMembership toContactGroupMembership() {
        ContactGroupMembership contactGroupMembership = new ContactGroupMembership();
        contactGroupMembership.setId(id);
        contactGroupMembership.setContactId(contactId);
        contactGroupMembership.setContactGroupId(contactGroupId);
        return contactGroupMembership;
    }

}
