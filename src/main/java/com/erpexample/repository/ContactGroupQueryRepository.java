package com.erpexample.repository;

import com.erpexample.entity.ContactGroup;
import com.erpexample.repository.dto.ContactGroupDTO;
import com.erpexample.repository.dto.ContactGroupDTORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContactGroupQueryRepository {

    @Autowired
    private ContactGroupDTORepository contactGroupDTORepository;

    public List<ContactGroup> findAll() {
        Iterable<ContactGroupDTO> dtoList = contactGroupDTORepository.findAll();
        List<ContactGroup> contactGroups = new ArrayList<>();
        dtoList.forEach(dto -> contactGroups.add(dto.toContactGroup()));
        return contactGroups;
    }
}
