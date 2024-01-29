package com.erpexample.repository;

import com.erpexample.entity.Contact;
import com.erpexample.repository.dto.ContactDTO;
import com.erpexample.repository.dto.ContactDTORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContactQueryRepository {
    @Autowired
    private ContactDTORepository contactDTORepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Contact findById(long id) {
        ContactDTO contactDTO = contactDTORepository.findById(id).orElse(null);
        if (contactDTO == null) {
            return null;
        }
        return contactDTO.toContact();
    }

    public List<Contact> findAll(String name, int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        List<Contact> contacts = new ArrayList<>();
        if (name != null && !name.isEmpty()) {
            contactDTORepository.findAllByNameContaining(name, pageable).forEach(contactDTO -> contacts.add(contactDTO.toContact()));
        } else {
            contactDTORepository.findAll(pageable).forEach(contactDTO -> contacts.add(contactDTO.toContact()));
        }
        return contacts;
    }

    public List<Contact> findAllByGroupId(Long groupId) {
        //groupId为0时，代表查询不在任何分组的联系人
        if (groupId == 0) {
            String sql = "select * from contact where id not in (select contactId from contactgroupmembership)";
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Contact.class));
        }
        String sql = "select c.* from contact c, contactgroupmembership m where c.id = m.contactId and m.contactGroupId = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Contact.class), groupId);
    }
}
