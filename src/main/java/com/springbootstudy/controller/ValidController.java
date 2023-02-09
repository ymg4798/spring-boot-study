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

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class ValidController {

    @GetMapping("/valid")
    public String valid(@ModelAttribute @Valid Valids valids) {
        return "index";
    }

    @PostMapping("/v0/valid")
    public String validV0(@ModelAttribute Valids valids, Model model) {
        Map<String, Object> error = new HashMap<>();

        if (valids.getName().length() > 4) {
            error.put("name", "이름의 글자 수를 초과 했습니다.");
        } else if (valids.getName().length() == 0 || valids.getName() == null) {
            error.put("name", "이름은 필수 입니다.");
        }

        if (valids.getAge() > 20 || valids.getAge() < 10) {
            error.put("age", "10 ~ 20세만 가능합니다.");
        }

        if (valids.getValue().length() == 0 || valids.getValue() == null) {
            error.put("value", "값은 필수 입니다");
        }

        if (!error.isEmpty()) {
            log.info("error = {}", error);
            model.addAttribute("error", error);
            model.addAttribute("result", "실패");
            return "index";
        }

        model.addAttribute("result", "성공");
        return "index";
    }

    @GetMapping("/v1/valid")
    public String validV1(Model model) {
        model.addAttribute("valids", new Valids(null, null, null));
        return "v1/index";
    }

    @PostMapping("/v1/valid")
    public String validV1(@ModelAttribute Valids valids, BindingResult bindingResult, Model model) {

        if (valids.getName().length() > 4) {
            bindingResult.addError(new FieldError("valids", "name", "이름의 글자 수를 초과 했습니다."));
        } else if (!StringUtils.hasText(valids.getName())) {
            bindingResult.addError(new FieldError("valids", "name", "이름은 필수 입니다."));
        }

        if (valids.getAge() > 20 || valids.getAge() < 10) {
            bindingResult.addError(new FieldError("valids", "age", "10 ~ 20세만 가능합니다."));
        }

        if (!StringUtils.hasText(valids.getValue())) {
            bindingResult.addError(new FieldError("valids", "value", "값은 필수 입니다."));
        }

        if ((valids.getValue() + valids.getName()).length() > 10) {
            bindingResult.addError(new ObjectError("valids", "값과 이름의 합이 10보다 초과하면 안됩니다."));
        }

        if (bindingResult.hasErrors()) {
            log.info("error = {}", bindingResult);
            model.addAttribute("result", "실패");
            return "v1/index";
        }

        model.addAttribute("result", "성공");
        return "v1/index";
    }

    @PostMapping("/v2/valid")
    public String validV2(@ModelAttribute Valids valids, BindingResult bindingResult, Model model) {

        if (valids.getName().length() > 4) {
            bindingResult.addError(new FieldError("valids", "name", "이름의 글자 수를 초과 했습니다."));
        } else if (!StringUtils.hasText(valids.getName())) {
            bindingResult.addError(new FieldError("valids", "name", "이름은 필수 입니다."));
        }

        if (valids.getAge() > 20 || valids.getAge() < 10) {
            bindingResult.addError(new FieldError("valids", "age", "10 ~ 20세만 가능합니다."));
        }

        if (!StringUtils.hasText(valids.getValue())) {
            bindingResult.addError(new FieldError("valids", "value", "값은 필수 입니다."));
        }

        if ((valids.getValue() + valids.getName()).length() > 10) {
            bindingResult.addError(new ObjectError("valids", "값과 이름의 합이 10보다 초과하면 안됩니다."));
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
