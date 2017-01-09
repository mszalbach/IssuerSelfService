package de.blogspot.mszalbach.iss.auth;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.security.access.expression.ExpressionUtils;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.util.SimpleMethodInvocation;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

/**
 * Can be used to check if a @PreAuthorize annotation is valid for current user.
 */
@Service
public class SecurityChecker {

    private final static Logger LOGGER = Logger.getLogger(SecurityChecker.class);
    private static Method triggerCheckMethod;
    private static SpelExpressionParser parser;

    static {
        try {
            triggerCheckMethod = SecurityObject.class.getMethod("triggerCheck");
        } catch (NoSuchMethodException e) {
            LOGGER.error(e);
        }
        parser = new SpelExpressionParser();
    }

    private final MethodSecurityExpressionHandler expressionHandler;



    @Autowired
    public SecurityChecker( MethodSecurityExpressionHandler expressionHandler ) {
        this.expressionHandler = expressionHandler;
    }



    public boolean check(String securityExpression) {

        SecurityObject securityObject = new SecurityObject();
        EvaluationContext evaluationContext = expressionHandler
            .createEvaluationContext(SecurityContextHolder.getContext().getAuthentication(),
                new SimpleMethodInvocation(securityObject, triggerCheckMethod));
        boolean checkResult = ExpressionUtils
            .evaluateAsBoolean(parser.parseExpression(securityExpression), evaluationContext);

        return checkResult;
    }

    private static class SecurityObject {

        public void triggerCheck() { /*NOP*/ }
    }

}
