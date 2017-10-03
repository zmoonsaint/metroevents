package com.cit.it.ccs323a.sia.me.ui;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.HeadlessException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.cit.it.ccs323a.sia.me.core.User;

/**
 *
 * @author L13y16w10
 */
public class Register extends javax.swing.JFrame {
	User user = new User();
	String regUserType;
    /**
     * Creates new form Register
     */
    public Register(String userType) {
    	System.out.println("................ Register.java");
    	regUserType = userType;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        lblProfile = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        btnNotification = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lblAge = new javax.swing.JLabel();
        lblBirthdate = new javax.swing.JLabel();
        lblAddress = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        lblConfirmPassword = new javax.swing.JLabel();
        txtAge = new javax.swing.JTextField();
        txtBirthdate = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtUsername = new javax.swing.JTextField();
        pwdPassword = new javax.swing.JPasswordField();
        pwdConfirmPassword = new javax.swing.JPasswordField();
        btnBack = new javax.swing.JButton();
        btnSubmit = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lblRegistration = new javax.swing.JLabel();

        jPanel2.setBackground(new java.awt.Color(51, 110, 123));

        lblProfile.setFont(new java.awt.Font("Segoe Print", 1, 24)); // NOI18N
        lblProfile.setForeground(new java.awt.Color(82, 179, 217));
        lblProfile.setText("Profile:");

        btnLogout.setBackground(new java.awt.Color(255, 102, 153));
        btnLogout.setText("Log out");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        btnNotification.setText("Notification");
        btnNotification.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNotificationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblProfile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNotification)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLogout)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnLogout)
                        .addComponent(btnNotification))
                    .addComponent(lblProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(52, 73, 94));

        lblName.setFont(new java.awt.Font("Segoe Print", 0, 11)); // NOI18N
        lblName.setForeground(new java.awt.Color(82, 179, 217));
        lblName.setText("Name:");

        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });

        lblAge.setFont(new java.awt.Font("Segoe Print", 0, 11)); // NOI18N
        lblAge.setForeground(new java.awt.Color(82, 179, 217));
        lblAge.setText("Age:");

        lblBirthdate.setFont(new java.awt.Font("Segoe Print", 0, 11)); // NOI18N
        lblBirthdate.setForeground(new java.awt.Color(82, 179, 217));
        lblBirthdate.setText("Birthdate:");

        lblAddress.setFont(new java.awt.Font("Segoe Print", 0, 11)); // NOI18N
        lblAddress.setForeground(new java.awt.Color(82, 179, 217));
        lblAddress.setText("Address:");

        lblEmail.setFont(new java.awt.Font("Segoe Print", 0, 11)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(82, 179, 217));
        lblEmail.setText("Email address:");

        lblUsername.setFont(new java.awt.Font("Segoe Print", 0, 11)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(82, 179, 217));
        lblUsername.setText("Username:");

        lblPassword.setFont(new java.awt.Font("Segoe Print", 0, 11)); // NOI18N
        lblPassword.setForeground(new java.awt.Color(82, 179, 217));
        lblPassword.setText("Password:");
        
        lblConfirmPassword.setFont(new java.awt.Font("Segoe Print", 0, 11)); // NOI18N
        lblConfirmPassword.setForeground(new java.awt.Color(82, 179, 217));
        lblConfirmPassword.setText("Confirm Password:");

        txtBirthdate.setText("MM/DD/YY");

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt, regUserType);
            }
        });

        btnSubmit.setText("Submit");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
                if(txtName.getText().equals("") || txtAge.getText().equals("") || txtBirthdate.getText().equals("") ||
                		txtAddress.getText().equals("") || txtEmail.getText().equals("") || txtUsername.getText().equals("") || 
                		pwdPassword.getText().equals("") || pwdConfirmPassword.getText().equals("")) {
                	
                    JOptionPane.showMessageDialog(null, "Please input on empty field/s.", "InfoBox: Registration", JOptionPane.ERROR_MESSAGE);
                    
                } else {
                	
                	if(!pwdPassword.getText().equals(pwdConfirmPassword.getText())) {
                        JOptionPane.showMessageDialog(null, "Password and Confirm Password doesn't match.", "InfoBox: Registration", JOptionPane.ERROR_MESSAGE);
                	} else {
                		if(!user.searchUser(txtUsername.getText())) {
                			btnSubmitActionPerformed(evt);
                		} else {
                			JOptionPane.showMessageDialog(null, "Username already exists. Please try another.", "InfoBox: Registration", JOptionPane.ERROR_MESSAGE);
                		}
                	}
                }
            }
        });

        jPanel3.setBackground(new java.awt.Color(51, 110, 123));

        lblRegistration.setFont(new java.awt.Font("Segoe Print", 0, 24)); // NOI18N
        lblRegistration.setForeground(new java.awt.Color(82, 179, 217));
        lblRegistration.setText("Registration:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRegistration)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(lblRegistration)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(lblAddress)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(lblEmail)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtEmail))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblUsername)
                                .addComponent(lblPassword)
                            	.addComponent(lblConfirmPassword))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    	                        .addComponent(pwdConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(pwdPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtUsername, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblBirthdate)
                            .addComponent(lblAge)
                            .addComponent(lblName))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBirthdate, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(28, 28, 28))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(btnBack)
                .addGap(45, 45, 45)
                .addComponent(btnSubmit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblName, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBirthdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblAge)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblBirthdate)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAddress)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsername)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pwdPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPassword))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pwdConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblConfirmPassword))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack)
                    .addComponent(btnSubmit))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt, String userType) {//GEN-FIRST:event_btnBackActionPerformed
        MetroEvent m = new MetroEvent(userType);
        m.show(true);
        this.show(false);        // TODO add your handling code here:
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
    	
    	User user = new User();
    	user.setUserFullName(txtName.getText());
    	user.setUserAge(Integer.valueOf(txtAge.getText()));
    	user.setUserBirthdate(txtBirthdate.getText());
    	user.setUserAddress(txtAddress.getText());
    	user.setUserEmail(txtEmail.getText());
    	user.setUserName(txtUsername.getText());
    	user.setUserPassword(pwdPassword.getText());
    	
    	switch(regUserType.toLowerCase()) {
        	case "administrator":
        		user.setUserType("1"); break;
            case "organizer":
        		user.setUserType("2"); break;
        	case "user":
        		user.setUserType("3"); break;
    	}
		try {
			if(user.registerNewUser(user)) {
				JOptionPane.showMessageDialog(null, "User is registered successfully.", "InfoBox: Registration", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Something went wrong. Please try again.", "InfoBox: Registration", JOptionPane.ERROR_MESSAGE);
			}
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if(user.getUserType().equals("3")) {
    		MainUser mu = new MainUser (user);
    		System.out.println("View MainUser.java");
    		mu.show();
    	} else {
    		Main m = new Main(user);
    		System.out.println("View Main.java");
    		m.show();
    	}
        this.show(false);        // TODO add your handling code here:
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        Start s = new Start();
        s.show(true);
        this.show(false);         // TODO add your handling code here:
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnNotificationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNotificationActionPerformed
        NotificationOrg n = new NotificationOrg(user);
        n.show(true);
        this.show(false);// TODO add your handling code here:
    }//GEN-LAST:event_btnNotificationActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
/*        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Register().setVisible(true);
            }
        });*/
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnNotification;
    private javax.swing.JLabel lblRegistration;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblAge;
    private javax.swing.JLabel lblBirthdate;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblConfirmPassword;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField pwdPassword;
    private javax.swing.JPasswordField pwdConfirmPassword;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtAge;
    private javax.swing.JTextField txtBirthdate;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
