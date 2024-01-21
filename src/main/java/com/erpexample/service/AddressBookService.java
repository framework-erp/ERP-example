package com.erpexample.service;

import com.erpexample.entity.Contact;
import com.erpexample.repository.ContactRepository;
import erp.annotation.Process;
import erp.repository.factory.RepositoryFactory;
import erp.springjdbc.mysql.MySQLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class AddressBookService {

    private ContactRepository contactRepository;

    @Autowired
    public AddressBookService(JdbcTemplate jdbcTemplate) {
        contactRepository = RepositoryFactory.newInstance(ContactRepository.class,
                        new MySQLRepository(jdbcTemplate, Contact.class));
    }

    @Process
    public void addContact(long id, String name, String phoneNumber) {
        Contact contact = new Contact();
        contact.setId(id);
        contact.setName(name);
        contact.setPhoneNumber(phoneNumber);
        contactRepository.put(contact);
    }

}
