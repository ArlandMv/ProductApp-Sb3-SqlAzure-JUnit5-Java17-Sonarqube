package com.mvprojects.SB3AzureMySQLcrud.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*  DEFAULT
    "timestamp": "2023-12-07T00:57:39.473+00:00",
    "status": 404,
    "error": "Not Found",
    "trace": "com.mvprojects.SB3AzureMySQLcrud.exceptions.ResourceNotFoundException\.."
    "message": "No message available",
    "path": "/api/v1/products/1"
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private String resourceName;
    private String fieldName;
    private Long fieldValue;

    //gives too much info to the client
    public ResourceNotFoundException(){}

    //this one too but with a message for the terminal
    public ResourceNotFoundException(String message){
        super(message);
    }

    public ResourceNotFoundException(String resourceName, String fieldName, Long fieldValue){
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
    }

}

