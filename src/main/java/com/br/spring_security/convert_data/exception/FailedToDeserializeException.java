package com.example.api_clima.convert_data.exception;

public class FailedToDeserializeException extends RuntimeException
{
    public FailedToDeserializeException(String message)
    {
        super(message);
    }
}
