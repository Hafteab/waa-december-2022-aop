package com.example.AOP.exceptions;

public class AopIsAwesomeHeaderException extends Exception{
    public AopIsAwesomeHeaderException(){
        super("Missing 'AOP-IS-AWESOME' Header on POST request.");
    }
}
