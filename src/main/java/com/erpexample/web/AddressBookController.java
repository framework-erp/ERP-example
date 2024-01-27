package com.erpexample.web;

import com.erpexample.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    @RequestMapping("/addcontact")
    @ResponseBody
    public CommonVO addContact(String name, String phoneNumber) {
        addressBookService.addContact(name, phoneNumber);
        return CommonVO.success();
    }

    @RequestMapping("/removecontact")
    @ResponseBody
    public CommonVO removeContact(Long id) {
        addressBookService.removeContact(id);
        return CommonVO.success();
    }

    @RequestMapping("/getcontactlist")
    @ResponseBody
    public CommonVO getContactList(String name, int pageNum, int pageSize) {
        return CommonVO.success(addressBookService.getContactList(name, pageNum, pageSize));
    }

    @RequestMapping("/getcontact")
    @ResponseBody
    public CommonVO getContact(Long id) {
        return CommonVO.success(addressBookService.getContact(id));
    }
}
