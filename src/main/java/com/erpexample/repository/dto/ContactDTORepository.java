package com.erpexample.repository.dto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactDTORepository extends CrudRepository<ContactDTO, Long> {
    Page<ContactDTO> findAll(Pageable pageable);

    Page<ContactDTO> findAllByNameContaining(String name, Pageable pageable);
}
