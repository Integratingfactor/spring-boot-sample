package com.integratingfactor.authn.sessions;

import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.session.SessionRepository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gcloud.datastore.Blob;
import com.google.gcloud.datastore.BlobValue;
import com.google.gcloud.datastore.Datastore;
import com.google.gcloud.datastore.DatastoreOptions;
import com.google.gcloud.datastore.Entity;
import com.google.gcloud.datastore.KeyFactory;

public class GdsSessionRepository implements SessionRepository<IdpSession> {
    private static Logger LOG = Logger.getLogger(GdsSessionRepository.class.getName());

    static final Integer ExpiryTimeInSec = 3600;

    static final int MaxRetry = 3;

    ObjectMapper mapper = new ObjectMapper();

    // Create an authorized Datastore service using Application Default
    // Credentials.
    private final Datastore datastore = DatastoreOptions.defaultInstance().service();

    // Create a Key factory to construct keys associated with this project.
    private final KeyFactory keyFactory = datastore.newKeyFactory().kind("DaoIdpSessions");

    public GdsSessionRepository() {
        // LOG.info("Registering IDP Session entities with Objectify
        // Service...");
        // ObjectifyService.register(IdpSessionUtil.DaoIdpSession.class);
        // LOG.info("... registration complete");
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
        // ofy().delete().entity(IdpSessionUtil.toPkFrom(arg0));
        datastore.delete(keyFactory.newKey(arg0));
    }

    @Override
    public IdpSession getSession(String arg0) {
        LOG.info("Request to read session: " + arg0);
        // return
        // IdpSessionUtil.toIdpSessionFrom(ofy().load().entity(IdpSessionUtil.toPkFrom(arg0)).now());
        Entity entity = datastore.get(keyFactory.newKey(arg0));
        IdpSession session = null;
        if (entity != null) {
            session = (IdpSession) SerializationUtils.deserialize(entity.getBlob("session").toByteArray());
        }
        try {
            LOG.info("read authentication: " + mapper
                    .writeValueAsString(session != null ? session.getAttribute("SPRING_SECURITY_CONTEXT") : null));
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return session;
    }


    @Override
    public void save(IdpSession arg0) {
        LOG.info("Request to save session: " + arg0.getId());
        // ofy().save().entity(IdpSessionUtil.toEntityFrom(arg0)).now();
        datastore.put(Entity
                .builder(keyFactory.newKey(arg0.getId())).set("session", BlobValue
                        .builder(Blob.copyFrom(SerializationUtils.serialize(arg0))).excludeFromIndexes(true).build())
                .build());
    }
}
