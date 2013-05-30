package controllers;

import java.util.*;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.db.jpa.*;

import views.html.*;

import models.*;

/**
 * Manage a database of employees
 */
public class Application extends Controller {
    
    /**
     * Handle default path requests, redirect to employees list
     */
    public static Result index() {
        return GO_HOME;
    }

    /**
     * This result directly redirect to application home.
     */
    public static Result GO_HOME = redirect(
        routes.Application.list(0, "id", "asc", "")
    );

    /**
     * Display the paginated list of employees.
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on employee last names
     */
    @Transactional(readOnly=true)
    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
            list.render(
                Employee.page(page, 10, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
}
