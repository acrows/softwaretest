import java.io.*;//Line 1
import java.util.*;

/**
 * Models the information of an employee.
 *
 * @author Author Name
 * @version 1.0.0
*
 */
public class Employee  {

    private static PrintWriter  stdErr =
        new PrintWriter(System.err, true);

    private final static double  SALE_COMMISSION = 0.02;

    private final static String  DATA_DELIM = "_";

    private final static String  SALES_DELIM = ",";

    private String  name;

    private double  earnings;

    /**
     * Test case for class <code>Employee</code>
     *
     * @param args  not used.
     */
    public static void main(String[] args) {

        String  data = "John Smith_10.00_20_1000.0,100.0,0.0";

        Employee  employee = new Employee(data);

        try {
            if (employee.getEarnings() == 222.0) {
                stdErr.println("Test 1 passed");
            } else {
                stdErr.println("Test 1 failed");
            }
        } catch (NumberFormatException  iae)  {
            stdErr.println("Test 1 failed, " + iae);
        }

        data = "Ruchi Dong_30.00_40_2000.0,200.0,600.0";

        employee = new Employee(data);

        try {
            if (employee.getEarnings() == 1256.0) {
                stdErr.println("Test 2 passed");
            } else {
                stdErr.println("Test 2 failed");
            }
        } catch (NumberFormatException  iae)  {
            stdErr.println("Test 2 failed, " + iae);
        }
    }

    /**
     * Constructs an <code>Employee</code> object with the
     * information specified in the parameter <code>data</code>.
     * <p>
     * The input data has the following format:
     * </p>
     * <p>
     * name_hourlyWage_hoursWorked_sale1,sale2,...,salen
     * </p>
     * <p>
     * The sale fields store the amount of each sale made by the
     * employee.
     * </p>
     *
     * @param data  a String with employee information.
     * @throws NumberFormatException  if data contains invalid
     *                                numbers
     */
    public Employee(String data) throws NumberFormatException  {

        StringTokenizer tokenizer =
            new StringTokenizer(data, DATA_DELIM);

        try {
            name = tokenizer.nextToken();

            double hourlyWage =
                Double.parseDouble(tokenizer.nextToken());
            int hoursWorked =
                Integer.parseInt(tokenizer.nextToken());

            double commission = (tokenizer.hasMoreTokens()) ?
                                computeCommission(tokenizer.nextToken()) : 0;

            earnings = hourlyWage * hoursWorked + commission;

        } catch (NumberFormatException  nfe)  {
            throw new NumberFormatException(
                "bad employee data: " + data);
        }
    }

    /**
     * Obtains the name of the employee.
     *
     * @return a String with the name of the employee.
     */
    public String  getName()  {

        return name;
    }

    /**
     * Obtains the earnings of the employee.
     *
     * @return the earnings of the employee.
     */
    public double  getEarnings()  {

        return earnings;
    }

    /*
     * Computes the commission of the employee with the data
     * specified in the parameter.
     *
     * @param data  a String with the employee's sales data.
     * @return the commission earned by the employee.
     * @throws NumberFormatException  if data contains invalid
     *                                numbers
     */
    private double  computeCommission(String data)
        throws NumberFormatException {

        StringTokenizer tokenizer =
            new StringTokenizer(data, SALES_DELIM);

        double total = 0.0;

        try {

            String token = tokenizer.nextToken();

            while (tokenizer.hasMoreTokens()) {
                token = tokenizer.nextToken();
                total += Double.parseDouble(token);
            }

        } catch (NumberFormatException  nfe)  {
            throw new NumberFormatException(
                "bad sales data: " + data);
        }

        return total * SALE_COMMISSION;
    }
}