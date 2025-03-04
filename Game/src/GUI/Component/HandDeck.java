package GUI.Component;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

import Gameplay.Player;

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
			this.setLayout(new FlowLayout(FlowLayout.CENTER, -20, 0));
		else
			this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setVisible(true);
	}

	public void	RenderHand()
	{
		CardPlayable	tmp;
		
		if (this.initialize)
			return ;
		scale = 1.0 - (0.05 * owner.getHand().size());
		for (int i = 0; i < owner.getHand().size(); i++) {
			tmp = new CardPlayable(owner.getHand().get(i), scale, isEnemy);
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
		scale = 1.0;
		this.revalidate();
		this.repaint();
	}

	public void	AppendCard()
	{
		CardPlayable	tmp = new CardPlayable(owner.getHand().getLast(), scale, isEnemy);

		for (CardPlayable c : list)
			c.rescale(scale);
		list.add(tmp);
		this.add(tmp);
		scale = 1.0 - (0.05 * list.size());
		this.revalidate();
		this.repaint();
	}
}
