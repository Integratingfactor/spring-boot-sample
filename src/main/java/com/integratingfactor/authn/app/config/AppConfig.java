package com.integratingfactor.authn.app.config;

import java.util.logging.Logger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

import com.integratingfactor.authn.sessions.GdsSessionRepository;

@EnableSpringHttpSession
@Configuration
public class AppConfig {
    private static Logger LOG = Logger.getLogger(AppConfig.class.getName());

    @Bean
    public GdsSessionRepository sessionRepository() {
        LOG.info("Creating instance of GdsSessionRepository");
        return new GdsSessionRepository();
    }

    /**
     * register Spring session with http container
     * 
     * @author gnulib
     *
     */
    public class Initializer extends AbstractHttpSessionApplicationInitializer {

    }
}
