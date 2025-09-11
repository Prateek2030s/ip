package alex;

/**
 * Represents the user interaction.
 */
public class Ui {

    /**
     * Sends the current tasklist, storage and user's input to the parser
     * and returns the chatbot's response after parsing the input.
     *
     * @param taskList List of task currently used.
     * @param storage Current storage of task list.
     * @param input User's input to the chatbot.
     * @return Response of the chatbot after parsing user's input.
     */
      public String run(TaskList taskList, Storage storage, String input) {
          try {
              Parser p = new Parser(input, taskList, storage);
              return p.parseInput();
          } catch (AlexException e) {
              return e.getMessage();
          }

      }


}
