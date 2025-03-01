

public class LobbyProfile{
    private String PFP, plrName, status;

    public LobbyProfile(){
        this.PFP = "Dragon of Dojima";
        this.plrName = "Kiryu Kazuma";
        this.status = "Not Ready";
    }
    
    public LobbyProfile(String PFP, String plrName, String status){
        this.PFP = PFP;
        this.plrName = plrName;
        this.status = status;
    }

    public String getPFP(){
        return this.PFP;
    }

    public String getName(){
        return this.plrName;
    }

    public String getStatus(){
        return this.status;
    }

}

