package validator;

import model.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        ValidationUtils.rejectIfEmpty(errors, "firstName","notEmptyError");
        ValidationUtils.rejectIfEmpty(errors, "lastName","notEmptyError");
        ValidationUtils.rejectIfEmpty(errors, "phoneNumber","notEmptyError");
        ValidationUtils.rejectIfEmpty(errors, "age","notEmptyError");
        ValidationUtils.rejectIfEmpty(errors, "email","notEmptyError");

        if (user.getFirstName().length()<5 || user.getFirstName().length()>45
                || user.getLastName().length()<5 || user.getLastName().length()>45){
            errors.rejectValue("firstName","nameInvalid");
        }

        if (!user.getPhoneNumber().matches("[0-9]{10}")) {
            errors.rejectValue("phoneNumber","phoneNumberInvalid");
        }

        if (user.getAge() <= 18 ) {
            errors.rejectValue("age","ageInvalid");
        }

        if(!user.getEmail().matches("\\w+@\\w+.\\w+")){
            errors.rejectValue("email", "emailInvalid");
        }
    }
}
