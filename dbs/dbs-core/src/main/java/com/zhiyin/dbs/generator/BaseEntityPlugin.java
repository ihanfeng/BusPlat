
package com.zhiyin.dbs.generator;

import java.util.List;
import java.util.Properties;

import com.zhiyin.dbs.common.base.BaseEntity;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.TopLevelClass;

public class BaseEntityPlugin extends PluginAdapter {

    private FullyQualifiedJavaType baseEntity;

    public BaseEntityPlugin() {
        super();
        baseEntity = new FullyQualifiedJavaType(BaseEntity.class.getName()); //$NON-NLS-1$
    }

    public boolean validate(List<String> warnings) {
        // this plugin is always valid
        return true;
    }

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);

    }
    
    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable) {
        makeSerializable(topLevelClass, introspectedTable);
        return true;
    }

    @Override
    public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable) {
        makeSerializable(topLevelClass, introspectedTable);
        return true;
    }

    @Override
    public boolean modelRecordWithBLOBsClassGenerated(
            TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        makeSerializable(topLevelClass, introspectedTable);
        return true;
    }

    protected void makeSerializable(TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable) {


            topLevelClass.addImportedType(baseEntity);
            topLevelClass.addSuperInterface(baseEntity);

    }

    public static void main(String[] args) {
        System.out.println(BaseEntity.class.getName());
    }
}
