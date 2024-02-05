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

import java.util.List;

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
}