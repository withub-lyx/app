package com.portal.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
@RequestMapping("/rest/test")
public class RestControllerTest {
    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public Date test1() {
        return new Date();
    }
}
