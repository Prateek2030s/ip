package alex;

import java.util.Scanner;

public class Ui {

      public void run(TaskList taskList, Storage storage) {
          try {
              Scanner scanner = new Scanner(System.in);
              String s = scanner.nextLine();
              Parser p = new Parser(s);
              p.setToParse(taskList, storage);
          } catch (AlexExecption e) {
              System.out.println(e);
              this.run(taskList, storage);
          }

      }

}
