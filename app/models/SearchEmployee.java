package models;

import java.util.*;
import javax.persistence.*;

import play.data.format.*;
import play.data.validation.*;

import play.db.jpa.*;

/**
 * Employee entity managed by JPA
 */
public class SearchEmployee {
    
    public String first_name;
    
    public String last_name;
    
    public String second_name;
    
    public String min_age;
    
    public String max_age;
    
    public String min_experience;
    
    public String max_experience;
    
    @Constraints.MaxLength(value=500)
    public String description;

    public SearchEmployee() {
    }

    public SearchEmployee(String fn
                         ,String ln 
                         ,String sn
                         ,String min_a
                         ,String max_a
                         ,String min_exp
                         ,String max_exp
                         ,String descr) {
        first_name = fn;
        last_name = ln;
        second_name = sn;
        try { 
            Integer.parseInt(min_a);
            min_age = min_a;
        } catch(NumberFormatException e) {
            min_age = "";
        }
        try { 
            Integer.parseInt(max_a);
            max_age = max_a;
        } catch(NumberFormatException e) {
            max_age = "";
        }
        try { 
            Integer.parseInt(min_exp);
            min_experience = min_exp;
        } catch(NumberFormatException e) {
            min_experience = "";
        }
        try { 
            Integer.parseInt(max_exp);
            max_experience = max_exp;
        } catch(NumberFormatException e) {
            max_experience = "";
        }
        description = descr;
    }

    public void fixNulls() {
        if (first_name == null) first_name = "";
        if (last_name == null) last_name = "";
        if (second_name == null) second_name = "";
        if (min_age == null) min_age = "";
        if (max_age == null) max_age = "";
        if (min_experience == null) min_experience = "";
        if (max_experience == null) max_experience = "";
        if (description == null) description = "";
    }
}
