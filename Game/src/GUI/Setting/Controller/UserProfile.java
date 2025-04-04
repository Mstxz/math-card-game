package GUI.Setting.Controller;

import GUI.Setting.Component.ProfilePicture;
import GUI.Setting.UserPreference;

import java.io.Serializable;
import java.util.HashMap;

public class UserProfile implements Serializable {
    private ProfilePicture profilePicture;
    private String name;
    private String profileName;
    public static HashMap<String,ProfilePicture> profilePictureList;
    static {
        profilePictureList = new HashMap<String,ProfilePicture>();
        profilePictureList.put("Pupr",new ProfilePicture("Pupr","Bob hair cat."));
        profilePictureList.put("Arsr",new ProfilePicture("Arsr","Glasses cat."));
        profilePictureList.put("Angy",new ProfilePicture("Angy","Angry cat."));
        profilePictureList.put("Who",new ProfilePicture("Who","Who tf is this?"));
        profilePictureList.put("Heyla",new ProfilePicture("Heyla","Often mistaken as Omm Thuk's son."));
        profilePictureList.put("Omm Thuk",new ProfilePicture("Omm Thuk","Omm Thuk."));
        profilePictureList.put("CC",new ProfilePicture("CC","Eat crab too much, turn to pink."));
        profilePictureList.put("Land",new ProfilePicture("Land","Best friend of Moon."));
        profilePictureList.put("Moon",new ProfilePicture("Moon","Best friend of Land."));
        profilePictureList.put("Laser",new ProfilePicture("Laser","Did you guy forget me?? (Best friend of Moon and Land)"));
        profilePictureList.put("Mai Kai Ya Sit",new ProfilePicture("Mai Kai Ya Sit","Maybe he has some magic."));
        profilePictureList.put("More",new ProfilePicture("More","Peeranat Madlek"));
        profilePictureList.put("Ngian",new ProfilePicture("Ngian","Move over from far sea."));
        profilePictureList.put("Chen",new ProfilePicture("Chen","Big brother of Ngian."));
        profilePictureList.put("Sam Nak Ngan",new ProfilePicture("Sam Nak Ngan","People call him \"Sam Nak Ngan\" cause he lives at Sam Nak Ngan.(Office in Thai)"));
        profilePictureList.put("Sheen",new ProfilePicture("Sheen","Did someone use grammar of Meow wrong!?"));
        profilePictureList.put("Mystyr",new ProfilePicture("Mystyr","Just a normal DJ"));
        profilePictureList.put("LifeCoach",new ProfilePicture("LifeCoach","Anyone knows  who is him but L I F E C O A C H"));
        profilePictureList.put("Toom",new ProfilePicture("Toom","Who let's the cat plays with a wire!!"));
    }

    public UserProfile(){
        this("Pupr","Pupr");
    }

    public UserProfile(String name,String profileName){
        this.name = name;
        this.profileName = profileName;
        this.profilePicture = profilePictureList.get(profileName);
    }

    public static void loadProfile(){
        int a = 0;
        if (UserPreference.getInstance().getAchievementProfile().isWinPupr){
            //System.out.println("Huh");
            a+=1;
            profilePictureList.put("Huh Bob",new ProfilePicture("Huh Bob","What this you just say huh?"));
        }
        if (UserPreference.getInstance().getAchievementProfile().isWinArsr){
            a+=1;
            profilePictureList.put("Romance Cat",new ProfilePicture("Romance Cat","Nah I just tell you, he loves Pupr &lt;3"));
        }
        if (UserPreference.getInstance().getAchievementProfile().isWinOmmThuk){
            a+=1;
            profilePictureList.put("Morning Sunday",new ProfilePicture("Morning Sunday","Prof. Omm Thuk is just a cat in the eyes of his owner."));
        }
        if (UserPreference.getInstance().getAchievementProfile().isWinMystyr){
            a+=1;
            profilePictureList.put("Night Light",new ProfilePicture("Night Light","Listen to music and enjoy the long night."));
        }
        if (UserPreference.getInstance().getAchievementProfile().isWinWho){
            a+=1;
            profilePictureList.put("43 Park Popular",new ProfilePicture("43 Park Popular","\"Look! A Little Kid, I'll show you how to be loved by human\""));
        }
        if (a == 5){
            profilePictureList.put("Klong Ha", new ProfilePicture("Klong Ha","How did you see Klong ha?"));
        }
        //profilePictureList.put("Klong ha",new ProfilePicture("Klong ha","Klong Eng Ha", "assets/Profile/Klong ha.jpg"));
        //profilePictureList.put("Clown",new ProfilePicture("Clown","Heavy is teammate", "assets/Profile/Clown.png"));
        //profilePictureList.put("Pleng's cat",new ProfilePicture("Pleng's cat","I don't know, this is not my cat!", "assets/Profile/Pleng's cat.png"));
        //profilePictureList.put("Karn Bob",new ProfilePicture("Karn Bob","Is that him cutting a new hair?", "assets/Profile/Karn Bob.png"));
    }


    public ProfilePicture getProfilePicture() {
        return profilePicture;
    }

    public String getProfilePictureURL(){
        return profilePicture.getProfileURL();
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
