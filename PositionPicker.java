import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.sql.*;
import java.util.ArrayList;
import java.io.Console;

public class PositionPicker extends JPanel
{
	public ArrayList<JCheckBox> checkBoxes;
	static String baseQuery = "select * from player where position.position_id = ";
	static boolean runClicked = false;
	static JFrame frame;
	static String qbString = "QB";
	static String rbString = "RB";
	static String teString = "TE";
	static String kString = "K";
	static String dString = "D/ST";
	static String wrString = "WR";
	static JPanel panel = new JPanel();
	static String season;
	
	//initiate checkboxes - add every stat to a checkbox
	static JCheckBox rushTDs;
	static JCheckBox rushYards;
	static JCheckBox passYards;
	static JCheckBox gamesPlayed;
	static JCheckBox targets;
	static JCheckBox receptions;
	static JCheckBox recYards;
	static JCheckBox totalYards;
	static JCheckBox tdTotal;
	static JCheckBox turnovers;
	static JCheckBox rating;
	static JCheckBox fantasyPoints;
	static JCheckBox ffppg;
	static JCheckBox completions;
	static JCheckBox passAttempts;
	static JCheckBox passTDs;
	static JCheckBox intThrown;
	static JCheckBox touches;
	static JCheckBox compRate;
	static JCheckBox catchRate;
	static JCheckBox FGR;
	static JCheckBox EPR;
	static JCheckBox fgRate;
	static JCheckBox sacks;
	static JCheckBox fumblesRecovered;
	static JCheckBox interceptions;
	static JCheckBox dTD;
	static JCheckBox PA;
	static JCheckBox passYardsPG;
	static JCheckBox rushYardsPG;
	static JCheckBox safety;
	static JCheckBox kickReturns;
	static JCheckBox turnoversForced;
	static JCheckBox yardsAllowedPG;
	
	//initialize drop-down list for seasons
	static JComboBox dropMenu = new JComboBox();
		
	//enumerate seasons
	private String[] seasons = { "Season","2010", "2011", "2012", "2013", "2014" };
	
	//initialize boxes for user interface
	static Box positionBox = Box.createVerticalBox();
	static Box statBox = Box.createVerticalBox();
	static Box goBox = Box.createHorizontalBox();
	
	//initialize buttons
	static JButton goButton;

