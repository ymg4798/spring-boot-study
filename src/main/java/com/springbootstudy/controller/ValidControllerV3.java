package com.springbootstudy.controller;

import com.springbootstudy.controller.dto.Valids;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class ValidControllerV3 {


    @PostMapping("/v3/valid")
    public String validV3(@ModelAttribute Valids valids, BindingResult bindingResult, Model model) {

        if (valids.getName() == null|| valids.getName().length() > 4) {
            bindingResult.rejectValue("name", "length");
        } else if (!StringUtils.hasText(valids.getName())) {
            bindingResult.rejectValue("name", "required");
        }

        if (valids.getAge() == null|| valids.getAge() > 20 || valids.getAge() < 10) {
            bindingResult.rejectValue("age", "range", new Object[]{10, 20}, null);
        }

        if (!StringUtils.hasText(valids.getValue())) {
            bindingResult.rejectValue("value", "required");
        }

        if ((valids.getValue() + valids.getName()).length() > 10) {
            bindingResult.reject("totalValueAge", new Object[]{10}, null);
        }

        if (bindingResult.hasErrors()) {
            log.info("error = {}", bindingResult);
            model.addAttribute("result", "실패");
            return "v1/index";
        }

        model.addAttribute("result", "성공");
        return "v1/index";
    }
}
