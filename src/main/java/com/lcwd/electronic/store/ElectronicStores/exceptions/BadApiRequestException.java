package com.lcwd.electronic.store.ElectronicStores.exceptions;

public class BadApiRequestException extends RuntimeException{

    public BadApiRequestException(String message){
        super(message);
    }

    public BadApiRequestException(){
        super("Bad Request");
    }
}
