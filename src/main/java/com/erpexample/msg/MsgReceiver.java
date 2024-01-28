package com.erpexample.msg;

import com.erpexample.entity.Contact;
import com.erpexample.entity.ContactGroupMembership;
import com.erpexample.service.AddressBookService;
import com.google.common.eventbus.Subscribe;
import erp.process.definition.Process;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MsgReceiver {

    @Autowired
    private AddressBookService addressBookService;

    @Subscribe
    public void updateGroupAfterContactRemoved(Process process) throws InterruptedException {
        if (!process.getName().equals("com.erpexample.service.AddressBookService.removeContact")) {
            return;
        }
        Contact contact = (Contact) process.getDeletedEntityList().get(0).getEntity();
        List<ContactGroupMembership> contactGroupMembershipList =
                addressBookService.getContactGroupMembershipListByContact(contact.getId());
        for (ContactGroupMembership contactGroupMembership : contactGroupMembershipList) {
            addressBookService.removeContactGroupMembership(contactGroupMembership.getId());
            Thread.sleep(1L);
        }
    }

}
