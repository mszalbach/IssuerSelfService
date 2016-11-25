package de.blogspot.mszalbach.iss.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.validation.Validator;

/**
 * Created by foobarkilla on 19.11.16.
 */
@Configuration
public class SpringDataRestValidationConfiguration extends RepositoryRestConfigurerAdapter {

    private Validator validator;



    @Autowired
    public SpringDataRestValidationConfiguration( Validator validator ) {
        this.validator = validator;
    }



    @Override
    public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {
        validatingListener.addValidator("beforeCreate", validator);
        validatingListener.addValidator("beforeSave", validator);
    }
}
