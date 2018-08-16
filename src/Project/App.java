package Project;




import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class App extends Application {

    @Override
    public void start(Stage stage) {
    	// Creates top-level object, set up the scene, and shown the stage here.
		PaneOrganiser organiser = new PaneOrganiser();
		Scene scene = new Scene((Parent) organiser.getRoot(),690,690);
		stage.setScene(scene);
		stage.setTitle("BYOP(L)");
		stage.show();

    }

    /*
    * Here is the mainline! No need to change this.
    */
    public static void main(String[] argv) {
        // launch is a method inherited from Application
        launch(argv);
    }
    
    
}
