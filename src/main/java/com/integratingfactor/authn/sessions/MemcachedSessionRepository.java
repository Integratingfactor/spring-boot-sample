package com.integratingfactor.authn.sessions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

import org.springframework.session.SessionRepository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;

public class MemcachedSessionRepository implements SessionRepository<IdpSession> {
    private static Logger LOG = Logger.getLogger(MemcachedSessionRepository.class.getName());

    // TODO replace this with memcached implementation once we figure out how
    // this works
    Map<String, IdpSession> inMem = new HashMap<String, IdpSession>();

    String mHost = "127.0.0.1";

    String mPort = "11211";

    MemcachedClient client = null;

    Integer ExpiryTimeInSec = 300;

    ObjectMapper mapper = new ObjectMapper();

    public MemcachedSessionRepository() {
        String env = System.getenv().get("MEMCACHE_PORT_11211_TCP_ADDR");
        if (env != null)
            mHost = env;

        env = System.getenv().get("MEMCACHE_PORT_11211_TCP_PORT");
        if (env != null)
            mPort = env;
        LOG.info("Connection with memcached on " + mHost + ":" + mPort);

        MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(mHost + ":" + mPort));
        try {
            builder.setCommandFactory(new BinaryCommandFactory());
            client = builder.build();
        } catch (IOException e) {
            e.printStackTrace();
            LOG.warning("Failed to connect with mem cache: " + e.getMessage());
            throw new RuntimeException("Failed to connect with mem cache");
        }

    }

    @Override
    public IdpSession createSession() {
        LOG.info("Request to create new session");
        // Session session = new GaeSession(UUID.randomUUID().toString());
        // inMem.put(session.getId(), session);
        // return session;
        return new IdpSession(UUID.randomUUID().toString());
    }

    @Override
    public void delete(String arg0) {
        LOG.info("Request to delete session: " + arg0);
        // inMem.remove(arg0);
        try {
            client.deleteWithNoReply(arg0);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.warning("Failed to delete session from memcache");
        }
    }

    @Override
    public IdpSession getSession(String arg0) {
        LOG.info("Request to read session: " + arg0);
        try {
            IdpSession session = client.getAndTouch(arg0, ExpiryTimeInSec);
            LOG.info("read authentication: " + mapper
                    .writeValueAsString(session != null ? session.getAttribute("SPRING_SECURITY_CONTEXT") : null));
            return session;
        } catch (TimeoutException | InterruptedException | MemcachedException | JsonProcessingException e) {
            e.printStackTrace();
            LOG.warning("Failed to get session from memcache");
        }
        return null;
    }

    @Override
    public void save(IdpSession arg0) {
        LOG.info("Request to save session: " + arg0.getId());
        // inMem.put(arg0.getId(), arg0);
        try {
            LOG.info("writing authentication: "
                    + mapper.writeValueAsString(arg0 != null ? arg0.getAttribute("SPRING_SECURITY_CONTEXT") : null));
            client.set(arg0.getId(), ExpiryTimeInSec, arg0);
        } catch (TimeoutException | InterruptedException | MemcachedException | JsonProcessingException e) {
            e.printStackTrace();
            LOG.warning("Failed to save session to memcache");
        }
    }
}
