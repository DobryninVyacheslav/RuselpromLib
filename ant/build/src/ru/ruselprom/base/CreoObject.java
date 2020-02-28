package ru.ruselprom.base;

import com.ptc.pfc.pfcSession.Session;

public abstract class CreoObject {
    protected Session session;

    public CreoObject(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}