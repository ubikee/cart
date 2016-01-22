package com.ubikee.fw;

/**
 *
 * @author jeroldan
 */
public class CommandResult {
    
    public static final boolean SUCCEED = true;
    
    public final boolean succeed;
    
    public CommandResult(boolean succeed) {
        this.succeed = succeed;
    }
}
