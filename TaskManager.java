import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

class Deadline {
    int day, month, year;

    public Deadline(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%d", day, month, year);
    }
}


class Task {
    String title, description;
    Deadline deadline;
    int priority;
    int status;

    public Task(String title, String description, Deadline deadline, int priority, int status) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Title: " + title +
               "\nDescription: " + description +
               "\nDeadline: " + deadline +
               "\nPriority: " + (priority == 1 ? "High" : "Low") +
               "\nStatus: " + (status == 1? "Complete" : "Incomplete");
    }
}

public class TaskManager {
    private ArrayList<Task> tasks = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    // Add a new task
    public void addTask() {
        System.out.print("Enter task title: ");
        String title = scanner.nextLine();

        System.out.print("Enter task description: ");
        String description = scanner.nextLine();

    

    

        int currentYear = Year.now().getValue(); // Get the current year
        


        int day, month, year, priority,status;
    do { 
        System.out.print("Enter deadline day: ");
        day = scanner.nextInt();
        if (day < 1 || day > 31) {
            System.out.println("     Invalid day. Enter a valid day (1-31).\n");
        }
} while (day < 1 || day > 31);
do{
        System.out.print("Enter deadline month: ");
        month = scanner.nextInt();
        if(month<1 || month>12){
            System.out.println("     Invalid Month. Enter a valid Month (1-12).\n");
        }
    }while(month<1 || month>12);

    do{
        System.out.print("Enter deadline year: ");
        year = scanner.nextInt();
if(year<currentYear){
    System.out.println("Invalid year. Enter a year greater than " + currentYear  );
}
    }while(year<currentYear);

    do{ 
        System.out.print("Enter priority (0 for low, 1 for high): ");
        priority = scanner.nextInt();
        scanner.nextLine();  // consume newline
if(priority < 0 || priority >1){
System.out.println("Invalid priority , Please entre only (0 for low, 1 for high) ");
}
}while(priority < 0 || priority >1);
    

        tasks.add(new Task(title, description, new Deadline(day, month, year), priority,status=0));
        System.out.println("Task added successfully!");
    }



    // Display all tasks
    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("============ Task " + (i + 1) + ":============");
                System.out.println(tasks.get(i));
                System.out.println("================================");
            }
        }
    }

    // Modify a task
    public void modifyTask() {

        int currentYear = Year.now().getValue(); // Get the current year

        System.out.print("Enter the task number to modify (1 to " + tasks.size() + "): ");
        int index = scanner.nextInt() - 1;

        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            System.out.println("1- Modify title");
            System.out.println("2- Modify description");
            System.out.println("3- Modify deadline");
            System.out.println("4- Modify priority");
            System.out.println("5- Modify status");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter new title: ");
                    task.title = scanner.nextLine();
                }
                case 2 -> {
                    System.out.print("Enter new description: ");
                    task.description = scanner.nextLine();
                }
                case 3 -> {
do{
                    System.out.print("Enter new deadline day: ");
                    task.deadline.day = scanner.nextInt();
    if(task.deadline.day<1 || task.deadline.day>31){
    System.out.println("Invalid day. Enter a valid day (1-31)");
}
    }while(task.deadline.day<1 || task.deadline.day>31);
do{
                    System.out.print("Enter new deadline month: ");
                    task.deadline.month = scanner.nextInt();
     if(task.deadline.month<1 || task.deadline.month>12){
                        System.out.println("Invalid Month. Enter a valid day (1-12)");
                    }
     }while(task.deadline.month<1 || task.deadline.month>12);

     do{
                    System.out.print("Enter new deadline year: ");
                    task.deadline.year = scanner.nextInt();
                    scanner.nextLine(); // consume newline
         if(task.deadline.year<currentYear) {

         System.out.println("Enter a year greater than " + currentYear);                 
}
}while(task.deadline.year<currentYear);
                }
                case 4 -> {
                    do{
                    System.out.print("Enter new priority (0 for low, 1 for high): ");
                    task.priority = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    if(task.priority < 0 || task.priority >1){
                        System.out.println("Invalid priority , Please entre only (0 for low, 1 for high) ");
                        }
                        }while(task.priority < 0 || task.priority >1);
                }
                case 5 -> {
                    do{
                    System.out.print("Enter new status (0 for incomplete, 1 for complete): ");
                    task.status = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    if(task.status < 0 || task.status >1){
                        System.out.println("Invalid Status , Please entre only (0 for Incomplete, 1 for Complete) ");
                        }
                        }while(task.status < 0 || task.status >1);
                }
                default -> System.out.println("Invalid choice.");
            }
            System.out.println("Task modified successfully!");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    // Delete a task
    public void deleteTask() {
        System.out.print("Enter the task number to delete (1 to " + tasks.size() + "): ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();  //consume newline

        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            System.out.println("Task deleted successfully.");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    // Filter tasks by priority
    public void filterByPriority(int priority) {
        boolean found = false;
        for (Task task : tasks) {
            if (task.priority == priority) {
                System.out.println(task);
                System.out.println("----------------------");
                found = true;
            }
        }
        if (!found) System.out.println("No tasks found with the specified priority.");
    }

    // Filter tasks by status
    public void filterByStatus(int status) {
        boolean found = false;
        for (Task task : tasks) {
            if (task.status == status) {
                System.out.println(task);
                System.out.println("----------------------");
                found = true;
            }
        }
        if (!found) System.out.println("No tasks found with the specified status.");
    }

    // Sort tasks by ascending deadline
    public void sortByAscendingDeadline() {
        tasks.sort((task1, task2) -> {
            if (task1.deadline.year != task2.deadline.year)
                return task1.deadline.year - task2.deadline.year;
            if (task1.deadline.month != task2.deadline.month)
                return task1.deadline.month - task2.deadline.month;
            return task1.deadline.day - task2.deadline.day;
        });
        System.out.println("Tasks sorted by ascending deadline.");
    }

    // Sort tasks by descending deadline
    public void sortByDescendingDeadline() {
        tasks.sort((task1, task2) -> {
            if (task2.deadline.year != task1.deadline.year)
                return task2.deadline.year - task1.deadline.year;
            if (task2.deadline.month != task1.deadline.month)
                return task2.deadline.month - task1.deadline.month;
            return task2.deadline.day - task1.deadline.day;
        });
        System.out.println("Tasks sorted by descending deadline.");
    }

    // Main menu
    public void start() {
        int choice;
        do {
            System.out.println("1- Add Task");
            System.out.println("2- Display Tasks");
            System.out.println("3- Modify Task");
            System.out.println("4- Delete Task");
            System.out.println("5- Filter Tasks by Priority");
            System.out.println("6- Filter Tasks by Status");
            System.out.println("7- Sort Tasks by Ascending Deadline");
            System.out.println("8- Sort Tasks by Descending Deadline");
            System.out.println("9- Quit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1 -> addTask();
                case 2 -> displayTasks();
                case 3 -> modifyTask();
                case 4 -> deleteTask();
                case 5 -> {
                    System.out.print("Enter priority (0 for low, 1 for high): ");
                    int priority = scanner.nextInt();
                    scanner.nextLine();
                    filterByPriority(priority);
                }
                case 6 -> {
                    System.out.print("Enter status (0 for incomplete, 1 for complete): ");
                    int status = scanner.nextInt();
                    scanner.nextLine();
                    filterByStatus(status);
                }
                case 7 -> sortByAscendingDeadline();
                case 8 -> sortByDescendingDeadline();
                case 9 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 9);
    }

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        taskManager.start();
    }
}
