package com.wanlin.util;

/**
 * 
 * @author kangwl_pc
 *
 */
public class CustomerException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 133172954152424020L;

	private String expMessage;

    public CustomerException() {

    }
    
    public CustomerException(String msg) {
    	super(msg);
        this.expMessage = msg;
    }

    public CustomerException(Throwable cause) {
    	super(cause);
	}

	public String getExpMessage() {
        return expMessage;
    }

    public void setExpMessage(String expMessage) {
        this.expMessage = expMessage;
    }
}
