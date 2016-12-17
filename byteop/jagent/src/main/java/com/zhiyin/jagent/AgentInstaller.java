package com.zhiyin.jagent;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.common.collect.Maps;
import com.zhiyin.jagent.transformer.ClassPathTransformer;
import com.zhiyin.jagent.transformer.TelemetryTransformer;
import com.zhiyin.jagent.transformer.handler.LogbackTraceLoggerHandler;
import com.zhiyin.jagent.transformer.handler.config.TelemetryConfiguration;
import com.zhiyin.jagent.util.ClassPathUtil;
import javassist.CannotCompileException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.Instrumentation;
import java.util.Map;

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

		System.out.println("process was attached by agentmain:" + AgentInstaller.class.getName());

		doIns(instrumentation);
	}

	public static void doIns(Instrumentation instrumentation){


		handlerTrans(instrumentation);

		instrumentation.addTransformer(new ClassPathTransformer());
//		instrumentation.addTransformer(new SleepingClassFileTransformer(),true);

//		new AopMethodAgent().createAgent(null).installOn(instrumentation);

		Class[] classes = instrumentation.getAllLoadedClasses();
		for (Class cls : classes) {
			boolean modify = ClazzUtil.classCouldModify(cls.getName());
			if(modify){
				System.out.println(ClassPathUtil.getClassAbsPath(cls));
			}
		}
	}



	private static void handlerTrans(Instrumentation instrumentation ) {

		try {
			InputStream schemaIS = AgentInstaller.class.getClassLoader().getResourceAsStream("META-INF/agent/config-default.yml");
			ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
			TelemetryConfiguration config = mapper.readValue(schemaIS, TelemetryConfiguration.class);
			if(config == null){
				return ;
			}
			final TelemetryTransformer transformer = new TelemetryTransformer();
			transformer.setHandlers( config.getHandlers() );

			instrumentation.addTransformer(transformer);
		}catch (Exception e){
			System.out.println("handler error.");
		}

	}

}
