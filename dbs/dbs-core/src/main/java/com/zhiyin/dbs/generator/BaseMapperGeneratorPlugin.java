package com.zhiyin.dbs.generator;

import java.util.List;

import com.zhiyin.dbs.common.base.BaseMapper;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 * @author wangmengjun
 *
 */
public class BaseMapperGeneratorPlugin extends PluginAdapter {

	public boolean validate(List<String> warnings) {
		return true;
	}

	/**
	 * 生成dao
	 */
	@Override
	public boolean clientGenerated(Interface interfaze,
			TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		/**
		 * 主键默认采用java.lang.Integer
		 */
		FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType("BaseMapper<"
				+ introspectedTable.getBaseRecordType() + ","
				+ introspectedTable.getExampleType() + ","
				+ "java.lang.Long" + ">");
		FullyQualifiedJavaType imp = new FullyQualifiedJavaType(
				BaseMapper.class.getName());
		/**
		 * 添加 extends MybatisBaseMapper
		 */
		interfaze.addSuperInterface(fqjt);

		/**
		 * 添加import my.mabatis.example.base.MybatisBaseMapper;
		 */
		interfaze.addImportedType(imp);
		/**
		 * 方法不需要
		 */
		interfaze.getMethods().clear();
		interfaze.getAnnotations().clear();
		return true;
	}

}