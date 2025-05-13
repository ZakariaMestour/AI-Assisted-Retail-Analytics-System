package poo.project.Converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import poo.project.Classes.DayHours;

import java.util.List;

@Converter
public class ScheduleConverter implements AttributeConverter<List<DayHours>,String> {
    private final ObjectMapper mapper = new ObjectMapper();


    @Override
    public String convertToDatabaseColumn(List<DayHours> dayHours) {
        try {
            return mapper.writeValueAsString(dayHours);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DayHours> convertToEntityAttribute(String json) {
        try {
            return mapper.readValue(json, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
