package mygame.GUI;

import com.jme3.niftygui.NiftyJmeDisplay;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.tools.SizeValue;
import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.terrain.geomipmap.TerrainQuad;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.Controller;
import de.lessvoid.nifty.controls.ListBox;
import de.lessvoid.nifty.controls.ListBoxSelectionChangedEvent;
import de.lessvoid.nifty.controls.Parameters;
import de.lessvoid.nifty.controls.TextField;
import de.lessvoid.nifty.elements.render.TextRenderer;
import interactiveworld.World;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import mygame.util.Account;
import mygame.util.Curriculum;
import mygame.util.DBHandler;
import mygame.util.Load;
import static mygame.util.Load.getFile;
 
public class TestLoadingScreen extends SimpleApplication implements ScreenController, Controller {
 
    private NiftyJmeDisplay niftyDisplay;
    private Nifty nifty;
    private Element progressBarElement;
    private TerrainQuad terrain;
    private Material mat_terrain;
    private boolean load = false;
    private ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(2);
    private Future loadFuture = null;
    private TextRenderer textRenderer;
    
    private Load loader = new Load();
    
    private Account currentUser = new Account();  
    private Curriculum selectedCurriculum = new Curriculum();
    
    private ArrayList<String> accounts = new ArrayList<String>();
    private ArrayList<String> curriculums = new ArrayList<String>();
    
    
    TextField u_name_textFiled ;
    TextField Age_textFiled;
    String userName;
    int age;
    public DBHandler dbHandler;
    Screen screen;
    Boolean tableCreated=false;


 
    public static void main(String[] args) {
        TestLoadingScreen app = new TestLoadingScreen();
        app.start();
    }
 
    @Override
    public void simpleInitApp() {
        
        flyCam.setEnabled(false);
        niftyDisplay = new NiftyJmeDisplay(assetManager,
                inputManager,
                audioRenderer,
                guiViewPort);
        nifty = niftyDisplay.getNifty();
 
        nifty.fromXml("Interface/ProgressBar.xml", "loadlevel", this);
        load = true;
        guiViewPort.addProcessor(niftyDisplay);
    }
 
