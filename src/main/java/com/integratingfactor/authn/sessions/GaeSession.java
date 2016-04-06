package com.integratingfactor.authn.sessions;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.session.ExpiringSession;

public class GaeSession implements ExpiringSession, Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 3027174095168527331L;
    String id;
    Map<String, Object> attributes = new HashMap<String, Object>();
    long created;
    long accessed;

    public GaeSession(String id) {
        this.id = id;
        this.created = new Date().getTime();
        this.accessed = this.created;
    }

    @Override
    public String getId() {
        return id;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getAttribute(String attributeName) {
        return (T) attributes.get(attributeName);
    }

    @Override
    public Set<String> getAttributeNames() {
        return attributes.keySet();
    }

    @Override
    public void setAttribute(String attributeName, Object attributeValue) {
        attributes.put(attributeName, attributeValue);
    }

    @Override
    public void removeAttribute(String attributeName) {
        attributes.remove(attributeName);
    }

    @Override
    public long getCreationTime() {
        return created;
    }

    @Override
    public long getLastAccessedTime() {
        return accessed;
    }

    @Override
    public void setMaxInactiveIntervalInSeconds(int interval) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getMaxInactiveIntervalInSeconds() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isExpired() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setLastAccessedTime(long arg0) {
        // TODO Auto-generated method stub

    }

}
