package com.ubikee.fw;

/**
 *
 * @author jeroldan
 */
public interface Bus {
    
    void send(Event event);
    
    void subscribe(EventHandler handler, Event event);
}
