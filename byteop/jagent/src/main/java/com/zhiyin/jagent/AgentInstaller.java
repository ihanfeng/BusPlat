package com.zhiyin.jagent;

import com.hg.awesome.java.agent.GetAllMethodTransformer;
import com.hg.awesome.java.agent.PrintBeforeMethodTransformer;
import com.zhiyin.jagent.agent.example.PerfMonXformer;
import com.zhiyin.jagent.agent.example.SleepingClassFileTransformer;
import com.zhiyin.jagent.agent.example.buddy.AopMethodAgent;
import javassist.CannotCompileException;

import java.lang.instrument.Instrumentation;

public class AgentInstaller {


	public static void premain(String agentArguments,
			Instrumentation instrumentation) {

		System.out.println("process was attached by premain");
		doIns(instrumentation);
	}

	public static void agentmain(String args, Instrumentation instrumentation) throws CannotCompileException {

//		if (args == null || args.isEmpty()) {
//			System.err.println(AgentConfig.AGENT_NAME_NOT_PROVIDED);
//		}

		System.out.println("process was attached agent:" + AgentmainAgentInstaller.class.getName());

		doIns(instrumentation);
	}

	public static void doIns(Instrumentation instrumentation){
//		instrumentation.addTransformer(new SleepingClassFileTransformer(),true);

		new AopMethodAgent().createAgent(null).installOn(instrumentation);
	}
	
}
