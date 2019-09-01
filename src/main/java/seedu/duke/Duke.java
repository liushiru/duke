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

    // Store input to RAM
    public static void storeInput(String userInput, ArrayList<Task> inputArray) throws IOException {
        String type = userInput.split(" ", 2)[0];
        String[] infoList = getInfo(type, userInput);
        String time = infoList[1].trim();
        Date date = getDate(time);
        switch (type) {
            case "deadline":
                Deadline newDeadline = new Deadline(infoList[0].trim(), infoList[1].trim());
                newDeadline.setType(Type.T);
                if (date != null) {
                    newDeadline.setTime(date);
                }
                inputArray.add(newDeadline);
                break;
            case "event":
               // infoList = getInfo("event", userInput);
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
        DukeFile dukeFile = new DukeFile();
        try {
            dukeFile.readFile(inputArray);
            //readFile(inputArray);
            dukeFile.displayFile("duke.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();

        while (!userInput.equals("bye")) {

            String[] tokens = userInput.split(" ");

            if (userInput.equals("list")) {
                System.out.println(dukeFile.taskArrayToString(inputArray));
                userInput = input.nextLine();
            } else if (tokens[0].equals("done")) {
                int taskNum = Integer.parseInt(tokens[1]) - 1;
                inputArray.get(taskNum).setDone();
                dukeFile.writeFile(inputArray, "duke.txt");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + inputArray.get(taskNum).toString());
                userInput = input.nextLine();
            } else if (tokens[0].equals("delete")) {

            } else {
                try {
                    inputValidation(userInput);
                    System.out.println("Got it. I've added this task: ");
                    storeInput(userInput, inputArray);
                    dukeFile.writeFile(inputArray, "duke.txt");
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
