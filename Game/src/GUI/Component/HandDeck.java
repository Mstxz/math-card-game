package GUI.Component;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

import GUI.Page.AvengerAssembleGUI;
import Gameplay.CardType;
import Gameplay.Player;
import Gameplay.Card;

public class HandDeck extends JPanel implements ActionListener {
	// We should make GUI static
	private AvengerAssembleGUI gui;
	private	Player						owner;
	private	ArrayList<CardPlayable>		list = new ArrayList<CardPlayable>();
	private ArrayList<Integer>			playableIndex = new ArrayList<Integer>();
	private	boolean						isEnemy;
	private	boolean						initialize;
	private	double						scale = 1.0;

	public HandDeck(AvengerAssembleGUI gui,Player owner, boolean isEnemy)
	{
		this.gui = gui;
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
		for (int i = 0;i < owner.getHand().size();i++) {
			tmp = new CardPlayable(this,owner.getHand().get(i), scale, isEnemy,playableIndex.contains(i));
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton){
			CardPlayable c = (CardPlayable) e.getSource();
			if(c.isPlayable()) {
				int index = list.indexOf(c);
				Card cardPlayed = owner.getHand().remove(index);
				//gui.addCardPlayed(cardPlayed);
				if (cardPlayed.getType() == CardType.GREEN){
					gui.getSelectOpponent().setVisible(true);
					//gui.addCardPlayed(cardPlayed);
					cardPlayed.action(owner, gui.getSelectOpponent().getReciever());
					gui.getSelectOpponent().setVisible(false);
				}
				else{
					cardPlayed.action(owner, gui.getEnemy());

				}
				owner.getDeck().addDispose(cardPlayed);
				this.updatePlayable(gui.getEnemy());
				gui.updatePlayerHUD();
				gui.initCard();
			}
		}
	}
}
