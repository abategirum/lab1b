package pensionPlan;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        Employee[] emps = {
                new Employee(123, "Daniel", "Agar", LocalDate.parse("2018-01-17"), 105945.50, new PensionPlan("EX1089",LocalDate.parse("2023-01-17"),100.00)),
                new Employee(345, "Benard", "Shaw", LocalDate.parse("2019-05-03"), 197750.00, new PensionPlan("",LocalDate.parse("2019-05-03"),null)),
                new Employee(567, "Carly", "Agar", LocalDate.parse("2014-05-16"), 842000.75, new PensionPlan("SM2307",LocalDate.parse("2019-11-04"),1555.50)),
                new Employee(789, "Wesley", "Schneider", LocalDate.parse("2019-05-02"), 74500.00, new PensionPlan("",null,null)),

        };

        print_employees(emps);
        upcomingEnrolles(emps);
    }


    public static void print_employees(Employee[] emps){
        Arrays.sort(emps, Comparator.comparing(Employee::getLastName)
                .thenComparing(Employee::getYearlySalary));

        System.out.println("Print in Json Format");
        System.out.println("[");
        Arrays.stream(emps).forEach(emp -> {
            System.out.println("{");
            System.out.println("\"EmployeeId\": " + emp.getEmployeeId() + "," + "\"name\": " + emp.getFirstName() + "," + "\"lastName\": " + emp.getLastName() + ","+ "\"Salary\": " + emp.getYearlySalary() + ","+ "\"planReferenceNumber\": "
                    + emp.pensionPlan.getPlanReferenceNumber()
                    + "\"monthly contribution\": " + emp.pensionPlan.getMonthlyContribution() +"},");
        });
        System.out.println("]");
        System.out.println("---------------------------------------------------");
    }
    public static void upcomingEnrolles(Employee[] emps){
        LocalDate today = LocalDate.now();
        LocalDate firstOfNextMonth = today.withDayOfMonth(1).plusMonths(1);
        LocalDate lastOfNextMonth = firstOfNextMonth.withDayOfMonth(firstOfNextMonth.lengthOfMonth());


        System.out.println("Upcoming Enrolls");
        System.out.println("[");
        Arrays.stream(emps)
                .filter(emp -> Objects.equals(emp.pensionPlan.getPlanReferenceNumber(), "")
                && emp.getEmployementDate().plusYears(5).isBefore(lastOfNextMonth.plusDays(1)))
                .sorted(Comparator.comparing(Employee::getEmployementDate))
                .forEach(emp -> {
                    System.out.println("{");
                    System.out.println("\"EmployeeId\": " + emp.getEmployeeId() + ",");
                    System.out.println("\"name\": " + emp.getFirstName() + ",");
                    System.out.println("\"lastName\": " + emp.getLastName() + ",");
                    System.out.println("\"employmentDate\": " + emp.getEmployementDate() + ",");
                    System.out.println("\"Salary\": " + emp.getYearlySalary());
                    System.out.println("},");
                });
        System.out.println("]");
    }
}
