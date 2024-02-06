package com.example.jobapi.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonUtil {
    public static String convertToJson(Object input, List<String> fields) throws JsonProcessingException {
        PropertyFilter theFilter = new SimpleBeanPropertyFilter() {
            @Override
            public void serializeAsField
                    (Object pojo, JsonGenerator jgen, SerializerProvider provider, PropertyWriter writer)
                    throws Exception {
                if (include(writer)) {
                    if (fields==null || fields.isEmpty()) {
                        writer.serializeAsField(pojo, jgen, provider);
                        return;
                    }
                    for (String field : fields) {
                        if (writer.getName().equals(field)) {
                            writer.serializeAsField(pojo, jgen, provider);
                            return;
                        }
                    }
                } else if (!jgen.canOmitFields()) { // since 2.3
                    writer.serializeAsOmittedField(pojo, jgen, provider);
                }
            }
            @Override
            protected boolean include(BeanPropertyWriter writer) {
                return true;
            }
            @Override
            protected boolean include(PropertyWriter writer) {
                return true;
            }
        };
        FilterProvider filters = new SimpleFilterProvider().addFilter("fieldsFilter", theFilter);
        ObjectMapper mapper = new ObjectMapper();
        String dtoAsString = mapper.writer(filters).writeValueAsString(input);
        return  dtoAsString;
    }

    public static List<Map<String, Object>> convertToMapList(List<Object[]> queryResults, List<String> fieldNames) {

        // Convert each Object[] to a Map with field names
        return queryResults.stream()
                .map(objects -> {
                    Map<String, Object> result = new HashMap<>();
                    for (int i = 0; i < fieldNames.size(); i++) {
                        result.put(fieldNames.get(i), objects[i]);
                    }
                    return result;
                })
                .toList();
    }

    public static String convertListToJSON(List<Object[]> queryResults, List<String> fieldNames) {
        try {
            if(fieldNames==null || fieldNames.isEmpty()){
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.writeValueAsString(queryResults);
            }
            List<Map<String, Object>> resultList = convertToMapList(queryResults, fieldNames);
            // Use Jackson ObjectMapper to convert the list of maps to JSON
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(resultList);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<String> getFieldNames(Class<?> clazz) {
        List<String> fieldNames = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            fieldNames.add(field.getName());
        }

        return fieldNames;
    }
}