
package com.ishaan.deduplication.Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.codec.language.Metaphone;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Ishaan Thakker 
 */

@Controller
public class baseController {
    public static LevenshteinDistance ld = new LevenshteinDistance();
    public static Metaphone m = new Metaphone();
    
    @RequestMapping(value = "index")
    public ModelAndView index(){
        File f;
        FileReader fr;
        BufferedReader br;
        try{
            f = new File("normal.csv");
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            
            
            
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        return new ModelAndView("index");
    }
}
