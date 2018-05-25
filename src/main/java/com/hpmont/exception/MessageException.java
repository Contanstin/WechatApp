package com.hpmont.exception;

/**
 * Created by Administrator on 2016/6/14.
 */
public class MessageException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 220069597161900224L;
    public MessageException(){
        super();
    }
    public MessageException(String msg){
        super();
        this.setErrMessage(msg);
    }
    private String errMessage;
    public String getErrMessage() {
        return errMessage;
    }
    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}
