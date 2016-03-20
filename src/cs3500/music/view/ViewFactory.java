package cs3500.music.view;

public class ViewFactory {
    IMusicView buildView(String viewName) {
        switch (viewName) {
            case "visual":
                return new GuiViewFrame();
//            case "midi":
//                return new MidiViewImpl();
            case "console":
                return new ConsoleView();
            default:
                return new ConsoleView(); //TODO: what should the default really be?
        }
    }
}
