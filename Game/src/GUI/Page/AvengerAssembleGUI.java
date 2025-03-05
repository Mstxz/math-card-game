package GUI.Page;

import java.awt.*;
import java.io.FileNotFoundException;
import javax.swing.*;

import GUI.Router;
import GUI.Component.HandDeck;
import Gameplay.Card;
import Gameplay.Deck;
import Gameplay.Numbers.Constant;
import Gameplay.Player;
import utils.SharedResource;

/**
 * <h1>Wanna create a doc??</h1>
 * <img src="https://iopwiki.com/images/8/8c/GFL2_Centaureissi_Story_7.png">
 */
public class AvengerAssembleGUI extends Page{
	private	HandDeck	OpponentPanel;
	private JPanel		MiddlePanel;
	private	HandDeck	UserPanel;
	private JPanel		OpponentInfo;
	private JPanel		OpponentStatus;
	private JPanel		OpponentMainPanel;
	private JPanel		PlayerMainPanel;
	private JLabel		OpponentName;
	private JLabel		OpponentProfile;
	private JLabel		OpponentMana;
	private JPanel		PlayerInfo;
	private JPanel		PlayerStatus;
	private JLabel		PlayerName;
	private JLabel		PlayerProfile;
	private JLabel		PlayerMana;
	private Player		player;
	private Player		enemy;

	public AvengerAssembleGUI()
	{
		super();
		this.getMainPanel().setBackground(SharedResource.SIAMESE_BRIGHT);

		player = new Player("Arktik");
		player.setHp(new Constant(100));
		try {
			player.setDeck(Deck.LoadDeck("a"));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		player.getDeck().shuffle();
		UserPanel = new HandDeck(player, false);
		OpponentPanel = new HandDeck(player, true);
		OpponentMainPanel = new JPanel();
		PlayerMainPanel = new JPanel();
		UserPanel = new HandDeck(player, false);
		OpponentInfo = new JPanel();
		OpponentStatus = new JPanel();
		PlayerInfo = new JPanel();
		PlayerStatus = new JPanel();
		MiddlePanel = new JPanel();

		OpponentName = new JLabel("OPPONENT's NAME");
		OpponentProfile = new JLabel("PFP");
		OpponentMana = new JLabel("MANA: 66 / 100");
		PlayerName = new JLabel("PLAYER's NAME");
		PlayerProfile = new JLabel("PFP");
		PlayerMana = new JLabel("MANA: 12 / 100");

		MiddlePanel.setLayout(new GridLayout(2,2));

		OpponentMainPanel.setLayout(new BorderLayout());
		OpponentInfo.setLayout(new BorderLayout());
		OpponentStatus.setLayout(new BorderLayout());

		PlayerMainPanel.setLayout(new BorderLayout());
		PlayerInfo.setLayout(new BorderLayout());
		PlayerStatus.setLayout(new BorderLayout());

		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(OpponentMainPanel, BorderLayout.NORTH);
		mainPanel.add(PlayerMainPanel, BorderLayout.SOUTH);
		
		OpponentMainPanel.add(OpponentPanel, BorderLayout.CENTER);
		OpponentMainPanel.add(OpponentInfo, BorderLayout.WEST);
		OpponentMainPanel.add(OpponentStatus, BorderLayout.EAST);
		OpponentStatus.add(OpponentMana, BorderLayout.NORTH);
		OpponentInfo.add(OpponentName, BorderLayout.NORTH);
		OpponentInfo.add(OpponentProfile, BorderLayout.CENTER);

		PlayerMainPanel.add(UserPanel, BorderLayout.CENTER);
		PlayerMainPanel.add(PlayerInfo, BorderLayout.EAST);
		PlayerMainPanel.add(PlayerStatus, BorderLayout.WEST);
		PlayerStatus.add(PlayerMana, BorderLayout.SOUTH);
		PlayerInfo.add(PlayerName, BorderLayout.NORTH);
		PlayerInfo.add(PlayerProfile, BorderLayout.CENTER);
		this.initCard();

		OpponentMainPanel.setBackground(SharedResource.SIAMESE_BRIGHT);
		OpponentInfo.setBackground(SharedResource.SIAMESE_BRIGHT);
		OpponentStatus.setBackground(SharedResource.SIAMESE_BRIGHT);
		OpponentPanel.setBackground(SharedResource.SIAMESE_BRIGHT);

		PlayerMainPanel.setBackground(SharedResource.SIAMESE_BRIGHT);
		PlayerInfo.setBackground(SharedResource.SIAMESE_BRIGHT);
		PlayerStatus.setBackground(SharedResource.SIAMESE_BRIGHT);
		UserPanel.setBackground(SharedResource.SIAMESE_BRIGHT);
		//Frame.setSize(1920, 1080);
		mainPanel.setVisible(true);
	}
	
	public void	initCard()
	{
		for (int i = 0; i < 15; i++) {
			player.draw();
		}
		OpponentPanel.RenderHand();
		UserPanel.RenderHand();
	}

	public static void main(String[] args) {
		AvengerAssembleGUI game =  new AvengerAssembleGUI();
		// game.Frame.setBackground(new Color(0x005F3F));
		game.initCard();
	}
}
