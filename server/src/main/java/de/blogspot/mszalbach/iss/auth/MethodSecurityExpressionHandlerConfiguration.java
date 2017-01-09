package de.blogspot.mszalbach.iss.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;

/**
 * Needed for SecurityChecker or else there is no MethodSecurityExpressionHandler implementation in the Spring Context.
 */
@Configuration
public class MethodSecurityExpressionHandlerConfiguration {

    @Bean
    public MethodSecurityExpressionHandler expressionHandler() {
        return new DefaultMethodSecurityExpressionHandler();
    }
}
