
package com.ishaan.deduplication.Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.UserData;
import org.apache.commons.codec.language.Metaphone;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Ishaan Thakker 
 */

@Controller
public class BaseController {
    public static LevenshteinDistance ld = new LevenshteinDistance();
    public static Metaphone m = new Metaphone();
    
    @RequestMapping(value = "index")
    public ModelAndView index(){
        return new ModelAndView("index");
    }
    
    @RequestMapping("getdata")
    public @ResponseBody String getData(){
        File f;
        FileReader fr;
        BufferedReader br;
        List<UserData> duplicate_records =new ArrayList<>();
        List<UserData> non_duplicate = new ArrayList<>();
        String response = "";
        ObjectMapper mapper = new ObjectMapper();
        
        try{
            f = new File("C:\\Users\\thakk\\Downloads\\takehome\\advanced.csv");
            fr = new FileReader(f);
            br = new BufferedReader(fr);
           
            CSVParser csvParser = new CSVParser(br, CSVFormat.DEFAULT);
        
            List<UserData> records = new ArrayList<>();
            non_duplicate = new ArrayList<>();
            
            int row_count = 0;
            for(CSVRecord x: csvParser){
            if(row_count==0){
                row_count++;
                continue;
            }
                
            String id = (x.get(0));
            String fName = x.get(1);
            String lName = x.get(2);
            String company = x.get(3);
            String email = x.get(4);
            String addr1 = x.get(5);
            String addr2 = x.get(6);
            String zip = x.get(7);
            String city = x.get(8);
            String state_long = x.get(9);
            String state_short = x.get(10);
            String phone = x.get(11);
           
            records.add(new UserData(id, fName, lName, company, email, addr1, 
                    addr2, zip, city, state_long, state_short, phone));
           
        }
            int ind = 0;
            boolean found_duplicate = false;
            
            for(int record_idx = 0; record_idx < records.size(); record_idx++){
                
                List<UserData> temp=  new ArrayList<>();
                UserData record1 = records.get(record_idx);
                found_duplicate = false;
                
                for(int compare_idx = record_idx + 1; compare_idx < records.size(); 
                        compare_idx++){
                    
                    UserData record2 = records.get(compare_idx);
                    if(isSame(record1, record2)){
                        
                        temp.add(records.get(compare_idx));
                        found_duplicate = true;
                        records.remove(compare_idx);
                    }
                }
                
                if(found_duplicate){
                    for(UserData data: temp){
                        duplicate_records.add(data);
                    }
                    ind++;
                }else{
                    non_duplicate.add(record1);
                }
                
            }
            System.out.println();
            System.out.println("**************************** Duplicate Records ****************************");
            for(UserData duplicate_record: duplicate_records){
                System.out.println(duplicate_record.getRow_id() +","+ 
                                           duplicate_record.getFirst_name() +","+
                                           duplicate_record.getLast_name() +","+
                                           duplicate_record.getCompany() +","+
                                           duplicate_record.getEmail() +","+
                                           duplicate_record.getAddress1() +","+
                                           duplicate_record.getAddress2() +","+
                                           duplicate_record.getZip() +","+
                                           duplicate_record.getCity() +","+
                                           duplicate_record.getState_long() +","+
                                           duplicate_record.getState_short() +","+
                                           duplicate_record.getPhone());
            }
            
            System.out.println();
            System.out.println();
            System.out.println("**************************** Non-Duplicate Records ****************************");
            
            for(UserData data: non_duplicate){
                System.out.println(data.getRow_id() +","+ 
                                           data.getFirst_name() +","+
                                           data.getLast_name() +","+
                                           data.getCompany() +","+
                                           data.getEmail() +","+
                                           data.getAddress1() +","+
                                           data.getAddress2() +","+
                                           data.getZip() +","+
                                           data.getCity() +","+
                                           data.getState_long() +","+
                                           data.getState_short() +","+
                                           data.getPhone());
            }
            
            List<List<UserData>> res = new ArrayList<>();
            res.add(duplicate_records);
            res.add(non_duplicate);
          response = mapper.writeValueAsString(res);
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch(java.io.IOException e){
            System.out.println(e.getMessage());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        
        return response;
    }
    
    public static boolean isSame(UserData user1, UserData user2){
        
        String user1_email = user1.getEmail().toLowerCase();
        String user2_email = user2.getEmail().toLowerCase();
        
        String user1_phone = user1.getPhone().toLowerCase();
        String user2_phone = user2.getPhone().toLowerCase();
        String user1_firstName = user1.getFirst_name().toLowerCase();
        String user2_firstName = user2.getFirst_name().toLowerCase();
        
        String user1_lastName = user1.getLast_name().toLowerCase();
        String user2_lastName = user2.getLast_name().toLowerCase();
        
        String user1_company = user1.getCompany().toLowerCase();
        String user2_company = user2.getCompany().toLowerCase();
        
        String user1_address1 = user1.getAddress1().toLowerCase();
        String user2_address1 = user2.getAddress1().toLowerCase();
        
        
        if(user1_email.equals(user2_email) || user1_phone.equals(user2_phone)){
            return true;
        }else if(ld.apply(user1_firstName,user2_firstName)<2 ||
                m.isMetaphoneEqual(user1_firstName,user2_firstName)){
            
            if(ld.apply(user1_lastName, user2_lastName)<2 || 
                    m.isMetaphoneEqual(user1_lastName, user2_lastName)){
                    
                
                    if(user1_company.length() < user2_company.length()){
                        
                        if(user2_company.contains(user1_company) 
                            || ld.apply(user1_company, user2_company) <
                            Math.max(user1_company.length(),user2_company.length())*0.4
                            ||
                            ld.apply(user1_address1,user2_address1) < 
                            Math.max(user1_address1.length(),user2_address1.length())*0.4){
                            
                                return true;
                        }
                    }else{
                        if(user1_company.contains(user2_company) 
                            || ld.apply(user1_company, user2_company) <
                            Math.max(user1_company.length(),user2_company.length())*0.4
                            ||
                            ld.apply(user1_address1,user2_address1) < 
                            Math.max(user1_address1.length(),user2_address1.length())*0.4){
                            
                                return true;
                        }
                    }
                    
                
                
            }
        }
        
        return false;
    }
    
    
}
