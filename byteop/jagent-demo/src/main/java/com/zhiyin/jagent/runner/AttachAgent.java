package com.zhiyin.jagent.runner;

import com.sun.tools.attach.VirtualMachine;
import pid.PidUitl;

public class AttachAgent {

    public static void main(String[] args) throws  Exception {

//        String nameOfRunningVM = ManagementFactory.getRuntimeMXBean().getName();
//
//        System.out.println(nameOfRunningVM);

        // server id
        int pid = PidUitl.getProcess(DemoServer.class);


        VirtualMachine vm = VirtualMachine.attach( pid + "");

        String agentPath = "jagent-0.0.1-SNAPSHOT.jar";
        agentPath = "E:\\Github\\BusPlat\\byteop\\jagent\\target\\jagent-0.0.1-full.jar";
        vm.loadAgent( agentPath );
    }

}