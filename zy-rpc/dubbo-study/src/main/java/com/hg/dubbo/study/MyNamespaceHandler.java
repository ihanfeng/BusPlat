package com.hg.dubbo.study;

import com.hg.dubbo.study.PeopleBeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
public class MyNamespaceHandler extends NamespaceHandlerSupport {
public void init() {
registerBeanDefinitionParser("people", new PeopleBeanDefinitionParser());
}
}