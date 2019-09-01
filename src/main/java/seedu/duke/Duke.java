package seedu.duke;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

enum Command {
    list,
    done,
    delete,
    todo,
    deadline,
    event,
    bye
}




public class Duke {

    // return arr[description][time] for ddl and event
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

    public static boolean validCommand(String userCmd) {
        for (Command c : Command.values()) {
            if (c.name().equals(userCmd)) {
                return true;
            }
        }
        return false;
    }

    public static boolean inputValidation(String input, ArrayList<Task> ... inputArray) throws Exception {
        String validCommand = "list " +"done " +"delete" +"todo" +"deadline" +"event" +"bye";
       // if (!validCommand.contains(input.split(" ", 1)[0])) {

        if (input.startsWith("event") && input.equals("event")) {
                throw new DukeException.emptyDescriptionException("event");
        }

        if (input.startsWith("todo") && input.equals("todo")) {
            throw new DukeException.emptyDescriptionException("todo");
        }

        if (input.startsWith("deadline") && input.equals("deadline")) {
            throw new DukeException.emptyDescriptionException("deadline");
        }

        if (input.startsWith("delete") || input.startsWith("done")) {
            if (input.contains(" ")) {
                String[] arr;
                arr = input.split(" ", 2);
                try {

                    if (!arr[1].equals("all")) {
                        int x = Integer.parseInt(arr[1]);
                        if (x > inputArray[0].size() || x <= 0) {
                            throw new DukeException.taskOutOfRangeException();
                        }
                    }
                } catch (Exception e) {
                    throw new DukeException.invalidTaskNumException();
                }
            } else {
                throw new DukeException.invalidTaskNumException();
            }
        }

        if (!validCommand(input.split(" ")[0])) {
            throw new DukeException.inputInvalidException();
        }

        return true;
    }

    // Store input to RAM
    public static void storeInput(String userInput, ArrayList<Task> inputArray) throws IOException {
        String type = userInput.split(" ", 2)[0];
        String[] infoList = getInfo(type, userInput);
        ;
        switch (type) {
            case "deadline":
                String time = infoList[1].trim();
                Date date = getDate(time);
                Deadline newDeadline = new Deadline(infoList[0].trim(), infoList[1].trim());
                newDeadline.setType(Type.T);
                if (date != null) {
                    newDeadline.setTime(date);
                }
                inputArray.add(newDeadline);
                break;
            case "event":
                time = infoList[1].trim();
                date = getDate(time);
                Event newEvent = new Event(infoList[0].trim(), infoList[1].trim());
                newEvent.setType(Type.E);
                if (date != null) {
                    newEvent.setTime(date);
                }
                inputArray.add(newEvent);
                break;
            case "todo":
                String description = userInput.split(" ", 2)[1].trim();
                Todo newTodo = new Todo(description);
                newTodo.setType(Type.T);
                inputArray.add(newTodo);
                break;
        }
    }


    //Get the date from a formatted input string
    public static Date getDate (String input) {

        try {
            Date date;
            if (input.contains(" ")) {
                date = new SimpleDateFormat("dd/MM/yyyy HHmm").parse(input);
            } else {
                date = new SimpleDateFormat("dd/MM/yyyy").parse(input);
            }
            return date;
        } catch (ParseException e) {
            return null;
        }
    }

    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        ArrayList<Task> inputArray = new ArrayList<Task>();
        DukeFile dukeFile = new DukeFile("duke.txt");
        try {
            dukeFile.readFile(inputArray);
            dukeFile.displayFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();

        while (!userInput.equals("bye")) {
            try {
                inputValidation(userInput,inputArray);
            } catch (Exception e) {
                userInput = input.nextLine();
            }

            String[] tokens = userInput.split(" ");

            if (userInput.equals("list")) {
                System.out.println(dukeFile.taskArrayToString(inputArray));
            } else if (tokens[0].equals("done")) {
                int taskIndex = Integer.parseInt(tokens[1]) - 1;
                inputArray.get(taskIndex).setDone();
                dukeFile.writeFile(inputArray);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + inputArray.get(taskIndex).toString());
            } else if (tokens[0].equals("delete")) {
                if (tokens[1].equals("all")) {
                    inputArray.clear();
                } else {
                    int taskIndex = Integer.parseInt(tokens[1]) - 1;
                    inputArray.remove(taskIndex);
                }
            } else if (tokens[0].equals("find")) {
                ArrayList<Task> relevantTaskList = new ArrayList<Task>();
                String keyword = userInput.split(" ", 2)[1];
                for(int i=0; i < inputArray.size(); i++) {
                    Task currTask = inputArray.get(i);
                    if (currTask.getDescription().contains(keyword)) {
                        relevantTaskList.add(currTask);
                    }
                }
                System.out.println("Here are the matching tasks in your list");
                System.out.println(dukeFile.taskArrayToString(relevantTaskList));
                userInput = input.nextLine();
            } else if (tokens[0].equals("delete")){
                try {
                    inputValidation(userInput);
                    System.out.println("Got it. I've added this task: ");
                    storeInput(userInput, inputArray);
                    dukeFile.writeFile(inputArray);
                    Task currTask = inputArray.get(inputArray.size() - 1);
                    System.out.println(" " + currTask.toString());
                    System.out.println("Now you have " + inputArray.size() + " tasks in the list.");
                } catch (Exception e) {
                    userInput = input.nextLine();
                }
                System.out.println("Nice! I've deleted the task");
                System.out.println("Now you left " + inputArray.size() + " in the list");
                dukeFile.writeFile(inputArray);
            } else {
                System.out.println("Got it. I've added this task: ");
                storeInput(userInput, inputArray);
                dukeFile.writeFile(inputArray);
                Task currTask = inputArray.get(inputArray.size() - 1);
                System.out.println(" " + currTask.toString());
                System.out.println("Now you have " + inputArray.size() + " tasks in the list.");
            }
            userInput = input.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
