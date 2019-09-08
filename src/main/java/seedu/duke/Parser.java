package seedu.duke;

import seedu.duke.command.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a parser to parse the user input and turn it into specific command
 */
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


    /**
     * Parses the given string to a command
     * @param input user input
     * @return The command in the input
     * @throws DukeException
     */
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




    /**
     * Validates the starting word to be a valid command
     *
     * @param userCmd User input
     * @return Command type
     * @throws DukeException.inputInvalidException Throw exception when the starting
     * word is not a valid command
     */
    private static CommandType getCommandType(String userCmd) throws DukeException.inputInvalidException {
        //make sure starting words is inside C enums
        for (CommandType c : CommandType.values()) {
            if (c.name().equals(userCmd.split(" ")[0])) {
                return c;
            }
        }
        throw new DukeException.inputInvalidException();
    }

    //

    /**
     * Returns arr[description][time] for ddl and event
     *
     * @param input User input
     * @return String array arr[description][time] for ddl and event
     * @throws DukeException
     */
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

    /**
     * Determines if the description component is empty in an input
     * @param input User input
     * @return True if description is empty, false otherwise
     */
    private static boolean isEmptyDescription(String input) {
        if ((input.split(" ", 2).length == 1)
                || input.split(" ", 2)[1].trim().equals("")) {
            return true;
        }
        return false;
    }

    /**
     * Returns the description of a todo task
     *
     * @param input User input
     * @return The description
     * @throws DukeException Throw exception when description is left empty
     */
    public static String getDescriptionOfTodo(String input) throws DukeException {

        if (!isEmptyDescription(input)) {
            return input.split(" ", 2)[1];
        }
        throw new DukeException.emptyDescriptionException();
    }

    /**
     * Returns the description of a deadline and event
     *
     * @param input User input
     * @return The description
     * @throws DukeException Throw exception when description is left empty
     */
    public static String getDescriptionOfEventAndDeadline(String input) throws DukeException {
        if (getInfo(input)[0].trim().equals("")) {
            throw new DukeException.emptyDescriptionException();
        }
        return getInfo(input)[0];
    }

    /**
     * Gets the time of a deadline of a event in the form of string
     *
     * @param input User input
     * @return Time
     * @throws DukeException Throw exception when time is left empty
     */
    public static String getTimeString(String input) throws DukeException {
        if (getInfo(input)[1].trim().equals("")) {
            throw new DukeException.missingTimeException();
        }
        return getInfo(input)[1];
    }

    //previously get date, get the date from string

    /**
     * Transform the time from String type to Date type,
     * if the string is of valid format
     *
     * @param input User input
     * @return Time of the task as a Date type, return "null" if format is not parsable
     * @throws DukeException throw exception if the format of the strings is invalid
     */
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