    @Override
    public void simpleUpdate(float tpf) {
        if (load) {
            if (loadFuture == null) {
                //if we have not started loading yet, submit the Callable to the executor
                loadFuture = exec.submit(loadingCallable);
            }
            //check if the execution on the other thread is done
            if (loadFuture.isDone()) {
                //these calls have to be done on the update loop thread, 
                //especially attaching the terrain to the rootNode
                //after it is attached, it's managed by the update loop thread 
                // and may not be modified from any other thread anymore!
                
                nifty.gotoScreen("accountList");
                
//                nifty.exit();
//                guiViewPort.removeProcessor(niftyDisplay);
//                flyCam.setEnabled(true);
//                flyCam.setMoveSpeed(50);
               // rootNode.attachChild(terrain);
                load = false;
            }
        }
    }
    //this is the callable that contains the code that is run on the other thread.
    //since the assetmananger is threadsafe, it can be used to load data from any thread
    //we do *not* attach the objects to the rootNode here!
    Callable<Void> loadingCallable = new Callable<Void>() {
 
        public Void call() {
 
            Element element = nifty.getScreen("loadlevel").findElementById("loadingtext");
            textRenderer = element.getRenderer(TextRenderer.class);
// 
//            mat_terrain = new Material(assetManager, "Common/MatDefs/Terrain/Terrain.j3md");
//            mat_terrain.setTexture("Alpha", assetManager.loadTexture("Textures/Terrain/splat/alphamap.png"));
            //setProgress is thread safe (see below)
           
            File[] files = loader.loadAccounts();
            if(files.length == 0){
                System.out.println("no .obj file");
            }     
            else{
                for(File file : files){
                    System.out.println(file.getName());
                    accounts.add(file.getName());
                }
            }
            
            
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(TestLoadingScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            setProgress(0.2f, "Loading grass");
               
//            Texture grass = assetManager.loadTexture("Textures/Terrain/splat/grass.jpg");
//            grass.setWrap(WrapMode.Repeat);
//            mat_terrain.setTexture("Tex1", grass);
//            mat_terrain.setFloat("Tex1Scale", 64f);
            
            files = loader.loadCurriculums();
            if(files.length == 0){
                System.out.println("no .cu file");
            }     
            else{
                for(File file : files){
                    System.out.println(file.getName());
                    curriculums.add(file.getName());
                }
            }
            try {
                TimeUnit.MICROSECONDS.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(TestLoadingScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            setProgress(0.4f, "Loading dirt");
 
//            Texture dirt = assetManager.loadTexture("Textures/Terrain/splat/dirt.jpg");
// 
//            dirt.setWrap(WrapMode.Repeat);
//            mat_terrain.setTexture("Tex2", dirt);
//            mat_terrain.setFloat("Tex2Scale", 32f);
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(TestLoadingScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            setProgress(0.5f, "Loading rocks");
 
//            Texture rock = assetManager.loadTexture("Textures/Terrain/splat/road.jpg");
// 
//            rock.setWrap(WrapMode.Repeat);
// 
//            mat_terrain.setTexture("Tex3", rock);
//            mat_terrain.setFloat("Tex3Scale", 128f);
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(TestLoadingScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            setProgress(0.6f, "Creating terrain");
 
//            AbstractHeightMap heightmap = null;
//            Texture heightMapImage = assetManager.loadTexture("Textures/Terrain/splat/mountains512.png");
//            heightmap = new ImageBasedHeightMap(heightMapImage.getImage());
// 
//            heightmap.load();
//            terrain = new TerrainQuad("my terrain", 65, 513, heightmap.getHeightMap());
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(TestLoadingScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            setProgress(0.8f, "Positioning terrain");
 
//            terrain.setMaterial(mat_terrain);
// 
//            terrain.setLocalTranslation(0, -100, 0);
//            terrain.setLocalScale(2f, 1f, 2f);
//            setProgress(0.9f, "Loading cameras");
// 
//            List<Camera> cameras = new ArrayList<Camera>();
//            cameras.add(getCamera());
//            TerrainLodControl control = new TerrainLodControl(terrain, cameras);
//            terrain.addControl(control);
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(TestLoadingScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            setProgress(1f, "Loading complete");
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(TestLoadingScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
    };
 
    public void setProgress(final float progress, final String loadingText) {
        //since this method is called from another thread, we enqueue the changes to the progressbar to the update loop thread
        enqueue(new Callable() {
 
            public Object call() throws Exception {
                final int MIN_WIDTH = 32;
                int pixelWidth = (int) (MIN_WIDTH + (progressBarElement.getParent().getWidth() - MIN_WIDTH) * progress);
                progressBarElement.setConstraintWidth(new SizeValue(pixelWidth + "px"));
                progressBarElement.getParent().layoutElements();
 
                textRenderer.setText(loadingText);
                return null;
            }
        });
 
    }
 
    public void showLoadingMenu() {
        nifty.gotoScreen("loadlevel");
        load = true;
    }
 
    @Override
    public void onStartScreen() {
    }
 
    @Override
    public void onEndScreen() {
    }
 
    @Override
    public void bind(Nifty nifty, Screen screen) {
        progressBarElement = nifty.getScreen("loadlevel").findElementByName("progressbar");
        this.screen = screen;
    }
 
    // methods for Controller
    @Override
    public boolean inputEvent(final NiftyInputEvent inputEvent) {
        return false;
    }
 
    public void onFocus(boolean getFocus) {
    }
 
 
    @Override
    public void stop() {
        super.stop();
        //the pool executor needs to be shut down so the application properly exits.
        exec.shutdown();
    }
   
    public void startGame(){
        
        
        Element name = nifty.getScreen("end").findElementById("name");
        Element age = nifty.getScreen("end").findElementById("age");
        Element curriculum = nifty.getScreen("end").findElementById("curriculum");
        
        textRenderer = name.getRenderer(TextRenderer.class);
        textRenderer.setText("user logedin: "+currentUser.getName());
        
        textRenderer = age.getRenderer(TextRenderer.class);
        textRenderer.setText("age: "+currentUser.getAge());
        
        textRenderer = curriculum.getRenderer(TextRenderer.class);
        textRenderer.setText("selected Curriculum: "+selectedCurriculum.getName());
        
        World world = new World(currentUser,selectedCurriculum);
        world.start();
    }
    
    public void loginClicked(){
       System.out.println("log Clicked");
       
       u_name_textFiled = screen.findNiftyControl("GTextfield1", TextField.class);
       Age_textFiled = screen.findNiftyControl("GTextfield2", TextField.class);
       
       
       
      userName=u_name_textFiled.getDisplayedText();
       age=Integer.parseInt(Age_textFiled.getDisplayedText());

       if(tableCreated==false){
           init_DB();
       }
     
       dbHandler.LoginUser(userName, age);
   }
    
    public void registorClicked(){
       
        System.out.println("Reg Clicked");
        u_name_textFiled = screen.findNiftyControl("GTextfield1", TextField.class);
        Age_textFiled = screen.findNiftyControl("GTextfield2", TextField.class);
       
        userName=u_name_textFiled.getDisplayedText();
        age=Integer.parseInt(Age_textFiled.getDisplayedText());

        if(tableCreated==false){
           init_DB();
        }
         
       
        dbHandler.register(userName, age);  
        nifty.gotoScreen("curriculumList");
        currentUser.setName(userName);
        currentUser.setAge(age);
   }
  
    public void init_DB(){
      dbHandler = new DBHandler();
      tableCreated=true;
    }
   
    public void newAccount(){
        nifty.gotoScreen("register");
    }

    public void newGame(){
        nifty.gotoScreen("end");
        startGame();
    }

    public void fillAccounts() {
        ListBox listBox = screen.findNiftyControl("accountListBox", ListBox.class);
        init_DB();
        ArrayList<String> names = dbHandler.getAllUsers();
        for (String name : names) {
            listBox.addItem(name);
            //System.out.println(name);
       }
        
    }
  
    public void fillCurriculums() {
        ListBox listBox = screen.findNiftyControl("curriculumListBox", ListBox.class);
        listBox.addAllItems(curriculums);
    }
    
    @NiftyEventSubscriber(id="accountListBox")
    public void onaccountListBoxSelectionChanged(final String id, final ListBoxSelectionChangedEvent<String> event) {
    List<String> selection = event.getSelection();
    for (String selectedItem : selection) {
      System.out.println("listbox selection [" + selectedItem + "]");
      currentUser.setName(selectedItem);
    }
    nifty.gotoScreen("curriculumList");
  }

    @NiftyEventSubscriber(id="curriculumListBox")
    public void oncurriculumListBoxSelectionChanged(final String id, final ListBoxSelectionChangedEvent<String> event) {
    List<String> selection = event.getSelection();
    for (String selectedItem : selection) {
        System.out.println("listbox selection [" + selectedItem + "]");
        selectedCurriculum.setName(selectedItem);

    }
    newGame();
  }

    
    public void bind(Nifty nifty, Screen screen, Element elmnt, Parameters prmtrs) {
        progressBarElement = elmnt.findElementById("progressbar");

    }

    public void init(Parameters prmtrs) {
    }
}

//handle exeptions
//change layouts
//fix listBox problems
//remove .obj and .cu on listBox display