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
import com.cit.it.ccs323a.sia.me.core.Request;
import com.cit.it.ccs323a.sia.me.core.Events;
import com.cit.it.ccs323a.sia.me.core.User;

public class ViewNotifications  extends JPanel
implements ActionListener {

	private String newline = "\n";
	protected static final String txtRequestIDString = "Request ID";
	protected static final String txtRequestorString = "Requestor";
	protected static final String txtRequestTypeString = "Request Type";
	protected static final String txtEventString = "Event";
	protected static final String txtStatusString = "Status";
	private static User user;
	
	JFrame frame = new JFrame("Notifications");
	JTextField txtRequestID;
	JTextField txtRequestor;
	JTextField txtRequestType;
	JTextField txtEvent;
	JTextField txtStatus ;
	JButton btnBack;
	JButton btnApprove;
	JButton btnDecline;
	JButton btnCancel;

	ArrayList<Request> eventArr;
	DefaultTableModel tableModel;
	
	JTable table;
	Object[] rowData;
	int selectedRow;

	Request request = new Request();


	String[] columnNames = {"Request ID", 
			"Requestor",
			"Request Type",
			"Event",
			"Status"} ;

	protected JLabel actionLabel;

	public ViewNotifications(User user) {
		this.user = user;
		System.out.println("..........................ViewNotification" + user.getUserID() + " : " + "userType: " + user.getUserType() + "; userName: "+  user.getUserName());

		setLayout(new BorderLayout());

		//Create a regular text field.
		txtRequestID = new JTextField(5);
		txtRequestID.setActionCommand(txtRequestIDString);
		txtRequestID.addActionListener(this);

		//Create a password field.
		txtRequestor = new JTextField(10);
		txtRequestor.setActionCommand(txtRequestorString);
		txtRequestor.addActionListener(this);

		//Create a formatted text field.
		txtRequestType = new JTextField(10);
		txtRequestType.setActionCommand(txtRequestIDString);
		txtRequestType.addActionListener(this);

		//Create a password field.
		txtEvent = new JTextField(10);
		txtEvent.setActionCommand(txtEventString);
		txtEvent.addActionListener(this);

		//Create a password field.
		txtStatus = new JTextField(10);
		txtStatus.enable(false);
		txtStatus.setActionCommand(txtStatusString);
		txtStatus.addActionListener(this);

		//Create some labels for the fields.
		JLabel txtRequestIDLabel = new JLabel(txtRequestIDString + ": ");
		txtRequestIDLabel.setLabelFor(txtRequestID);
		JLabel txtRequestorLabel = new JLabel(txtRequestorString + ": ");
		txtRequestorLabel.setLabelFor(txtRequestor);
		JLabel txtRequestTypeLabel = new JLabel(txtRequestTypeString + ": ");
		txtRequestTypeLabel.setLabelFor(txtRequestType);
		JLabel txtEventLabel = new JLabel(txtEventString + ": ");
		txtRequestTypeLabel.setLabelFor(txtRequestType);
		JLabel txtStatusLabel = new JLabel(txtStatusString + ": ");
		txtRequestTypeLabel.setLabelFor(txtRequestType);
		btnBack = new JButton("Back");
		btnBack.addActionListener(this);

		//Lay out the text controls and the labels.
		JPanel textControlsPane = new JPanel();
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();

		textControlsPane.setLayout(gridbag);

		JLabel[] labels = {txtRequestIDLabel, txtRequestorLabel, txtRequestTypeLabel, txtEventLabel, txtStatusLabel};
		JTextField[] txtRequestIDs = {txtRequestID, txtRequestor, txtRequestType, txtEvent, txtStatus};
		addLabelTextRows(labels, txtRequestIDs, gridbag, textControlsPane);

		c.gridwidth = GridBagConstraints.REMAINDER; //last
		c.anchor = GridBagConstraints.WEST;
		c.weightx = 1.0;

		if (user.getUserType().equals("administrator") || user.getUserType().equals("organizer") ) {
			btnApprove = new JButton("Approve Request");
			btnApprove.setEnabled(true);
			btnDecline = new JButton("Decline Request");
			//btnDecline.setEnabled(false);
			//btnCancel = new JButton("Cancel Event");
			//btnCancel.setEnabled(false);
			textControlsPane.add(btnApprove);
			textControlsPane.add(btnDecline);
			//textControlsPane.add(btnCancel);
			btnApprove.addActionListener(this);
			btnDecline.addActionListener(this);
			//btnCancel.addActionListener(this);

		}
		
		textControlsPane.add(btnBack);
		textControlsPane.setBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createTitledBorder("Request Data"),
						BorderFactory.createEmptyBorder(5,5,5,5)));

		//Create a text area.
		JTextArea textArea = new JTextArea("");
		textArea.setFont(new Font("Serif", Font.ITALIC, 16));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setVisible(false);
		JScrollPane areaScrollPane = new JScrollPane(textArea);
		areaScrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		areaScrollPane.setPreferredSize(new Dimension(250, 250));
		areaScrollPane.setBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createCompoundBorder(
								BorderFactory.createTitledBorder("Cancel Reasons"),
								BorderFactory.createEmptyBorder(5,5,5,5)),
						areaScrollPane.getBorder()));

		//Create an editor pane.



		setTableData();
		table = new JTable(tableModel);
		table.setPreferredSize(new Dimension (300,500));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		//JEditorPane editorPane = createEditorPane();
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {


				int row = table.rowAtPoint(evt.getPoint());
				int col = table.columnAtPoint(evt.getPoint());		       
				selectedRow = row;
				
				
				String requestID = table.getValueAt(row, 0).toString();
				String requestor = table.getValueAt(row, 1).toString();
				String requestType = table.getValueAt(row, 2).toString();
				String eventCode =  table.getValueAt(row, 3).toString();
				String status = table.getValueAt(row, 4).toString();
				System.out.println(requestID + requestor + requestType +  eventCode + status );
				
				txtRequestID.setText(requestID);
				txtRequestor.setText(requestor);
				txtRequestType.setText(requestType);
				txtEvent.setText(eventCode);
				txtStatus.setText(status);
		

			}});




		JScrollPane editorScrollPane = new JScrollPane(table);
		editorScrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		editorScrollPane.setHorizontalScrollBarPolicy(
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		editorScrollPane.setPreferredSize(new Dimension(750, 500));
		editorScrollPane.setMinimumSize(new Dimension(750, 500));

		JPanel rightPane = new JPanel(new GridLayout(1,0));
		rightPane.add(editorScrollPane);
		rightPane.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Requests Status Notification"),
				BorderFactory.createEmptyBorder(5,5,5,5)));


		//Put everything together.

		JPanel leftPane = new JPanel(new BorderLayout());
		leftPane.setPreferredSize(new Dimension(750, 500));

		leftPane.add(textControlsPane, 
				BorderLayout.PAGE_START);
		leftPane.add(areaScrollPane,
				BorderLayout.CENTER);

		add(rightPane, BorderLayout.LINE_START);
		add(leftPane, BorderLayout.LINE_END);
	}


	private void addLabelTextRows(JLabel[] labels,
			JTextField[] txtRequestIDs,
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
			container.add(txtRequestIDs[i], c);
		}
	}

	
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());

		//String eventDescription = txtArea.getText();
		if(e.getActionCommand().equals("Approve Request")) {
			int requestID = Integer.valueOf(txtRequestID.getText());
			String requestor = txtRequestor.getText();
			System.out.println(txtRequestType.getText());
			int requestType = request.getRequestTypeID(txtRequestType.getText());
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			java.util.Date txtfieldAsDate = null;
			//String eventDescription = textArea.getText();
			String eventLocation = txtEvent.getText();
			int eventOrganizer = user.getUserID();

			request.processRequest(requestID, 2);
			if(requestType == 1 || requestType == 4 || requestType == 5 ) {
				user.setUserAccountType(requestor, requestType);
	        	tableModel.removeRow(selectedRow);
			}
		} else if (e.getActionCommand().equals("Decline Request")) {
			int requestID = Integer.valueOf(txtRequestID.getText());
			String requestor = txtRequestor.getText();
			System.out.println(txtRequestType.getText());
			int requestType = request.getRequestTypeID(txtRequestType.getText());
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			java.util.Date txtfieldAsDate = null;
			//String eventDescription = textArea.getText();
			String eventLocation = txtEvent.getText();
			int eventOrganizer = user.getUserID();

			request.processRequest(requestID, 3);
			System.out.println("Decline");
        	tableModel.removeRow(selectedRow);

		} /*else if (e.getActionCommand().equals("Cancel Event")) {
			if(JOptionPane.showConfirmDialog(null, "Do you want to cancel event " + eventCode + "?", "CONFIRMATION", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				System.out.println("Confirm request to delete event..............");
				//orgEvent.deleteEvent(eventCode);
				tableModel.removeRow(selectedRow);
				JOptionPane.showMessageDialog(null, "Event: "+ eventCode + " was successfully deleted.", "InfoBox: CONFIRMATION", JOptionPane.INFORMATION_MESSAGE);
			} 

		} */else if (e.getActionCommand().equals("Join Event")) {
			System.out.println("Confirm join the event..............");
			JOptionPane.showMessageDialog(null, "Request to joint event is sent to Organizer for approval.\nCheck request status through notification.", "InfoBox: CONFIRMATION", JOptionPane.INFORMATION_MESSAGE);
			// code to join event here.
		} else if (e.getActionCommand().equals("Back")) {
			frame.setVisible(false);
			frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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
		if(txtRequestID.getText().equals("") || txtRequestor.getText().equals("") 
				|| txtRequestType.getText().equals("") || txtEvent.getText().equals("") 
				|| txtRequestor.getText().equals("MM/dd/yyyy")) 
			return true;

		return false;
	}

	public void setTableData() {

		System.out.print("setTableData inside");

		switch (user.getUserType().toLowerCase()) {
		case "administrator":
			System.out.println("administrator");
			eventArr = request.getAllUserRequestsByStatus(1);
			break;
		case "organizer":
			//eventArr = request.getAllUserRequests(user);
			eventArr = request.getAllUserRequestsForOrganizer(user.getUserID());
			break;
		default:
			eventArr = request.getAllUserRequests(user);
			break;
		}

		tableModel = new DefaultTableModel(columnNames, 0);

		for(int i = 0; i < eventArr.size(); i++) {
		
			int requestID = eventArr.get(i).getRequestID();
			int requestorID = eventArr.get(i).getUserID();
			
			Request objRequest = new Request();
			objRequest.getRequestType(requestID);
			String RequestType = objRequest.getRequestType(eventArr.get(i).getRequestTypeID());


			
			
			String event = "";
			if(eventArr.get(i).getRequestTypeID() == 2 || eventArr.get(i).getRequestTypeID() == 3 ) {
				Events objEvent = new Events();
				event  = objEvent.getRequestedEventCode(requestID);
				System.out.println("Event............." + eventArr.get(i).getRequestTypeID() + event );
			}
			
			System.out.println(eventArr.get(i).getRequestStatusID());
			int stat = eventArr.get(i).getRequestStatusID();
			String status = objRequest.getStatusString(stat);
			/*switch (stat) {
			case 1:
				status = "PENDING"; 
				System.out.println(status);break;
			case 2:
				status = "APPROVED"; break;
			case 3:
				status = "DECLINED"; break;
			case 4:
				status = "CANCELLED"; break;
			default: 
				status = "";
			}*/

			User tempUser = user.getUserData(requestorID);
			String Requestor = tempUser.getUserFullName();
    		

			Object[] rowData = {requestID, Requestor, RequestType, event, status};
			tableModel.addRow(rowData);

		}	        

	}
	
	

	public void createAndShowGUI(boolean open) {
		//Create and set up the window.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Add content to the window.
		frame.add(new ViewNotifications(user));

		//Display the window.
		frame.pack();
		frame.setVisible(open);
	}

}
