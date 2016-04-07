package com.integratingfactor.authn.app.config;

import java.util.logging.Logger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

import com.integratingfactor.authn.sessions.MemcachedSessionRepository;

@EnableSpringHttpSession
@Configuration
public class AppConfig {
    private static Logger LOG = Logger.getLogger(AppConfig.class.getName());

    MemcachedSessionRepository sessionRepo = new MemcachedSessionRepository();

    @Bean
    public MemcachedSessionRepository sessionRepository() {
        LOG.info("Creating instance of GaeSessionRepository");
        return sessionRepo;
    }

    // @Bean
    // public SessionRepositoryFilter<GaeSession>
    // springSessionRepositoryFilter() {
    // LOG.info("Creating instance of SessionRepositoryFilter");
    // return new SessionRepositoryFilter<GaeSession>(sessionRepo);
    // }

    /**
     * register Spring session with http container
     * 
     * @author gnulib
     *
     */
    public class Initializer extends AbstractHttpSessionApplicationInitializer {

    }
}
