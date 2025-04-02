import GUI.Router;
import utils.Setup;

class Main {
    public static void main(String[] args) {
        Setup.createDefaultDeck();
        new Router();
    }
}