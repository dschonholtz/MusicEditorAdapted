package cs3500.music.otherView;


import cs3500.music.model.Model;

/**
 * Represent the view of the music editor
 */
public interface View {

  /**
   * display this model in different ways
   * for example: GuiViewImpl displays colored image representing the model
   *              MidiViewImpl displays AudibleMusic representing the model
   *              ConsoleViewImpl displays String representing the model
   *
   * @param model the model we want to draw
   */
  void display(Model model);
}
