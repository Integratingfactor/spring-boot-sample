package com.integratingfactor.authn.sessions;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Serialize;

public class IdpSessionUtil {
    @Entity
    public static class DaoIdpSession {
        @Id
        String sessionId;

        @Serialize
        IdpSession session;
    }

    // public static Key<DaoIdpSession> toPkFrom(String sessionId) {
    // return Key.create(DaoIdpSession.class, sessionId);
    // }

    public static DaoIdpSession toPkFrom(String sessionId) {
        DaoIdpSession entity = new DaoIdpSession();
        entity.sessionId = sessionId;
        return entity;
    }

    public static DaoIdpSession toEntityFrom(IdpSession session) {
        DaoIdpSession entity = new DaoIdpSession();
        entity.sessionId = session.getId();
        entity.session = session;
        return entity;
    }

    public static IdpSession toIdpSessionFrom(DaoIdpSession entity) {
        if (entity == null)
            return null;
        return entity.session;
    }

}
