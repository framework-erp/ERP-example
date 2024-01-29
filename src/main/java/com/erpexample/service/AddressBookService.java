package com.erpexample.service;

import com.erpexample.entity.Contact;
import com.erpexample.entity.ContactGroup;
import com.erpexample.entity.ContactGroupMembership;
import com.erpexample.repository.*;
import dml.id.entity.SnowflakeIdGenerator;
import erp.annotation.Process;
import erp.repository.factory.RepositoryFactory;
import erp.repository.factory.SingletonRepositoryFactory;
import erp.springjdbc.mysql.MySQLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddressBookService {

    private ContactRepository contactRepository;
    private ContactIdGeneratorRepository contactIdGeneratorRepository;
    private ContactGroupRepository contactGroupRepository;
    private ContactGroupIdGeneratorRepository contactGroupIdGeneratorRepository;
    private ContactGroupMembershipRepository contactGroupMembershipRepository;
    private ContactGroupMembershipIdGeneratorRepository contactGroupMembershipIdGeneratorRepository;

    @Autowired
    private ContactQueryRepository contactQueryRepository;

    @Autowired
    private ContactGroupMembershipQueryRepository contactGroupMembershipQueryRepository;

    @Autowired
    private ContactGroupQueryRepository contactGroupQueryRepository;

    @Autowired
    public AddressBookService(JdbcTemplate jdbcTemplate) {
        contactRepository = RepositoryFactory.newInstance(ContactRepository.class,
                new MySQLRepository<>(jdbcTemplate, Contact.class));
        contactIdGeneratorRepository = SingletonRepositoryFactory.newInstance(ContactIdGeneratorRepository.class,
                new SnowflakeIdGenerator(1L) {
                });
        contactGroupRepository = RepositoryFactory.newInstance(ContactGroupRepository.class,
                new MySQLRepository<>(jdbcTemplate, ContactGroup.class));
        contactGroupIdGeneratorRepository = SingletonRepositoryFactory.newInstance(ContactGroupIdGeneratorRepository.class,
                new SnowflakeIdGenerator(1L) {
                });
        contactGroupMembershipRepository = RepositoryFactory.newInstance(ContactGroupMembershipRepository.class,
                new MySQLRepository<>(jdbcTemplate, ContactGroupMembership.class));
        contactGroupMembershipIdGeneratorRepository = SingletonRepositoryFactory.newInstance(ContactGroupMembershipIdGeneratorRepository.class,
                new SnowflakeIdGenerator(1L) {
                });
    }

    @Process
    public void addContact(String name, String phoneNumber) {
        Contact contact = new Contact();
        contact.setId(contactIdGeneratorRepository.take().generateId());
        contact.setName(name);
        contact.setPhoneNumber(phoneNumber);
        contactRepository.put(contact);
    }

    @Process
    public void removeContact(Long id) {
        contactRepository.remove(id);
    }

    public Contact getContact(Long id) {
        return contactQueryRepository.findById(id);
    }

    public List<Contact> getContactList(String name, int pageNum, int pageSize) {
        return contactQueryRepository.findAll(name, pageNum, pageSize);
    }

    @Process
    public void createContactGroup(String name) {
        ContactGroup contactGroup = new ContactGroup();
        contactGroup.setId(contactGroupIdGeneratorRepository.take().generateId());
        contactGroup.setName(name);
        contactGroupRepository.put(contactGroup);
    }

    @Process
    public void removeContactGroup(Long id) {
        contactGroupRepository.remove(id);
    }

    @Process
    public void putContactIntoGroup(Long contactId, Long groupId) {
        ContactGroupMembership contactGroupMembership = new ContactGroupMembership();
        contactGroupMembership.setId(contactGroupMembershipIdGeneratorRepository.take().generateId());
        contactGroupMembership.setContactId(contactId);
        contactGroupMembership.setContactGroupId(groupId);
        contactGroupMembershipRepository.put(contactGroupMembership);
    }

    public List<ContactGroupMembership> getContactGroupMembershipListByContact(long contactId) {
        return contactGroupMembershipQueryRepository.findAllByContactId(contactId);
    }

    @Process
    public void removeContactGroupMembership(long contactId) {
        contactGroupMembershipRepository.remove(contactId);
    }

    public List<ContactGroupMembership> getContactGroupMembershipListByContactGroup(long groupId) {
        return contactGroupMembershipQueryRepository.findAllByContactGroupId(groupId);
    }

    @Process
    public void removeContactFromGroup(Long contactId, Long groupId) {
        ContactGroupMembership contactGroupMembership
                = contactGroupMembershipQueryRepository.findByContactIdAndContactGroupId(contactId, groupId);
        contactGroupMembershipRepository.remove(contactGroupMembership.getId());
    }

    public List<ContactGroup> getContactGroupList() {
        return contactGroupQueryRepository.findAll();
    }

    public List<Contact> getContactListByGroup(Long groupId) {
        return contactQueryRepository.findAllByGroupId(groupId);
    }
}
