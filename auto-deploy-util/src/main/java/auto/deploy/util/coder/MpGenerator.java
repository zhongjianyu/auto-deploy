package auto.deploy.util.coder;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * 
 * @描述：mybatisplus代码生成器
 *
 * @作者：zhongjy
 *
 * @时间：2017年4月24日 下午12:31:13
 */
public class MpGenerator {

	public static void main(String[] args) {
		// 模块名
		String moduleName = "sys";
		// 表名
		String[] tableName = new String[] { "sys_data_dict", "sys_operate_log" };
		// String[] tableName = new String[] { "aut_user" };
		AutoGenerator mpg = new AutoGenerator();
		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		final String codePath = "E:/developer/coder";
		gc.setOutputDir(codePath);// 代码生成路径
		gc.setFileOverride(true);
		gc.setActiveRecord(true);
		gc.setEnableCache(false);// XML 二级缓存
		gc.setBaseResultMap(true);// XML ResultMap
		gc.setBaseColumnList(true);// XML columList
		gc.setOpen(true);
		gc.setAuthor("zhongjy");
		// 自定义文件命名，注意 %s 会自动填充表实体属性！
		// gc.setMapperName("%sDao");
		// gc.setXmlName("%sDao");
		gc.setServiceName("%sService");
		// gc.setServiceImplName("%sServiceDiy");
		// gc.setControllerName("%sAction");
		gc.setActiveRecord(false);// 不生成实体pkVal()方法
		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setDbType(DbType.MYSQL);
		dsc.setDriverName("com.mysql.jdbc.Driver");
		dsc.setUsername("root");
		dsc.setPassword("1qaz@WSX");
		dsc.setUrl("jdbc:mysql://127.0.0.1:3306/auto-deploy?characterEncoding=utf8");
		mpg.setDataSource(dsc);

		// 策略配置(自定义，源代码有缺陷，重写里面的一个方法)
		// StrategyConfig strategy = new StrategyConfig();
		CustomStrategyConfig strategy = new CustomStrategyConfig();
		// strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
		strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
		strategy.setDbColumnUnderline(true);
		strategy.setInclude(tableName); // 需要生成的表
		// strategy.setExclude(new String[] { "aut_remember_me" }); // 排除生成的表
		// 自定义实体父类
		strategy.setSuperEntityClass("auto.deploy.dao.entity.Entity");
		// 自定义实体，公共字段
		strategy.setSuperEntityColumns(new String[] { "id", "create_time", "last_update_time", "version", "is_delete", "last_update_user_id",
				"last_update_user_name", "create_user_id", "create_user_name" });
		// 自定义 mapper 父类
		// strategy.setSuperMapperClass("com.baomidou.demo.TestMapper");
		// 自定义 service 父类
		// strategy.setSuperServiceClass("com.baomidou.demo.TestService");
		// 自定义 service 实现类父类
		// strategy.setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl");
		// 自定义 controller 父类
		// strategy.setSuperControllerClass("com.baomidou.demo.TestController");
		// 【实体】是否生成字段常量（默认 false）
		// strategy.setEntityColumnConstant(true);
		// 【实体】是否为构建者模型（默认 false）
		// public User setName(String name) {this.name = name; return this;}
		strategy.setEntityBuilderModel(true);
		mpg.setStrategy(strategy);

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setParent("auto.deploy");

		pc.setController("web.controller." + moduleName);
		pc.setEntity("dao.entity." + moduleName);
		pc.setMapper("dao.mapper." + moduleName);
		pc.setXml("dao.mapper." + moduleName + ".xml");
		// pc.setModuleName("aut");
		pc.setService("service." + moduleName);
		pc.setServiceImpl("service." + moduleName + ".impl");

		mpg.setPackageInfo(pc);

		// 自定义模板配置(如果设置null则不生成该模块)
		TemplateConfig tc = new TemplateConfig();
		tc.setController("code_template/controller.java.vm");
		tc.setEntity("code_template/entity.java.vm");
		tc.setMapper("code_template/mapper.java.vm");
		tc.setXml("code_template/mapper.xml.vm");
		tc.setService("code_template/service.java.vm");
		tc.setServiceImpl("code_template/serviceImpl.java.vm");
		mpg.setTemplate(tc);

		// 执行生成
		mpg.execute();
	}

}
