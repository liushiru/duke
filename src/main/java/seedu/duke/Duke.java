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

    /*
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
*/
    public static void storeInput(String userInput, ArrayList<Task> inputArray) throws IOException {
        String type = userInput.split(" ", 2)[0];
        String[] infoList = getInfo(type, userInput);

        switch (type) {
            case "deadline":
                String time = infoList[1].trim();
                Date date = getDate(time);
                if (date != null) {
                   // System.out.println("getDate" + date);
                }
                Deadline newDeadline = new Deadline(infoList[0].trim(), infoList[1].trim());
                inputArray.add(newDeadline);
                break;
            case "event":
                infoList = getInfo("event", userInput);
                Event newEvent = new Event(infoList[0].trim(), infoList[1].trim());
                inputArray.add(newEvent);
                break;
            case "todo":
                String description = userInput.split(" ", 2)[1].trim();
                Todo newTodo = new Todo(description);
                inputArray.add(newTodo);
                break;
        }
        writeFile(inputArray);
    }

    public static String taskArrayToString (ArrayList<Task> inputArray) {
        String content = "";
        int i = 1;
        for(Task task : inputArray) {
            content += i++ + "." + task.toString() + "\n";
        }
        return content;
    }

    public static Task decodeLine (String line) {
        int typeIndex = line.indexOf("[") + 1;
        char type = line.charAt(typeIndex);
        int doneIndex = line.indexOf("][") + 1;
        boolean done = (line.charAt(doneIndex) == '\u2713') ? true : false;
        String description;
        String time;
        String info = line.split(" ", 2)[1];
        String[] infoList;
        if (type == 'T') {
            description = info;
            if (done) {
                Todo todo = new Todo(description);
                todo.setDone();
                return todo;
            } else {
                return new Todo(description);
            }
        } else if (type == 'D') {
            infoList = info.split(" \\(by: ", 2);
            description = infoList[0];
            time = infoList[1].substring(0, infoList[1].length() - 1);
            if (done) {
                Deadline deadline = new Deadline(description, time);
                deadline.setDone();
                return deadline;
            } else {
                return new Deadline(description, time);
            }
        } else {
            infoList = info.split(" \\(at: ", 2);
            description = infoList[0];

            time = infoList[1].substring(0, infoList[1].length() - 1);
            if (done) {
                Event event = new Event(description, time);
                event.setDone();
                return event;
            } else {
                return new Event(description, time);
            }
        }
    }
    public static void readFile (ArrayList<Task> inputArray) {
        inputArray.clear();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("duke.txt"));
            String line = reader.readLine();
            if (line==null) {
                return;
            }
            while (line != null) {
                Task task = decodeLine(line);
                inputArray.add(task);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(ArrayList<Task> inputArray) {
        String content = taskArrayToString(inputArray);
        try {
            PrintWriter outputStream = new PrintWriter(new FileWriter("duke.txt"));
            outputStream.print(content);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Date getDate (String input) {

        try {
            Date date;
            if (input.contains(" ")) {
                date = new SimpleDateFormat("dd/MM/yyyy HHmm").parse(input);
            } else {
                date = new SimpleDateFormat("dd/MM/yyyy").parse(input);
            }
            //System.out.println(date);
            return date;
        } catch (ParseException e) {
            //System.out.println("invalid date with space");
            return null;
        }
    }
    public static void storeTask(Task task) {
        try {
            PrintWriter outputStream = new PrintWriter(new FileWriter("duke.txt", true));
            outputStream.println(task.toString());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
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

        try {
            readFile(inputArray);
            displayFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();

        while (!userInput.equals("bye")) {

            String[] tokens = userInput.split(" ");

            if (userInput.equals("list")) {
                System.out.println(taskArrayToString(inputArray));
                userInput = input.nextLine();
            } else if (tokens[0].equals("done")) {
                int taskNum = Integer.parseInt(tokens[1]) - 1;
                inputArray.get(taskNum).setDone();
                writeFile(inputArray);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + inputArray.get(taskNum).toString());
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
