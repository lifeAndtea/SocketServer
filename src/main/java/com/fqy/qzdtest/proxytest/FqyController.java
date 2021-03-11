package com.fqy.qzdtest.proxytest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "第一个")
@RestController
@RequestMapping("/first")
public class FqyController {

    @ApiOperation("hello world")
    @GetMapping("/ee")
    public String sayHello(){
        return "eeeeeeeeeeee";
    }
}
