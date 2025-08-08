package com.example.api_clima.convert_data.mapper;

import com.example.api_clima.convert_data.contract.DataMapper;
import com.example.api_clima.convert_data.exception.FailedToDeserializeException;
import com.example.api_clima.convert_data.exception.FailedToSerializeException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class JsonMapper implements DataMapper
{
    private final ObjectMapper mapper;

    public JsonMapper(ObjectMapper mapper)
    {
        this.mapper = mapper;
    }

    @Override
    public <T> Optional<T> toObject(String json, Class<T> classType)
    {
        if(isInvalidJson(json))
        {
            return Optional.empty();
        }

        try
        {
            T obj = mapper.readValue(json, classType);
            return Optional.ofNullable(obj);
        }
        catch (JsonProcessingException e)
        {
            throw new FailedToDeserializeException("Erro do servidor, volte mais tarde...");
        }
    }

    @Override
    public <T> List<T> toObjectList(String json, Class<T> classType)
    {
        if(isInvalidJson(json))
        {
            return Collections.emptyList();
        }

        try
        {
            return returnListByObjectMapper(json, classType);
        }
        catch (JsonProcessingException ex)
        {
            throw new FailedToDeserializeException("Erro do servidor, volte mais tarde...");
        }
    }

    @Override
    public <T> String toJson(T object)
    {
        try
        {
            return mapper.writeValueAsString(object);
        }
        catch (JsonProcessingException ex)
        {
            throw new FailedToSerializeException("Erro do servidor, volte mais tarde...");
        }
    }

    private boolean isInvalidJson(String json)
    {
        return json == null;
    }

    private <T> List<T> returnListByObjectMapper(String json, Class<T> tClass) throws JsonProcessingException
    {
        return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, tClass));
    }
}
