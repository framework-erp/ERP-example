package com.erpexample.repository.dto;

import com.erpexample.entity.ContactGroup;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("contactgroup")
public class ContactGroupDTO {
    @Id
    private long id;
    private String name;

    public ContactGroup toContactGroup() {
        ContactGroup contactGroup = new ContactGroup();
        contactGroup.setId(id);
        contactGroup.setName(name);
        return contactGroup;
    }

}
