package com.erpexample;

import com.erpexample.msg.MsgReceiver;
import com.erpexample.msg.MsgSender;
import erp.ERP;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ExampleApp {
    public static void main(String[] args) {
        ERP.useAnnotation();

        ApplicationContext context = SpringApplication.run(ExampleApp.class, args);
        MsgSender msgSender = context.getBean(MsgSender.class);
        MsgReceiver msgReceiver = context.getBean(MsgReceiver.class);
        msgSender.register(msgReceiver);
    }
}
