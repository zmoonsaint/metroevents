package com.cit.it.ccs323a.sia.me.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.cit.it.ccs323a.sia.me.core.Events;
import com.cit.it.ccs323a.sia.me.core.Request;
import com.cit.it.ccs323a.sia.me.core.User;

import java.awt.*;              //for layout managers and more
import java.awt.event.*;        //for action events
import java.util.ArrayList;


public class UserNotification extends JPanel
implements ActionListener {
	User user;
	
	DefaultTableModel myRequestsTableModel;
	JTable myRequestsTable;
	Request request = new Request();
	ArrayList<Request> myRequestData;

	//Create and set up the window.
	JFrame frame = new JFrame("Notification");
	int myRequestSelectedRow;
	

	public UserNotification(User user) {
		this.user = user;
		placeComponents();
	}

	public void placeComponents() {
		setLayout(new BorderLayout());


		String[] myRequests = {"Request ID", 
				"Request Type",
				"Event",
				"Request Status"} ;
		
/*		String[] myRequestData = {"My Event", 
				"eventName",
				"07/10/2017",
				"Ayala",
				"this is an event",
				"Zilah",
		"07/10/2017"} ;*/
		

		myRequestsTableModel = new DefaultTableModel(myRequests, 0);
		setMyRequestTableData();
		//myRequestsTableModel.addRow(myRequestData);
		myRequestsTable = new JTable(myRequestsTableModel);
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
					UserRequestDetails rd = new UserRequestDetails(user, requestID);
					rd.createAndShowGUI();
					System.out.println(requestID);
				}
				
			}});
		
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
		leftPane.add(flowPanel, BorderLayout.PAGE_END);

		frame.add(leftPane, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
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
			
			Object[] rowData = {requestID, requestType, event, requestStatus};
			myRequestsTableModel.addRow(rowData);

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
		MainUser mu = new MainUser(user);
		mu.show();
		

	}

/*	public static void main(String[] args) {
		//Schedule a job for the event dispatching thread:
		//creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				//Turn off metal's use of bold fonts
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				UserNotification on = new UserNotification();
				on.createAndShowGUI();
			}
		});
	}*/


}
