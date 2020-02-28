package ru.test;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSession.Session;

import ru.ruselprom.base.CreoObject;

public class test extends CreoObject {

    public test(Session session) {
        super(session);
    }
    
    public void gerHelloMessage() throws jxthrowable {
        session.UIShowMessageDialog("Hello", null);
    }
}
