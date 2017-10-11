package com.cit.it.ccs323a.sia.me.ui;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.cit.it.ccs323a.sia.me.core.Events;
import com.cit.it.ccs323a.sia.me.core.Request;
import com.cit.it.ccs323a.sia.me.core.User;

import java.awt.*;              //for layout managers and more
import java.awt.event.*;        //for action events
import java.util.ArrayList;


public class OrganizerNotification extends JPanel
implements ActionListener {

	


	//Create and set up the window.
	JFrame frame = new JFrame("Organizer Notification");
	int myRequestSelectedRow;
	int userRequestSelectedRow;
	
	DefaultTableModel myRequestsTableModel;
	DefaultTableModel userRequestsTableModel;
	Request request = new Request();
	ArrayList<Request> myRequestData;
	ArrayList<Request> userRequestData;
	User user;

	public OrganizerNotification(User user) {
		this.user = user;
		placeComponents();
	}	
	
	public void placeComponents() {
		setLayout(new BorderLayout());


		String[] myRequests = {"Request ID", 
				"Request Type",
				"Request Status"} ;

		myRequestsTableModel = new DefaultTableModel(myRequests, 0);
		setMyRequestTableData();
		JTable myRequestsTable = new JTable(myRequestsTableModel);
		myRequestsTable.setPreferredSize(new Dimension (300,500));
		myRequestsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		JScrollPane myRequestScrollPane = new JScrollPane(myRequestsTable);
		myRequestScrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		myRequestScrollPane.setPreferredSize(new Dimension(1000, 250));
		myRequestScrollPane.setBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createCompoundBorder(
								BorderFactory.createTitledBorder("My Requests Status"),
								BorderFactory.createEmptyBorder(5,5,5,5)),
						myRequestScrollPane.getBorder()));
		myRequestsTable.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {


				int row = myRequestsTable.rowAtPoint(evt.getPoint());
				int col = myRequestsTable.columnAtPoint(evt.getPoint());
				if(row != -1) {
					myRequestSelectedRow = row;
					int requestID = Integer.valueOf(myRequestsTable.getValueAt(row, 0).toString());
					System.out.println("Will open UserRequestDetails");
					UserRequestDetails urd = new UserRequestDetails(user, requestID);
					urd.createAndShowGUI();
					System.out.println(requestID);

				}
				
			}});
		
		//Create a text area.
		String[] userRequests = {"Request ID", 
				"Requestor",
				"Request Type",
				"Request Status"} ;

		userRequestsTableModel = new DefaultTableModel(userRequests, 0);
		setUserRequestTableData();
		JTable userRequestsTable = new JTable(userRequestsTableModel);
		userRequestsTable.setPreferredSize(new Dimension (300,500));
		userRequestsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		userRequestsTable.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {


				int row = userRequestsTable.rowAtPoint(evt.getPoint());
				int col = userRequestsTable.columnAtPoint(evt.getPoint());		       
				userRequestSelectedRow = row;
				
				if(row != -1) {
					int requestID = Integer.valueOf(userRequestsTable.getValueAt(row, 0).toString());
					System.out.println("Will open RequestDetails");

					RequestDetails rd = new RequestDetails(user, requestID);
					rd.createAndShowGUI();
					System.out.println(requestID);
				}
				
			}});
		
		JScrollPane userRequestScrollPane = new JScrollPane(userRequestsTable);
		userRequestScrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		userRequestScrollPane.setPreferredSize(new Dimension(1000, 250));
		userRequestScrollPane.setBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createCompoundBorder(
								BorderFactory.createTitledBorder("User Requests"),
								BorderFactory.createEmptyBorder(5,5,5,5)),
						userRequestScrollPane.getBorder()));

		//Create an editor pane.
		JButton btnClose = new JButton("Close Notification");
		btnClose.setPreferredSize(new Dimension(250,50));
		JPanel flowPanel = new JPanel(new FlowLayout());
		flowPanel.add(btnClose);
		btnClose.addActionListener(this);


		//Put everything together.
		JPanel leftPane = new JPanel(new BorderLayout());
		leftPane.add(myRequestScrollPane, 
				BorderLayout.PAGE_START);
		leftPane.add(userRequestScrollPane,
				BorderLayout.CENTER);
		leftPane.add(flowPanel, BorderLayout.PAGE_END);

		frame.add(leftPane, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		if(e.getActionCommand().equals("Close Notification")) {
			closeNotificationFrame();
		}
	}
	
	private void setMyRequestTableData() {
		
		myRequestData = request.getAllUserRequests(user);
		
		for(int i = 0; i < myRequestData.size(); i++) {
			
			int requestID = myRequestData.get(i).getRequestID();
			//int requestorID = myRequestData.get(i).getUserID();
			
			Request objRequest = new Request();
			objRequest.getRequestType(requestID);
			String requestType = objRequest.getRequestType(myRequestData.get(i).getRequestTypeID());

			
			String event = "";
			if(myRequestData.get(i).getRequestTypeID() == 2 || myRequestData.get(i).getRequestTypeID() == 3 ) {
				Events objEvent = new Events();
				event  = objEvent.getRequestedEventCode(myRequestData.get(i).getRequestTypeID(), myRequestData.get(i).getRequestID());
				System.out.println("Event............." + myRequestData.get(i).getRequestTypeID() + event );
			}
			
			System.out.println(myRequestData.get(i).getRequestStatusID());
			int stat = myRequestData.get(i).getRequestStatusID();
			String requestStatus = objRequest.getStatusString(stat);
			
			Object[] rowData = {requestID, requestType, requestStatus};
			myRequestsTableModel.addRow(rowData);

		}	        		
		
	}
	
	

		private void setUserRequestTableData() {
			
			userRequestData = request.getAllUserRequestsForOrganizer(user.getUserID());
			
			for(int i = 0; i < userRequestData.size(); i++) {
				
				int requestID = userRequestData.get(i).getRequestID();
				//int requestorID = myRequestData.get(i).getUserID();
				
				Request objRequest = new Request();
				objRequest.getRequestType(requestID);
				String requestType = objRequest.getRequestType(userRequestData.get(i).getRequestTypeID());
				 
				User tempUser = user.getUserData(userRequestData.get(i).getUserID());
				String requestor = tempUser.getUserFullName();



				
				String event = "";
				if(userRequestData.get(i).getRequestTypeID() == 2 || userRequestData.get(i).getRequestTypeID() == 3 ) {
					Events objEvent = new Events();
					event  = objEvent.getRequestedEventCode(userRequestData.get(i).getRequestTypeID(), userRequestData.get(i).getRequestID());
					System.out.println("Event............." + userRequestData.get(i).getRequestTypeID() + event );
				}
				
				System.out.println(userRequestData.get(i).getRequestStatusID());
				int stat = userRequestData.get(i).getRequestStatusID();
				String requestStatus = objRequest.getStatusString(stat);
				
				Object[] rowData = {requestID, requestor, requestType, requestStatus};
				userRequestsTableModel.addRow(rowData);

			}	        		
			
		}
		

	/**
	 * Create the GUI and show it.  For thread safety,
	 * this method should be invoked from the
	 * event dispatch thread.
	 */
	public void createAndShowGUI() {
		placeComponents();
	}

	public void closeNotificationFrame() {
		System.out.println("Call Close");
		this.frame.setVisible(false);
		Main m = new Main(user);
		m.show();
	}

}