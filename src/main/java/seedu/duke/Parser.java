package seedu.duke;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

enum Command {
    list,
    done,
    delete,
    todo,
    deadline,
    event,
    bye
}

public class Parser {
    protected String input;

    //make sure starting words is inside command enums
    public boolean validCommand(String userCmd) {
        for (Command c : Command.values()) {
            if (c.name().equals(userCmd)) {
                return true;
            }
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

    //validate and create Parser object
    public Parser(String input, ArrayList<Task> ... inputArray) throws Exception {
        if(isValidInput(input, inputArray[0])) {
            this.input = input;
        };
    }

    //Decide which command
    public Command getCommand() {
        if (this.input.startsWith("list")) {
            return Command.list;
        }
        if (this.input.startsWith("delete")) {
            return Command.delete;
        }
        if (this.input.startsWith("deadline")) {
            return Command.deadline;
        }
        if (this.input.startsWith("event")) {
            return Command.event;
        }
        if (this.input.startsWith("todo")) {
            return Command.todo;
        }
        if (this.input.startsWith("done")) {
            return Command.done;
        }
        if (this.input.startsWith("bye")) {
            return Command.bye;
        }
        return null;
    }

    // return arr[description][time] for ddl and event
    public String[] getInfo() throws DukeException.invalidCommandType {
        Command type = this.getCommand();
        if (!(type == Command.event || type == Command.deadline)) {
            throw new DukeException.invalidCommandType();
        }
        String descriptionWithTime = this.input.split(" ", 2)[1];
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

    public String getDescriptionOfTodo() {
        return this.input.split(" ", 2)[1];
    }
    public String getDesciptionOfEventAndDeadline() throws DukeException.invalidCommandType {
        return this.getInfo()[0];
    }

    public String getTimeString() throws DukeException.invalidCommandType {
        return this.getInfo()[1];
    }

    //previously get date, get the date from string
    public Date getTimeDate (String input) throws DukeException.invalidCommandType {
        if (this.getCommand() == Command.deadline || this.getCommand() == Command.event) {
            try {
                String time = this.getTimeString();
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
/*    public Task createTask() {
        if (this.getCommand() == Command.todo) {
            Todo todo = new Todo(this.getDescriptionOfTodo());
            return todo;
        }

        if (this.getCommand() == Command.event) {
            createTask()
        }
    }
    */

}
