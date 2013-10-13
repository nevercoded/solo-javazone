package com.ds.sync.exception;

/**
 * Created with IntelliJ IDEA.
 * User: skumar
 * Date: 10/13/13
 * Time: 2:25 PM
 * Exception thrown for invalid index passed to the list
 */
public class InvalidIndexException extends RuntimeException {

    public InvalidIndexException(String msg) {
        super(msg);
    }
}
