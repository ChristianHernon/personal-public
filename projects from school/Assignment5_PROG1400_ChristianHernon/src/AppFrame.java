//************************************************
//Author: 	Christian Hernon, W0223388
//Date: 	April 16, 2015
//Purpose: 	PROG1400 Assignment #5 - Screensaver
//************************************************

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class AppFrame extends JFrame {

	//Properties
	private JPanel contentPane;
	private JPanel panelStart;
	private JLabel labelShapesNum;
	private panelScreenSaver myScreen;
	int numShapes = 1;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppFrame frame = new AppFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}//end main

	public AppFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 706, 481);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		panelStart = new JPanel();
		contentPane.add(panelStart, "name_10434459745744");
		panelStart.setLayout(null);
		
		JLabel lblHowManyShapes = new JLabel("How many shapes would you like?");
		lblHowManyShapes.setHorizontalAlignment(SwingConstants.CENTER);
		lblHowManyShapes.setBounds(220, 119, 240, 20);
		panelStart.add(lblHowManyShapes);
		
		labelShapesNum = new JLabel("1");
		labelShapesNum.setHorizontalAlignment(SwingConstants.CENTER);
		labelShapesNum.setBounds(317, 208, 46, 14);
		panelStart.add(labelShapesNum);
		
		JButton btnMinus = new JButton("-");
		btnMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//decrement the number of shapes to a minimum of 1
				if(numShapes > 1) {
					numShapes -= 1;
					labelShapesNum.setText(Integer.toString(numShapes));
				}
			}
		});
		btnMinus.setBounds(218, 204, 89, 23);
		panelStart.add(btnMinus);
		
		JButton btnPlus = new JButton("+");
		btnPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//increment the number of shapes
				numShapes += 1;
				labelShapesNum.setText(Integer.toString(numShapes));
			}
		});
		btnPlus.setBounds(373, 204, 89, 23);
		panelStart.add(btnPlus);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//on start create a ScreenSaver panel the same size as the content pane
				contentPane.add(myScreen = new panelScreenSaver(numShapes, contentPane.getWidth(), contentPane.getHeight()));
				panelStart.setVisible(false);
				myScreen.setVisible(true);
			}
		});
		btnStart.setBounds(295, 282, 89, 23);
		panelStart.add(btnStart);
	}//end constructor
	
}//end AppFrame class
