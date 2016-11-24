package de.blogspot.mszalbach.iss.statemachine;

import org.squirrelframework.foundation.fsm.ObjectSerializableSupport;
import org.squirrelframework.foundation.fsm.StateMachineData;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.UUID;

/**
 * Created by ms on 21.11.16.
 */
@Converter(autoApply = true)
public class StateMachineDataConverter
        implements AttributeConverter<StateMachineData.Reader, String> {


    @Override
    public String convertToDatabaseColumn( StateMachineData.Reader reader ) {
        return ObjectSerializableSupport.serialize(reader);
    }



    @Override
    public StateMachineData.Reader convertToEntityAttribute( String bytes ) {
        return ObjectSerializableSupport.deserialize( bytes );
    }
}
