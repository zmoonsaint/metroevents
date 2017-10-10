package com.cit.it.ccs323a.sia.me.ui;


import javax.swing.*;
import javax.swing.text.*;

import com.cit.it.ccs323a.sia.me.core.Request;
import com.cit.it.ccs323a.sia.me.core.User;

import java.awt.*;              //for layout managers and more
import java.awt.event.*;        //for action events

import java.net.URL;
import java.io.IOException;


public class RequestDetails extends JPanel
implements ActionListener {
	JFrame frame = new JFrame("Request Details");
	

	Request request = new Request();
	User user;
	int requestID;


	private String newline = "\n";
	protected static final String lblRequestID = "Request ID";
	protected static final String lblRequestor = "Requestor";
	protected static final String lblRequestType = "Request Type";
	protected static final String lblRequestDate = "Date Requested";
	protected static final String lblRequestStatus = "Request Status";
	protected static final String lblClose = "CLOSE";
	protected static final String lblApprove = "Approve";
	protected static final String lblDecline = "Decline";
	

	protected JLabel actionLabel;
	
	RequestDetails(User user, int requestID) {
		this.user = user;
		this.requestID = requestID;
		placeComponents();
	}
	
	public void placeComponents() {
		setLayout(new BorderLayout());

		//Create a regular text field.
		JTextField txtRequestID = new JTextField(10);
		JTextField txtRequestor = new JTextField(10);
		JTextField txtRequestType = new JTextField(10);
		JTextField txtRequestDate = new JTextField(10);
		JTextField txtRequestStatus = new JTextField(10);



		//Create some labels for the fields.
		JLabel txtRequestIDLabel = new JLabel(lblRequestID + ": ");
		txtRequestIDLabel.setLabelFor(txtRequestID);
		JLabel txtRequestorLabel = new JLabel(lblRequestID + ": ");
		txtRequestorLabel.setLabelFor(txtRequestor);		
		JLabel txtRequestTypeLabel = new JLabel(lblRequestType + ": ");
		txtRequestTypeLabel.setLabelFor(txtRequestType);
		JLabel txtRequestDateLabel = new JLabel(lblRequestType + ": ");
		txtRequestDateLabel.setLabelFor(txtRequestDate);		
		JLabel txtRequestStatusLabel = new JLabel(lblRequestStatus + ": ");
		txtRequestStatusLabel.setLabelFor(txtRequestStatus);

		
		JButton btnClose = new JButton(lblClose);

		//Lay out the text controls and the labels.
		JPanel textControlsPane = new JPanel();
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();

		textControlsPane.setLayout(gridbag);

		JLabel[] labels = {txtRequestIDLabel, txtRequestorLabel, txtRequestTypeLabel, txtRequestDateLabel, txtRequestStatusLabel};
		JTextField[] textFields = {txtRequestID, txtRequestor, txtRequestType, txtRequestDate, txtRequestStatus};
		addLabelTextRows(labels, textFields, gridbag, textControlsPane);

		c.gridwidth = GridBagConstraints.REMAINDER; //last
		c.anchor = GridBagConstraints.WEST;
		c.weightx = 1.0;
		textControlsPane.setBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createTitledBorder("Request Details"),
						BorderFactory.createEmptyBorder(5,5,5,5)));
		
		JButton btnApprove = new JButton(lblApprove);
		btnApprove.addActionListener(this);
		JButton btnDecline = new JButton(lblDecline);
		btnDecline.addActionListener(this);


		btnClose.setPreferredSize(new Dimension(80,30));
		JPanel flowPanel = new JPanel(new FlowLayout());
		flowPanel.add(btnApprove);
		flowPanel.add(btnDecline);
		flowPanel.add(btnClose);
		btnClose.addActionListener(this);


		//Put everything together.
		JPanel leftPane = new JPanel(new BorderLayout());
		leftPane.add(textControlsPane, 
				BorderLayout.PAGE_START);
		leftPane.add(flowPanel, BorderLayout.PAGE_END);	
		
		request = request.getUserRequestData(requestID);
		txtRequestID.setText(String.valueOf(request.getRequestID()));
		
		User tempUser = user.getUserData(request.getUserID());
		String Requestor = tempUser.getUserFullName();
		
		txtRequestor.setText(Requestor);
		txtRequestType.setText(request.getRequestType(request.getRequestTypeID()));
		txtRequestDate.setText(String.valueOf(request.getRequestDate()));
		txtRequestStatus.setText(request.getStatusString(request.getRequestStatusID()));
		
		
		frame.add(leftPane, BorderLayout.CENTER);
		frame.setSize(300, 300);
		frame.setResizable(false);
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
		System.out.println(e.getActionCommand());
		if(e.getActionCommand().equals("CLOSE")) {
			closeRequestDetail();
		} else if (e.getActionCommand().equals("Approve")) {
			System.out.println("Approve");

			request.processRequest(requestID, 2);
			if(request.getRequestTypeID() == 1 || request.getRequestTypeID()  == 4 || request.getRequestTypeID()  == 5 ) {
				User tempUser = user.getUserData(request.getUserID());
				String Requestor = tempUser.getUserName();
				System.out.println("Request Type : ----------------" + Requestor + ", " + request.getRequestTypeID());
				user.setUserAccountType(Requestor, request.getRequestTypeID());
			}
		} else if (e.getActionCommand().equals("Decline")) {
			System.out.println("Decline");
			request.processRequest(requestID, 3);
			
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
	
	public void closeRequestDetail() {
		System.out.println("Call Close");
		this.frame.setVisible(false);
	}
	


}


