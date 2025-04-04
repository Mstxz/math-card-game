package GUI.Component;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

import GUI.Page.AvengerAssembleGUI;
import Gameplay.Card;
import Gameplay.Player;
import utils.SharedResource;

public class HandDeck extends JPanel{
	// We should make GUI static
	protected AvengerAssembleGUI gui;
	private	Player						owner;
	private	ArrayList<CardPlayable>		list = new ArrayList<CardPlayable>();
	private ArrayList<Card>				redundentList = new ArrayList<Card>();
	private ArrayList<Integer>			playableIndex = new ArrayList<Integer>();
	private	boolean						isEnemy;
	private int							gap;
	private	double						scale = 1.0;

	public HandDeck(AvengerAssembleGUI gui,Player owner, boolean isEnemy)
	{
		this.gui = gui;
		this.owner = owner;
		this.isEnemy = isEnemy;
		gap = -5 * owner.getHand().size();
		this.setLayout(new FlowLayout(FlowLayout.CENTER, this.gap, 0));
		this.setVisible(true);

		if (!isEnemy){
			this.updatePlayable(gui.getActiveEnemy());
		}
	}

	public void	RenderHand()
	{
		if (owner.getHand().equals(redundentList)){
			return;
		}
		redundentList.clear();
		this.CleanHand();
		scale = 1.0 - (0.02 * owner.getHand().size());
		if (scale < 0.6)
			scale = 0.6;
		if (5 * owner.getHand().size() < SharedResource.CARD_WIDTH / 2)
			this.gap = (-5 * owner.getHand().size()) % (SharedResource.CARD_WIDTH / 2);
		this.setLayout(new FlowLayout(FlowLayout.CENTER, this.gap, 0));
		for (int i = 0;i < owner.getHand().size();i++) {
			CardPlayable tmp = new CardPlayable(this,owner.getHand().get(i), scale, isEnemy,playableIndex.contains(i));
			tmp.setPlayable(playableIndex.contains(i));
			tmp.addActionListener(gui);
			list.add(tmp);
			redundentList.add(tmp.getCard());
			this.add(tmp);
		}
		this.revalidate();
		this.repaint();
	}

	public void updatePlayable(Player enemy){
		//System.out.println(owner.getHand());
		playableIndex = Player.listPlayableCard(owner,enemy);
	}

	public void	CleanHand()
	{
		this.removeAll();
		list.clear();
		scale = 1.0;
//		this.setLayout(new FlowLayout(FlowLayout.CENTER, -5 * owner.getHand().size(), 0));
	}

	public void	AppendCard()
	{
		CardPlayable	tmp = new CardPlayable(this,owner.getHand().getLast(), scale, isEnemy,true);

		for (CardPlayable c : list)
			c.rescale(scale);
		this.setLayout(new FlowLayout(FlowLayout.CENTER, -5 * owner.getHand().size(), 0));
		list.add(tmp);
		this.add(tmp);
		scale = 1.0 - (0.02 * list.size());
		this.revalidate();
		this.repaint();
	}


	public void cleanUp(){
		for (CardPlayable c : list) {
			c.removeActionListener(gui);
			c.removeMouseListener(c);
		}
	}
}
