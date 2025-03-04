package GUI.Component;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

import Gameplay.Card;
import utils.ResourceLoader;
import utils.SharedResource;

public class CardPlayable extends JButton implements MouseListener {
	private	Card 	card;
	private	boolean	isEnemy;

	public CardPlayable(Card card, double scale, boolean isEnemy) {
		super();
		this.card = card;
		this.isEnemy = isEnemy;
		this.setSize((int)(SharedResource.CARD_WIDTH * scale), (int)(SharedResource.CARD_HEIGHT * scale));
		this.setIcon();
		this.addMouseListener(this);
	}

	public void	rescale(double scale)
	{
		this.setSize((int)(SharedResource.CARD_WIDTH * scale), (int)(SharedResource.CARD_HEIGHT * scale));
		this.setIcon();
	}

	public void	setIcon()
	{
		if (!isEnemy)
			this.setIcon(ResourceLoader.loadPicture(this.card.getPicture(), this.getWidth(), this.getHeight()));
		else
			this.setIcon(ResourceLoader.loadPicture("assets/BackSideCard.png", this.getWidth(), this.getHeight()));
	}

	@Override
	public void	setSize(int width, int height) {
		super.setSize(width, height);
		this.setPreferredSize(new Dimension(width, height));
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (isEnemy)
		{
			System.out.println("Trying to inspect me??");
			return ;
		}
		System.out.println(card.getDescription());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
