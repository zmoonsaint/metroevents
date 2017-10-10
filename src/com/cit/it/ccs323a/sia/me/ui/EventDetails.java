package com.cit.it.ccs323a.sia.me.ui;

import javax.swing.*;
import javax.swing.text.*;

import com.cit.it.ccs323a.sia.me.core.Events;
import com.cit.it.ccs323a.sia.me.core.User;

import java.awt.*;              //for layout managers and more
import java.awt.event.*;        //for action events
 
import java.net.URL;
import java.sql.SQLException;
import java.io.IOException;
 
public class EventDetails extends JPanel
                             implements ActionListener {
    JFrame frame = new JFrame("Event Detail");
    JButton btnJoin = new JButton(btnJoinString);
    User user;
	String eventCode;
	Events event = new Events();
	
    private String newline = "\n";
    protected static final String eventIDString = "Event ID";
    protected static final String eventCodeString = "Event Code";
    protected static final String eventNameString = "Name of Event";
    protected static final String eventDateString = "Date of Event";
    protected static final String eventOrganizerString = "Organizer";
    protected static final String eventLocationString = "Location";
    protected static final String eventDateAddString = "Date Added";
    
    protected static final String btnOkString = "OK";
    protected static final String btnJoinString = "Join Event";
 
    protected JLabel actionLabel;

 
    public void placeComponents(User user, String eventCode ) {
    	
    	this.user = user;
    	this.eventCode = eventCode;
    	
        setLayout(new BorderLayout());
 
        //Create a regular text field.
        JTextField eventIDText = new JTextField(10);
        JTextField eventCodeText = new JTextField(10);
        JTextField eventNameText = new JTextField(10);
        JTextField eventDateText = new JTextField(10);
        JTextField eventOrganizerText = new JTextField(10);
        JTextField eventLocationText = new JTextField(10);
        JTextField eventDateAddText = new JTextField(10);


        //Create some labels for the fields.
        JLabel eventIDLabel = new JLabel(eventIDString + ": ");
        JLabel eventCodeLabel = new JLabel(eventCodeString + ": ");
        JLabel eventNameLabel = new JLabel(eventNameString + ": ");
        JLabel eventDateLabel = new JLabel(eventDateString + ": ");
        JLabel eventOrganizerLabel = new JLabel(eventOrganizerString + ": ");
        JLabel eventLocationLabel = new JLabel(eventLocationString + ": ");
        JLabel eventDateAddLabel = new JLabel(eventDateAddString + ": ");

 
        //Create a label to put messages during an action event.
        actionLabel = new JLabel("Type text in a field and press Enter.");
        actionLabel.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
        
        
        JButton btnOk = new JButton(btnOkString);
        btnJoin.setPreferredSize(new Dimension(100, 50));
        btnOk.setPreferredSize(new Dimension(100, 50));

 
        btnJoin.addActionListener(this);
        btnOk.addActionListener(this);
        
    	try {
			if(user.alreadyJoinEvent(user.getUserID(), eventCode)) {
				btnJoin.setText("Joined");
				btnJoin.setEnabled(false);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        //Lay out the text controls and the labels.
        JPanel textControlsPane = new JPanel();
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
 
        textControlsPane.setLayout(gridbag);
 
        JLabel[] labels = {eventIDLabel, eventCodeLabel, eventNameLabel,eventDateLabel, eventOrganizerLabel,eventLocationLabel , eventDateAddLabel};
        JTextField[] textFields = {eventIDText, eventCodeText, eventNameText, eventDateText, eventOrganizerText, eventLocationText, eventDateAddText};
        addLabelTextRows(labels, textFields, gridbag, textControlsPane);
 
        c.gridwidth = GridBagConstraints.REMAINDER; //last
        c.anchor = GridBagConstraints.WEST;
        c.weightx = 1.0;
        textControlsPane.add(btnJoin);
        textControlsPane.add(btnOk);
        textControlsPane.setBorder(
                BorderFactory.createCompoundBorder(
                                BorderFactory.createTitledBorder("Text Fields"),
                                BorderFactory.createEmptyBorder(5,5,5,5)));
 
        //Create a text area.
        JTextArea eventDescriptionText = new JTextArea();
        eventDescriptionText.setFont(new Font("Serif", Font.ITALIC, 16));
        eventDescriptionText.setLineWrap(true);
        eventDescriptionText.setWrapStyleWord(true);
        JScrollPane areaScrollPane = new JScrollPane(eventDescriptionText);
        areaScrollPane.setVerticalScrollBarPolicy(
                        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(350, 150));
        areaScrollPane.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createCompoundBorder(
                                BorderFactory.createTitledBorder("Event Description"),
                                BorderFactory.createEmptyBorder(5,5,5,5)),
                areaScrollPane.getBorder()));
 
        //Put everything together.
        JPanel leftPane = new JPanel(new BorderLayout());
        leftPane.add(textControlsPane, 
                     BorderLayout.PAGE_START);
        leftPane.add(areaScrollPane,
                     BorderLayout.CENTER);
        
        
        //setting up text
        
    	event = event.getEventDetails(eventCode);
    	int eventID = event.getEventID();
    	String eventName = event.getEventName();
    	String eventDate = String.valueOf(event.getEventDate());
    	int eventOrganizer = event.getEventOrganizer();
    	String eventLocation = event.getEventLocation();
    	String eventDateAdd = String.valueOf(event.getEventDateAdd());
    	String eventDescription = String.valueOf(event.getEventDescription());
    	String eventOrganizerName = user.getUserData(eventOrganizer).getUserFullName();
    	

    	
        eventIDText.setText(String.valueOf(eventID));
        eventCodeText.setText(eventCode);
        eventNameText.setText(eventName);
        eventDateText.setText(eventDate);
        eventOrganizerText.setText(eventOrganizerName);
        eventLocationText.setText(eventLocation);
        eventDateAddText.setText(String.valueOf(eventDateAdd));
        eventDescriptionText.setText(eventDescription);
        
        frame.add(leftPane, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500,600));
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
     }
 
    private void addLabelTextRows(JLabel[] labels,
                                  JTextField[] textFields,
                                  GridBagLayout gridbag,
                                  Container container) {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.EAST;
        int numLabels = labels.length;
 
        for (int i = 0; i < numLabels; i++) {
            c.gridwidth = GridBagConstraints.RELATIVE; //next-to-last
            c.fill = GridBagConstraints.NONE;      //reset to default
            c.weightx = 0.0;                       //reset to default
            container.add(labels[i], c);
 
            c.gridwidth = GridBagConstraints.REMAINDER;     //end row
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 1.0;
            container.add(textFields[i], c);
        }
    }
 

 
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(btnOkString)) {
			System.out.println("OK button Pressed.");
			closeRequestDetail();
			
		} else if (e.getActionCommand().equals(btnJoinString)) {
			System.out.println("Join button Pressed.");
			if(!eventCode.equals("")) {
				try {
					user.userJoinEvent(user.getUserID(), eventCode);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
		
	}
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    public void createAndShowGUI(User user, String eventCode) {
        //Create and set up the window.
    	System.out.println("createAndShowGUI -> Event Details " + user.getUserID() + " , " + eventCode);
		placeComponents(user, eventCode);

    }
    
	public void closeRequestDetail() {
		System.out.println("Call Close");
		this.frame.setVisible(false);
	}
 
/*    public static void main(String[] args) {
        //Schedule a job for the event dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                 //Turn off metal's use of bold fonts
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        createAndShowGUI();
            }
        });
    }
*/

}