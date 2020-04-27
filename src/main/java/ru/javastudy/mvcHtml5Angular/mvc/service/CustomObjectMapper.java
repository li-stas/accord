package ru.javastudy.mvcHtml5Angular.mvc.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Service;

@Service
public class CustomObjectMapper extends ObjectMapper
{
    public CustomObjectMapper()
    {
        this.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
                false);
    }
}