package seedu.duke;

import java.util.Scanner;


public class UI {

    private Scanner scanner;

    public UI() {
        scanner = new Scanner(System.in);
    }
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

    public void displayTasks(TaskList tasks) {
        System.out.println(tasks.toString());
    }

    public void displayNumOfTasks(TaskList tasks) {
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }
    public void addTaskUI(Task task) {
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + task.toString());
    }

    public void doneTaskUI(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task.toString());
    }

    public void deleteTaskUI(int taskNum) {
        System.out.println("I have deleted task No." + taskNum + " for you");
    }

    public void findTaskUI(TaskList relevantTasks) {
        System.out.println("Here are the relevant tasks");
        System.out.println(relevantTasks.toString());
    }
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

    public void showLine() {
        System.out.println("_____________________________________________________");
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }


 /*   public String taskArrayToString (TaskList tasks) {
        String content = "";
        int i = 1;
        for(Task task : tasks) {
            content += i++ + "." + task.toString() + "\n";
        }
        return content;
    }*/


}
