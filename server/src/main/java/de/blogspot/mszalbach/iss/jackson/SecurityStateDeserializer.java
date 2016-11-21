package de.blogspot.mszalbach.iss.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import de.blogspot.mszalbach.iss.domain.SecurityState;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * Created by ms on 21.11.16.
 */
public class SecurityStateDeserializer
        extends JsonDeserializer {

    @Override
    public Object deserialize( JsonParser jsonParser, DeserializationContext deserializationContext )
            throws IOException, JsonProcessingException {
        final String jsonValue = jsonParser.getText();
        if ( !StringUtils.isEmpty( jsonValue ) ) {
            return SecurityState.valueOf( jsonValue );
        }
        return null;

    }
}
