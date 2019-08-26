package seedu.duke;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    // return arr[description][time]
    public static String[] getInfo(String type, String input) {
        String descriptionWithTime = input.split(" ", 2)[1];
        String[] arr = new String[2];
        switch (type) {
            case "deadline":
                    arr = descriptionWithTime.split(" /by ");
                break;
            case "event":
                arr = descriptionWithTime.split(" /at ");
                break;
        }
        return arr;
    }

    public static boolean inputValidation(String input) throws Exception {
        if (!(input.startsWith("todo") || input.startsWith("event") || input.startsWith("deadline"))) {
            throw new DukeException.inputInvalidException();
        }

        if (input.startsWith("event") && input.equals("event")) {
                throw new DukeException.emptyDescriptionException("event");
        }

        if (input.startsWith("todo") && input.equals("todo")) {
            throw new DukeException.emptyDescriptionException("todo");
        }

        if (input.startsWith("deadline") && input.equals("deadline")) {
            throw new DukeException.emptyDescriptionException("deadline");
        }

        return true;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
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
                try {
                    inputValidation(userInput);
                    System.out.println("Got it. I've added this task: ");
                    String[] infoList;
                    if (userInput.startsWith("deadline")) {
                        infoList = getInfo("deadline", userInput);
                        Deadline newDeadline = new Deadline(infoList[0], infoList[1]);
                        inputArray.add(newDeadline);
                    } else if (userInput.startsWith("event")) {
                        infoList = getInfo("event", userInput);
                        Event newEvent = new Event(infoList[0], infoList[1]);
                        inputArray.add(newEvent);
                    } else {
                        String description = userInput.split(" ", 2)[1];
                        Todo newTodo = new Todo(description);
                        inputArray.add(newTodo);
                    }
                    Task currTask = inputArray.get(inputArray.size() - 1);
                    System.out.println(" " + currTask.toString());
                    System.out.println("Now you have " + inputArray.size() + " tasks in the list.");
                    userInput = input.nextLine();
                } catch (Exception e) {
                    userInput = input.nextLine();
                }
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

}
