package seedu.duke;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();

        ArrayList<Task> inputArray = new ArrayList<Task>();
        while (!userInput.equals("bye")) {

            String[] tokens = userInput.split(" ");


            if (userInput.equals("list")) {
                for(int i = 0; i < inputArray.size(); i++) {
                    String description = inputArray.get(i).getDescription();
                    System.out.println((i + 1) + ".[" + inputArray.get(i).getStatusIcon() + "] " + description);
                }
                userInput = input.nextLine();
            } else if (tokens[0].equals("done")) {
                int taskNum = Integer.parseInt(tokens[1]) - 1;
                inputArray.get(taskNum).setDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + inputArray.get(taskNum).getStatusIcon() + "] " + inputArray.get(taskNum).getDescription());
                userInput = input.nextLine();
            } else {
                Task newTask= new Task(userInput);
                inputArray.add(newTask);
                System.out.println("added: " + userInput);
                userInput = input.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

}
