package GUI.Page;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.*;

import GUI.Router;
import Gameplay.Card;
import Gameplay.Deck;
import Gameplay.Numbers.Constant;
import Gameplay.Player;

public class AvengerAssembleGUI extends Page implements ActionListener{
	private static final Dimension	OpponentSize = new Dimension(165, 225);
	private static final Dimension	HandSize = new Dimension(247, 337);
	private JPanel	OpponentPanel;
	private JPanel	MiddlePanel;
	private JPanel	UserPanel;
	private JPanel	OpponentInfo;
	private JPanel	OpponentStatus;
	private JPanel	OpponentMainPanel;
	private JPanel	PlayerMainPanel;
	private ArrayList<JButton>	OpponentHand;
	private ArrayList<JButton>	UserHand;
	private JLabel	OpponentName;
	private JLabel	OpponentProfile;
	private JLabel	OpponentMana;
	private JPanel	PlayerInfo;
	private JPanel	PlayerStatus;
	private JLabel	PlayerName;
	private JLabel	PlayerProfile;
	private JLabel	PlayerMana;
	private Player player;
	private Player enemy;
	public AvengerAssembleGUI()
	{
		super();
		player = new Player("Arktik");
		player.setHp(new Constant(100));
		try {
			player.setDeck(Deck.LoadDeck("a"));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		player.getDeck().shuffle();
		OpponentPanel = new JPanel();
		OpponentMainPanel = new JPanel();
		PlayerMainPanel = new JPanel();
		UserPanel = new JPanel();
		OpponentInfo = new JPanel();
		OpponentStatus = new JPanel();
		PlayerInfo = new JPanel();
		PlayerStatus = new JPanel();
		MiddlePanel = new JPanel();
		OpponentHand = new ArrayList<JButton>();
		UserHand = new ArrayList<JButton>();
		OpponentName = new JLabel("OPPONENT's NAME");
		OpponentProfile = new JLabel("PFP");
		OpponentMana = new JLabel("MANA: 66 / 100");
		PlayerName = new JLabel("PLAYER's NAME");
		PlayerProfile = new JLabel("PFP");
		PlayerMana = new JLabel("MANA: 12 / 100");

		OpponentName.setFont(new Font("Arial", Font.BOLD, 40));
		OpponentProfile.setFont(new Font("Arial", Font.BOLD, 50));
		OpponentMana.setFont(new Font("Arial", Font.BOLD, 40));

		PlayerName.setFont(new Font("Arial", Font.BOLD, 40));
		PlayerProfile.setFont(new Font("Arial", Font.BOLD, 50));
		PlayerMana.setFont(new Font("Arial", Font.BOLD, 40));

		OpponentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, -50, 0));
		OpponentPanel.setBackground(new Color(0xFF0000));

		MiddlePanel.setLayout(new GridLayout(2,2));

		UserPanel.setLayout(new FlowLayout(FlowLayout.CENTER, -50, 0));
		UserPanel.setBackground(new Color(0x00FFFF));

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

		//Frame.setSize(1920, 1080);
		mainPanel.setVisible(true);
	}

	public void updateHand() {
		UserPanel.removeAll();// Clear old cards
		//ArrayList<Integer> playable = Player.listPlayableCard(this.player,this.enemy);
		for (int i = 0; i < player.getHand().size(); i++) {
			Card card = player.getHand().get(i);
			System.out.println(card.getPicture());
			newCardBtn(UserPanel, UserHand,card.getPicture(), HandSize);
//			if (playable.contains(i)) {
//
//			}
//			else{
//				cardLabel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
//			}
		}
		UserPanel.revalidate();
		UserPanel.repaint();
	}
	
	public void	initCard()
	{
		for (int i = 0; i < 5; i++)
		// OpponentPanel.add(this.newCardBtn("assets/BackSideCard.png", OpponentSize));
			newCardBtn(OpponentPanel, OpponentHand,"assets/BackSideCard.png", OpponentSize);

//		for (int i = 0; i < 5; i++)
//		// UserPanel.add(this.newCardBtn("assets/Yellow_BetaCatNap.png", HandSize));
//			newCardBtn(UserPanel, UserHand,"assets/Yellow_BetaCatNap.png", HandSize);
		for (int i = 0; i < 5; i++) {
			player.draw();
			newCardBtn(UserPanel, UserHand,"assets/Yellow_BetaCatNap.png", HandSize);
		}
		updateHand();
	}
	
	public void newCardBtn(JPanel panel, ArrayList<JButton> hand, String path, Dimension dimension)
	{
		JButton	res;
		Image	img;
		
		res = new JButton();
		res.setSize(dimension);
		res.setPreferredSize(dimension);
		try
		{
			img = new ImageIcon(Router.class.getResource(path)).getImage().getScaledInstance(res.getWidth(), res.getHeight(), Image.SCALE_DEFAULT);
			res.setIcon(new ImageIcon(img));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return ;
		}
		res.addActionListener(this);
		hand.add(res);
		panel.add(res);
		mainPanel.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			System.out.println("Click");
		}
	}

	public static void main(String[] args) {
		AvengerAssembleGUI game =  new AvengerAssembleGUI();
		// game.Frame.setBackground(new Color(0x005F3F));
		game.initCard();
	}
}
