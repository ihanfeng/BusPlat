package com.zhiyin.jagent.runner;

import com.sun.tools.attach.VirtualMachine;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.UUID;

public class DemoServer {

	public static void main(String[] args) throws Exception {

		loadAgent();

		String nameOfRunningVM = ManagementFactory.getRuntimeMXBean().getName();

		System.out.println("server pid:" + nameOfRunningVM);

//		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();//可在jconsole中使用
//		mbs.registerMBean(new Controller(), new ObjectName("MyappMBean:name=controller"));

		while (true) {
			runBody();
		}
	}

	public static void loadAgent() {
		String pid = getProcessID();
		try {
			VirtualMachine vm = VirtualMachine.attach(pid);
			String jarFilePath = "jagent-0.0.1-SNAPSHOT.jar";
			jarFilePath = "E:\\Github\\BusPlat\\byteop\\jagent\\target\\jagent-0.0.1-full.jar";
			vm.loadAgent(jarFilePath, "");
//			vm.detach();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static final String getProcessID() {
		RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
		return ""+Integer.valueOf(runtimeMXBean.getName().split("@")[0])
				.intValue();
	}

	public static void runBody() throws InterruptedException {

		try {

			User u = new User();
			u.setName(UUID.randomUUID().toString());
			System.out.println(u.getName());
			u.hello();

		} catch (Exception e) {
			System.out.println("java.lang.ArithmeticException");
		}
		Thread.sleep(5000);
	}



}
