package GameSocket;
//pfp's lobby
public class LobbyProfile {
    private String PFP, plrName;
    private boolean isHost;

    public LobbyProfile(){
        this("","",false);
    }
    
    public LobbyProfile(String PFP, String plrName, boolean isHost){
        this.PFP = PFP;
        this.plrName = plrName;
        this.isHost = isHost;
    }

    public String getPFP(){
        return this.PFP;
    }

    public String getName(){
        return this.plrName;
    }

    public boolean isHost() {
        return isHost;
    }
}

