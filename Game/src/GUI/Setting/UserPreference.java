package GUI.Setting;

import GUI.Component.RotatingSettingOption;

import java.io.*;

public class UserPreference implements Serializable {
    private int resolutionIndex;
    private static UserPreference userPreference = null;

    public static UserPreference getInstance(){
        if (userPreference==null){
            File userFile = new File("Game/src/GUI/Setting/Preference.dat");
            if (userFile.exists()){
                try (ObjectInputStream oi = new ObjectInputStream(new FileInputStream(userFile));){
                    userPreference = (UserPreference) oi.readObject();
                }
                catch (ClassNotFoundException | IOException e){

                }
            }
            else {
                userPreference = new UserPreference();
            }
        }
        return userPreference;
    }

    public static void writeFile(){
        File f = new File("Game/src/GUI/Setting/Preference.dat");
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));){
            out.writeObject(UserPreference.getInstance());
            System.out.println("write");
        }
        catch (IOException e){

        }
    }

    private UserPreference(){
        this.resolutionIndex = 0;
    }

    private UserPreference(int resolutionIndex){
        this.resolutionIndex = resolutionIndex;
    }

    public int getResolutionIndex() {
        return resolutionIndex;
    }

    public void setResolutionIndex(int resolutionIndex) {
        this.resolutionIndex = resolutionIndex;
    }
}
