package com.integratingfactor.authn.sessions;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.session.SessionRepository;

public class GaeSessionRepository implements SessionRepository<GaeSession> {
    private static Logger LOG = Logger.getLogger(GaeSessionRepository.class.getName());

    // TODO replace this with memcached implementation once we figure out how
    // this works
    Map<String, GaeSession> inMem = new HashMap<String, GaeSession>();

    @Override
    public GaeSession createSession() {
        LOG.info("Request to create new session");
        // Session session = new GaeSession(UUID.randomUUID().toString());
        // inMem.put(session.getId(), session);
        // return session;
        return new GaeSession(UUID.randomUUID().toString());
    }

    @Override
    public void delete(String arg0) {
        LOG.info("Request to delete session: " + arg0);
        inMem.remove(arg0);
    }

    @Override
    public GaeSession getSession(String arg0) {
        LOG.info("Request to read session: " + arg0);
        return inMem.get(arg0);
    }

    @Override
    public void save(GaeSession arg0) {
        LOG.info("Request to save session: " + arg0.getId());
        inMem.put(arg0.getId(), arg0);
    }

}
