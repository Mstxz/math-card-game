package GUI.Page;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import AudioPlayer.*;

import GUI.Component.*;
import GUI.Component.Game;
import Gameplay.CardAction.*;
import Gameplay.*;
import utils.SharedResource;

/**
 * <h1>Wanna create a doc??</h1>
 * <img src="https://iopwiki.com/images/8/8c/GFL2_Centaureissi_Story_7.png">
 */

public class AvengerAssembleGUI extends Page implements ActionListener,GameObserver {
	private	HandDeck		OpponentPanel;
	private	HandDeck		UserPanel;

	private JPanel			MiddlePanel;
	private JPanel			OpponentInfo;
	private JPanel			OpponentStatus;
	private JPanel			OpponentMainPanel;
	private JPanel			PlayerMainPanel;
	private JPanel			PlayerInfo;
	private JPanel			PlayerStatus;
	private JPanel			handPanel;
	private ArrayList<Player> opponents;
	private int activeOpponent;
	private PlayerProfile 	playerProfile;
	private PlayerInfo 		playerInfo;
	private PlayerProfile	enemyProfile;
	private PlayerInfo 		enemyInfo;
	private ArrayList<Card> cardPlayed = new ArrayList<Card>();
	private JButton 		endTurnButton;
	private boolean 		isPlayerTurn;
	private SelectOpponent 	selectOpponent;
	private Game game;
	public boolean	isBlocked;

	private Random rand = new Random();

