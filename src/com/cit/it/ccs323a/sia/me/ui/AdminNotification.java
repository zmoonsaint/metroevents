package com.cit.it.ccs323a.sia.me.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.cit.it.ccs323a.sia.me.core.Events;
import com.cit.it.ccs323a.sia.me.core.Request;
import com.cit.it.ccs323a.sia.me.core.User;

import java.awt.*;              //for layout managers and more
import java.awt.event.*;        //for action events
import java.util.ArrayList;


public class AdminNotification extends JPanel
implements ActionListener {



	//Create and set up the window.
	JFrame frame = new JFrame("Notification");
	int organizerRequestselectedRow;
	int userRequestSelectedRow;
	User user;
	Request request = new Request();
	Request userRequest = new Request();


	ArrayList<Request> organizerRequestsData;
	ArrayList<Request> userRequestsData;
	DefaultTableModel organizerRequestsTableModel;
	DefaultTableModel userRequestsTableModel;

	public AdminNotification(User user) {
		this.user = user;
		placeComponents();
	}

	public void placeComponents() {
		setLayout(new BorderLayout());


		String[] organizerRequests = {"Request ID", 
				"Requestor",
				"Request Type",
				"Event Code",
		"Status"} ;

		organizerRequestsTableModel = new DefaultTableModel(organizerRequests, 0);
		setOrganizerRequestsTable();
		JTable organizerRequestsTable = new JTable(organizerRequestsTableModel);
		organizerRequestsTable.setPreferredSize(new Dimension (300,500));
		organizerRequestsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		JScrollPane organizerRequestscrollPane = new JScrollPane(organizerRequestsTable);
		organizerRequestscrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		organizerRequestscrollPane.setPreferredSize(new Dimension(1000, 250));
		organizerRequestscrollPane.setBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createCompoundBorder(
								BorderFactory.createTitledBorder("Organizer Requests"),
								BorderFactory.createEmptyBorder(5,5,5,5)),
						organizerRequestscrollPane.getBorder()));
		organizerRequestsTable.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {

				int row = organizerRequestsTable.rowAtPoint(evt.getPoint());
				int col = organizerRequestsTable.columnAtPoint(evt.getPoint());		       
				userRequestSelectedRow = row;
				
				if(row != -1) {
					int requestID = Integer.valueOf(organizerRequestsTable.getValueAt(row, 0).toString());
					System.out.println("Will open RequestDetails");

					RequestDetails rd = new RequestDetails(user, requestID);
					rd.createAndShowGUI();
					System.out.println(requestID);
				}

			}});

		//Create a text area.
		
		String[] userRequests = {"Request ID", 
			"Requestor",
			"Request Type",
			"Event",
			"Status"} ;
		
		userRequestsTableModel = new DefaultTableModel(userRequests, 0);
		setUserRequestsTable();
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
		leftPane.add(organizerRequestscrollPane, 
				BorderLayout.PAGE_START);
		leftPane.add(userRequestScrollPane,
				BorderLayout.CENTER);
		leftPane.add(flowPanel, BorderLayout.PAGE_END);

		frame.add(leftPane, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}


	private void setOrganizerRequestsTable() {

		organizerRequestsData = request.getAllUserRequestsByStatus(1);

		for(int i = 0; i < organizerRequestsData.size(); i++) {

			int requestID = organizerRequestsData.get(i).getRequestID();
			int requestorID = organizerRequestsData.get(i).getUserID();

			Request objRequest = new Request();
			objRequest.getRequestType(requestID);
			String RequestType = objRequest.getRequestType(organizerRequestsData.get(i).getRequestTypeID());

			String event = "";
			if(organizerRequestsData.get(i).getRequestTypeID() == 2) {
				Events objEvent = new Events();
				event  = objEvent.getRequestedEventCode(organizerRequestsData.get(i).getRequestTypeID(), requestID);
				System.out.println("Event............." + organizerRequestsData.get(i).getRequestTypeID() + "Event Code : " + event );
			}
			if(organizerRequestsData.get(i).getRequestTypeID() == 3 ) {
				Events objEvent = new Events();
				event  = objEvent.getRequestedEventCode(organizerRequestsData.get(i).getRequestTypeID(), requestID);
				System.out.println("Event............." + organizerRequestsData.get(i).getRequestTypeID() + "Event Code : " + event );
			}			

			System.out.println(organizerRequestsData.get(i).getRequestStatusID());
			int stat = organizerRequestsData.get(i).getRequestStatusID();
			String status = objRequest.getStatusString(stat);
			User tempUser = user.getUserData(requestorID);
			String Requestor = tempUser.getUserFullName();

			Object[] rowData = {requestID, Requestor, RequestType, event, status};
			if(organizerRequestsData.get(i).getRequestTypeID() == 2) {
				organizerRequestsTableModel.addRow(rowData);
			}
		}	        		

	}

	private void setUserRequestsTable() {

		userRequestsData = userRequest.getAllUserRequestsByStatus(1);
		System.out.println(userRequestsData.size());

		for(int i = 0; i < userRequestsData.size(); i++) {

			int requestID = userRequestsData.get(i).getRequestID();
			System.out.println(requestID);
			//int requestorID = myRequestData.get(i).getUserID();

			Request objRequest = new Request();
			objRequest.getRequestType(requestID);
			String requestType = objRequest.getRequestType(userRequestsData.get(i).getRequestTypeID());
			System.out.println(requestType);


			User tempUser = user.getUserData(userRequestsData.get(i).getUserID());
			String requestor = tempUser.getUserFullName();
			System.out.println(requestor);


			String event = "";
			if(userRequestsData.get(i).getRequestTypeID() == 3) {
				Events objEvent = new Events();
				event  = objEvent.getRequestedEventCode(userRequestsData.get(i).getRequestTypeID(), userRequestsData.get(i).getRequestID());
				System.out.println("Inside userRequest Event Type............." + userRequestsData.get(i).getRequestTypeID() + "Event............."+ event );
			}
			
			System.out.println(event);


			System.out.println(userRequestsData.get(i).getRequestStatusID());
			int stat = userRequestsData.get(i).getRequestStatusID();
			String requestStatus = objRequest.getStatusString(stat);
			System.out.println(requestStatus);
			
			Object[] rowData = {requestID, requestor, requestType, event, requestStatus};

			if(userRequestsData.get(i).getRequestTypeID() != 2) {
				userRequestsTableModel.addRow(rowData);
			}
		}	        		

	}	



	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		if(e.getActionCommand().equals("Close Notification")) {
			closeNotificationFrame();
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
