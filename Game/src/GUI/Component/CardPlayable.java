package GUI.Component;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

import AudioPlayer.SFXPlayer;
import AudioPlayer.SFXSwitcher;
import Gameplay.Card;
import utils.ResourceLoader;
import utils.SharedResource;

public class CardPlayable extends JButton implements MouseListener {
	private HandDeck handDeck;
	private	Card 	card;
	private	boolean	isEnemy;
	private	int		OLD_WIDTH;
	private	int		OLD_HEIGHT;
	private boolean isPlayable;
	private ImageIcon cardPic;

	public CardPlayable(HandDeck hand,Card card, double scale, boolean isEnemy,boolean isPlayable) {
		super();
		this.handDeck = hand;
		this.card = card;
		this.isEnemy = isEnemy;
		this.setPlayable(isPlayable);
		this.setSize((int)(SharedResource.CARD_WIDTH * scale), (int)(SharedResource.CARD_HEIGHT * scale));
		if (isEnemy){
			cardPic = ResourceLoader.loadPicture("assets/BackSideCard.png");
		}
		else {
			cardPic = ResourceLoader.loadPicture(this.card.getPicture());
		}
		this.setIcon();
		this.addMouseListener(this);
		//System.out.println(card.getName());
	}

	public void	rescale(double scale)
	{
		this.setSize((int)(SharedResource.CARD_WIDTH * scale), (int)(SharedResource.CARD_HEIGHT * scale));
		this.setIcon();
	}

	public void	setIcon()
	{
		String Path;
		this.setIcon(new ImageIcon(cardPic.getImage().getScaledInstance(this.getWidth(),this.getHeight(),Image.SCALE_FAST)));
		if (!isEnemy) {
			if (!isPlayable) {
				this.setEnabled(false);
			}
		}
	}

	public void setPlayable(boolean playable) {
		this.isPlayable = playable;
	}

	@Override
	public void	setSize(int width, int height) {
		super.setSize(width, height);
		this.setPreferredSize(new Dimension(width, height));
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		 if (isEnemy)
		 	return ;
		this.OLD_WIDTH = this.getWidth();
		this.OLD_HEIGHT = this.getHeight();
		this.setForeground(new Color(0x1E1E1E5F));
		this.setSize((int)(this.getWidth() * 1.2), (int)(this.getHeight() * 1.2));
		this.setIcon();
	}

	@Override
	public void mousePressed(MouseEvent e) {}
	
	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3 && !isEnemy && !handDeck.gui.isBlocked)
		{
			SFXSwitcher.cardDescUP(card);

			JPanel	overlay;

			overlay = handDeck.gui.getOverlayPanel();
			overlay.add(new CardInspector(card, handDeck.gui,cardPic));
			handDeck.gui.setBackdropDim(true);
		} else if (e.getButton() == MouseEvent.BUTTON1 && !isEnemy && !handDeck.gui.isBlocked) {
			SFXPlayer.playSound("Game/src/assets/Audio/SFX/Cannot_Play_Click.wav");
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (isEnemy || handDeck.gui.isBlocked)
			return ;
		this.setSize(OLD_WIDTH, OLD_HEIGHT);
		this.setIcon();
	}

	public Card getCard() {
		return card;
	}

	public boolean isPlayable() {
		return isPlayable;
	}
}