	public PositionPicker(){
		super(true);
		
		//set size of drop menu
		dropMenu.setMaximumSize(dropMenu.getPreferredSize());
		
		//add seasons to menu and add listener
		for (int i = 0; i <= 5; i++){
			dropMenu.addItem(seasons[i]);
		}
		
		//initialize menuListener and add to dropMenu
		DropMenuListener menuListener = new DropMenuListener();
		dropMenu.addActionListener(menuListener);
		
		//set panel
		panel.setPreferredSize(new Dimension(700, 620));
		panel.setLayout(new GridBagLayout());
	
		//create player type radio buttons
		JRadioButton qbButton = new JRadioButton(qbString, true);
		JRadioButton wrButton = new JRadioButton(wrString, false);
		JRadioButton teButton = new JRadioButton(teString, false);
		JRadioButton dButton = new JRadioButton(dString, false);
		JRadioButton kButton = new JRadioButton(kString, false);
		JRadioButton rbButton = new JRadioButton(rbString, false);
		
		//add action listener to buttons
		RadioListener onClick = new RadioListener();
		qbButton.addActionListener(onClick);
		rbButton.addActionListener(onClick);
		teButton.addActionListener(onClick);
		kButton.addActionListener(onClick);
		dButton.addActionListener(onClick);
		wrButton.addActionListener(onClick);
		
		//create group add buttons to group (ensures only 1 selected at a time)
		ButtonGroup positionSelector = new ButtonGroup();
		positionSelector.add(qbButton);
		positionSelector.add(wrButton);
		positionSelector.add(teButton);
		positionSelector.add(rbButton);
		positionSelector.add(dButton);
		positionSelector.add(kButton);
		
		//Add all positions to the position box
		positionBox.setBorder(BorderFactory.createTitledBorder("Player"));
		positionBox.add(qbButton);
		positionBox.add(rbButton);
		positionBox.add(teButton);
		positionBox.add(kButton);
		positionBox.add(wrButton);
		positionBox.add(dButton);
		
		//create stat type check buttons
		//TO-DO: The strings in JCheckBox param need to match the fields in our SQL database
		rushYards = new JCheckBox("Rushing Yards");
		passYards = new JCheckBox("Passing Yards");
		rushTDs = new JCheckBox("Rushing TDs");
		gamesPlayed = new JCheckBox("Games Played");
		targets = new JCheckBox("Targets");
		receptions = new JCheckBox("Receptions");
		recYards = new JCheckBox("Receiving Yards");
		totalYards = new JCheckBox("Total Yards");
		tdTotal = new JCheckBox("Total TDs");
		turnovers = new JCheckBox("Turnovers");
		rating = new JCheckBox("UpperDeck Rating");
		fantasyPoints = new JCheckBox("Fantasy Points");
		ffppg = new JCheckBox("Fantasy PPG");
		completions = new JCheckBox("Completions");
		passAttempts = new JCheckBox("Pass Attempts");
		passTDs = new JCheckBox("Passing TDs");
		intThrown = new JCheckBox("Interceptions Thrown");
		touches = new JCheckBox("Touches");
		compRate = new JCheckBox("Completion Rate");
		catchRate = new JCheckBox("Catch Rate");
		FGR = new JCheckBox("Field Goal Rate");
		EPR = new JCheckBox("Extra Point Rate");
		fgRate = new JCheckBox("Field Goal Rate");
		sacks = new JCheckBox("Sacks");
		fumblesRecovered = new JCheckBox("Fumbles Recovered");
		interceptions = new JCheckBox("Interceptions Forced");
		dTD = new JCheckBox("Defensive Touchdowns");
		PA = new JCheckBox("Points Allowed");
		passYardsPG = new JCheckBox("Pass Yards Allowed Per Game");
		rushYardsPG = new JCheckBox("Rush Yards Allowed Per Game");
		safety = new JCheckBox("Safeties Forced");
		kickReturns = new JCheckBox("Kickoffs Returned For TDs");
		turnoversForced = new JCheckBox("Turnovers Forced");
		yardsAllowedPG = new JCheckBox("Yards Allowed Per Game");
		
		//add each checkbox to arrayList
		checkBoxes = new ArrayList<JCheckBox>();
		checkBoxes.add(rushTDs);
		checkBoxes.add(rushYards);
		checkBoxes.add(passYards);
		checkBoxes.add(gamesPlayed);
		checkBoxes.add(targets);
		checkBoxes.add(receptions);
		checkBoxes.add(recYards);
		checkBoxes.add(totalYards);
		checkBoxes.add(tdTotal);
		checkBoxes.add(turnovers);
		checkBoxes.add(rating);
		checkBoxes.add(fantasyPoints);
		checkBoxes.add(ffppg);
		checkBoxes.add(completions);
		checkBoxes.add(passAttempts);
		checkBoxes.add(passTDs);
		checkBoxes.add(intThrown);
		checkBoxes.add(touches);
		checkBoxes.add(compRate);
		checkBoxes.add(EPR);
		checkBoxes.add(FGR);
		checkBoxes.add(fgRate);
		checkBoxes.add(sacks);
		checkBoxes.add(fumblesRecovered);
		checkBoxes.add(interceptions);
		checkBoxes.add(dTD);
		checkBoxes.add(PA);
		checkBoxes.add(passYardsPG);
		checkBoxes.add(rushYardsPG);
		checkBoxes.add(safety);
		checkBoxes.add(kickReturns);
		checkBoxes.add(turnoversForced);
		checkBoxes.add(yardsAllowedPG);
		
		//create QB stat box (use as default)
		statBox.setBorder(BorderFactory.createTitledBorder("QB Stats"));
		statBox.add(gamesPlayed);
		statBox.add(passYards);
		statBox.add(rushYards);
		statBox.add(totalYards);
		statBox.add(passTDs);
		statBox.add(rushTDs);
		statBox.add(tdTotal);
		statBox.add(completions);
		statBox.add(passAttempts);
		statBox.add(compRate);
		statBox.add(intThrown);
		statBox.add(fantasyPoints);
		statBox.add(ffppg);
		statBox.add(rating);
		
		//initialize checkbox listener
		CheckBoxListener produceStats = new CheckBoxListener();
		
		//add itemListener to all stat checkboxes
		for (JCheckBox obj: checkBoxes)
		{
			obj.addItemListener(produceStats);
		}
		
		//create goButton and add listener
		goButton = new JButton("Go!");
		RunButtonListener executeQuery = new RunButtonListener();
		goButton.addActionListener(executeQuery);
		
		//add season menu and go button to one box
		goBox.setAlignmentX(LEFT_ALIGNMENT);
		goBox.add(Box.createVerticalStrut(10));
		goBox.add(Box.createRigidArea(new Dimension(0,50)));
		goBox.add(dropMenu);
		goBox.add(Box.createRigidArea(new Dimension(10,0)));
		goBox.add(goButton);
		goBox.add(Box.createRigidArea(new Dimension(10,0)));
		
	
		//stuff all boxes into one vertical box, forming left side of interface
		Box container = Box.createVerticalBox();
		container.setBorder(BorderFactory.createTitledBorder("Settings"));
		container.add(positionBox);
		container.add(statBox);
		container.add(goBox);
		container.add(Box.createVerticalStrut(20));
		container.add(Box.createRigidArea(new Dimension(0,300)));
		
		//add the container box containing all boxes to panel
		addItem(panel, container, 0, 0, 0, 0, GridBagConstraints.WEST);
		
		//add panel to frame
		add(panel);
	}
	
