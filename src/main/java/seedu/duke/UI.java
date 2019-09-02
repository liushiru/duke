package seedu.duke;

import java.util.ArrayList;

public class UI {


    public void displayTaskListSize(ArrayList<Task> inputArray){
        System.out.println("Now you have " + inputArray.size() + " tasks in the list.");
    }

    public void addTaskUI(Task task) {
        System.out.println("Got it. I've added this task: ");
        task.toString();
    }

    public void doneTaskUI(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    public void deleteTaskUI(int taskNum) {
        System.out.println("I have deleted task No." + taskNum + " for you");
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


    public void welfare() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    public String taskArrayToString (ArrayList<Task> inputArray) {
        String content = "";
        int i = 1;
        for(Task task : inputArray) {
            content += i++ + "." + task.toString() + "\n";
        }
        return content;
    }

}
