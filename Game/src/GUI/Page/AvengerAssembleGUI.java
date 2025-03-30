package GUI.Page;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import AudioPlayer.*;

import GUI.Component.*;
import GUI.Component.Game;
import GUI.Router;
import Gameplay.*;
import org.w3c.dom.ls.LSOutput;
import utils.SharedResource;
import utils.UIManager.RoundPanelUI;

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
	private GameMenu quitMenu;
	private TurnCount turnCount;
	private Game game;
	public boolean	isBlocked;
	private final AncestorListener listener;
	private Random rand = new Random();

	public AvengerAssembleGUI(Game game) {
		super();
		listener = new AncestorListener() {
			@Override
			public void ancestorAdded(AncestorEvent event) {
				mainPanel.requestFocus();
			}

			@Override
			public void ancestorRemoved(AncestorEvent event) {

			}

			@Override
			public void ancestorMoved(AncestorEvent event) {

			}
		};
		mainPanel.addAncestorListener(listener);
		this.getMainPanel().setBackground(SharedResource.SIAMESE_BRIGHT);
		this.isBlocked = false;
		this.game = game;
		this.game.setObserver(this);
		opponents = new ArrayList<>();
		for (Player entity:game.getTurnOrder()){
			if (entity != game.getPlayer() && entity != null){
				opponents.add(entity);
			}
		}
		activeOpponent = 0;
		quitMenu = new GameMenu(this);

		mainPanel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "quit");
		mainPanel.getActionMap().put("quit", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (quitMenu.isVisible()){
					quitMenu.setVisible(false);
					removeOverlay(quitMenu);
					isBlocked = false;
				}
				else{
					showOverlay(quitMenu,OverlayPlacement.CENTER);
					quitMenu.setVisible(true);
					isBlocked = true;
				}

			}
		});


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
		OpponentMainPanel.setBorder(new EmptyBorder(0,20,0,40));
		OpponentInfo.setLayout(new BorderLayout());
		OpponentStatus.setLayout(new BorderLayout());

		PlayerMainPanel.setLayout(new BorderLayout());
		PlayerMainPanel.setBorder(new EmptyBorder(0,20,0,40));
		PlayerInfo.setLayout(new BorderLayout());
		PlayerStatus.setLayout(new BorderLayout());
		handPanel.setLayout(new BorderLayout());

		mainPanel.setLayout(new BorderLayout(10, 10));
		mainPanel.setBorder(new EmptyBorder(20, 0, 40, 0));
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

		endTurnButton.setPreferredSize(new Dimension(150, 150));
		endTurnButton.setMaximumSize(new Dimension(150, 150));
		JPanel middlePanel = new JPanel();
		middlePanel.setLayout(new BorderLayout());
		middlePanel.setBackground(SharedResource.SIAMESE_BRIGHT);

		JPanel endTurnPanel = new JPanel();
		endTurnPanel.setLayout(new BoxLayout(endTurnPanel,BoxLayout.X_AXIS));
		endTurnPanel.setBackground(SharedResource.SIAMESE_BRIGHT);
		endTurnPanel.add(endTurnButton);
		endTurnPanel.setPreferredSize(new Dimension(210, 150));
		endTurnPanel.setBorder(new EmptyBorder(0,0,0,40));


		turnCount = new TurnCount();

		JPanel turnCountPanel = new JPanel();
		turnCountPanel.setLayout(new BoxLayout(turnCountPanel,BoxLayout.X_AXIS));
		turnCountPanel.setBackground(SharedResource.SIAMESE_BRIGHT);
		turnCountPanel.add(turnCount);
		turnCountPanel.setPreferredSize(new Dimension(250, 150));


		middlePanel.add(endTurnPanel,BorderLayout.EAST);
		middlePanel.add(turnCountPanel,BorderLayout.WEST);

		mainPanel.add(middlePanel);

		mainPanel.setVisible(true);

		endTurnButton.addActionListener(this);

		onHandChanged();
		updatePlayerHUD();
		game.notifyGameStart();
		if (game instanceof GameForGUI){
			game.start();
		}


		BGMPlayer.stopBackgroundMusic();

		BGMPlayer.playBackgroundMusic("Game/src/assets/Audio/BGM/Gameplay_BGM_Mixed.wav", true);
	}



	public void updatePlayerHUD(){
		playerInfo.updateInfo();
		enemyInfo.updateInfo();
	}

	public void setPlayerTurn(boolean playerTurn) {
		isPlayerTurn = playerTurn;
		System.out.println(isPlayerTurn);
		SFXPlayer.playSound("Game/src/assets/Audio/SFX/PlayerTurn.wav");
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
			SFXPlayer.playSound("Game/src/assets/Audio/SFX/Meow.wav");
			setPlayerTurn(false);
			game.notifyEndTurn();
		}
		if(e.getSource() instanceof CardPlayable && isPlayerTurn){
			CardPlayable c = (CardPlayable) e.getSource();
			System.out.println(c.isPlayable());
			if(c.isPlayable()) {
				System.out.println(c.getCard());
				System.out.println(Arrays.toString(game.getPlayer().getHand().toArray()));
				int index = game.getPlayer().getHand().indexOf(c.getCard());

				//gui.addCardPlayed(cardPlayed);
				if (game.getPlayer().getHand().get(index).getType() == CardType.GREEN){
					SFXPlayer.playSound("Game/src/assets/Audio/SFX/Card_Play_Click.wav");
					showOverlay(new SelectOpponent(game.getPlayer(),opponents,index,this),OverlayPlacement.CENTER);
				}
				else{
					SFXPlayer.playSound("Game/src/assets/Audio/SFX/Card_Play.wav");
					playCard(index,getActiveEnemy());
				}
			}
		}
	}

	public void playCard(int cardIndex,Player receiver){
		game.playerPlay(cardIndex,receiver);
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

	@Override
	public void onGameStart(int startTurn) {
		setPlayerTurn(game.getPlayerOrder() == startTurn);
		onStatChanged();
		onHandChanged();
		mainPanel.requestFocus();
	}

	@Override
	public void onCardPlayed() {

		onStatChanged();
		onHandChanged();
	}

	@Override
	public void onHandChanged() {
		getUserPanel().updatePlayable(getActiveEnemy());
		OpponentPanel.RenderHand();
		UserPanel.RenderHand();
	}

	@Override
	public void onStatChanged() {
		playerInfo.updateInfo();
		enemyInfo.updateInfo();
	}

	@Override
	public void onPlayerQuit(Player playerQuit) {
		onGameEnded(getPlayer());
	}

	@Override
	public void onGameEnded(Player winner) {
		BGMPlayer.stopBackgroundMusic();
		if (winner == game.getPlayer()){
			SFXPlayer.playSound("Game/src/assets/Audio/SFX/Game_Victory.wav");
			BGMPlayer.playBackgroundMusic("Game/src/assets/Audio/BGM/Game_Victory_BGM.wav", false);
			BGMPlayer.playBackgroundMusic("Game/src/assets/Audio/BGM/Game_Victory_Trap_BGM.wav", true);
			showOverlay(new ResultShow("Victory"),0,0, mainPanel.getWidth(), mainPanel.getHeight());
			setBackdropDim(true);
			//endTurnButton.removeActionListener(this);
		}
		else if(winner != null){
			SFXPlayer.playSound("Game/src/assets/Audio/SFX/Game_Defeat.wav");
			BGMPlayer.playBackgroundMusic("Game/src/assets/Audio/BGM/Game_Defeat_BGM.wav", false);
			BGMPlayer.playBackgroundMusic("Game/src/assets/Audio/BGM/Game_Defeat_Trap_BGM.wav", true);
			showOverlay(new ResultShow("Defeat"),0,0, mainPanel.getWidth(), mainPanel.getHeight());
			setBackdropDim(true);
		}
		else{
			SFXPlayer.playSound("Game/src/assets/Audio/SFX/Game_Draw.wav");
			BGMPlayer.playBackgroundMusic("Game/src/assets/Audio/BGM/Game_Draw_BGM.wav", false);
			BGMPlayer.playBackgroundMusic("Game/src/assets/Audio/BGM/Game_Draw_Trap_BGM.wav", true);
			showOverlay(new ResultShow("Draw"),0,0, mainPanel.getWidth(), mainPanel.getHeight());
			setBackdropDim(true);
		}
	}

	@Override
	public void onTurnArrive() {
		setPlayerTurn(true);
	}

	@Override
	public void onTurnCountChange(int count) {
		turnCount.updateCount(count);
	}

	@Override
	public void onTurnEnded() {
		System.out.println("OnTurnEnd");
		setPlayerTurn(false);
		getUserPanel().updatePlayable(getActiveEnemy());
		playerInfo.updateInfo();
		enemyInfo.updateInfo();
	}

	public void quitGame(){
		game.notifyQuit();
		mainPanel.getInputMap().remove(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
		mainPanel.getActionMap().remove("quit");
		mainPanel.removeAncestorListener(listener);
		Router.setRoute("MainMenu",null);
	}
}
