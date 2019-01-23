package com.ixx.controller.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description:
 * User: purui_zhang
 * Date: 2019-01-05
 * Time: 21:28
 */
@Controller
@RequestMapping(value = "error")
public class ErrorController {

    @GetMapping(value = "403")
    public String index403(){
        return "error/403";
    }
    @GetMapping(value = "404")
    public String index404(){
        return "error/404";
    }
    @GetMapping(value = "500")
    public String index500(){
        return "error/500";
    }

}
