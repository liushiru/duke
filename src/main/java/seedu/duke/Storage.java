package seedu.duke;


import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;

//to read and write from the file
public class Storage {

    protected String fileName;
    protected Path path;
    protected FileReader fileReader;
    public Storage(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
    }
/*
    public String taskArrayToString (ArrayList<Task> inputArray) {
        String content = "";
        int i = 1;
        for(Task task : inputArray) {
            content += i++ + "." + task.toString() + "\n";
        }
        return content;
    }
*/
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

    public void writeFile(ArrayList<Task> inputArray) {
        //String content = taskArrayToString(inputArray);
        UI ui = new UI();
        String content = ui.taskArrayToString(inputArray);
        try {
            PrintWriter outputStream = new PrintWriter(new FileWriter(this.fileName));
            outputStream.print(content);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TaskList readFile () {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(this.fileName));
            String line = reader.readLine();
            if (line==null) {
                return;
            }
            while (line != null) {
                Task task = decodeLine(line);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayFile() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(this.fileName));
        String line;
        while((line = in.readLine()) != null) {
            System.out.println(line);
        }
    }
}
