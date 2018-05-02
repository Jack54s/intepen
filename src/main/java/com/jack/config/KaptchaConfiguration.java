package com.jack.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * Created by 11407 on 2/002.
 */
@Configuration
public class KaptchaConfiguration {

    @Bean(value = "captchaProducer")
    public DefaultKaptcha createKaptchaProducer(){
        DefaultKaptcha captchaProducer = new DefaultKaptcha();

        Properties properties = new Properties();

        properties.setProperty("kaptcha.border", "yes");
        properties.setProperty("kaptcha.border.color", "105,179,90");
        properties.setProperty("kaptcha.textproducer.font.color", "blue");
        properties.setProperty("kaptcha.image.width", "125");
        properties.setProperty("kaptcha.image.height", "55");
        properties.setProperty("kaptcha.textproducer.font.size", "45");
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");

        Config captchaConfig = new Config(properties);

        captchaProducer.setConfig(captchaConfig);

        return captchaProducer;
    }
}
