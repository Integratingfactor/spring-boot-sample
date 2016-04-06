package com.integratingfactor.authn.app.config;

import java.util.logging.Logger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;

import com.integratingfactor.authn.sessions.GaeSessionRepository;

@EnableSpringHttpSession
@Configuration
public class AppConfig {
    private static Logger LOG = Logger.getLogger(AppConfig.class.getName());

    GaeSessionRepository sessionRepo = new GaeSessionRepository();

    @Bean
    public GaeSessionRepository sessionRepository() {
        LOG.info("Creating instance of GaeSessionRepository");
        return sessionRepo;
    }

    // @Bean
    // public SessionRepositoryFilter<GaeSession>
    // springSessionRepositoryFilter() {
    // LOG.info("Creating instance of SessionRepositoryFilter");
    // return new SessionRepositoryFilter<GaeSession>(sessionRepo);
    // }
}
