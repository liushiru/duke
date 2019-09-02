package seedu.duke;

import seedu.duke.command.AddCommand;
import seedu.duke.command.Command;
import seedu.duke.command.ListCommand;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Parser {
    protected String input;

//    public Parser(String userInput) {
//        this.input = userInput;
//    }

    //validate and create Parser object
  /*  public Parser(String input, ArrayList<Task> ... inputArray) throws Exception {
        if(isValidInput(input, inputArray[0])) {
            this.input = input;
        };
    }*/

//
//    public Parser(String input, ArrayList<Task> ... inputArray) throws Exception {
//        if(isValidInput(input, inputArray[0])) {
//            this.input = input;
//        };
//    }

    public static Command parse (String input) throws DukeException.invalidCommandType {

        if (validCommand(input)) {
            CommandType type = getCommandType(input);
            switch (type) {
                case deadline: {
                    String description = getDescriptionOfEventAndDeadline(input);
                    String time = getTimeString(input);
                    Deadline deadline = new Deadline(description, time);
                    if (getTimeDate(input) != null) {
                        deadline.setTime(getTimeDate(input));
                    }
                    return new AddCommand(deadline);
                }

                case event: {
                    String description = getDescriptionOfEventAndDeadline(input);
                    String time = getTimeString(input);
                    Event event = new Event(description, time);
                    if (getTimeDate(input) != null) {
                        event.setTime(getTimeDate(input));
                    }
                    return new AddCommand(event);
                }

                case todo: {
                    String description = getDescriptionOfTodo(input);
                    Todo todo = new Todo(description);
                    return new AddCommand(todo);
                }

                case list: {
                    return new ListCommand();
                }
            }
        }
            System.out.println("invalid Input");
            return null;

    }


    //make sure starting words is inside C enums
    public static boolean validCommand(String userCmd) {
        for (CommandType c : CommandType.values()) {
            if (c.name().equals(userCmd.split(" ")[0])) {
                return true;
            }
        }

        try {
            throw new DukeException.inputInvalidException();
        } catch (DukeException.inputInvalidException e) {
            new UI().readCommand();
        }
        return false;
    }

    //test if input is valid
    public boolean isValidInput(String input, ArrayList<Task>... inputArray) throws Exception {

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
    };



    //Decide which C
    public static CommandType getCommandType(String input) {
        if (input.startsWith("list")) {
            return CommandType.list;
        }
        if (input.startsWith("delete")) {
            return CommandType.delete;
        }
        if (input.startsWith("deadline")) {
            return CommandType.deadline;
        }
        if (input.startsWith("event")) {
            return CommandType.event;
        }
        if (input.startsWith("todo")) {
            return CommandType.todo;
        }
        if (input.startsWith("done")) {
            return CommandType.done;
        }
        if (input.startsWith("bye")) {
            return CommandType.bye;
        }
        return null;
    }

    // return arr[description][time] for ddl and event
    private static String[] getInfo(String input) {
        CommandType type = getCommandType(input);
//        if (!(type == CommandType.event || type == CommandType.deadline)) {
//            throw new DukeException.invalidCommandType();
//        }
        String descriptionWithTime = input.split(" ", 2)[1];
        String[] arr = new String[2];
        switch (type) {
            case deadline:
                arr = descriptionWithTime.split(" /by ");
                break;
            case event:
                arr = descriptionWithTime.split(" /at ");
                break;
        }
        return arr;
    }

    public static String getDescriptionOfTodo(String input) {
        return input.split(" ", 2)[1];
    }
    public static String getDescriptionOfEventAndDeadline(String input) {
        return getInfo(input)[0];
    }

    public static String getTimeString(String input) {
        return getInfo(input)[1];
    }

    //previously get date, get the date from string
    public static Date getTimeDate (String input) throws DukeException.invalidCommandType {
        if (getCommandType(input) == CommandType.deadline || getCommandType(input) == CommandType.event) {
            try {
                String time = getTimeString(input);
                Date date;
                if (time.contains(" ")) {
                    date = new SimpleDateFormat("dd/MM/yyyy HHmm").parse(input);
                } else {
                    date = new SimpleDateFormat("dd/MM/yyyy").parse(input);
                }
                return date;
            } catch (ParseException e) {
                return null;
            }
        } else {
            throw new DukeException.invalidCommandType();
        }
    }
}
