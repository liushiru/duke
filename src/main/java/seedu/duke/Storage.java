package seedu.duke;

import java.io.*;

/**
 * Represent a class to read and write from a file
 */
public class Storage {

    /**
     * Represents the name of the text file to be stored to.
     */
    protected String fileName;

    /**
     * Creates a Storage object with the given file
     *
     * @param fileName path of the file that needs to be read from and write to
     * @throws FileNotFoundException throw exception when file is not found
     */
    public Storage(String fileName) throws FileNotFoundException {
        this.fileName = getFilePath(fileName);
    }


    /**
     * Gets file path
     *
     * @param fileName name of the file
     * @return Relative path of the file from the accessing place
     */
    private String getFilePath(String fileName) {
        String UiTestFilePath = "..\\" + fileName;
        String accessFrom = System.getProperty("user.dir");
        if (accessFrom.contains("text-ui-test")) {
            return UiTestFilePath;
        }
        return fileName;
    }

    /**
     * Decode the string to a task
     *
     * @param line The string to decode
     * @return Task in that string
     */
    private static Task decodeLine(String line) {
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


    /**
     * Overwrite the file with the updated task list
     *
     * @param tasks Updated tasks lists
     */
    public void writeFile(TaskList tasks) {
        String content = tasks.toString();
        try {
            PrintWriter outputStream = new PrintWriter(new FileWriter(this.fileName));
            outputStream.print(content);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read all tasks to task list
     *
     * @return Task list that contains all tasks stored in the file
     */
    public TaskList readFile() throws IOException {
        BufferedReader reader;
        try {
            TaskList tasks = new TaskList();
            reader = new BufferedReader(new FileReader(this.fileName));
            String line = reader.readLine();
            if (line == null) {
                return new TaskList();
            }
            while (line != null) {
                Task task = decodeLine(line);
                tasks.add(task);
                line = reader.readLine();
            }
            return tasks;
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            String accessFrom = System.getProperty("user.dir");
            System.out.println(accessFrom);
            String absoluteFilePath = accessFrom + "\\" + this.fileName;
            System.out.println(absoluteFilePath);
            File file = new File(absoluteFilePath);
            if (file.createNewFile()) {
                System.out.println("file created: " + absoluteFilePath);
            }
            return new TaskList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new TaskList();
    }
}