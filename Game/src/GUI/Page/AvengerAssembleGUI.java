package GUI.Page;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import GUI.Component.PlayerInfo;
import GUI.Component.PlayerProfile;
import GUI.Component.HandDeck;
import Gameplay.Bot;
import Gameplay.Deck;
import Gameplay.GameForGUI;
import Gameplay.Numbers.Constant;
import Gameplay.Player;
import utils.SharedResource;

/**
 * <h1>Wanna create a doc??</h1>
 * <img src="https://iopwiki.com/images/8/8c/GFL2_Centaureissi_Story_7.png">
 */
public class AvengerAssembleGUI extends Page implements ActionListener {
	private	HandDeck	OpponentPanel;
	private JPanel		MiddlePanel;
	private	HandDeck	UserPanel;
	private JPanel		OpponentInfo;
	private JPanel		OpponentStatus;
	private JPanel		OpponentMainPanel;
	private JPanel		PlayerMainPanel;
	private JPanel		PlayerInfo;
	private JPanel		PlayerStatus;
	private JPanel		handPanel;
	private Player		player;
	private Player		enemy;
	private PlayerProfile playerProfile;
	private PlayerInfo playerInfo;
	private PlayerProfile enemyProfile;
	private PlayerInfo enemyInfo;

	private JButton endTurnButton;
	private boolean isPlayerTurn;
	private GameForGUI game;
	public AvengerAssembleGUI()
	{
		super();
		this.getMainPanel().setBackground(SharedResource.SIAMESE_BRIGHT);

		player = new Player("Buk George","assets/ProfileCat1.jpg");
		player.setHp(new Constant(100));
		enemy = new Bot();
		enemy.setHp(new Constant(100));

		UserPanel = new HandDeck(player, false);
		OpponentPanel = new HandDeck(enemy, true);
		OpponentMainPanel = new JPanel();
		handPanel = new JPanel();
		PlayerMainPanel = new JPanel();
		OpponentInfo = new JPanel();
		OpponentStatus = new JPanel();
		PlayerInfo = new JPanel();
		PlayerStatus = new JPanel();
		MiddlePanel = new JPanel();

		playerProfile = new PlayerProfile(player.getName(),player.getProfilePicture());
		playerInfo = new PlayerInfo(player.getHp(),player.getMana(),"");
		enemyProfile = new PlayerProfile(enemy.getName(),enemy.getProfilePicture());
		enemyInfo = new PlayerInfo(enemy.getHp(),enemy.getMana(),enemy.getName());

		MiddlePanel.setLayout(new GridLayout(2,2));

		OpponentMainPanel.setLayout(new BorderLayout());
		OpponentInfo.setLayout(new BorderLayout());
		OpponentStatus.setLayout(new BorderLayout());

		PlayerMainPanel.setLayout(new BorderLayout());
		PlayerInfo.setLayout(new BorderLayout());
		PlayerStatus.setLayout(new BorderLayout());
		handPanel.setLayout(new BorderLayout());

		mainPanel.setLayout(new BorderLayout(10,10));
		mainPanel.setBorder(new EmptyBorder(20,20,20,20));
		mainPanel.add(OpponentMainPanel, BorderLayout.NORTH);
		mainPanel.add(PlayerMainPanel, BorderLayout.SOUTH);

		OpponentMainPanel.add(OpponentPanel, BorderLayout.CENTER);
		OpponentMainPanel.add(OpponentInfo, BorderLayout.WEST);
		OpponentMainPanel.add(OpponentStatus, BorderLayout.EAST);
		OpponentStatus.add(enemyInfo, BorderLayout.NORTH);
		OpponentInfo.add(enemyProfile, BorderLayout.CENTER);

		PlayerMainPanel.add(handPanel, BorderLayout.CENTER);
		PlayerMainPanel.add(PlayerInfo, BorderLayout.EAST);
		PlayerMainPanel.add(PlayerStatus, BorderLayout.WEST);
		handPanel.add(UserPanel,BorderLayout.SOUTH);

		PlayerStatus.add(playerInfo,BorderLayout.SOUTH);
		PlayerInfo.add(playerProfile);
		//this.initCard();

		OpponentMainPanel.setBackground(SharedResource.SIAMESE_BRIGHT);
		OpponentInfo.setBackground(SharedResource.SIAMESE_BRIGHT);
		OpponentStatus.setBackground(SharedResource.SIAMESE_BRIGHT);
		OpponentPanel.setBackground(SharedResource.SIAMESE_BRIGHT);

		PlayerMainPanel.setBackground(SharedResource.SIAMESE_BRIGHT);
		PlayerInfo.setBackground(SharedResource.SIAMESE_BRIGHT);
		PlayerStatus.setBackground(SharedResource.SIAMESE_BRIGHT);
		handPanel.setBackground(SharedResource.SIAMESE_BRIGHT);
		UserPanel.setBackground(SharedResource.SIAMESE_BRIGHT);
		//Frame.setSize(1920, 1080);

		endTurnButton = new JButton("End Turn");
		endTurnButton.setPreferredSize(new Dimension(170,170));
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1,6));
		p.setBackground(SharedResource.SIAMESE_BRIGHT);
		for (int i = 0;i<5;i++){
			JPanel tmp = new JPanel();
			tmp.setBackground(SharedResource.SIAMESE_BRIGHT);
			p.add(tmp);
		}
		JPanel p2 = new JPanel();
		p2.setBackground(SharedResource.SIAMESE_BRIGHT);
		p2.add(endTurnButton);
		p2.setLayout(new FlowLayout());
		p.add(p2);
		mainPanel.add(p);

		mainPanel.setVisible(true);

		endTurnButton.addActionListener(this);

		this.gameLogic();
	}
	public void updatePlayerHUD(){
		playerInfo.setHp(player.getHp());
		playerInfo.setMana(player.getMana());
		enemyInfo.setHp(enemy.getHp());
		enemyInfo.setMana(enemy.getMana());
	}

	public void playerRenderHand(){
		UserPanel.RenderHand();
	}

	public void enemyRenderHand(){
		OpponentPanel.RenderHand();
	}

	public void	initCard()
	{

		OpponentPanel.RenderHand();

		UserPanel.RenderHand();
	}
	public boolean isPlayerTurn(){
		return isPlayerTurn;
	}

	public void setPlayerTurn(boolean playerTurn) {
		isPlayerTurn = playerTurn;
	}

	public void gameLogic(){
		GameForGUI game = new GameForGUI(player,enemy,this);
		game.setGame();
		setPlayerTurn(game.getSelfNumber() == 0);
		this.updatePlayerHUD();
		this.initCard();
		game.start();
//		int count;
//		while (!Player.checkWin(player,enemy)){
//			if (!isPlayerTurn){
//				enemy.play(enemy,player);
//				this.setPlayerTurn(true);
//			}
//		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (c && c){
			endTurnButton.setText("End Turn");
		}
		else  if (){
			endTurnButton.setText("Enemy's Turn");
			endTurnButton.set(); //TODO: find me method that can change button to unclickable
		}
		}
	}
}
