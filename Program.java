import entities.Department;
import entities.HourContract;
import entities.Worker;
import enums.WorkerLevel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws ParseException {
        Locale.setDefault(Locale.US);
        try (Scanner sc = new Scanner(System.in)){
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            System.out.print("Enter department's name: ");
            String department = sc.nextLine();

            System.out.println("Enter Worker Data:");
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Level: ");
            String level = sc.nextLine();
            System.out.print("Base Salary: ");
            Double baseSalary = sc.nextDouble();


            Worker worker = new Worker(name, baseSalary, WorkerLevel.valueOf(level), new Department(department));

            System.out.print("How many contracts to this worker? ");
            int n = sc.nextInt();

            for(int i = 0; i<n; i++){
                System.out.println("Enter contract #" + (i+1) + " data:");
                System.out.print("Date (DD/MM/YYYY): ");
                Date date = sdf.parse(sc.next());
                System.out.print("Value per hour: ");
                Double valuePerHour = sc.nextDouble();
                System.out.print("Duration (hours): ");
                Integer hours = sc.nextInt();

                HourContract contract = new HourContract(date, valuePerHour, hours);

                worker.addContract(contract);
            }
            System.out.println();
            System.out.print("Enter month and year to calculate income (MM/YYYY): ");
            String monthAndYear = sc.next();

            int month = Integer.parseInt(monthAndYear.substring(0, 2));
            int year = Integer.parseInt(monthAndYear.substring(3));

            System.out.println("Name: " + worker.getName());
            System.out.println("Department: " + worker.getDepartment().getName());
            System.out.println("Income for " + monthAndYear + ": " + String.format("%.2f", worker.income(year, month)));

        }
    }

}