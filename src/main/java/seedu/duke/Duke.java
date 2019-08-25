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
                    System.out.println((i + 1) + "." + inputArray.get(i).toString());
                }
                userInput = input.nextLine();
            } else if (tokens[0].equals("done")) {
                int taskNum = Integer.parseInt(tokens[1]) - 1;
                inputArray.get(taskNum).setDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + inputArray.get(taskNum).getStatusIcon() + "] " + inputArray.get(taskNum).getDescription());
                userInput = input.nextLine();
            } else {
                System.out.println("Got it. I've added this task: ");

                if (userInput.charAt(0) == 'd') {
                    String descriptionWithTime = userInput.split(" ", 2)[1];
                    String description = descriptionWithTime.split(" /by ")[0];
                    String by = descriptionWithTime.split(" /by ")[1];
                    Deadline newDeadline = new Deadline(description, by);
                    inputArray.add(newDeadline);
                    System.out.println("  " + newDeadline.toString());
                } else if (userInput.charAt(0) == 'e') {
                    String descriptionWithTime = userInput.split(" ", 2)[1];
                    String description = descriptionWithTime.split(" /at ")[0];
                    String at = descriptionWithTime.split(" /at ")[1];
                    Event newEvent = new Event(description, at);
                    inputArray.add(newEvent);
                    System.out.println( "  " + newEvent.toString());
                } else {
                    String description = userInput.split(" ", 2)[1];
                    Todo newTodo = new Todo(description);
                    inputArray.add(newTodo);
                    System.out.println( "  " + newTodo.toString());
                }

                System.out.println("Now you have " + inputArray.size() + " tasks in the list.");
                userInput = input.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

}
