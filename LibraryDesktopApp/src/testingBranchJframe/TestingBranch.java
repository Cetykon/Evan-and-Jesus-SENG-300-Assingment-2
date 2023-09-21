package testingBranchJframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;


//Declare any classes





class AppFrame extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Variables for Home Panel
	private static JPanel startPanel;
	private static JButton toBookSearchBtn = new JButton();
	private static JLabel titleLabel = new JLabel();
	private static String userInput;
	private static JButton topTenBooksBtn = new JButton();
	private static JButton bottomTenBooksBtn = new JButton();
	
	// Variables for Book Search Panel
	private static JPanel bookSearchPanel = new JPanel();
	private static JLabel bookSearchTitleLabel = new JLabel();
	private static JButton bookSearchHomeBtn = new JButton();
	private static JTextField userInputFld = new JTextField();
	private static JLabel searchLabel = new JLabel();
	private static JButton searchBookBtn = new JButton();
	
	// Variables for Search Result Panel
	private static JPanel searchResultPanel = new JPanel();
	private static JLabel searchResultTitleLabel = new JLabel();
	private static JButton searchHomeBtn = new JButton();
	private static JTextArea searchResultTextArea = new JTextArea();
	private static JButton searchBackBtn = new JButton();
	
	// Variables for Sort Result Panel
	private static JPanel sortResultPanel = new JPanel();
	private static JLabel sortResultTitleLabel = new JLabel();
	private static JButton sortHomeBtn = new JButton();
	JTextArea sortResultTextArea = new JTextArea();
	private static JButton sortBackBtn = new JButton();
	
	AppFrame() {
		
		this.setTitle("Library App");
		getContentPane().setLayout(new BorderLayout(10, 10));
		this.getContentPane().setBackground(Color.decode("#304529"));
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(500, 600));
		this.setResizable(false);
		
		
		// Home Panel
		startPanel = new JPanel();
		startPanel.setBackground(Color.decode("#304529"));
		startPanel.setBounds(0, 0, 455, 600);
		startPanel.setLayout(null);
		startPanel.setVisible(true);
		
		titleLabel.setText("Library App");
		titleLabel.setBounds(170, 30, 150, 25);
		titleLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
		titleLabel.setForeground(Color.white);
		titleLabel.setVisible(true);
		
		toBookSearchBtn = new JButton("Search for Book");
		toBookSearchBtn.setFont(new Font("Courier New", Font.PLAIN, 13));
		toBookSearchBtn.setBounds(140, 200, 182, 70);
		toBookSearchBtn.setFocusable(false);
		toBookSearchBtn.addActionListener(this);
		toBookSearchBtn.setVisible(true);
		
		topTenBooksBtn = new JButton("Top 10 Books by Rating");
		topTenBooksBtn.setFont(new Font("Courier New", Font.PLAIN, 10));
		topTenBooksBtn.setBounds(140, 275, 182, 70);
		topTenBooksBtn.setFocusable(false);
		topTenBooksBtn.addActionListener(this);
		topTenBooksBtn.setVisible(true);
		
		bottomTenBooksBtn = new JButton("Bottom 10 Books by Rating");
		bottomTenBooksBtn.setFont(new Font("Courier New", Font.PLAIN, 10));
		bottomTenBooksBtn.setBounds(140, 350, 182, 70);
		bottomTenBooksBtn.setFocusable(false);
		bottomTenBooksBtn.addActionListener(this);
		bottomTenBooksBtn.setVisible(true);
		
		startPanel.add(titleLabel);
		startPanel.add(toBookSearchBtn);
		startPanel.add(topTenBooksBtn);
		startPanel.add(bottomTenBooksBtn);
		
		
		// Book Search Panel
		bookSearchPanel = new JPanel();
		bookSearchPanel.setBackground(Color.decode("#304529"));
		bookSearchPanel.setBounds(0, 0, 455, 600);
		bookSearchPanel.setLayout(null);
		bookSearchPanel.setVisible(false);
		
		bookSearchTitleLabel.setText("Book Search");
		bookSearchTitleLabel.setBounds(170, 30, 150, 25);
		bookSearchTitleLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
		bookSearchTitleLabel.setForeground(Color.white);
		bookSearchTitleLabel.setVisible(true);
		
		searchLabel.setText("Search for Book by ID");
		searchLabel.setBounds(155, 220, 150, 25);
		searchLabel.setFont(new Font("Courier New", Font.PLAIN, 12));
		searchLabel.setForeground(Color.white);
		searchLabel.setVisible(true);
		
		bookSearchHomeBtn = new JButton("Home");
		bookSearchHomeBtn.setFont(new Font("Courier New", Font.PLAIN, 13));
		bookSearchHomeBtn.setBounds(140, 55, 180, 35);
		bookSearchHomeBtn.setFocusable(false);
		bookSearchHomeBtn.addActionListener(this);
		bookSearchHomeBtn.setVisible(true);
		
		userInputFld.setFont(new Font("Courier New", Font.PLAIN, 13));
		userInputFld.setPreferredSize(new Dimension(20, 30));
		userInputFld.setCaretColor(Color.black); // cursor color
		userInputFld.setEditable(true);
		userInputFld.setBounds(140, 250, 180, 35);
		userInputFld.setVisible(true);
		
		searchBookBtn = new JButton("Search");
		searchBookBtn.setFont(new Font("Courier New", Font.PLAIN, 13));
		searchBookBtn.setBounds(140, 300, 180, 35);
		searchBookBtn.setFocusable(false);
		searchBookBtn.addActionListener(this);
		searchBookBtn.setVisible(true);
		
		bookSearchPanel.add(bookSearchTitleLabel);
		bookSearchPanel.add(bookSearchHomeBtn);
		bookSearchPanel.add(userInputFld);
		bookSearchPanel.add(searchLabel);
		bookSearchPanel.add(searchBookBtn);
		
		// Search Result Panel
		searchResultPanel = new JPanel();
		searchResultPanel.setBackground(Color.decode("#304529"));
		searchResultPanel.setBounds(0, 0, 455, 600);
		searchResultPanel.setLayout(null);
		searchResultPanel.setVisible(false);
		
		searchResultTitleLabel.setText("Search Results");
		searchResultTitleLabel.setBounds(170, 30, 150, 25);
		searchResultTitleLabel.setFont(new Font("Courier New", Font.PLAIN, 15));
		searchResultTitleLabel.setForeground(Color.white);
		searchResultTitleLabel.setVisible(true);
		
		searchHomeBtn = new JButton("Home");
		searchHomeBtn.setFont(new Font("Courier New", Font.PLAIN, 13));
		searchHomeBtn.setBounds(140, 55, 180, 35);
		searchHomeBtn.setFocusable(false);
		searchHomeBtn.addActionListener(this);
		searchHomeBtn.setVisible(true);
		
		searchResultTextArea = new JTextArea(400, 400);
		searchResultTextArea.setFont(new Font("Courier New", Font.PLAIN, 14));
		searchResultTextArea.setEditable(false);
		JScrollPane searchResultPane = new JScrollPane(searchResultTextArea);
		searchResultPane.setBounds(30, 100, 400, 410);
		
		searchBackBtn = new JButton("Back");
		searchBackBtn.setFont(new Font("Courier New", Font.PLAIN, 13));
		searchBackBtn.setBounds(140, 515, 180, 35);
		searchBackBtn.setFocusable(false);
		searchBackBtn.addActionListener(this);
		searchBackBtn.setVisible(true);
		
		searchResultPanel.add(searchResultTitleLabel);
		searchResultPanel.add(searchHomeBtn);
		searchResultPanel.add(searchResultPane);
		searchResultPanel.add(searchBackBtn);
		
		// Sort Result Panel
		sortResultPanel = new JPanel();
		sortResultPanel.setBackground(Color.decode("#304529"));
		sortResultPanel.setBounds(0, 0, 455, 600);
		sortResultPanel.setLayout(null);
		sortResultPanel.setVisible(false);
		
		sortResultTitleLabel.setText("Search Results");
		sortResultTitleLabel.setBounds(170, 30, 150, 25);
		sortResultTitleLabel.setFont(new Font("Courier New", Font.PLAIN, 15));
		sortResultTitleLabel.setForeground(Color.white);
		sortResultTitleLabel.setVisible(true);
		
		sortHomeBtn = new JButton("Home");
		sortHomeBtn.setFont(new Font("Courier New", Font.PLAIN, 13));
		sortHomeBtn.setBounds(140, 55, 180, 35);
		sortHomeBtn.setFocusable(false);
		sortHomeBtn.addActionListener(this);
		sortHomeBtn.setVisible(true);
		
		sortResultTextArea = new JTextArea(400, 400);
		sortResultTextArea.setFont(new Font("Courier New", Font.PLAIN, 14));
		sortResultTextArea.setEditable(false);
		JScrollPane sortResultPane = new JScrollPane(sortResultTextArea);
		sortResultPane.setBounds(30, 100, 400, 410);

		sortResultPanel.add(sortResultTitleLabel);
		sortResultPanel.add(sortHomeBtn);
		sortResultPanel.add(sortResultPane);
		sortResultPanel.add(sortBackBtn);
		
		
		getContentPane().add(startPanel);
		getContentPane().add(bookSearchPanel);
		getContentPane().add(searchResultPanel);
		getContentPane().add(sortResultPanel);
		
		
	}


	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == toBookSearchBtn) {
			try {
				bookSearchWindow();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if (e.getSource() == bookSearchHomeBtn) {
			try {
				screenReset();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if (e.getSource() == searchBookBtn) {
			userInput = userInputFld.getText();
			searchResultTextArea.setText("");
			try {
				bookSearchResultWindow();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			
			//Start Main code_______________________________________


		}
		
		if (e.getSource() == topTenBooksBtn) {
			sortResultTextArea.setText("");
			try {
				bookSortResultWindow();
				sortResultTextArea.append("Top 10 Books Sorted by Rating: ");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if (e.getSource() == searchHomeBtn) {
			try {
				screenReset();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		if (e.getSource() == searchBackBtn) {
			try {
				bookSearchWindow();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		if (e.getSource() == sortHomeBtn) {
			try {
				screenReset();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	
	
	public static void screenReset() throws IOException {
		startPanel.setVisible(true);
		bookSearchPanel.setVisible(false);
		searchResultPanel.setVisible(false);
		sortResultPanel.setVisible(false);
	}
	
	public static void bookSearchWindow() throws IOException {
		startPanel.setVisible(false);
		searchResultPanel.setVisible(false);
		bookSearchPanel.setVisible(true);
	}
	
	public void bookSearchResultWindow() throws IOException {
		startPanel.setVisible(false);
		bookSearchPanel.setVisible(false);
		searchResultPanel.setVisible(true);
	}
	
	public void bookSortResultWindow() throws IOException {
		startPanel.setVisible(false);
		bookSearchPanel.setVisible(false);
		searchResultPanel.setVisible(false);
		sortResultPanel.setVisible(true);
	}
}


public class TestingBranch {

	public static void main(String[] args) {
		testingBranchJframe.AppFrame appFrame = new AppFrame();
		appFrame.getContentPane().setLayout(null);
	}

}





 


