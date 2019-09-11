package seedu.duke;

import java.util.Scanner;

/**
 * Represents a class that interact with the user
 */
public class UI {


    private Scanner scanner;

    /**
     * Creates a UI object.
     */
    public UI() {
        scanner = new Scanner(System.in);
    }

    /**
     * Read user input.
     *
     * @return Input string from user
     */
    public String readCommand() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }

        try {
            throw new DukeException("No Line Found");
        } catch (DukeException e) {
            System.out.println(e.message);
        }

        return "bye";
    }

    /**
     * Display all tasks in the tasks list
     *
     * @param tasks Task list containing all tasks
     */
    public void displayTasks(TaskList tasks) {
        System.out.println(tasks.toString());
    }

    /**
     * Displays total number of tasks in the list
     *
     * @param tasks Task list
     */
    public void displayNumOfTasks(TaskList tasks) {
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }

    /**
     * Displays message after adding a task
     *
     * @param task The task that is added
     */
    public void addTaskUI(Task task) {
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + task.toString());
    }

    /**
     * Displays message after a task is marked as done
     *
     * @param task The task that is marked as done
     */
    public void doneTaskUI(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task.toString());
    }

    /**
     * Displays message after a task is deleted
     *
     * @param taskNum The task number of the task that needs to be deleted
     */
    public void deleteTaskUI(int taskNum) {
        System.out.println("I have deleted task No." + (taskNum + 1) + " for you");
    }

    /**
     * Displays all tasks found
     *
     * @param relevantTasks Task list containing all tasks found
     */
    public void findTaskUI(TaskList relevantTasks) {
        System.out.println("Here are the relevant tasks");
        System.out.println(relevantTasks.toString());
    }

    /**
     * Displays greeting message when program starts
     */
    public void start() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * Displays separation line between each user input
     */
    public void showLine() {
        System.out.println("_____________________________________________________");
    }

    /**
     * Display goodbye message when user exits
     */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
