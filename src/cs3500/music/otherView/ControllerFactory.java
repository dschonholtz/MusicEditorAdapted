package cs3500.music.otherView;


import cs3500.music.controller.*;
import cs3500.music.model.Model;

/**
 * Create the view
 */
public class ControllerFactory {


  public static Controller create(Model model, View view) {
    if (view instanceof GuiView) {
      GuiView newView = (GuiView)view;
      return new Controller2Impl(model, newView);
    }
    else {
      return new ControllerImpl(model, view);
    }
  }

}