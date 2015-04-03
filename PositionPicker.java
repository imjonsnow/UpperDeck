import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PositionPicker extends JPanel{
	static JFrame frame;
	static String qbString = "QB";
	static String rbString = "RB";
	static String teString = "TE";
	static String kString = "K";
	static String dString = "D/ST";
	static String wrString = "WR";
	static JPanel panel = new JPanel();
	
	//initiate checkboxes
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
	static JCheckBox FGM;
	static JCheckBox FGA;
	static JCheckBox EPM;
	static JCheckBox EPA;
	static JCheckBox fgRate;
	//D/ST stats
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
	static Box positionBox = Box.createVerticalBox();
	static Box statBox = Box.createVerticalBox();
	
	static JButton goButton;

	
	public PositionPicker(){
		
		super(true);
		
		panel.setPreferredSize(new Dimension(700, 610));
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
		FGM = new JCheckBox("Field Goals Made");
		FGA = new JCheckBox("Field Goals Attempted");
		EPM = new JCheckBox("Extra Points Made");
		EPA = new JCheckBox("Extra Points Attempted");
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
		
		//stuff all boxes into one vertical box, forming left side of interface
		Box container = Box.createVerticalBox();
		container.setBorder(BorderFactory.createTitledBorder("Settings"));
		container.add(positionBox);
		container.add(statBox);
		goButton = new JButton("Go!");
		container.add(goButton);
		container.add(Box.createVerticalStrut(20));
		container.add(Box.createRigidArea(new Dimension(0,300)));
		addItem(panel, container, 0, 0, 0, 0, GridBagConstraints.WEST);
		
		
		
		add(panel);
	}
	class RadioListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (e.getActionCommand() == qbString){
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
				
				//update statbox
				frame.validate();
			}
			if (e.getActionCommand() == wrString){
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
				
				//update statbox
				frame.validate();
			}
			if (e.getActionCommand() == dString){
				
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
				
				//update statbox
				frame.validate();
			}
			if (e.getActionCommand() == kString){
				
				//reset stat box
				statBox.removeAll();
				frame.validate();
				
				//create K stat box
				statBox.setBorder(BorderFactory.createTitledBorder("K Stats"));
				statBox.add(gamesPlayed);
				statBox.add(FGM);
				statBox.add(FGA);
				statBox.add(EPM);
				statBox.add(EPA);
				statBox.add(fgRate);
				statBox.add(fantasyPoints);
				statBox.add(ffppg);
				statBox.add(rating);
				
				//update statbox
				frame.validate();
			}
			if (e.getActionCommand() == teString){
				
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
				
				//update statbox
				frame.validate();
			}
			if (e.getActionCommand() == rbString){
				
				//reset stat box
				statBox.removeAll();
				frame.validate();
				
				//create RB stat box
				statBox.setBorder(BorderFactory.createTitledBorder("RB Stats"));
				statBox.add(rushYards);
				statBox.add(rushTDs);
				statBox.add(passYards);
				statBox.add(gamesPlayed);
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
				//update statbox
				frame.validate();
			}
			
		}
	}
	public static void main(String args[]){
		frame = new JFrame();
		frame.add(new PositionPicker());
		frame.pack();
		frame.setVisible(true);
		frame.setTitle("Upper Deck: Taking Fantasy Football To The Next Level");
		frame.setResizable(false);
	}
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
}

