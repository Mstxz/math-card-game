package GUI.Component;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

import Gameplay.Player;
import Gameplay.Card;

public class HandDeck extends JPanel {
	private	Player						owner;
	private	ArrayList<CardPlayable>		list = new ArrayList<CardPlayable>();
	private ArrayList<Integer>			playableIndex = new ArrayList<Integer>();
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
		for (int i = 0;i < owner.getHand().size();i++) {
			tmp = new CardPlayable(owner.getHand().get(i), scale, isEnemy,playableIndex.contains(i));
			tmp.setPlayable(playableIndex.contains(i));
			list.add(tmp);
			this.add(tmp);
		}
		this.initialize = true;
		this.revalidate();
		this.repaint();
	}

	public void updatePlayable(Player enemy){
		playableIndex = Player.listPlayableCard(owner,enemy);
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
		CardPlayable	tmp = new CardPlayable(owner.getHand().getLast(), scale, isEnemy,true);

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
