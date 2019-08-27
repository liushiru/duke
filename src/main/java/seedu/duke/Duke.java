package seedu.duke;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
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

    public static void storeTask(char type, Task task) throws IOException {
        try {
            PrintWriter outputStream = new PrintWriter(new FileWriter("duke.txt", true));
            outputStream.print(type + " | ");
            if (task.isDone) {
                outputStream.print(1 + " | ");
            } else {
                outputStream.print(0 + " | ");
            }

            if (type == 'T') {
                outputStream.println(task.getDescription());
            } else if (type == 'D'){
                outputStream.print(task.getDescription() + " | ");
                outputStream.println(((Deadline) task).getTime());
            } else {
                outputStream.print(task.getDescription() + " | ");
                outputStream.println(((Event) task).getTime());
            }
            outputStream.close(); //flush the data to the file
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void storeInput(String userInput, ArrayList<Task> inputArray) throws IOException {
        String type = userInput.split(" ", 2)[0];
        String[] infoList = getInfo(type, userInput);
        switch (type) {
            case "deadline":
                Deadline newDeadline = new Deadline(infoList[0], infoList[1]);
                storeTask('D', newDeadline);
                inputArray.add(newDeadline);
                break;
            case "event":
                infoList = getInfo("event", userInput);
                Event newEvent = new Event(infoList[0], infoList[1]);
                storeTask('E', newEvent);
                inputArray.add(newEvent);
                break;
            case "todo":
                String description = userInput.split(" ", 2)[1];
                Todo newTodo = new Todo(description);
                storeTask('T', newTodo);
                inputArray.add(newTodo);
                break;
        }
    }

    public static void displayFile() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("duke.txt"));
        String line;
        while((line = in.readLine()) != null) {
            System.out.println(line);
        }
    }
            /*
    public static void display(ArrayList<Task> inputArray) {
        Task currTask = inputArray.get(inputArray.size() - 1);
        System.out.println(" " + currTask.toString());
        System.out.println("Now you have " + inputArray.size() + " tasks in the list.");
    }
*/
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        try {
            displayFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                    storeInput(userInput, inputArray);

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
