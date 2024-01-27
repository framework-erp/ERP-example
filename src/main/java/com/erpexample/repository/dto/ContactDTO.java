package com.erpexample.repository.dto;

import com.erpexample.entity.Contact;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("contact")
public class ContactDTO {
    @Id
    private long id;
    private String name;
    @Column("phoneNumber")
    private String phoneNumber;

    public ContactDTO() {
    }

    public ContactDTO(Contact contact) {
        this.id = contact.getId();
        this.name = contact.getName();
        this.phoneNumber = contact.getPhoneNumber();
    }

    public Contact toContact() {
        Contact contact = new Contact();
        contact.setId(id);
        contact.setName(name);
        contact.setPhoneNumber(phoneNumber);
        return contact;
    }
}
