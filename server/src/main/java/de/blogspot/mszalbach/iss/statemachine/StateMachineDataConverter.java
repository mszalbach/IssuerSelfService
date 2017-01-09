package de.blogspot.mszalbach.iss.statemachine;

import org.squirrelframework.foundation.fsm.ObjectSerializableSupport;
import org.squirrelframework.foundation.fsm.StateMachineData;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Converter to store the State of a Statemachiene as String for JPA.
 */
@Converter(autoApply = true)
public class StateMachineDataConverter
    implements AttributeConverter<StateMachineData.Reader, String> {


    @Override
    public String convertToDatabaseColumn(StateMachineData.Reader reader) {
        return ObjectSerializableSupport.serialize(reader);
    }


    @Override
    public StateMachineData.Reader convertToEntityAttribute(String bytes) {
        return ObjectSerializableSupport.deserialize(bytes);
    }
}
