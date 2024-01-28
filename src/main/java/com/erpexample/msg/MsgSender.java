package com.erpexample.msg;

import com.google.common.eventbus.EventBus;
import erp.ERP;
import erp.process.definition.Process;
import org.springframework.stereotype.Component;

/**
 * @author zheng chengdong
 */
@Component
public class MsgSender {

    EventBus processBus;

    public MsgSender() {
        this.processBus = new EventBus("process");
    }

    public void register(MsgReceiver receiver) {
        processBus.register(receiver);
    }

    public void sendProcess() {
        Process process = ERP.getProcess();
        if (process == null) {
            return;
        }
        processBus.post(process);
    }

}
