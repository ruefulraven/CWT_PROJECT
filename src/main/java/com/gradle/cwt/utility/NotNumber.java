package com.gradle.cwt.utility;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


import javax.validation.Constraint;

import javax.validation.Payload;
/**
 * The annotated element must not be {@code null} and must contain numbers only
 *
 *
 * @author Axel
 * @since 1.0
 *
 * @see Character#isWhitespace(char)
 */
@Documented
@Constraint(validatedBy = NotNumberValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNumber {
	
	String message() default "Field must contain numbers only";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
