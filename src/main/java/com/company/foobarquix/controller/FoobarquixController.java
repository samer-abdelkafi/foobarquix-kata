package com.company.foobarquix.controller;

import com.company.foobarquix.service.NumberProcessorService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Validated
@RestController
@RequestMapping("/api/foobarquix")
public class FoobarquixController {

    @GetMapping("/{number}")
    public String processNumber(@PathVariable @Min(0) @Max(100) int number) {
        return NumberProcessorService.process(number);
    }


}
