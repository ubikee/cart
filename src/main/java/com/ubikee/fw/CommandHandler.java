package com.ubikee.fw;

/**
 *
 * @author jeroldan
 * @param <T>
 */
@FunctionalInterface
public interface CommandHandler<T extends Command> {
    
    public CommandResult execute(T command);
    
}
