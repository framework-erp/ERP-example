package com.erpexample.web;

import com.erpexample.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/addressBook")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    @RequestMapping("/addcontact")
    @ResponseBody
    public CommonVO addContact(String name, String phoneNumber) {
        addressBookService.addContact(name, phoneNumber);
        return CommonVO.success();
    }
}
