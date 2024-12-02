import java.time.Year; // Importing the Year class to get the current year
import java.util.ArrayList; // Importing ArrayList for managing the list of tasks
import java.util.Scanner; // Importing Scanner for user input

// Class representing a deadline for a task
class Deadline {
    int day, month, year;

    // Constructor to initialize the deadline
    public Deadline(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    // Method to return deadline as a formatted string
    @Override
    public String toString() {
        return String.format("%02d/%02d/%d", day, month, year);
    }
}

// Class representing a task
class Task {
    String title, description; // Task title and description
    Deadline deadline; // Task deadline
    int priority; // Task priority (0 = low, 1 = high)
    int status; // Task status (0 = incomplete, 1 = complete)

    // Constructor to initialize the task
    public Task(String title, String description, Deadline deadline, int priority, int status) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.status = status;
    }

    // Method to return task details as a formatted string
    @Override
    public String toString() {
        return "Title: " + title +
               "\nDescription: " + description +
               "\nDeadline: " + deadline +
               "\nPriority: " + (priority == 1 ? "High" : "Low") +
               "\nStatus: " + (status == 1 ? "Complete" : "Incomplete");
    }
}

// Main class for managing tasks
public class TaskManager {
    private ArrayList<Task> tasks = new ArrayList<>(); // List of tasks
    private Scanner scanner = new Scanner(System.in); // Scanner for user input

    // Method to add a new task
    public void addTask() {
        // Get task title and description from the user
        System.out.print("Enter task title: ");
        String title = scanner.nextLine();
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();

        int currentYear = Year.now().getValue(); // Get the current year

        // Variables for deadline, priority, and status
        int day, month, year, priority, status;

        // Input validation for day
        do {
            System.out.print("Enter deadline day: ");
            day = scanner.nextInt();
            if (day < 1 || day > 31) {
                System.out.println("     Invalid day. Enter a valid day (1-31).\n");
            }
        } while (day < 1 || day > 31);

        // Input validation for month
        do {
            System.out.print("Enter deadline month: ");
            month = scanner.nextInt();
            if (month < 1 || month > 12) {
                System.out.println("     Invalid Month. Enter a valid Month (1-12).\n");
            }
        } while (month < 1 || month > 12);

        // Input validation for year
        do {
            System.out.print("Enter deadline year: ");
            year = scanner.nextInt();
            if (year < currentYear) {
                System.out.println("Invalid year. Enter a year greater than " + currentYear);
            }
        } while (year < currentYear);

        // Input validation for priority
        do {
            System.out.print("Enter priority (0 for low, 1 for high): ");
            priority = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            if (priority < 0 || priority > 1) {
                System.out.println("Invalid priority, Please enter only (0 for low, 1 for high) ");
            }
        } while (priority < 0 || priority > 1);

        // Add the new task to the list
        tasks.add(new Task(title, description, new Deadline(day, month, year), priority, status = 0));
        System.out.println("==============================================");
        System.out.println("========== Task added successfully!===========");
        System.out.println("==============================================");
        System.out.println("\n");


    }

    // Method to display all tasks
    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available."); // Message if no tasks exist
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("================================");
                System.out.println("============ Task " + (i + 1) + ":============");
                System.out.println("================================");

