package com.example.api_clima.convert_data.exception;

public class FailedToSerializeException extends RuntimeException
{
    public FailedToSerializeException(String message)
    {
        super(message);
    }
}
