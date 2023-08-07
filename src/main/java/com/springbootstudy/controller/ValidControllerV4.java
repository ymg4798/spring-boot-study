package com.springbootstudy.controller;

import com.springbootstudy.controller.dto.Valids;
import com.springbootstudy.validation.ValidV4Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ValidControllerV4 {

    private final ValidV4Validator validV4Validator;

    @InitBinder
    public void init(WebDataBinder dataBinder) {
        dataBinder.addValidators(validV4Validator);
    }

    //@Validation
    @PostMapping("/v4/valid")
    public String validV4(@Validated @ModelAttribute Valids valids, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            log.info("error = {}", bindingResult);
            model.addAttribute("result", "실패");
            return "v1/index";
        }

        model.addAttribute("result", "성공");
        return "v1/index";
    }
    /*
    /*@PostMapping("/v4/valid")
    public String validV4(@ModelAttribute Valids valids, BindingResult bindingResult, Model model) {

        validV4Validator.validate(valids, bindingResult);

        if (bindingResult.hasErrors()) {
            log.info("error = {}", bindingResult);
            model.addAttribute("result", "실패");
            return "v1/index";
        }

        model.addAttribute("result", "성공");
        return "v1/index";
    }*/
}
