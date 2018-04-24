package com.jack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.jack.ApplicationStartListener;

/**
 * Created by 11407 on 23/023.
 */
@Configuration
public class ApplicationListenConfiguration {

    @Bean
    public ApplicationStartListener applicationStartListener(){
        return new ApplicationStartListener();
    }
}
