package com.springbootstudy.validation;

import com.springbootstudy.controller.dto.Valids;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ValidV4Validator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return Valids.class.isAssignableFrom(clazz);// 클래스 검증
    }

    @Override
    public void validate(Object target, Errors errors) {
        Valids valids = (Valids) target;

        if (valids.getName() == null|| valids.getName().length() > 4) {
            errors.rejectValue("name", "length");
        } else if (!StringUtils.hasText(valids.getName())) {
            errors.rejectValue("name", "required");
        }

        if (valids.getAge() == null|| valids.getAge() > 20 || valids.getAge() < 10) {
            errors.rejectValue("age", "range", new Object[]{10, 20}, null);
        }

        //값이 비어 있으면 주어진 오류 코드와 함께 주어진 필드를 거부한다.
        ValidationUtils.rejectIfEmpty(errors, "value", "required");

        if ((valids.getValue() + valids.getName()).length() > 10) {
            errors.reject("totalValueAge", new Object[]{10}, null);
        }
    }
}
