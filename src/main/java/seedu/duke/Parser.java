package seedu.duke;

import seedu.duke.command.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {

    public static int getTaskNum (String input) throws DukeException.invalidTaskNumException {
         if (input.contains(" ")) {
            String[] arr;
            arr = input.split(" ", 2);
             try {
             //   if (!arr[1].equals("all")) {
                    int x = Integer.parseInt(arr[1]);
                    return x-1;
             //   }
            } catch (Exception e) {
                throw new DukeException.invalidTaskNumException();
            }
        } else {
            throw new DukeException.invalidTaskNumException();
        }
    }


    public static Command parse(String input) throws DukeException {

        CommandType type = getCommandType(input);
        switch (type) {
            case deadline: {
                String description = getDescriptionOfEventAndDeadline(input).trim();
                String time = getTimeString(input).trim();
                Deadline deadline = new Deadline(description, time);
                if (getTimeDate(input) != null) {
                    deadline.setTime(getTimeDate(input));
                }
                return new AddCommand(deadline);
            }

            case event: {
                String description = getDescriptionOfEventAndDeadline(input).trim();
                String time = getTimeString(input).trim();
                Event event = new Event(description, time);
                if (getTimeDate(input) != null) {
                    event.setTime(getTimeDate(input));
                }
                return new AddCommand(event);
            }

            case todo: {
                String description = getDescriptionOfTodo(input).trim();
                Todo todo = new Todo(description);
                return new AddCommand(todo);
            }

            case list: {
                return new ListCommand();
            }

            case done: {
                int taskNum = getTaskNum(input);
                return new DoneCommand(taskNum);
            }

            case delete: {
                int taskNum = getTaskNum(input);
                return new DeleteCommand(taskNum);
            }

            case find: {
                String keyword = input.split(" ", 2)[1];
                return new FindCommand(keyword);
            }

            case bye: {

                if (input.equals("bye")) {
                    return new ExitCommand();
                }
                throw new DukeException.inputInvalidException();
            }
        }
            System.out.println("invalid Input");
            return null;
    }


    //make sure starting words is inside C enums
    public static CommandType getCommandType(String userCmd) throws DukeException.inputInvalidException {
        for (CommandType c : CommandType.values()) {
            if (c.name().equals(userCmd.split(" ")[0])) {
                return c;
            }
        }
        throw new DukeException.inputInvalidException();
    }

    // return arr[description][time] for ddl and event
    private static String[] getInfo(String input) throws DukeException {
        CommandType type = getCommandType(input);
        if (input.split(" ", 2).length == 1) {
            throw new DukeException.emptyDescriptionException();
        }
        String descriptionWithTime= input.split(" ", 2)[1];
        String[] arr = new String[2];
        switch (type) {
            case deadline:
                if(!input.contains(" /by ")) {
                    throw new DukeException.invalidFormatException();
                } else {
                    arr = descriptionWithTime.split(" /by ");
                }
                break;
            case event:
                if(!input.contains(" /at ")) {
                    throw new DukeException.invalidFormatException();
                }
                arr = descriptionWithTime.split(" /at ");
                break;
        }
        if (arr.length == 1 || arr.length == 0) {
            throw new DukeException.inputInvalidException();
        }
        return arr;
    }

    private static boolean isEmptyDescription(String input) {
        if ((input.split(" ", 2).length == 1)
                || input.split(" ", 2)[1].trim().equals("")) {
            return true;
        }
        return false;
    }

    public static String getDescriptionOfTodo(String input) throws DukeException {

        if (!isEmptyDescription(input)) {
            return input.split(" ", 2)[1];
        }
        throw new DukeException.emptyDescriptionException();
    }
    public static String getDescriptionOfEventAndDeadline(String input) throws DukeException {
        if (getInfo(input)[0].trim().equals("")) {
            throw new DukeException.emptyDescriptionException();
        }
        return getInfo(input)[0];
    }

    public static String getTimeString(String input) throws DukeException {
        if (getInfo(input)[1].trim().equals("")) {
            throw new DukeException.missingTimeException();
        }
        return getInfo(input)[1];
    }

    //previously get date, get the date from string
    public static Date getTimeDate (String input) throws DukeException{
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
    }
}
