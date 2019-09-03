package seedu.duke;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {

   @Override
   public String toString() {
       String content = "";
       int i = 1;
       for(Task task : this) {
           content += i++ + "." + task.toString() + "\n";
       }
       return content;
   }

}
