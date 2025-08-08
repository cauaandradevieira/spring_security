package com.example.api_clima.convert_data.contract;

import java.util.List;
import java.util.Optional;

public interface DataMapper
{
    <T> Optional<T> toObject(String json, Class<T> classType);
    <T> List<T> toObjectList(String json, Class<T> classType);
    <T> String toJson(T object);
}
