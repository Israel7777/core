package mygame.GUI;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.Controller;
import de.lessvoid.nifty.controls.Parameters;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.xml.xpp3.Attributes;
import java.util.Properties;

public class MainMenuController extends AbstractAppState implements ScreenController, Controller{

  private Application app;
  private AppStateManager stateManager;
  private Nifty nifty;
  private Screen screen;
    
  public MainMenuController() {
  
  }
  
  public void bind(Nifty nifty, Screen screen) {
        this.nifty = nifty;
        this.screen = screen;
  }
  
    public void onStartScreen() {
  }

    public void onEndScreen() {
  }
    
  @Override
  public void initialize(AppStateManager stateManager, Application app) {
    this.app = app;
    this.stateManager = stateManager;
  }

    public void bind(Nifty nifty, Screen screen, Element element, Properties parameter, Attributes controlDefinitionAttributes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void init(Properties parameter, Attributes controlDefinitionAttributes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void onFocus(boolean getFocus) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean inputEvent(NiftyInputEvent inputEvent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void bind(Nifty nifty, Screen screen, Element elmnt, Parameters prmtrs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void init(Parameters prmtrs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}