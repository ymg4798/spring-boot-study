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
public class ValidControllerV0 {

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
}
