package com.erpexample.repository.dto;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactGroupDTORepository extends CrudRepository<ContactGroupDTO, Long> {
}
