package com.ubikee.fw;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.MethodParameterScanner;

/**
 *
 * @author jeroldan
 */
public abstract class AggregateRoot {

    private Bus bus;

    private final List<Event> pendingEvents = new ArrayList();

    protected void mutate(Event event) {
        apply(event);
    }

    protected void emit(Event event) {
        apply(event);
        this.pendingEvents.add(event);
        send(event);
    }

    private void send(Event event) {
        //this.bus.send(event);
    }

    protected void apply(Event event) {
        Reflections reflections = new Reflections("com.mycompany.cart", new MethodAnnotationsScanner(), new MethodParameterScanner());
        //Set<Method> methods = reflections.getMethodsAnnotatedWith(Persist.class);
        Set<Method> methods = reflections.getMethodsMatchParams(event.getClass());
        methods.stream().forEach(method -> {
            try {
                method.invoke(this, event);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(AggregateRoot.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public List<Event> getPendingEvents() {
        return pendingEvents;
    }

    public void clearPendingEvents() {
        this.pendingEvents.clear();
    }
}
