package com.leo.es_project_in_actions.controller;

import com.leo.es_project_in_actions.entity.Goods;
import com.leo.es_project_in_actions.service.EsService;
import org.elasticsearch.index.engine.Engine;
import org.elasticsearch.search.profile.ProfileShardResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    private EsService service;

    @GetMapping({"/",""})
    public String index(){
        return "index";
    }

    @GetMapping("/search/{key}")
    public String query(@PathVariable String key){
        try {
            service.sycn(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "index";
    }

    @GetMapping("/localsearch/{key}/{index}/{pageSize}")
    public String localsearch(@PathVariable String key,
                                                @PathVariable Integer index,
                                                @PathVariable Integer pageSize,
                              Model model){
        List<Map<String, Object>> search = null;
        try {
            search = service.search(key, index, pageSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("map",search);
        return "index";
    }
}
