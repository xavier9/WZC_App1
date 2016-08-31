package com.ehb.xavier.api;


import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

/**
 * Objectify service wrapper so we can statically register our persistence classes
 * More on Objectify here : https://code.google.com/p/objectify-appengine/
 *
 */
public class OfyService {

    static {
        ObjectifyService.register(Rol.class);
        ObjectifyService.register(Quote.class);
        ObjectifyService.register(Contact.class);
        ObjectifyService.register(User.class);
        ObjectifyService.register(MenuKeuze.class);
        ObjectifyService.register(MenuInsert.class);
        ObjectifyService.register(Event.class);
    }

    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
}
