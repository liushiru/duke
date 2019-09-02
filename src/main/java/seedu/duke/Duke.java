package seedu.duke;

import seedu.duke.CommandType;
import seedu.duke.command.Command;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;



public class Duke {
    private Storage storage;
    private TaskList tasks;
    private UI ui;

    public Duke(String filePath) {
        ui = new UI();
        tasks = new TaskList();
        try {
            storage = new Storage(filePath);
            tasks = storage.readFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //have the file, have loaded the tasks, can call method to parse
    public void run() {
        ui.start();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                // isExit = c.isExit();
            } catch (DukeException.invalidCommandType invalidCommandType) {
                invalidCommandType.printStackTrace();
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Duke("tasks.txt").run();
    }
}
/*        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();

        while (!userInput.equals("bye")) {
            try {
                inputValidation(userInput,tasks);
            } catch (Exception e) {
                userInput = input.nextLine();
            }

            String[] tokens = userInput.split(" ");

            if (userInput.equals("list")) {
                System.out.println(dukeFile.taskArrayToString(tasks));
            } else if (tokens[0].equals("done")) {
                int taskIndex = Integer.parseInt(tokens[1]) - 1;
                tasks.get(taskIndex).setDone();
                dukeFile.writeFile(tasks);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks.get(taskIndex).toString());
            } else if (tokens[0].equals("delete")) {
                if (tokens[1].equals("all")) {
                    tasks.clear();
                } else {
                    int taskIndex = Integer.parseInt(tokens[1]) - 1;
                    tasks.remove(taskIndex);
                }
            } else if (tokens[0].equals("find")) {
                ArrayList<Task> relevantTaskList = new ArrayList<Task>();
                String keyword = userInput.split(" ", 2)[1];
                for(int i=0; i < tasks.size(); i++) {
                    Task currTask = tasks.get(i);
                    if (currTask.toString().contains(keyword)) {
                        relevantTaskList.add(currTask);
                    }
                }
                System.out.println("Here are the matching tasks in your list");
                System.out.println(dukeFile.taskArrayToString(relevantTaskList));
            } else if (tokens[0].equals("delete")){
                try {
                    inputValidation(userInput);
                    System.out.println("Got it. I've added this task: ");
                    storeInput(userInput, tasks);
                    dukeFile.writeFile(tasks);
                    Task currTask = tasks.get(tasks.size() - 1);
                    System.out.println(" " + currTask.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } catch (Exception e) {
                    userInput = input.nextLine();
                }
                System.out.println("Nice! I've deleted the task");
                System.out.println("Now you left " + tasks.size() + " in the list");
                dukeFile.writeFile(tasks);
            } else {
                System.out.println("Got it. I've added this task: ");
                storeInput(userInput, tasks);
                dukeFile.writeFile(tasks);
                Task currTask = tasks.get(tasks.size() - 1);
                System.out.println(" " + currTask.toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            }
            userInput = input.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!"); */


