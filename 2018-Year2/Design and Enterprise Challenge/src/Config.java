import model.DataHandler;
import model.Employee;

import java.util.ArrayList;

/**
 * @author Domantas Giedraitis (student id: u1757704 )
 * @version 1
 * @since 2019-01-09
 */
public class Config {
    public static final ArrayList<Employee> EMPLOYEES = new DataHandler().getEmployees();
}
