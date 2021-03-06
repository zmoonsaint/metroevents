package com.cit.it.ccs323a.sia.me.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.security.Timestamp;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.cit.it.ccs323a.sia.me.constants.RequestStatus;
import com.cit.it.ccs323a.sia.me.constants.RequestType;
import com.cit.it.ccs323a.sia.me.core.Events;
import com.cit.it.ccs323a.sia.me.core.Request;
import com.cit.it.ccs323a.sia.me.core.User;

public class ViewEvents  extends JPanel
implements ActionListener {

	private String newline = "\n";
	protected static final String txtEventCodeString = "Event code";
	protected static final String txtEventNameString = "Event Name";
	protected static final String txtEventDateString = "Event Date";
	protected static final String txtEventLocationString = "Event Location";
	protected static final String txtEventOrganizerString = "Event Organizer";
	protected static final String buttonString = "JButton";  
	private String eventCode;
	private static User user;
	JFrame frame = new JFrame("Events");
	JTextField txtEventCode;
	JTextField txtEventName;
	JFormattedTextField txtEventDate;
	JTextField txtEventLocation;
	JTextField txtEventOrganizer ;
	JTextArea textArea;
	JButton btnBack;
	JButton btnAddEvent;
	JButton btnUpdateEvent;
	JButton btnDeleteEvent;
	JButton btnJoinEvent;
	ArrayList<Events> eventArr;
	DefaultTableModel tableModel;
	JTable table;
	Object[] rowData;
	String previousCodeName;
	int selectedRow;

	Events events = new Events();
	Request request = new Request();

	String[] columnNames = {"Event Code", 
			"Event Name",
			"Event Date",
			"Event Location",
			"Event Description",
			"Event Organizer",
	"Event Date Added"} ;
	/*Object [][] rowData = {{""event1", "eventN ame", "eve     ntDate", "eve                 ntLocation", "eventDateAdded"},
				{"event2", "eventName", "eventDate", "eventLocation", "eventDateAdded"}};*/

	protected JLabel actionLabel;

	public void placeComponents (User user) {
		this.user = user;
		System.out.println("..........................ViewEvents" + user.getUserID() + " : " + "userType: " + user.getUserType() + "; userName: "+  user.getUserName());

		setLayout(new BorderLayout());

		//Create a regular text field.
		txtEventCode = new JTextField(5);
		txtEventCode.setActionCommand(txtEventCodeString);
		txtEventCode.addActionListener(this);

		//Create a password field.
		txtEventName = new JTextField(10);
		txtEventName.setActionCommand(txtEventNameString);
		txtEventName.addActionListener(this);

		//Create a formatted text field.
		txtEventDate = new JFormattedTextField("dd-MM-yyyy");
		txtEventDate.setActionCommand(txtEventCodeString);
		txtEventDate.addActionListener(this);

		//Create a password field.
		txtEventLocation = new JTextField(10);
		txtEventLocation.setActionCommand(txtEventLocationString);
		txtEventLocation.addActionListener(this);

		//Create a password field.
		txtEventOrganizer = new JTextField(10);
		txtEventOrganizer.enable(false);
		txtEventOrganizer.setActionCommand(txtEventOrganizerString);
		txtEventOrganizer.addActionListener(this);

		//Create some labels for the fields.
		JLabel txtEventCodeLabel = new JLabel(txtEventCodeString + ": ");
		txtEventCodeLabel.setLabelFor(txtEventCode);
		JLabel txtEventNameLabel = new JLabel(txtEventNameString + ": ");
		txtEventNameLabel.setLabelFor(txtEventName);
		JLabel txtEventDateLabel = new JLabel(txtEventDateString + ": ");
		txtEventDateLabel.setLabelFor(txtEventDate);
		JLabel txtEventLocationLabel = new JLabel(txtEventLocationString + ": ");
		txtEventDateLabel.setLabelFor(txtEventDate);
		JLabel txtEventOrganizerLabel = new JLabel(txtEventOrganizerString + ": ");
		txtEventDateLabel.setLabelFor(txtEventDate);
		btnBack = new JButton("Back");
		btnBack.addActionListener(this);



		//Lay out the text controls and the labels.
		JPanel textControlsPane = new JPanel();
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();

		textControlsPane.setLayout(gridbag);

		JLabel[] labels = {txtEventCodeLabel, txtEventNameLabel, txtEventDateLabel, txtEventLocationLabel, txtEventOrganizerLabel};
		JTextField[] txtEventCodes = {txtEventCode, txtEventName, txtEventDate, txtEventLocation, txtEventOrganizer};
		addLabelTextRows(labels, txtEventCodes, gridbag, textControlsPane);

		c.gridwidth = GridBagConstraints.REMAINDER; //last
		c.anchor = GridBagConstraints.WEST;
		c.weightx = 1.0;

		/*        if (user.getUserType().equals("administrator") || user.getUserType().equals("organizer") ) {
	        	btnAddEvent.setEnabled(true);
	        	btnUpdateEvent.setEnabled(true);
	        	btnDeleteEvent.setEnabled(true);

	        } else {
	        	btnJoinEvent = new JButton("Join Event");
	        	btnJoinEvent.setEnabled(true);
	        }
		 */

		if (user.getUserType().equals("administrator") || user.getUserType().equals("organizer") ) {
			btnAddEvent = new JButton("Add Event");
			btnAddEvent.setEnabled(true);
			btnUpdateEvent = new JButton("Update Event");
			//btnUpdateEvent.setEnabled(false);
			btnDeleteEvent = new JButton("Delete Event");
			//btnDeleteEvent.setEnabled(false);
			textControlsPane.add(btnAddEvent);
			textControlsPane.add(btnUpdateEvent);
			textControlsPane.add(btnDeleteEvent);
			btnAddEvent.addActionListener(this);
			btnUpdateEvent.addActionListener(this);
			btnDeleteEvent.addActionListener(this);

		} else {
			txtEventCode.setEnabled(false);
			btnJoinEvent = new JButton("Join Event");
			btnJoinEvent.setEnabled(true);
			textControlsPane.add(btnJoinEvent);
			btnJoinEvent.addActionListener(this);
		}
		textControlsPane.add(btnBack);
		textControlsPane.setBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createTitledBorder("Event Data"),
						BorderFactory.createEmptyBorder(5,5,5,5)));

		//Create a text area.
		textArea = new JTextArea("");
		textArea.setFont(new Font("Serif", Font.ITALIC, 16));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		JScrollPane areaScrollPane = new JScrollPane(textArea);
		areaScrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		areaScrollPane.setPreferredSize(new Dimension(250, 250));
		areaScrollPane.setBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createCompoundBorder(
								BorderFactory.createTitledBorder("Event Description"),
								BorderFactory.createEmptyBorder(5,5,5,5)),
						areaScrollPane.getBorder()));

		//Create an editor pane.



		setTableData();
		table = new JTable(tableModel);
		table.setPreferredSize(new Dimension (750,500));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		//JEditorPane editorPane = createEditorPane();
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {


				int row = table.rowAtPoint(evt.getPoint());
				int col = table.columnAtPoint(evt.getPoint());		       
				eventCode = (String) table.getValueAt(row, 0);
				previousCodeName = eventCode;
				selectedRow = row;
				String eventName = (String) table.getValueAt(row, 1);
				Date eventDate =  (Date) table.getValueAt(row, 2);
				String eventLocation = (String) table.getValueAt(row, 3);
				String eventDescription = (String) table.getValueAt(row, 4);
				String eventOrganizer = (String) table.getValueAt(row, 5);
				System.out.println(eventCode);

				txtEventCode.setText(eventCode);
				txtEventName.setText(eventName);
				txtEventDate.setText(String.valueOf(eventDate));
				txtEventLocation.setText(eventLocation);
				textArea.setText(eventDescription);

				try {
					if(user.alreadyJoinEvent(user.getUserID(), eventCode)) {
						btnJoinEvent.setEnabled(false);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


				System.out.println("EventCode: "+ eventCode + ";" + "Organizer : "+ eventOrganizer + "User get Data: " + user.getUserData(eventOrganizer));
				txtEventOrganizer.setText(eventOrganizer);


			}});




		JScrollPane editorScrollPane = new JScrollPane(table);
		editorScrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		editorScrollPane.setHorizontalScrollBarPolicy(
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		editorScrollPane.setPreferredSize(new Dimension(750, 500));
		editorScrollPane.setMinimumSize(new Dimension(500, 500));

		//Create a text pane.
		/*
	        JTextPane textPane = createTextPane();
	        JScrollPane paneScrollPane = new JScrollPane(textPane);
	        paneScrollPane.setVerticalScrollBarPolicy(
	                        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	        paneScrollPane.setPreferredSize(new Dimension(750, 155));
	        paneScrollPane.setMinimumSize(new Dimension(10, 10));

	        //Put the editor pane and the text pane in a split pane.
	        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
	                                              editorScrollPane,
	                                              paneScrollPane);
	        splitPane.setOneTouchExpandable(true);
	        splitPane.setResizeWeight(0.5);*/
		JPanel rightPane = new JPanel(new GridLayout(1,0));
		rightPane.add(editorScrollPane);
		rightPane.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Events"),
				BorderFactory.createEmptyBorder(5,5,5,5)));


		//Put everything together.

		JPanel leftPane = new JPanel(new BorderLayout());
		leftPane.setPreferredSize(new Dimension(450, 200));

		leftPane.add(textControlsPane, 
				BorderLayout.PAGE_START);
		leftPane.add(areaScrollPane,
				BorderLayout.CENTER);

		frame.add(rightPane, BorderLayout.LINE_START);
		frame.add(leftPane, BorderLayout.LINE_END);
		//Create and set up the window.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}


	private void addLabelTextRows(JLabel[] labels,
			JTextField[] txtEventCodes,
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
			container.add(txtEventCodes[i], c);
		}
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());

		String eventCode = txtEventCode.getText();
		String eventName = txtEventName.getText();
		String eventDate = txtEventDate.getText();
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date date;
		java.sql.Date sqlStartDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		java.sql.Date txtfieldAsDate = null;
		String eventDescription = textArea.getText();
		String eventLocation = txtEventLocation.getText();
		int eventOrganizer = user.getUserID();


		Events orgEvent = new Events();
		orgEvent.setEventCode(eventCode);
		orgEvent.setEventName(eventName);
		orgEvent.setEventDate((java.sql.Date) txtfieldAsDate);
		orgEvent.setEventLocation(eventLocation);
		orgEvent.setEventOrganizer(eventOrganizer);
		orgEvent.setEventDescription(eventDescription);        	

		//String eventDescription = txtArea.getText();
		if(e.getActionCommand().equals("Add Event")) {
			if(checkEmptyFields()) {
				JOptionPane.showMessageDialog(null, "Please enter data on required fields.", "InfoBox: CREATE EVENT", JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					date = sdf1.parse(eventDate);
					sqlStartDate = new java.sql.Date(date.getTime());    
					orgEvent.setEventDate(sqlStartDate);

					if(orgEvent.verifyEventCodeExist(eventCode)) {
						JOptionPane.showMessageDialog(null, "Event Code already exist! Please input inexisting code.", "InfoBox: CREATE EVENT", JOptionPane.ERROR_MESSAGE);
					} else {
						if(orgEvent.createEvent(orgEvent, user)) {
							previousCodeName = eventCode;
							System.out.println(orgEvent.getEventID(previousCodeName));
							orgEvent.setEventID(orgEvent.getEventID(previousCodeName));
							if(user.getUserType().equals("administrator")) {
								JOptionPane.showMessageDialog(null, "Event "+ eventCode + " was added successfully.", "InfoBox: CREATE EVENT", JOptionPane.INFORMATION_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(null, "Request to create event " + eventCode + " is sent to Admin for approval.\nCheck request status through notification.", "InfoBox: CONFIRMATION", JOptionPane.INFORMATION_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(null, "Something went wrong. Please try again.", "InfoBox: Registration", JOptionPane.ERROR_MESSAGE);
						}
						Date eDate = new java.util.Date();
						Object[] insertRowData = {eventCode, eventName, eventDate, eventLocation, eventDescription, eventOrganizer, eDate};
						tableModel.addRow(insertRowData);

					}
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(null, "Invalid date format. Format should be dd-MM-yyyy", "InfoBox: CREATE EVENT", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}

		} else if (e.getActionCommand().equals("Update Event")) {

			System.out.println("Previous Code Name: " + previousCodeName);
			System.out.println(orgEvent.getEventID(previousCodeName));
			orgEvent.setEventID(orgEvent.getEventID(previousCodeName));


			if(checkEmptyFields()) {
				JOptionPane.showMessageDialog(null, "Please enter data on required fields.", "InfoBox: CREATE EVENT", JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					date = sdf1.parse(eventDate);
					sqlStartDate = new java.sql.Date(date.getTime());    
					orgEvent.setEventDate(sqlStartDate);

					if(orgEvent.verifyEventCodeExist(eventCode) &&  !eventCode.toLowerCase().equals(previousCodeName)) {
						JOptionPane.showMessageDialog(null, "Event Code already exist! Please input inexisting code.", "InfoBox: CREATE EVENT", JOptionPane.ERROR_MESSAGE);
					} else {
						if(orgEvent.updateEvent(orgEvent, user)) {
							setTableData();
							JOptionPane.showMessageDialog(null, "Updated the event successfully.", "InfoBox: CONFIRMATION", JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "Something went wrong. Please try again.", "InfoBox: Registration", JOptionPane.ERROR_MESSAGE);
						}
						Date eDate = new java.util.Date();
						Object[] insertRowData = {eventCode, eventName, eventDate, eventLocation, eventDescription, eventOrganizer, eDate};
						tableModel.addRow(insertRowData);
					}
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(null, "Invalid date format. Format should be dd-MM-yyyy", "InfoBox: CREATE EVENT", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}

			}


		} else if (e.getActionCommand().equals("Delete Event")) {
			if(JOptionPane.showConfirmDialog(null, "Do you want to delete event " + eventCode + "?", "CONFIRMATION", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				System.out.println("Confirm request to delete event..............");
				orgEvent.deleteEvent(eventCode);
				request.updateRequestStatus(orgEvent.getEventCodeRequestID(eventCode), 4);
				tableModel.removeRow(selectedRow);
				JOptionPane.showMessageDialog(null, "Event: "+ eventCode + " was successfully deleted.", "InfoBox: CONFIRMATION", JOptionPane.INFORMATION_MESSAGE);
			} 

		} else if (e.getActionCommand().equals("Join Event")) {
			System.out.println("Confirm join the event..............");
			JOptionPane.showMessageDialog(null, "Request to joint event is sent to Organizer or Admin for approval.\nCheck request status through notification.", "InfoBox: CONFIRMATION", JOptionPane.INFORMATION_MESSAGE);
			if(!eventCode.equals("")) {
				try {
					user.userJoinEvent(user.getUserID(), eventCode);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		} else if (e.getActionCommand().equals("Back")) {
			closeViewEvents();
			if(user.getUserType().equals("administrator")|| user.getUserType().equals("organizer")) {
				System.out.println("User Type................ " + user.getUserType());
				Main m= new Main(user);
				m.show(true);

			} else {
				MainUser m= new MainUser(user);
				m.show(true);

			}
		} else  {
			System.out.println(e.getSource());
		}
	}

	public boolean checkEmptyFields() {
		if(txtEventCode.getText().equals("") || txtEventName.getText().equals("") 
				|| txtEventDate.getText().equals("") || txtEventLocation.getText().equals("") 
				|| txtEventName.getText().equals("MM/dd/yyyy")) 
			return true;

		return false;
	}

	public void setTableData() {

		System.out.print("setTableData inside");

		switch (user.getUserType().toLowerCase()) {
		case "administrator":
			System.out.println("administrator");
			eventArr = events.getAllUserEvents(user);
			break;
		case "organizer":
			eventArr = events.getAllEventsByOrganizer(user.getUserID());
			break;
		default:
			eventArr = events.getAllEventsByStatus(2);
			break;
		}

		tableModel = new DefaultTableModel(columnNames, 0);

		for(int i = 0; i < eventArr.size(); i++) {
			String eventCode = eventArr.get(i).getEventCode();
			String eventName = eventArr.get(i).getEventName();
			Date eventDate = eventArr.get(i).getEventDate();
			String eventLocation = eventArr.get(i).getEventLocation();
			String eventDescription = eventArr.get(i).getEventDescription();

			User tempUser = user.getUserData(eventArr.get(i).getEventOrganizer());
			String eventOrganizer = tempUser.getUserFullName();

			Date eventDateAdded = eventArr.get(i).getEventDateAdd();

			Object[] rowData = {eventCode, eventName, eventDate, eventLocation, eventDescription, eventOrganizer, eventDateAdded};
			tableModel.addRow(rowData);

		}	        

	}
	
	public void closeViewEvents() {
		System.out.println("Call Close");
		this.frame.setVisible(false);
	}

	public void createAndShowGUI(User user) {
		placeComponents(user);
	}


}
