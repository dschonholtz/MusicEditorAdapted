package cs3500.music.otherView;


import cs3500.music.model.Model;

/**
 * Created by liangmanman1 on 3/20/16.
 */
public class ConsoleViewImpl implements View {
  private String console = "";

  @Override
  public void display(Model model) {
    console = model.display();
    System.out.println(console);
  }

  public String getConsole() {
    return console;
  }
}
