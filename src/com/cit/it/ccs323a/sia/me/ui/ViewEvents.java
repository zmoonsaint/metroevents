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

import com.cit.it.ccs323a.sia.me.core.Events;
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

	    Events events = new Events();
	    
	    
		String[] columnNames = {"Event Code", 
				"Event Name",
				"Event Date",
				"Event Location",
				"Event Description",
				"Event Organizer",
				"Event Date Added"} ;
		/*Object [][] rowData = {{"event1", "eventN ame", "eve     ntDate", "eve                 ntLocation", "eventDateAdded"},
				{"event2", "eventName", "eventDate", "eventLocation", "eventDateAdded"}};*/
	 
	    protected JLabel actionLabel;
	 
	    public ViewEvents(User user) {
	    	this.user = user;
	        setLayout(new BorderLayout());
	 
	        //Create a regular text field.
	        JTextField txtEventCode = new JTextField(10);
	        txtEventCode.setActionCommand(txtEventCodeString);
	        txtEventCode.addActionListener(this);
	 
	        //Create a password field.
	        JTextField txtEventName = new JTextField(10);
	        txtEventName.setActionCommand(txtEventNameString);
	        txtEventName.addActionListener(this);
	 
	        //Create a formatted text field.
	        JFormattedTextField txtEventDate = new JFormattedTextField(
	                java.util.Calendar.getInstance().getTime());
	        txtEventDate.setActionCommand(txtEventCodeString);
	        txtEventDate.addActionListener(this);
	        
	        //Create a password field.
	        JTextField txtEventLocation = new JTextField(10);
	        txtEventLocation.setActionCommand(txtEventLocationString);
	        txtEventLocation.addActionListener(this);
	        
	        //Create a password field.
	        JTextField txtEventOrganizer = new JTextField(10);
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
	        JButton btnJoinEvent = new JButton("Join Event");
	        JButton btnBack = new JButton("Back");

	 

	 
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
	        textControlsPane.add(btnJoinEvent);
	        textControlsPane.add(btnBack);
	        textControlsPane.setBorder(
	                BorderFactory.createCompoundBorder(
	                                BorderFactory.createTitledBorder("Event Data"),
	                                BorderFactory.createEmptyBorder(5,5,5,5)));
	 
	        //Create a text area.
	        JTextArea textArea = new JTextArea(
	                "This is a description of the event. "
	        );
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

	        
	        
	    	ArrayList<Events> eventArr = events.getAllEventsByStatus(2);
	        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
	    	
	    	for(int i = 0; i < eventArr.size(); i++) {
	    		String eventCode = eventArr.get(i).getEventCode();
	    		String eventName = eventArr.get(i).getEventName();
	    		Date eventDate = eventArr.get(i).getEventDate();
	    		String eventLocation = eventArr.get(i).getEventLocation();
	    		String eventDescription = eventArr.get(i).getEventDescription();
	    		int eventOrganizer = eventArr.get(i).getEventOrganizer();
	    		Date eventDateAdded = eventArr.get(i).getEventDateAdd();
   		   		
	    		Object[] rowData = {eventCode, eventName, eventDate, eventLocation, eventDescription, eventOrganizer, eventDateAdded};
	    		tableModel.addRow(rowData);

	    	}	        
	        
	        
	        final JTable table = new JTable(tableModel);
			table.setPreferredSize(new Dimension (100,100));
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	        //JEditorPane editorPane = createEditorPane();
			table.addMouseListener(new java.awt.event.MouseAdapter() {
			    @Override
			    public void mouseClicked(java.awt.event.MouseEvent evt) {

			    
			        int row = table.rowAtPoint(evt.getPoint());
			        int col = table.columnAtPoint(evt.getPoint());

			    	eventCode = (String) table.getValueAt(row, 0);
			    	String eventName = (String) table.getValueAt(row, 1);
			    	Date eventDate =  (Date) table.getValueAt(row, 2);
			    	String eventLocation = (String) table.getValueAt(row, 3);
			    	String eventDescription = (String) table.getValueAt(row, 4);
			    	int eventOrganizer = (int) table.getValueAt(row, 5);
			    	System.out.println(eventCode);
			    	
			    	txtEventCode.setText(eventCode);
			    	txtEventName.setText(eventName);
			    	//txtEventDate = new JFormattedTextField(java.util.Calendar.getInstance().getTime());
			    	txtEventLocation.setText(eventLocation);
			    	textArea.setText(eventDescription);
			    	txtEventOrganizer.setText(String.valueOf(eventOrganizer));
			    	
			  }});
			
	        btnJoinEvent.addActionListener(new java.awt.event.ActionListener() {
	        	public void actionPerformed(java.awt.event.ActionEvent evt) {
	        		btnJoinEventActionPerformed(evt);
	        	}

				private void btnJoinEventActionPerformed(ActionEvent evt) {
			    	if(JOptionPane.showConfirmDialog(null, "Do you want to join event: \"" + eventCode + "\"", "CONFIRMATION", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			    		System.out.println("Confirm join the event..............");
						JOptionPane.showMessageDialog(null, "Request to joint event is sent to Organizer for approval.\nCheck request status through notification.", "InfoBox: CONFIRMATION", JOptionPane.INFORMATION_MESSAGE);
						// code to join event here.
			    	} 				
				}
	        });
	        
	        btnBack.addActionListener(new java.awt.event.ActionListener() {
	        	public void actionPerformed(java.awt.event.ActionEvent evt) {
	        		btnBackEventActionPerformed(evt);
	        	}

				private void btnBackEventActionPerformed(ActionEvent evt) {
					frame.setVisible(false);
					frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					Main m= new Main(user);
				     m.show(true);
				    
				}
	        });
			
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
	 
	        add(rightPane, BorderLayout.LINE_START);
	        add(leftPane, BorderLayout.LINE_END);
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
	        String prefix = "You typed \"";
	        if (txtEventCodeString.equals(e.getActionCommand())) {
	            JTextField source = (JTextField)e.getSource();
	            actionLabel.setText(prefix + source.getText() + "\"");
	        } else if (txtEventNameString.equals(e.getActionCommand())) {
	            JPasswordField source = (JPasswordField)e.getSource();
	            actionLabel.setText(prefix + new String(source.getPassword())
	                                + "\"");
	        } else if (buttonString.equals(e.getActionCommand())) {
	            Toolkit.getDefaultToolkit().beep();
	        }
	    }
	    
	    public void createAndShowGUI(boolean open) {
	        //Create and set up the window.
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 
	        //Add content to the window.
	        frame.add(new ViewEvents(user));
	 
	        //Display the window.
	        frame.pack();
	        frame.setVisible(open);
	    }
	 
	
}
