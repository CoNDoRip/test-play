package models;

import java.util.*;
import javax.persistence.*;

import play.data.format.*;
import play.data.validation.*;

import play.db.jpa.*;

/**
 * Employee entity managed by JPA
 */
@Entity 
@SequenceGenerator(name = "employee_seq", sequenceName = "employee_seq")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
    public Long id;
    
    @Constraints.Required
    public String first_name;
    
    @Constraints.Required
    public String last_name;
    
    public String second_name;
    
    @Constraints.Min(value=18)
    public Short age;
    
    @Constraints.Min(value=0)
    public Short experience;
    
    @Constraints.MaxLength(value=500)
    public String description;

    /**
     * Find an employee by id.
     */
    public static Employee findById(Long id) {
        return JPA.em().find(Employee.class, id);
    }
    
    /**
     * Insert this new employee.
     */
    public void save() {
        this.id = id;
        JPA.em().persist(this);
    }

    /**
     * Update this employee.
     */
    public void update(Long id) {
        this.id = id;
        JPA.em().merge(this);
    }
    
    /**
     * Delete this employee.
     */
    public void delete() {
        JPA.em().remove(this);
    }

    /**
     * Return a page of employees
     *
     * @param page Page to display
     * @param pageSize Number of employees per page
     * @param sortBy Employee property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the last_name column
     */
    public static Page page(int page, int pageSize, String sortBy, String order, String filter) {
        if(page < 1) page = 1;
        Long total = (Long)JPA.em()
            .createQuery("SELECT count(e) FROM Employee e WHERE lower(e.last_name) like ?")
            .setParameter(1, "%" + filter.toLowerCase() + "%")
            .getSingleResult();
        List<Employee> data = JPA.em()
            .createQuery("FROM Employee e WHERE lower(e.last_name) like ? order by e." + sortBy + " " + order)
            .setParameter(1, "%" + filter.toLowerCase() + "%")
            .setFirstResult((page - 1) * pageSize)
            .setMaxResults(pageSize)
            .getResultList();
        return new Page(data, total, page, pageSize);
    }
    
    /**
     * Used to represent a computers page.
     */
    public static class Page {
        
        private final int pageSize;
        private final long totalRowCount;
        private final int pageIndex;
        private final List<Employee> list;
        
        public Page(List<Employee> data, long total, int page, int pageSize) {
            this.list = data;
            this.totalRowCount = total;
            this.pageIndex = page;
            this.pageSize = pageSize;
        }
        
        public long getTotalRowCount() {
            return totalRowCount;
        }
        
        public int getPageIndex() {
            return pageIndex;
        }
        
        public List<Employee> getList() {
            return list;
        }
        
        public boolean hasPrev() {
            return pageIndex > 1;
        }
        
        public boolean hasNext() {
            return (totalRowCount/pageSize) >= pageIndex;
        }
        
        public String getDisplayXtoYofZ() {
            int start = ((pageIndex - 1) * pageSize + 1);
            int end = start + Math.min(pageSize, list.size()) - 1;
            return start + " to " + end + " of " + totalRowCount;
        }
        
    }
}
