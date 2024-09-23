package main;
import com.thebest12lines.worldmanager.util.UpdateBuildException;
import com.thebest12lines.worldmanager.util.Updater;


public class UpdateTest {
    public static void main(String[] args) {
       try {
        Updater.downloadAndInstallUpdates();
    } catch (UpdateBuildException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    }
}
