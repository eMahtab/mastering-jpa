package net.mahtabalam;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class AnswerConverter implements AttributeConverter<Answer, String> {

    @Override
    public String convertToDatabaseColumn(Answer answer) {
        return answer == Answer.YES ? "Y" : "N";
    }

    @Override
    public Answer convertToEntityAttribute(String dbValue) {
        return "Y".equals(dbValue) ? Answer.YES : Answer.NO;
    }
}