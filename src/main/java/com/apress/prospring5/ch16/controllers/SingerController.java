package com.apress.prospring5.ch16.controllers;

import com.apress.prospring5.ch16.entities.Singer;
import com.apress.prospring5.ch16.services.SingerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/singers")
public class SingerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SingerController.class);

    @Autowired
    private SingerService singerService;

    @GetMapping
    public String list(Model model) {
        LOGGER.info("Listing singers");
        List<Singer> singers = singerService.findAll();
        model.addAttribute("singers", singers);
        LOGGER.info("No. of singers: " + singers.size());
        return "singers";
    }

    @GetMapping(value = "/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        Singer singer = singerService.findById(id);
        model.addAttribute("singer", singer);
        return "show";
    }

    @GetMapping(value = "/edit/{id}")
    public String displayUpdateForm(@PathVariable Long id, Model model) {
        model.addAttribute("singer", singerService.findById(id));
        return "update";
    }

    @GetMapping(value = "/new")
    public String displayCreateForm(Model model) {
        Singer singer = new Singer();
        model.addAttribute("singer", singer);
        return "update";
    }

    @PostMapping
    public String saveSinger(@Valid Singer singer) {
        singerService.save(singer);
        return "redirect:/singers/" + singer.getId();
    }

}