	public AvengerAssembleGUI(Game game) {
		super();
		this.getMainPanel().setBackground(SharedResource.SIAMESE_BRIGHT);
		this.isBlocked = false;
		this.game = game;
		this.game.setObserver(this);
		opponents = new ArrayList<>();
		for (Player entity:game.getTurnOrder()){
			if (entity != game.getPlayer()){
				opponents.add(entity);
			}
		}
		activeOpponent = 0;

		UserPanel = new HandDeck(this, game.getPlayer(), false);
		OpponentPanel = new HandDeck(this, opponents.get(activeOpponent), true);
		OpponentMainPanel = new JPanel();
		handPanel = new JPanel();
		PlayerMainPanel = new JPanel();
		OpponentInfo = new JPanel();
		OpponentStatus = new JPanel();
		PlayerInfo = new JPanel();
		PlayerStatus = new JPanel();
		MiddlePanel = new JPanel();

		playerProfile = new PlayerProfile(game.getPlayer());
		playerInfo = new PlayerInfo(game.getPlayer(),true);

		enemyProfile = new PlayerProfile(opponents.get(activeOpponent));
		enemyInfo = new PlayerInfo(opponents.get(activeOpponent),false);

		MiddlePanel.setLayout(new GridLayout(2, 2));

		OpponentMainPanel.setLayout(new BorderLayout());
		OpponentInfo.setLayout(new BorderLayout());
		OpponentStatus.setLayout(new BorderLayout());

		PlayerMainPanel.setLayout(new BorderLayout());
		PlayerInfo.setLayout(new BorderLayout());
		PlayerStatus.setLayout(new BorderLayout());
		handPanel.setLayout(new BorderLayout());

		mainPanel.setLayout(new BorderLayout(10, 10));
		mainPanel.setBorder(new EmptyBorder(20, 20, 40, 40));
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
		handPanel.add(UserPanel, BorderLayout.SOUTH);

		PlayerStatus.add(playerInfo, BorderLayout.SOUTH);
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
		endTurnButton.setFont(SharedResource.getCustomSizeFont(36));

		endTurnButton.setBorder(BorderFactory.createLineBorder(new Color(163, 190, 208, 255), 8));
		endTurnButton.setBackground(new Color(216, 220, 223, 255));
		endTurnButton.setForeground(new Color(102, 142, 169, 0));
		//endTurnButton.set
		endTurnButton.setPreferredSize(new Dimension(170, 170));
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1, 6));
		p.setBackground(SharedResource.SIAMESE_BRIGHT);
		for (int i = 0; i < 5; i++) {
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

		onHandChanged();
		updatePlayerHUD();
		if (game instanceof GameForGUI){
			game.start();
		}
		else{
			game.notifyGameStart();
		}
		BGMPlayer.stopBackgroundMusic();

		BGMPlayer.playBackgroundMusic("Game/src/assets/Audio/BGM/Gameplay_BGM_Mixed.wav");
	}

	public void updatePlayerHUD(){
		playerInfo.updateInfo();
		enemyInfo.updateInfo();
	}

	public void setPlayerTurn(boolean playerTurn) {
		isPlayerTurn = playerTurn;
		SFXPlayer.playSound("Game/src/assets/Audio/SFX/PlayerTurn.wav", -10.0f);
		if (isPlayerTurn){
			endTurnButton.setText("<html><body>End<br>Turn</body></html>");
			endTurnButton.setEnabled(true);
		}
		else{
			endTurnButton.setBackground(new Color(216, 220, 223, 255));
			endTurnButton.setForeground(new Color(102, 142, 169, 0));
			endTurnButton.setText("<html><body>Enemy's<br>Turn</body></html>");
			endTurnButton.setEnabled(false);
		}

	}


	public ArrayList<Card> getCardPlayed() {
		return cardPlayed;
	}

	public void addCardPlayed(Card cardPlayed) {
		this.cardPlayed.add(cardPlayed);
	}

	public SelectOpponent getSelectOpponent() {
		return selectOpponent;
	}

	public void setSelectOpponent(SelectOpponent selectOpponent) {
		this.selectOpponent = selectOpponent;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==endTurnButton){
			SFXPlayer.playSound("Game/src/assets/Audio/SFX/Meow.wav", 0f);
			setPlayerTurn(false);
			game.notifyEndTurn();
		}
		if(e.getSource() instanceof CardPlayable && isPlayerTurn){
			SFXPlayer.playSound("Game/src/assets/Audio/SFX/Card_Play_Click.wav", 0f);
			CardPlayable c = (CardPlayable) e.getSource();
			if(c.isPlayable()) {
				int index = game.getPlayer().getHand().indexOf(c.getCard());
				Card cardPlayed = game.getPlayer().getHand().remove(index);

				//gui.addCardPlayed(cardPlayed);
				if (cardPlayed.getType() == CardType.GREEN){
					showOverlay(new SelectOpponent(game.getPlayer(),opponents,cardPlayed,this),OverlayPlacement.CENTER);
				}
				else{
					playCard(cardPlayed,getActiveEnemy());
				}
			}
		}
	}

	public void playCard(Card cardPlayed,Player receiver){
		game.playerPlay(cardPlayed,receiver);
		if (game.isGameEnded()){
			onGameEnded(Player.checkWin(game.getTurnOrder()));
		}
	}

	public Player getPlayer() {
		return game.getPlayer();
	}

	public Player getActiveEnemy(){
		return opponents.get(activeOpponent);
	}


	public HandDeck getUserPanel() {
		return UserPanel;
	}

	public void setUserPanel(HandDeck userPanel) {
		UserPanel = userPanel;
	}

	public void updatePlayerHand(){
		UserPanel.RenderHand();
	}

	public void updateEnemyHand(){
		OpponentPanel.RenderHand();
	}

	@Override
	public void onGameStart(int startTurn) {
		setPlayerTurn(game.getPlayerOrder() == startTurn);
	}

	@Override
	public void onCardPlayed(ArrayList<CardAction> actionsTaken) {
//		for (CardAction action:actionsTaken){
//			if (action.getTarget() == game.getPlayer()){
//				switch (action.getType()){
//					case DRAW -> {
//						updatePlayerHand();
//					}
//					case DISCARD -> {
//						updatePlayerHand();
//					}
//				}
//			}
//		}
		onHandChanged();
		playerInfo.updateInfo();
		enemyInfo.updateInfo();
	}

	@Override
	public void onHandChanged() {
		getUserPanel().updatePlayable(getActiveEnemy());
		OpponentPanel.RenderHand();
		UserPanel.RenderHand();
	}

	@Override
	public void onPlayerQuit(Player playerQuit) {

	}

	@Override
	public void onGameEnded(Player winner) {
		BGMPlayer.stopBackgroundMusic();
		if (winner == game.getPlayer()){
			SFXPlayer.playSound("Game/src/assets/Audio/Test2.wav", -10.0f);
			showOverlay(new ResultShow("Victory"),0,0, mainPanel.getWidth(), mainPanel.getHeight());
			setBackdropDim(true);
			//endTurnButton.removeActionListener(this);
		}
		else if(winner != null){
			SFXPlayer.playSound("Game/src/assets/Audio/SFX/Game_Lose_Awakened.wav", -10.0f);
			showOverlay(new ResultShow("Defeat"),0,0, mainPanel.getWidth(), mainPanel.getHeight());
			setBackdropDim(true);
		}
		else{
			showOverlay(new ResultShow("Draw"),0,0, mainPanel.getWidth(), mainPanel.getHeight());
			setBackdropDim(true);
		}
	}

	@Override
	public void onTurnArrive() {
		setPlayerTurn(true);
	}

	@Override
	public void onTurnEnded() {
		getUserPanel().updatePlayable(getActiveEnemy());
		playerInfo.updateInfo();
		enemyInfo.updateInfo();
	}
}
