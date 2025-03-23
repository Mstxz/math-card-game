package GUI.Setting.Controller;

import GUI.Setting.Component.ProfilePicture;

import java.io.Serializable;
import java.util.HashMap;

public class UserProfile implements Serializable {
    private ProfilePicture profilePicture;
    private String name;
    private String profileName;
    public static HashMap<String,ProfilePicture> profilePictureList;

    public UserProfile(){
        this("Klong","Klong ha");
    }

    public UserProfile(String name,String profileName){
        this.name = name;
        this.profileName = profileName;
        this.profilePicture = profilePictureList.get(profileName);
    }

    public static void loadProfile(){
        profilePictureList = new HashMap<String,ProfilePicture>();
        profilePictureList.put("Klong ha",new ProfilePicture("Klong ha","Klong Eng Ha","assets/testLobby/Cat3.jpg"));
        profilePictureList.put("Clown",new ProfilePicture("Clown","Heavy is teammate","assets/testLobby/clown.png"));
        profilePictureList.put("Pleng's cat",new ProfilePicture("Pleng's cat","I don't know, this is not my cat!","assets/testLobby/pleng_cat.png"));
        profilePictureList.put("Karn Bob",new ProfilePicture("Karn Bob","Is that him cutting a new hair?","assets/testLobby/pupe_karn_1.png"));
    }

    public ProfilePicture getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(ProfilePicture profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }
}
