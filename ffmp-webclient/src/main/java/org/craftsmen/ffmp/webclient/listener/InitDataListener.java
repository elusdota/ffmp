package org.craftsmen.ffmp.webclient.listener;

import com.jrtech.templates.services.SystemInitializeService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.config.AppenderRef;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.config.builder.api.*;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Created by suelmer on 2016/7/1.
 */
@Service
public class InitDataListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private SystemInitializeService initService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logupdate();
//        initService.clearData();
//        initService.createData();
    }

    public void logupdate() {
        ConfigurationBuilder<BuiltConfiguration> builder = ConfigurationBuilderFactory.newConfigurationBuilder();

        builder.setStatusLevel(Level.INFO);
        builder.setConfigurationName("RollingBuilder");
// create a console appender
        AppenderComponentBuilder appenderBuilder = builder.newAppender("Stdout", "CONSOLE").addAttribute("target",
                ConsoleAppender.Target.SYSTEM_OUT);
        appenderBuilder.add(builder.newLayout("PatternLayout")
                .addAttribute("pattern", "%d %p  %m%n"));

// create a rolling file appender
        LayoutComponentBuilder layoutBuilder = builder.newLayout("PatternLayout")
                .addAttribute("pattern", "%d %p %m%n");
        ComponentBuilder triggeringPolicy = builder.newComponent("Policies")
                .addComponent(builder.newComponent("CronTriggeringPolicy").addAttribute("schedule", "0 0 23 * * ?"))
                .addComponent(builder.newComponent("SizeBasedTriggeringPolicy").addAttribute("size", "10M"));
        appenderBuilder = builder.newAppender("RollingFile", "RollingFile")
                .addAttribute("fileName", "../logs/target/ffmp.log").addAttribute("immediateFlush", true).addAttribute("bufferedIO", true)
                .addAttribute("filePattern", "../logs/target/archive/ffmp-%d{MM-dd-yy}-%i.log.gz")
                .add(layoutBuilder)
                .addComponent(triggeringPolicy);
        builder.add(appenderBuilder);
// create the new logger
        builder.add(builder.newLogger("TestLogger", Level.DEBUG)
                .add(builder.newAppenderRef("RollingFile"))
                .addAttribute("additivity", false));

        builder.add(builder.newRootLogger(Level.INFO)
                .add(builder.newAppenderRef("RollingFile")));
        BuiltConfiguration configuration = builder.build();
        //启动日志配置
        configuration.getAppender("RollingFile");
        final LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        final Configuration config = ctx.getConfiguration();
        Appender appender = configuration.getAppender("RollingFile");
        appender.start();
        updateLoggers(appender, config);
    }

    private void updateLoggers(final Appender appender, final Configuration config) {
        final Level level = null;
        final Filter filter = null;
        for (final LoggerConfig loggerConfig : config.getLoggers().values()) {
            loggerConfig.addAppender(appender, level, filter);
        }
        config.getRootLogger().addAppender(appender, level, filter);
    }
}
