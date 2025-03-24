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
        this("Pupr","Pupr");
    }

    public UserProfile(String name,String profileName){
        this.name = name;
        this.profileName = profileName;
        this.profilePicture = profilePictureList.get(profileName);
    }

    public static void loadProfile(){
        profilePictureList = new HashMap<String,ProfilePicture>();
        profilePictureList.put("Pupr",new ProfilePicture("Pupr","Bob hair cat."));
        profilePictureList.put("Arsr",new ProfilePicture("Arsr","Glasses cat."));
        profilePictureList.put("Angy",new ProfilePicture("Angy","Angry cat."));
        profilePictureList.put("Who",new ProfilePicture("Who","Who tf is this?"));

        //profilePictureList.put("Klong ha",new ProfilePicture("Klong ha","Klong Eng Ha", "assets/Profile/Klong ha.jpg"));
        //profilePictureList.put("Clown",new ProfilePicture("Clown","Heavy is teammate", "assets/Profile/Clown.png"));
        //profilePictureList.put("Pleng's cat",new ProfilePicture("Pleng's cat","I don't know, this is not my cat!", "assets/Profile/Pleng's cat.png"));
        //profilePictureList.put("Karn Bob",new ProfilePicture("Karn Bob","Is that him cutting a new hair?", "assets/Profile/Karn Bob.png"));
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
