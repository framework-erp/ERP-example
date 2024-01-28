package com.erpexample.repository.dto;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactGroupMembershipDTORepository extends CrudRepository<ContactGroupMembershipDTO, Long> {
    List<ContactGroupMembershipDTO> findAllByContactId(long contactId);
}
