package com.daiwf.javalearndemos.spring.controller;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/aoptest")
public class AOPTestOneController
{
   @GetMapping("hello")
   @ApiOperation("just return hello")
    public String Hello(){
       return "Hello the fucking world";
    }
}
