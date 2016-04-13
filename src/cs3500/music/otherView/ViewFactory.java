package cs3500.music.otherView;


/**
 * Create the view
 */
public class ViewFactory {

  /**
   * create different type of view
   *
   * @param s the view type we take in
   * @return the view we want
   */
  public static View create(String s) {
    switch (s) {
      case "console":
        return new ConsoleViewImpl();
      case "visual":
        return new GuiViewImpl();
      case "midi":
        return new MidiViewImpl();
      case "composite":
        return new CompositeViewImpl();
      default:
        throw new IllegalArgumentException("Cannot create such view");
    }
  }
}
