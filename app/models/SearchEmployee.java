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
    
    @Constraints.Min(value=18)
    public Short min_age;
    
    @Constraints.Max(value=60)
    public Short max_age;
    
    @Constraints.Min(value=0)
    public Short min_experience;
    
    @Constraints.Max(value=42)
    public Short max_experience;
    
    @Constraints.MaxLength(value=500)
    public String description;

}
