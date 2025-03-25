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
import GUI.Router;
import Gameplay.*;
import Gameplay.Numbers.Constant;
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

	private ArrayList<String> playlist = new ArrayList<>(Arrays.asList(
			"Game/src/assets/Audio/BGM/Lobby_BGM_2.wav",
			"Game/src/assets/Audio/BGM/Gameplay_BGM_LoFiVersion.wav"
	));

	private Random rand = new Random();

	public AvengerAssembleGUI() {
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
		setPlayerTurn(game.getPlayerOrder() == 0);
		onCardPlayed();
		game.start();
		BGMPlayer.stopBackgroundMusic();
		int randomIndex = rand.nextInt(playlist.size());
		BGMPlayer.playBackgroundMusic(playlist.get(randomIndex), -20.0f);
	}

	public void updatePlayerHUD(){
		playerInfo.updateInfo();
		enemyInfo.updateInfo();
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
		//System.out.println("Called : "+isPlayerTurn);
		return isPlayerTurn;
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
					cardPlayed.action(game.getPlayer(), getActiveEnemy());
					onCardPlayed();
				}

				if (game.isGameEnded()){
					onGameEnded(Player.checkWin(game.getTurnOrder()));
				}
			}
		}
	}

	public void checkGameEnd(){
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

	@Override
	public void onCardPlayed() {
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
		SFXPlayer.playSound("Game/src/assets/Audio/Test2.wav", -10.0f);
		if (winner == game.getPlayer()){
			showOverlay(new ResultShow("Victory"),0,0, mainPanel.getWidth(), mainPanel.getHeight());
			setBackdropDim(true);
			//endTurnButton.removeActionListener(this);
		}
		else if(winner != null){
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


//public class AvengerAssembleGUI extends Page implements ActionListener,GameObserver {
//	private	HandDeck		OpponentPanel;
//	private	HandDeck		UserPanel;
//
//	private JPanel			MiddlePanel;
//	private JPanel			OpponentInfo;
//	private JPanel			OpponentStatus;
//	private JPanel			OpponentMainPanel;
//	private JPanel			PlayerMainPanel;
//	private JPanel			PlayerInfo;
//	private JPanel			PlayerStatus;
//	private JPanel			handPanel;
//	private Player			player;
//	private Player			enemy;
//	private PlayerProfile 	playerProfile;
//	private PlayerInfo 		playerInfo;
//	private PlayerProfile	enemyProfile;
//	private PlayerInfo 		enemyInfo;
//	private ArrayList<Card> cardPlayed = new ArrayList<Card>();
//	private JButton 		endTurnButton;
//	private boolean 		isPlayerTurn;
//	private SelectOpponent 	selectOpponent;
//	private GameForGUI 		game;
//	public boolean	isBlocked;
//	public AvengerAssembleGUI() {
//		super();
//		this.getMainPanel().setBackground(SharedResource.SIAMESE_BRIGHT);
//		this.isBlocked = false;
//
//		player = new Player("Soda Mun Za", "assets/ProfileCat1.jpg");
//		player.setHp(new Constant(100));
//		enemy = new Bot();
//		enemy.setHp(new Constant(100));
//
//		//selectOpponent = new SelectOpponent(player,enemy);
//
//		UserPanel = new HandDeck(this, player, false);
//		OpponentPanel = new HandDeck(this, enemy, true);
//		OpponentMainPanel = new JPanel();
//		handPanel = new JPanel();
//		PlayerMainPanel = new JPanel();
//		OpponentInfo = new JPanel();
//		OpponentStatus = new JPanel();
//		PlayerInfo = new JPanel();
//		PlayerStatus = new JPanel();
//		MiddlePanel = new JPanel();
//
//		playerProfile = new PlayerProfile(player.getName(), player.getProfilePicture());
//		playerInfo = new PlayerInfo(player,true);
//
//		enemyProfile = new PlayerProfile(enemy.getName(), enemy.getProfilePicture());
//		enemyInfo = new PlayerInfo(enemy,false);
//
//		MiddlePanel.setLayout(new GridLayout(2, 2));
//
//		OpponentMainPanel.setLayout(new BorderLayout());
//		OpponentInfo.setLayout(new BorderLayout());
//		OpponentStatus.setLayout(new BorderLayout());
//
//		PlayerMainPanel.setLayout(new BorderLayout());
//		PlayerInfo.setLayout(new BorderLayout());
//		PlayerStatus.setLayout(new BorderLayout());
//		handPanel.setLayout(new BorderLayout());
//
//		mainPanel.setLayout(new BorderLayout(10, 10));
//		mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
//		mainPanel.add(OpponentMainPanel, BorderLayout.NORTH);
//		mainPanel.add(PlayerMainPanel, BorderLayout.SOUTH);
//
//		OpponentMainPanel.add(OpponentPanel, BorderLayout.CENTER);
//		OpponentMainPanel.add(OpponentInfo, BorderLayout.WEST);
//		OpponentMainPanel.add(OpponentStatus, BorderLayout.EAST);
//		OpponentStatus.add(enemyInfo, BorderLayout.NORTH);
//		OpponentInfo.add(enemyProfile, BorderLayout.CENTER);
//
//		PlayerMainPanel.add(handPanel, BorderLayout.CENTER);
//		PlayerMainPanel.add(PlayerInfo, BorderLayout.EAST);
//		PlayerMainPanel.add(PlayerStatus, BorderLayout.WEST);
//		handPanel.add(UserPanel, BorderLayout.SOUTH);
//
//		PlayerStatus.add(playerInfo, BorderLayout.SOUTH);
//		PlayerInfo.add(playerProfile);
//		//this.initCard();
//
//		OpponentMainPanel.setBackground(SharedResource.SIAMESE_BRIGHT);
//		OpponentInfo.setBackground(SharedResource.SIAMESE_BRIGHT);
//		OpponentStatus.setBackground(SharedResource.SIAMESE_BRIGHT);
//		OpponentPanel.setBackground(SharedResource.SIAMESE_BRIGHT);
//
//		PlayerMainPanel.setBackground(SharedResource.SIAMESE_BRIGHT);
//		PlayerInfo.setBackground(SharedResource.SIAMESE_BRIGHT);
//		PlayerStatus.setBackground(SharedResource.SIAMESE_BRIGHT);
//		handPanel.setBackground(SharedResource.SIAMESE_BRIGHT);
//		UserPanel.setBackground(SharedResource.SIAMESE_BRIGHT);
//		//Frame.setSize(1920, 1080);
//
//		endTurnButton = new JButton("End Turn");
//		endTurnButton.setFont(SharedResource.getCustomSizeFont(36));
//
//		endTurnButton.setBorder(BorderFactory.createLineBorder(new Color(163, 190, 208, 255), 8));
//		endTurnButton.setBackground(new Color(216, 220, 223, 255));
//		endTurnButton.setForeground(new Color(102, 142, 169, 0));
//		//endTurnButton.set
//		endTurnButton.setPreferredSize(new Dimension(170, 170));
//		JPanel p = new JPanel();
//		p.setLayout(new GridLayout(1, 6));
//		p.setBackground(SharedResource.SIAMESE_BRIGHT);
//		for (int i = 0; i < 5; i++) {
//			JPanel tmp = new JPanel();
//			tmp.setBackground(SharedResource.SIAMESE_BRIGHT);
//			p.add(tmp);
//		}
//		JPanel p2 = new JPanel();
//		p2.setBackground(SharedResource.SIAMESE_BRIGHT);
//		p2.add(endTurnButton);
//		p2.setLayout(new FlowLayout());
//		p.add(p2);
//		mainPanel.add(p);
//
//		mainPanel.setVisible(true);
//
//		endTurnButton.addActionListener(this);
//
//		this.gameLogic();
//		BGMPlayer.stopBackgroundMusic();
//		BGMPlayer.playBackgroundMusic("Game/src/assets/Audio/BGM/Gameplay_BGM_LoFiVersion.wav", -20.0f);
//	}
//
//	public void updatePlayerHUD(){
//		playerInfo.updateInfo();
//		enemyInfo.updateInfo();
//	}
//
//	public void playerRenderHand(){
//		UserPanel.RenderHand();
//	}
//
//	public void enemyRenderHand(){
//		OpponentPanel.RenderHand();
//	}
//
//	public void	initCard()
//	{
//
//		OpponentPanel.RenderHand();
//
//		UserPanel.RenderHand();
//	}
//	public boolean isPlayerTurn(){
//		//System.out.println("Called : "+isPlayerTurn);
//		return isPlayerTurn;
//	}
//
//	public void setPlayerTurn(boolean playerTurn) {
//		isPlayerTurn = playerTurn;
//		SFXPlayer.playSound("Game/src/assets/Audio/SFX/PlayerTurn.wav", -10.0f);
//		if (isPlayerTurn){
//			endTurnButton.setText("<html><body>End<br>Turn</body></html>");
//			endTurnButton.setEnabled(true);
//		}
//		else{
//			endTurnButton.setBackground(new Color(216, 220, 223, 255));
//			endTurnButton.setForeground(new Color(102, 142, 169, 0));
//			endTurnButton.setText("<html><body>Enemy's<br>Turn</body></html>");
//			endTurnButton.setEnabled(false);
//		}
//
//	}
//
//	public ArrayList<Card> getCardPlayed() {
//		return cardPlayed;
//	}
//
//	public void addCardPlayed(Card cardPlayed) {
//		this.cardPlayed.add(cardPlayed);
//	}
//
//	public void gameLogic(){
//		game = new GameForGUI(player,enemy,this);
//		game.setGame();
//		setPlayerTurn(game.getSelfNumber() == 0);
//		this.UserPanel.updatePlayable(enemy);
//		this.updatePlayerHUD();
//		this.initCard();
//		game.start();
//
//	}
//	public void result(Player winner){
//		BGMPlayer.stopBackgroundMusic();
//		SFXPlayer.playSound("Game/src/assets/Audio/Test2.wav", -10.0f);
//		if (winner == player){
//			showOverlay(new ResultShow("Victory"),0,0, mainPanel.getWidth(), mainPanel.getHeight());
//			setBackdropDim(true);
//			//endTurnButton.removeActionListener(this);
//		}
//		else if(winner == enemy){
//			showOverlay(new ResultShow("Defeat"),0,0, mainPanel.getWidth(), mainPanel.getHeight());
//			setBackdropDim(true);
//		}
//		else{
//			showOverlay(new ResultShow("Draw"),0,0, mainPanel.getWidth(), mainPanel.getHeight());
//			setBackdropDim(true);
//		}
//
//	}
//
//	public SelectOpponent getSelectOpponent() {
//		return selectOpponent;
//	}
//
//	public void setSelectOpponent(SelectOpponent selectOpponent) {
//		this.selectOpponent = selectOpponent;
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		if (e.getSource()==endTurnButton){
//			SFXPlayer.playSound("Game/src/assets/Audio/SFX/Meow.wav", 0f);
//			setPlayerTurn(false);
//			game.resumeGame();
//		}
//		if(e.getSource() instanceof CardPlayable && isPlayerTurn){
//			SFXPlayer.playSound("Game/src/assets/Audio/SFX/Card_Play_Click.wav", 0f);
//			CardPlayable c = (CardPlayable) e.getSource();
//			if(c.isPlayable()) {
//				int index = player.getHand().indexOf(c.getCard());
//				Card cardPlayed = player.getHand().remove(index);
//				//gui.addCardPlayed(cardPlayed);
//				if (cardPlayed.getType() == CardType.GREEN){
//					showOverlay(new SelectOpponent(player,enemy,cardPlayed,this),(Router.getMainFrame().getWidth() - 850)/2, (Router.getMainFrame().getHeight() - 400)/2, 850, 400);
//				}
//				else{
//					cardPlayed.action(player, getEnemy());
//					player.getDeck().addDispose(cardPlayed);
//					getUserPanel().updatePlayable(enemy);
//					updatePlayerHUD();
//					initCard();
//				}
//
//				if (Player.checkWin(player,enemy) != null){
//					result(Player.checkWin(player,enemy));
//				}
//			}
//		}
//	}
//
//	public Player getPlayer() {
//		return player;
//	}
//
//	public Player getEnemy() {
//		return enemy;
//	}
//
//	public HandDeck getUserPanel() {
//		return UserPanel;
//	}
//
//	public void setUserPanel(HandDeck userPanel) {
//		UserPanel = userPanel;
//	}
//
//	@Override
//	public void onCardPlayed() {
//		onHandChanged();
//		playerInfo.updateInfo();
//		enemyInfo.updateInfo();
//	}
//
//	@Override
//	public void onHandChanged() {
//		OpponentPanel.RenderHand();
//		UserPanel.RenderHand();
//	}
//
//	@Override
//	public void onPlayerQuit(Player playerQuit) {
//
//	}
//
//	@Override
//	public void onGameEnded(Player winner) {
//		BGMPlayer.stopBackgroundMusic();
//		SFXPlayer.playSound("Game/src/assets/Audio/Test2.wav", -10.0f);
//		if (winner == player){
//			showOverlay(new ResultShow("Victory"),0,0, mainPanel.getWidth(), mainPanel.getHeight());
//			setBackdropDim(true);
//			//endTurnButton.removeActionListener(this);
//		}
//		else if(winner != null){
//			showOverlay(new ResultShow("Defeat"),0,0, mainPanel.getWidth(), mainPanel.getHeight());
//			setBackdropDim(true);
//		}
//		else{
//			showOverlay(new ResultShow("Draw"),0,0, mainPanel.getWidth(), mainPanel.getHeight());
//			setBackdropDim(true);
//		}
//	}
//}
