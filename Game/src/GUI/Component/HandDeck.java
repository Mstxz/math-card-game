package GUI.Component;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

import Gameplay.Player;
import Gameplay.Card;

public class HandDeck extends JPanel {
	private	Player						owner;
	private	ArrayList<CardPlayable>		list = new ArrayList<CardPlayable>();
	private	boolean						isEnemy;
	private	boolean						initialize;
	private	double						scale = 1.0;

	public HandDeck(Player owner, boolean isEnemy)
	{
		this.owner = owner;
		this.isEnemy = isEnemy;
		this.initialize = false;
		if (isEnemy)
			this.setLayout(new FlowLayout(FlowLayout.CENTER, -5 * owner.getHand().size(), 0));
		else
			this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setVisible(true);
	}

	public void	RenderHand()
	{
		CardPlayable	tmp;

		this.CleanHand();
		scale = 1.0 - (0.02 * owner.getHand().size());
		this.setLayout(new FlowLayout(FlowLayout.CENTER, -5 * owner.getHand().size(), 0));
		System.out.println(owner.getName()+ " : " + owner.getHand().size());
		for (Card c : owner.getHand()) {
			tmp = new CardPlayable(c, scale, isEnemy);
			list.add(tmp);
			this.add(tmp);
		}
		this.initialize = true;
		this.revalidate();
		this.repaint();
	}

	public void	CleanHand()
	{
		this.removeAll();
		list.clear();
		scale = 1.0;
		this.setLayout(new FlowLayout(FlowLayout.CENTER, -5 * owner.getHand().size(), 0));
		this.revalidate();
		this.repaint();
	}

	public void	AppendCard()
	{
		CardPlayable	tmp = new CardPlayable(owner.getHand().getLast(), scale, isEnemy);

		for (CardPlayable c : list)
			c.rescale(scale);
		this.setLayout(new FlowLayout(FlowLayout.CENTER, -5 * owner.getHand().size(), 0));
		list.add(tmp);
		this.add(tmp);
		scale = 1.0 - (0.02 * list.size());
		this.revalidate();
		this.repaint();
	}
}