	/**
	 * 
	 * This class is the item listener for the checkboxes. This will feed information into the query to be executed.
	 *
	 */
	class DropMenuListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			season = dropMenu.getSelectedItem().toString();
			if (isNumeric(season)){
			System.out.println(season);
			dropMenu.setEnabled(false);
			frame.validate();
			}
			
			
		}
	}
	
	/**
	 * 
	 * This class is the item listener for the checkboxes. This will feed information into the query to be executed.
	 *
	 */
	class CheckBoxListener implements ItemListener
	{
		public void itemStateChanged(ItemEvent ie) {
			
			JCheckBox cb = (JCheckBox) ie.getItem();
			if (ie.getStateChange() == ItemEvent.SELECTED){
				//System.out.println(cb.getText() + " selected.");
				cb.setEnabled(false);
			}
		}
	}
	
	/**
	 * 
	 * This class is the action listener for the Go! button, which will execute the query and clear the checkboxes
	 *
	 */
	class RunButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			for (JCheckBox obj: checkBoxes)
			{
				obj.setSelected(false);
				obj.setEnabled(true);
			}
			dropMenu.setEnabled(true);
			frame.validate();
		}
	}
	
	/**
	 * 
	 * This class is the action listener for the position radio buttons
	 *
	 */
	class RadioListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (e.getActionCommand() == qbString)
			{
				//reset query to prevent repeating positions/stats
				String temp = "select * from player where position.position_id = ";
				int resetLength = temp.length();
				baseQuery = baseQuery.substring(0, resetLength);
				
				//reset check boxes
				for (JCheckBox obj: checkBoxes)
				{
					obj.setSelected(false);
					obj.setEnabled(true);
				}
				
				//reset stat box
				statBox.removeAll();
				frame.validate();
				
				//create QB stat box
				statBox.setBorder(BorderFactory.createTitledBorder("QB Stats"));
				statBox.add(gamesPlayed);
				statBox.add(passYards);
				statBox.add(rushYards);
				statBox.add(totalYards);
				statBox.add(passTDs);
				statBox.add(rushTDs);
				statBox.add(tdTotal);
				statBox.add(completions);
				statBox.add(passAttempts);
				statBox.add(compRate);
				statBox.add(intThrown);
				statBox.add(fantasyPoints);
				statBox.add(ffppg);
				statBox.add(rating);
				
				//add position_id to query
				if (!baseQuery.contains(e.getActionCommand())){
					baseQuery += e.getActionCommand();
				}
				
				System.out.println(baseQuery);
				
				//update frame
				frame.validate();
			}
				
			if (e.getActionCommand() == wrString){
				
				//reset query to prevent repeating positions/stats
				String temp = "select * from player where position.position_id = ";
				int resetLength = temp.length();
				baseQuery = baseQuery.substring(0, resetLength);
				
				//reset check boxes
				for (JCheckBox obj: checkBoxes)
				{
					obj.setSelected(false);
					obj.setEnabled(true);
				}
				
				//reset stat box
				statBox.removeAll();
				frame.validate();
				
				//create WR stat box
				statBox.setBorder(BorderFactory.createTitledBorder("WR Stats"));
				statBox.add(gamesPlayed);
				statBox.add(receptions);
				statBox.add(targets);
				statBox.add(recYards);
				statBox.add(totalYards);
				statBox.add(tdTotal);
				statBox.add(turnovers);
				statBox.add(catchRate);
				statBox.add(fantasyPoints);
				statBox.add(ffppg);
				statBox.add(rating);
				
				//add position_id to query
				if (!baseQuery.contains(e.getActionCommand())){
					baseQuery += e.getActionCommand();
				}
				
				System.out.println(baseQuery);
				
				//update statbox
				frame.validate();
			}
			
			if (e.getActionCommand() == dString){
				//reset query to prevent repeating positions/stats
				String temp = "select * from player where position.position_id = ";
				int resetLength = temp.length();
				baseQuery = baseQuery.substring(0, resetLength);
				
				//reset check boxes
				for (JCheckBox obj: checkBoxes)
				{
					obj.setSelected(false);
					obj.setEnabled(true);
				}
				
				//reset stat box
				statBox.removeAll();
				frame.validate();
				
				//create D/ST stat box
				statBox.setBorder(BorderFactory.createTitledBorder("D/ST Stats"));
				statBox.add(sacks);
				statBox.add(fumblesRecovered);
				statBox.add(interceptions);
				statBox.add(dTD);
				statBox.add(PA);
				statBox.add(passYardsPG);
				statBox.add(rushYardsPG);
				statBox.add(safety);
				statBox.add(kickReturns);
				statBox.add(turnoversForced);
				statBox.add(yardsAllowedPG);
				statBox.add(fantasyPoints);
				statBox.add(ffppg);
				statBox.add(rating);
				
				//add position_id to query
				if (!baseQuery.contains(e.getActionCommand())){
					baseQuery += e.getActionCommand();
				}
				
				System.out.println(baseQuery);
				
				//update statbox
				frame.validate();
			}
			
			if (e.getActionCommand() == kString){
				//reset query to prevent repeating positions/stats
				String temp = "select * from player where position.position_id = ";
				int resetLength = temp.length();
				baseQuery = baseQuery.substring(0, resetLength);
				
				//reset check boxes
				for (JCheckBox obj: checkBoxes)
				{
					obj.setSelected(false);
					obj.setEnabled(true);
				}
				
				//reset stat box
				statBox.removeAll();
				frame.validate();
				
				//create K stat box
				statBox.setBorder(BorderFactory.createTitledBorder("K Stats"));
				statBox.add(gamesPlayed);
				statBox.add(EPR);
				statBox.add(fgRate);
				statBox.add(fantasyPoints);
				statBox.add(ffppg);
				statBox.add(rating);
				
				//add position_id to query
				if (!baseQuery.contains(e.getActionCommand())){
					baseQuery += e.getActionCommand();
				}
				
				System.out.println(baseQuery);
				
				//update statbox
				frame.validate();
			}
			
			if (e.getActionCommand() == teString){
				//reset query to prevent repeating positions/stats
				String temp = "select * from player where position.position_id = ";
				int resetLength = temp.length();
				baseQuery = baseQuery.substring(0, resetLength);
				
				//reset check boxes
				for (JCheckBox obj: checkBoxes)
				{
					obj.setSelected(false);
					obj.setEnabled(true);
				}
				
				//reset stat box
				statBox.removeAll();
				frame.validate();
				
				//create TE stat box
				statBox.setBorder(BorderFactory.createTitledBorder("TE Stats"));
				statBox.add(gamesPlayed);
				statBox.add(targets);
				statBox.add(receptions);
				statBox.add(recYards);
				statBox.add(totalYards);
				statBox.add(tdTotal);
				statBox.add(turnovers);
				statBox.add(catchRate);
				statBox.add(fantasyPoints);
				statBox.add(ffppg);
				statBox.add(rating);
				
				//add position_id to query
				if (!baseQuery.contains(e.getActionCommand())){
					baseQuery += e.getActionCommand();
				}
				
				System.out.println(baseQuery);
				
				//update statbox
				frame.validate();
			}
			
			if (e.getActionCommand() == rbString){
				//reset query to prevent repeating positions/stats
				String temp = "select * from player where position.position_id = ";
				int resetLength = temp.length();
				baseQuery = baseQuery.substring(0, resetLength);
				
				//reset check boxes
				for (JCheckBox obj: checkBoxes)
				{
					obj.setSelected(false);
					obj.setEnabled(true);
				}
				
				//reset stat box
				statBox.removeAll();
				frame.validate();
				
				//create RB stat box
				statBox.setBorder(BorderFactory.createTitledBorder("RB Stats"));
				statBox.add(gamesPlayed);
				statBox.add(rushYards);
				statBox.add(rushTDs);
				statBox.add(passYards);
				statBox.add(targets);
				statBox.add(receptions);
				statBox.add(recYards);
				statBox.add(totalYards);
				statBox.add(tdTotal);
				statBox.add(turnovers);
				statBox.add(touches);
				statBox.add(catchRate);
				statBox.add(fantasyPoints);
				statBox.add(ffppg);
				statBox.add(rating);
				
				//add position_id to query
				if (!baseQuery.contains(e.getActionCommand())){
					baseQuery += e.getActionCommand();
				}
				
				System.out.println(baseQuery);
				
				//update statbox
				frame.validate();
			}
		}
	}
	//display window and frame
	public static void main(String args[]){
		frame = new JFrame();
		frame.add(new PositionPicker());
		frame.pack();
		frame.setVisible(true);
		frame.setTitle("Upper Deck: Taking Fantasy Football To The Next Level");
		frame.setResizable(false);
	}
	
	//add a componenet to a Panel 
	private void addItem(JPanel p, JComponent c, int x, int y, int width, int height, int align){
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = x;
		gc.gridy = y;
		gc.gridwidth = width;
		gc.gridheight = height;
		gc.weightx = 100.0;
		gc.weighty = 100.0;
		gc.insets = new Insets(5, 5, 5, 5);
		gc.anchor = align;
		gc.fill = GridBagConstraints.NONE;
		p.add(c, gc);
	}
	
    //Establish a connection with specified database. Return connection object
    private static Connection establish_connection(String database_name, String sql_username, String sql_passwd) 
    {
        Connection conn = null;
        try {
            
            System.out.println("Establishing connection with webdev..");
            conn = DriverManager.getConnection(
            "jdbc:mysql://webdev.cislabs.uncw.edu/"+database_name+"?user="+sql_username+"&password="+sql_passwd);
            
            System.out.println("Connection with webdev.cislabs.uncw.edu established.");
        }
        catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState:     " + e.getSQLState());
            System.out.println("VendorError:  " + e.getErrorCode());
        }
        
        return conn;
    }
    
    //returns true if numeric
    public static boolean isNumeric(String str)
    {
        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
}
