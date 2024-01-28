package com.erpexample.repository;

import com.erpexample.entity.ContactGroupMembership;
import com.erpexample.repository.dto.ContactGroupMembershipDTO;
import com.erpexample.repository.dto.ContactGroupMembershipDTORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContactGroupMembershipQueryRepository {

    @Autowired
    private ContactGroupMembershipDTORepository contactGroupMembershipDTORepository;

    public List<ContactGroupMembership> findAllByContactId(long contactId) {
        List<ContactGroupMembershipDTO> dtoList =
                contactGroupMembershipDTORepository.findAllByContactId(contactId);
        List<ContactGroupMembership> contactGroupMembershipList = new ArrayList<>();
        for (ContactGroupMembershipDTO dto : dtoList) {
            contactGroupMembershipList.add(dto.toContactGroupMembership());
        }
        return contactGroupMembershipList;
    }
}
