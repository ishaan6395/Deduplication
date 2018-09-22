
package com.ishaan.deduplication.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Ishaan Thakker 
 */

@Controller
public class baseController {
    
    @RequestMapping(value = "index")
    public ModelAndView index(){
        return new ModelAndView("index");
    }
}
