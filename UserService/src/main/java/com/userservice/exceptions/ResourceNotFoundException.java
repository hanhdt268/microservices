package com.userservice.exceptions;

public class ResourceNotFoundException extends RuntimeException{


    //extra properties that want mange
        public ResourceNotFoundException(){
            super("Resource not found on server!!");
        }

        public ResourceNotFoundException(String message){
            super(message);
        }
}
