package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Employee;
import org.json.simple.*;
import org.json.simple.parser.*;

import java.io.*;
import java.util.ArrayList;

/**
 * @author Domantas Giedraitis - Navus
 * @version 1.001
 **/

public class DataHandler {
    private ArrayList<Employee> allEmployees;
    private JSONArray jsonDataArray;

    public DataHandler() {
        loadEmployees();
    }

    private void setArraySize(int size) {
        allEmployees = new ArrayList<>();
    }

    public ArrayList<Employee> getEmployees() {
        return this.allEmployees;
    }

    /*
     * Opens file - reads data and converts it to JSON array object
     * */
    private void loadDataArray() {
        String filePath = "D:\\GitHub Repositories\\HD-University\\2018-Year2\\Design and Enterprise Challenge\\data\\EmployeeList";
        System.out.println("Reading data...");
        JSONParser parser = new JSONParser();
        try (FileReader fileReader = new FileReader(filePath)) {
            // store whole JSON data into array
            this.jsonDataArray = (JSONArray) parser.parse(fileReader);
        } catch (IOException | ParseException e1) {
            System.out.println("Error while reading data... File is empty or file does not exists");
            System.out.println("Creating new file...");
            this.jsonDataArray = new JSONArray();
        }
    }

    /*
     * Reads the specified file, iterates through it and stores all JSON object
     * within it into an JSON array .
     * */
    public void loadEmployees() {
        // read data from file and store it into Json Objects array
        loadDataArray();

        // create Employee array.
        setArraySize(this.jsonDataArray.size());

        // Iterate through JSON data
        for (Object dataIterator : this.jsonDataArray) {
            readAndStoreSingleObject(dataIterator);
        }
        System.out.println("Data reading finished...");
    }

    /*
     * 'Translates' one JSON object to Strings, ints and arrays,
     * then eventually pass those converted values to other method,
     * which stores them in a new Employee object , in arrays.
     * @param : dataIterator - JSON Object
     * @param : questionIterator - index to show current json array place.
     * */
    private void readAndStoreSingleObject(Object dataIterator) {
        // set current JSON object (Current Employee)
        JSONObject currentEmployee = (JSONObject) dataIterator;

        // Setters follow
//        int employeeID = (int)((long)currentEmployee.get("employeeID"));
//        String name = currentEmployee.get("Name").toString();
//        String surname = currentEmployee.get("Surname").toString();
        int workGroup = (int) Integer.parseInt(String.valueOf(currentEmployee.get("WorkGroup")));
//        short warehouse =(short)((int)currentEmployee.get("Warehouse"));
        String username = currentEmployee.get("Username").toString();
        String password = currentEmployee.get("Password").toString();
        int privileges = ((int) Integer.parseInt(String.valueOf(currentEmployee.get("Privileges"))));

        storeData(username, password, workGroup, privileges);
    }

    /*
     * Creates new Employee object, and depending on level, stores it to either
     * Easy, Medium or Hard array lists. Without that, it ALWAYS stores the file
     * into allQuestion array.
     * */
    private void storeData(String username, String password, int workGroup, int privileges) {
        // set Employee.
        allEmployees.add(new Employee(username, password, workGroup, privileges));
    }
}