                System.out.println(tasks.get(i));

            }
        }
    }

    // Method to modify an existing task
    public void modifyTask() {
        int currentYear = Year.now().getValue(); // Get the current year

        // Prompt user to select a task to modify
        System.out.print("Enter the task number to modify (1 to " + tasks.size() + "): ");
        int index = scanner.nextInt() - 1;

        if (index >= 0 && index < tasks.size()) { // Check if the task number is valid
            Task task = tasks.get(index); // Get the selected task
            System.out.println("1- Modify title");
            System.out.println("2- Modify description");
            System.out.println("3- Modify deadline");
            System.out.println("4- Modify priority");
            System.out.println("5- Modify status");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Perform the modification based on the user's choice
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
                    // Modify deadline with input validation
                    do {
                        System.out.print("Enter new deadline day: ");
                        task.deadline.day = scanner.nextInt();
                        if (task.deadline.day < 1 || task.deadline.day > 31) {
                            System.out.println("Invalid day. Enter a valid day (1-31)");
                        }
                    } while (task.deadline.day < 1 || task.deadline.day > 31);
                    do {
                        System.out.print("Enter new deadline month: ");
                        task.deadline.month = scanner.nextInt();
                        if (task.deadline.month < 1 || task.deadline.month > 12) {
                            System.out.println("Invalid Month. Enter a valid day (1-12)");
                        }
                    } while (task.deadline.month < 1 || task.deadline.month > 12);
                    do {
                        System.out.print("Enter new deadline year: ");
                        task.deadline.year = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        if (task.deadline.year < currentYear) {
                            System.out.println("Enter a year greater than " + currentYear);
                        }
                    } while (task.deadline.year < currentYear);
                }
                case 4 -> {
                    // Modify priority with input validation
                    do {
                        System.out.print("Enter new priority (0 for low, 1 for high): ");
                        task.priority = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        if (task.priority < 0 || task.priority > 1) {
                            System.out.println("Invalid priority, Please enter only (0 for low, 1 for high) ");
                        }
                    } while (task.priority < 0 || task.priority > 1);
                }
                case 5 -> {
                    // Modify status with input validation
                    do {
                        System.out.print("Enter new status (0 for incomplete, 1 for complete): ");
                        task.status = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        if (task.status < 0 || task.status > 1) {
                            System.out.println("Invalid Status, Please enter only (0 for Incomplete, 1 for Complete) ");
                        }
                    } while (task.status < 0 || task.status > 1);
                }
                default -> System.out.println("Invalid choice.");
            }
            System.out.println("Task modified successfully!");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    // Method to delete a task
    public void deleteTask() {
        // Prompt user to select a task to delete
        System.out.print("Enter the task number to delete (1 to " + tasks.size() + "): ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline

        if (index >= 0 && index < tasks.size()) { // Check if the task number is valid
            tasks.remove(index); // Remove the selected task
            System.out.println("Task deleted successfully.");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    // Method to filter tasks by priority
    public void filterByPriority(int priority) {
        boolean found = false; // Flag to check if any task matches the criteria
        for (Task task : tasks) {
            if (task.priority == priority) { // Check if the task matches the priority
                System.out.println(task);
                System.out.println("----------------------");
                found = true;
            }
        }
        if (!found) System.out.println("No tasks found with the specified priority.");
    }

    // Method to filter tasks by status
    public void filterByStatus(int status) {
        boolean found = false; // Flag to check if any task matches the criteria
        for (Task task : tasks) {
            if (task.status == status) { // Check if the task matches the status
                System.out.println(task);
                System.out.println("----------------------");
                found = true;
            }
        }
        if (!found) System.out.println("No tasks found with the specified status.");
    }

    // Method to sort tasks by ascending deadline
    public void sortByAscendingDeadline() {
        tasks.sort((task1, task2) -> {
            // Compare years, then months, then days
            if (task1.deadline.year != task2.deadline.year)
                return task1.deadline.year - task2.deadline.year;
            if (task1.deadline.month != task2.deadline.month)
                return task1.deadline.month - task2.deadline.month;
            return task1.deadline.day - task2.deadline.day;
        });
        System.out.println("Tasks sorted by ascending deadline.");
        displayTasks();
    }

    // Method to sort tasks by descending deadline
    public void sortByDescendingDeadline() {
        tasks.sort((task1, task2) -> {
            // Compare years, then months, then days
            if (task2.deadline.year != task1.deadline.year)
                return task2.deadline.year - task1.deadline.year;
            if (task2.deadline.month != task1.deadline.month)
                return task2.deadline.month - task1.deadline.month;
            return task2.deadline.day - task1.deadline.day;
        });
        System.out.println("Tasks sorted by descending deadline.");
        displayTasks();
    }

    // Main menu to interact with the user
    public void start() {
        int choice;
        do {
            // Display menu options
            System.out.println("======================================");
            System.out.println("=================MENU=================");
            System.out.println("======================================");

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
            scanner.nextLine(); // Consume newline

            // Perform the chosen operation
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
        } while (choice != 9); // Repeat until the user chooses to quit
    }

    // Main method to run the program
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager(); // Create TaskManager object
        taskManager.start(); // Start the menu
    }
}
