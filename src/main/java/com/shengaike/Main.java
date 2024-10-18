package com.shengaike;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class Main {
    public static void main(String[] args) {
        AutoGenerator autoGenerator = new AutoGenerator();
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("aA123456");
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/swiftManagerPro?useUnicode=true&characterEncoding=UTF-8");
        autoGenerator.setDataSource(dataSourceConfig);

        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOpen(false);
        globalConfig.setOutputDir(System.getProperty("user.dir")+"/src/main/java");
        globalConfig.setAuthor("Hardy");
        globalConfig.setServiceName("%sService");
        autoGenerator.setGlobalConfig(globalConfig);

        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.shengaike");
        packageConfig.setEntity("entity");
        packageConfig.setMapper("mapper");
        packageConfig.setController("controller");
        packageConfig.setService("service");
        packageConfig.setServiceImpl("service.impl");
        autoGenerator.setPackageInfo(packageConfig);

        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setInclude("user");
//        TableFill tableFill1 = new TableFill("kuaidi_time", FieldFill.INSERT);
//        TableFill tableFill2 = new TableFill("kuaidi_ftime", FieldFill.INSERT);
//        List<TableFill> list = Arrays.asList(tableFill1,tableFill2);
//        strategyConfig.setTableFillList(list);
        autoGenerator.setStrategy(strategyConfig);

        autoGenerator.execute();
    }
}
