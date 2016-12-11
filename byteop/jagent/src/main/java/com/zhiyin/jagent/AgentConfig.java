package com.zhiyin.jagent;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wangqinghui on 2016/12/8.
 */
public class AgentConfig {

    public static final String AGENT_NAME_NOT_PROVIDED = "Agent name not provided.";

    public static final String AGENT_DEFAULT_NAME = "";

    public static List<String> NotModifyMethodNames = Arrays.asList("toString", "finalize", "getClass","equals","wait","main","<init>");


    private static final String ERR_UNKOWN_AGENT = "Agent with provided name does not exist.";

}
