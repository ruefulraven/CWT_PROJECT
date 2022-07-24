package com.gradle.cwt.utility;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotNumberValidator implements ConstraintValidator<NotNumber, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		boolean isValid = true;
		for(Character character : value.toCharArray()) {
			if(Character.isLetter(character)) {
				isValid = false;
				System.out.println(character + " " + isValid);
			}
		}
		System.out.println("Valid: " + isValid);
		return isValid;
	}

}
