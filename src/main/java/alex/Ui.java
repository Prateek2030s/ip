package alex;

import java.util.Scanner;

/**
 * Represents the user interaction
 */
public class Ui {

    /**
     * Receives user inputs
     * @param taskList List of task currently used
     * @param storage Current storage of task list
     */
      public String run(TaskList taskList, Storage storage, String input) {
          try {
//              Scanner scanner = new Scanner(System.in);
//              String s = scanner.nextLine();
              Parser p = new Parser(input);
              return p.parseInput(taskList, storage);
          } catch (AlexExecption e) {
              //System.out.println(e);
              //this.run(taskList, storage);
              return e.getMessage();
          }

      }


}
