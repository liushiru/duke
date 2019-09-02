package seedu.duke;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {


   public String displayContent(TaskList tasks) {
       String content = "";
       int i = 1;
       for(Task task : tasks) {
           content += i++ + "." + task.toString() + "\n";
       }
       System.out.println("content" + content);
       return content;
   }

}
