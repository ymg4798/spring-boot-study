package com.springbootstudy.controller;

import com.springbootstudy.controller.dto.Valids;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class ValidControllerV2 {


    @PostMapping("/v2/valid")
    public String validV2(@ModelAttribute Valids valids, BindingResult bindingResult, Model model) {

        if (valids.getName().length() > 4) {
            bindingResult.addError(new FieldError("valids", "name", valids.getName(), false, new String[]{"length.valids.name"}, new Object[]{}, null));
        } else if (!StringUtils.hasText(valids.getName())) {
            bindingResult.addError(new FieldError("valids", "name", valids.getName(), false, new String[]{"required.valids.name"}, new Object[]{}, null));
        }

        if (valids.getAge() == null ||valids.getAge() > 20 || valids.getAge() < 10) {
            bindingResult.addError(new FieldError("valids", "age", valids.getAge(), false, new String[]{"range.valids.age"}, new Object[]{10, 20}, null));
        }

        if (!StringUtils.hasText(valids.getValue())) {
            bindingResult.addError(new FieldError("valids", "value", valids.getValue(), false, new String[]{"required.valids.value"}, new Object[]{}, null));
        }

        if ((valids.getValue() + valids.getName()).length() > 10) {
            bindingResult.addError(new ObjectError("valids", new String[]{"totalValueAge"}, new Object[]{10}, null));

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
