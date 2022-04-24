package com.apress.prospring5.ch16.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/singers")
@Controller
public class SingerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SingerController.class);
}
