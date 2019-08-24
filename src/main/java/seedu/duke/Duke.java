package seedu.duke;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();

        ArrayList<String> inputArray = new ArrayList<String>();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                for(int i = 0; i < inputArray.size(); i++) {
                    System.out.println((i + 1) + ". " + inputArray.get(i));
                }
                userInput = input.nextLine();
            } else {
                inputArray.add(userInput);
                System.out.println("added: " + userInput);
                userInput = input.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

}
