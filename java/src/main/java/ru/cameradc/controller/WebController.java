package ru.cameradc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import ru.cameradc.service.MotionService;
import ru.cameradc.bean.Motion;

import java.util.logging.Logger;


@Controller
public class WebController extends WebMvcConfigurerAdapter {
    private Logger logger = Logger.getLogger(WebController.class.toString());

    @Autowired
    private MotionService service;

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String showForm() {
        return "form";
    }

    @RequestMapping(value="/move", method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String checkPersonInfo(@RequestBody Motion motion) {
        logger.info(motion.getDirection());
        service.move(motion);
        return "ok";
    }

}
