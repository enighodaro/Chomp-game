package src;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Game {
	static int height = 400;
	static int width = 700;
	static JFrame frame = new JFrame("CHOMP");
	static JLabel title = new JLabel("CHOMP GAME");
	
	static JButton small = new JButton("4x5");
	static JButton medium = new JButton("5x8");
	static JButton large = new JButton("8x10");
	
	static boolean loser = false;
	static String PlayerLost = "";
	static String currPlayer = "";
	static int rows;
	static int cols;
	static JButton[] buttons;
	static JButton but1;
	static JButton but2;
			
	public static void GUI() {
		title.setBounds(310,25,100,100);
		small.setBounds(100,100,100,150);
		medium.setBounds(300,100,100,150);
		large.setBounds(500,100,100,150);
		frame.add(title);
		frame.add(small);
		frame.add(medium);
		frame.add(large);
		frame.setSize(width,height);
		frame.setLayout(null);
		frame.setVisible(true);
		small.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rows = 4;
				cols = 5;
				playGame(4,5);
				
			}
		});
		medium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rows = 5;
				cols = 8;
				playGame(5,8);
				
			}
		});
		large.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rows = 8;
				cols = 10;
				playGame(8,10);
				
			}
		});
	}
	
	public static void playGame(int GameHeight,int GameWidth){
		frame.remove(title);
		frame.remove(small);
		frame.remove(medium);
		frame.remove(large);
		

        	currPlayer = "Player";

		
		frame.setTitle("CHOMP - Player");
		frame.revalidate();
		frame.repaint();
		
        int k = 0;
        buttons = new JButton[GameHeight*GameWidth];
		frame.setLayout(new GridLayout(GameHeight,GameWidth));
		for (int row = 0; row < GameHeight; row++){
		    for (int col = 0; col < GameWidth; col++){
		    	if (row == 0 & col == 0) {
			        JButton b = new JButton ("Soap");
			        frame.add(b).setLocation(row, col);
			        frame.add(b);
			        b.setBackground(Color.yellow);
			        b.addActionListener(new ActionListener() {
		    			public void actionPerformed(ActionEvent e) {
		    				getLegalMove(b);
		    				
		    			}
		    		});
		    	}
		    	else if (row == 1 & col == 0){
		    		JButton b = new JButton ();
		    		b.setName("("+row+","+col+")");
		    		frame.add(b).setLocation(row, col);
		    		frame.add(b);
		    		b.setBackground(Color.darkGray);
		    		buttons[k] = b;
		    		k++;
		    		but1 = b;
		    		b.addActionListener(new ActionListener() {
		    			public void actionPerformed(ActionEvent e) {
		    				getLegalMove(b);
		    				
		    			}
		    		});
		    	}
		    	else if(row == 0 & col == 1) {
			        JButton b = new JButton ();
			        b.setName("("+row+","+col+")");
		    		frame.add(b).setLocation(row, col);
		    		frame.add(b);
		    		but2 = b;
		    		b.setBackground(Color.darkGray);
		    		buttons[k] = b;
		    		k++;
		    		but2 = b;
		    		b.addActionListener(new ActionListener() {
		    			public void actionPerformed(ActionEvent e) {
		    				getLegalMove(b);
		    				
		    			}
		    		});
		    	}
		    	else{
		    		
		    		JButton b = new JButton ();
		    		b.setName("("+row+","+col+")");
		    		frame.add(b).setLocation(row, col);
		    		frame.add(b);
		    		b.setBackground(Color.darkGray);
		    		buttons[k] = b;
		    		k++;
		    		b.addActionListener(new ActionListener() {
		    			public void actionPerformed(ActionEvent e) {
		    				getLegalMove(b);
		    				
		    			}
		    		});
		    	}
		    	
		    }
		}
		
		frame.revalidate();
		frame.repaint();
		
	}
	public static void getLegalMove(JButton b) {
		if(but1.isVisible() ==  false & but2.isVisible() == false) {
			loser = true;
		}
		if(loser == true) {
			loser = false;
			frame.removeAll();
			frame.dispose();
			frame = new JFrame("CHOMP");
			JFrame frame2 = new JFrame("LOSER OF CHOMP");
			JLabel title2 = new JLabel(currPlayer + " LOST!");
			JButton playAgain = new JButton("Play Again");
			playAgain.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				GUI();
    				
    			}
    		});
			title2.setBounds(135,25,100,100);
			playAgain.setBounds(130,100,100,50);
			frame2.add(title2);
			frame2.add(playAgain);
			frame2.setSize(400,250);
			frame2.setLayout(null);
			frame2.setVisible(true);
		}
		if(b.getText() == "Soap") {
			//invalid move
		}
		else if(currPlayer == "Computer") {
			Random Rand2 = new Random();
			boolean isGood = false;
			
			while(isGood == false) {
				int n = Rand2.nextInt(buttons.length);
				JButton newb = buttons[n];
				if(newb.isVisible()) {
					isGood = true;
					frame.revalidate();
					frame.repaint();
					currPlayer = "Player";
					frame.setTitle("CHOMP - Player");
					frame.revalidate();
					frame.repaint();
					String name = newb.getName();
					name = name.replaceAll("\\D+", "");
					int row = (int)(name.charAt(0));
					int col = (int)(name.charAt(1));
					for (JButton button : buttons) {
						String name2 = button.getName();
					    name2 = name2.replaceAll("\\D+", "");
					    int row2 = (int)(name2.charAt(0));
					    int col2 = (int)(name2.charAt(1));
					    if(row2 >= row & col2 >= col) {
					    	if(button.isVisible()) {
					    		button.setVisible(false);
					    	}
					    }
					}
				}
			}
		
		}else{
			if (currPlayer == "Player") {currPlayer = "Computer";}
			else {currPlayer = "Player";}
			String name = b.getName();
			name = name.replaceAll("\\D+", "");
			int row = (int)(name.charAt(0));
			int col = (int)(name.charAt(1));
			currPlayer = "Computer";
			frame.setTitle("CHOMP - Computer");
			frame.revalidate();
			frame.repaint();
			for (JButton button : buttons) {
				String name2 = button.getName();
		    	name2 = name2.replaceAll("\\D+", "");
		    	int row2 = (int)(name2.charAt(0));
		    	int col2 = (int)(name2.charAt(1));
		    	if(row2 >= row & col2 >= col) {
		    		if(button.isVisible()) {
		    			button.setVisible(false);
		    		}
		    	
		    	}
			}
		}
		
	}
	public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GUI();
                
            }
        });
    }
}
