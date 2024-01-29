package com.erpexample.web;

import com.erpexample.msg.MsgSender;
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

    @Autowired
    private MsgSender msgSender;

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
        msgSender.sendProcess();
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

    @RequestMapping("/createcontactgroup")
    @ResponseBody
    public CommonVO createContactGroup(String name) {
        addressBookService.createContactGroup(name);
        return CommonVO.success();
    }

    @RequestMapping("/removecontactgroup")
    @ResponseBody
    public CommonVO removeContactGroup(Long id) {
        addressBookService.removeContactGroup(id);
        msgSender.sendProcess();
        return CommonVO.success();
    }

    @RequestMapping("/putcontactintogroup")
    @ResponseBody
    public CommonVO putContactIntoGroup(Long contactId, Long groupId) {
        addressBookService.putContactIntoGroup(contactId, groupId);
        return CommonVO.success();
    }

    @RequestMapping("/removecontactfromgroup")
    @ResponseBody
    public CommonVO removeContactFromGroup(Long contactId, Long groupId) {
        addressBookService.removeContactFromGroup(contactId, groupId);
        return CommonVO.success();
    }

    @RequestMapping("/getcontactgrouplist")
    @ResponseBody
    public CommonVO getContactGroupList() {
        return CommonVO.success(addressBookService.getContactGroupList());
    }

    @RequestMapping("/getcontactlistbygroup")
    @ResponseBody
    public CommonVO getContactListByGroup(Long groupId) {
        return CommonVO.success(addressBookService.getContactListByGroup(groupId));
    }

}
