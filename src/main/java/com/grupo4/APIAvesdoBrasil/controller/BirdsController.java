package com.grupo4.APIAvesdoBrasil.controller;


import com.grupo4.APIAvesdoBrasil.entity.Bird;
import com.grupo4.APIAvesdoBrasil.service.BirdService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/birds")
public class BirdsController {

    private BirdService birdService;

    public BirdsController(BirdService theBirdService){

        birdService = theBirdService;

    }

    @GetMapping("/list")
    public String listBirds(Model model){

        List<Bird> birdsList = birdService.findAll();
        model.addAttribute("birds",birdsList);

        return "birds/birds-list";
    }

    @GetMapping("/showFormSave")
    public String showFormSave(Model model){

        Bird bird = new Bird();
        model.addAttribute("bird", bird);

        return "birds/bird-save-form";
    }

    @PostMapping("/save")
    public String saveBird(@ModelAttribute("bird") Bird bird){

        birdService.save(bird);

        return "redirect:/birds/list";
    }

    @GetMapping("/update")
    public String updateBird(@ModelAttribute("bird") Bird bird) {
        birdService.save(bird);

        return "redirect:/birds/list";
    }

    @GetMapping("/showFormUpdate")
    public String showFormUpdate(@RequestParam("birdId") int id, Model model){

        Bird bird = birdService.findById(id);
        model.addAttribute("bird", bird);

        return "birds/bird-update-form";
    }

    @GetMapping("/delete")
    public String deleteBird(@RequestParam("birdId")int id){
        birdService.deleteById(id);
        return "redirect:/birds/list";
    }


}
