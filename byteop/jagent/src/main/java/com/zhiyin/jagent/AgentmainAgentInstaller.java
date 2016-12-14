package com.zhiyin.jagent;



import com.hg.awesome.java.agent.PrintBeforeMethodTransformerAsm;
import com.zhiyin.jagent.agent.example.PerfMonXformer;
import com.zhiyin.jagent.agent.example.SleepingClassFileTransformer;
import javassist.*;

import java.lang.instrument.Instrumentation;
import java.util.Arrays;
import java.util.List;

public class AgentmainAgentInstaller {

	private static Instrumentation inst;


	public static void agentmain(String args, Instrumentation instrumentation) throws CannotCompileException {

		if (args == null || args.isEmpty()) {
			System.err.println(AgentConfig.AGENT_NAME_NOT_PROVIDED);
		}

		System.out.println("process was attached agent:" + AgentmainAgentInstaller.class.getName());
		instrumentation.addTransformer(new SleepingClassFileTransformer(),true);

//		instrumentation.addTransformer(new PerfMonXformer(),true);
//		instrumentation.addTransformer(new GetAllMethodTransformer());
//		modify(instrumentation);
//		instrumentation.addTransformer(new PrintBeforeMethodTransformerAsm(),true);
	}


	public static void modify(Instrumentation instrumentation) {
		Class[] classes = instrumentation.getAllLoadedClasses();
		for (Class cls : classes) {
			String className = cls.getName();
			String compareClass = className.replace('/', '.');

			if(cls.isInterface()){
				continue;
			}

			if (cls.getName().startsWith("com.zhiyin")) {
				CtClass clazz = null;
				try {
					clazz = ClassPool.getDefault().get(compareClass);
					CtMethod[] methods = clazz.getMethods();
					for (CtMethod method : methods) {
						if(methodCouldModify(method) == false){
							continue;
						}

						String methodName = method.getName();

						System.out.println("process method：" +className +"." + methodName);

												method.insertBefore( " System.out.println(\" agent insert syso!!\"); ");


//						method.insertAt(1, " System.out.println(\" agent insert syso!!\"); ");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
	}



	public static boolean methodCouldModify(CtMethod ctMethod){

		if( Modifier.isNative(ctMethod.getModifiers()) ){
			return false;
		}

		if( AgentConfig.NotModifyMethodNames.contains(ctMethod.getName())){
			return false;
		}

		return true;
	}

}