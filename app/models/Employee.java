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
    public Integer age;
    
    @Constraints.Min(value=0)
    public Integer experience;
    
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
    public static Page page(int page, int pageSize, String sortBy, String order, SearchEmployee filter) {
        if(page < 1) page = 1;
        String fromWhere = addCond(filter);

        Query q1 = JPA.em().createQuery("SELECT count(e) " + fromWhere);
        setParams(q1, filter);
        Long total = (Long)q1.getSingleResult();

        Query q2 = JPA.em().createQuery(fromWhere + " order by e." + sortBy + " " + order);
        setParams(q2, filter);
        List<Employee> data = q2
            .setFirstResult((page - 1) * pageSize)
            .setMaxResults(pageSize)
            .getResultList();
        return new Page(data, total, page, pageSize);
    }

    private static String addCond(SearchEmployee filter) {
        StringBuilder fromWhere = new StringBuilder();

        fromWhere.append("FROM Employee e ");
        fromWhere.append("WHERE lower(e.first_name) like :fn");
        fromWhere.append("  and lower(e.last_name) like :ln");
        fromWhere.append("  and lower(e.second_name) like :sn");
        fromWhere.append("  and lower(e.description) like :descr");

        if (!filter.min_age.equals("")) fromWhere.append("  and e.age >= " + filter.min_age);
        if (!filter.max_age.equals("")) fromWhere.append("  and e.age <= " + filter.max_age);
        if (!filter.min_experience.equals("")) fromWhere.append("  and e.experience >= " + filter.min_experience);
        if (!filter.max_experience.equals("")) fromWhere.append("  and e.experience <= " + filter.max_experience);
        
        return fromWhere.toString();
    }

    private static void setParams(Query q, SearchEmployee filter) {
        q.setParameter("fn", "%" + filter.first_name.toLowerCase() + "%");
        q.setParameter("ln", "%" + filter.last_name.toLowerCase() + "%");
        q.setParameter("sn", "%" + filter.second_name.toLowerCase() + "%");
        q.setParameter("descr", "%" + filter.description.toLowerCase() + "%");
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
