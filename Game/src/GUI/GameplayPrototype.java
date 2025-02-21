package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameplayPrototype implements ActionListener{
	private static final Dimension	OpponentSize = new Dimension(165, 225);
	private static final Dimension	HandSize = new Dimension(247, 337);
	public JFrame					Frame;
	private JPanel					OpponentPanel;
	private JPanel					UserPanel;
	private ArrayList<JButton>		OpponentHand;
	private ArrayList<JButton>		UserHand;

	public GameplayPrototype()
	{
		Frame = new JFrame();
		OpponentPanel = new JPanel();
		UserPanel= new JPanel();
		OpponentHand = new ArrayList<JButton>();
		UserHand = new ArrayList<JButton>();
		
		OpponentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, -50, 0));
		OpponentPanel.setBackground(new Color(0xFF0000));

		UserPanel.setLayout(new FlowLayout(FlowLayout.CENTER, -50, 0));
		UserPanel.setBackground(new Color(0x00FFFF));
	
		Frame.setLayout(new BorderLayout());
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame.add(OpponentPanel, BorderLayout.NORTH);
		Frame.add(UserPanel, BorderLayout.SOUTH);
		Frame.setSize(1920, 1080);
		Frame.setVisible(true);
	}
	
	
	public void	initCard()
	{
		for (int i = 0; i < 5; i++)
		// OpponentPanel.add(this.newCardBtn("assets/BackSideCard.png", OpponentSize));
			newCardBtn(OpponentPanel, OpponentHand,"assets/BackSideCard.png", OpponentSize);
		for (int i = 0; i < 5; i++)
		// UserPanel.add(this.newCardBtn("assets/Yellow_BetaCatNap.png", HandSize));
			newCardBtn(UserPanel, UserHand,"assets/Yellow_BetaCatNap.png", HandSize);
	}
	
	public void newCardBtn(JPanel panel, ArrayList<JButton> hand, String path, Dimension dimension)
	{
		JButton	res;
		Image	img;
		
		res = new JButton();
		res.setSize(dimension);
		res.setPreferredSize(dimension);
		try
		{
			img = new ImageIcon(getClass().getResource(path)).getImage().getScaledInstance(res.getWidth(), res.getHeight(), Image.SCALE_DEFAULT);
			res.setIcon(new ImageIcon(img));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return ;
		}
		res.addActionListener(this);
		hand.add(res);
		panel.add(res);
		Frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			System.out.println("Click");
		}
	}

	public static void main(String[] args) {
		GameplayPrototype game =  new GameplayPrototype();
		// game.Frame.setBackground(new Color(0x005F3F));
		game.initCard();
	}
}
