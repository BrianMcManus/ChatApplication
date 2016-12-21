/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import business.User;
import java.util.ArrayList;
import static GUI.Login.chatService;
import business.Message;
import callback_support.RMIChatClientImpl;
import callback_support.RMIChatClientInterface;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Megatronus
 */
public class Chatroom extends javax.swing.JFrame {

    
    /**
     * Creates new form Chatroom
     */
    public Chatroom() {
        initComponents();
    }
    private static ArrayList<User> users;
    private static ArrayList<Message> messages;
    
    public static ArrayList<User> populateUserList()
    {
        users = new ArrayList();
        try {
            users = chatService.getAllUsers();
        } catch (RemoteException ex) {
            Logger.getLogger(Chatroom.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }
    public static ArrayList<Message> populateMessageList(){
        messages = new ArrayList();
        try{
            messages = chatService.getAllMessages();
        } catch (RemoteException ex){
            Logger.getLogger(Chatroom.class.getName()).log(Level.SEVERE, null, ex);
        }
        return messages;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chatRoomPanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        userListLabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        messageField = new javax.swing.JTextArea();
        messageLabel = new javax.swing.JLabel();
        sendButton = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        userList = new javax.swing.JList<String>();
        jScrollPane5 = new javax.swing.JScrollPane();
        messageList = new javax.swing.JList<String>();
        logoutButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titleLabel.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        titleLabel.setText("CHATROOM");

        userListLabel.setText("User list");

        messageField.setColumns(20);
        messageField.setRows(5);
        messageField.setName("mesageField"); // NOI18N
        jScrollPane3.setViewportView(messageField);

        messageLabel.setText("Message Field");

        sendButton.setText("Send");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        userList.setModel(new javax.swing.AbstractListModel<String>() {
            ArrayList<User> users = populateUserList();
            public int getSize() { return users.size(); }
            public String getElementAt(int i) { return users.get(i).getUserName(); }
        });
        userList.setName("userList"); // NOI18N
        jScrollPane4.setViewportView(userList);

        messageList.setModel(new javax.swing.AbstractListModel() {
            ArrayList<Message> messageList = populateMessageList();
            public int getSize() { return messageList.size(); }
            public Object getElementAt(int i) { return messageList.get(i).getMessageContent(); }
        });
        messageList.setName("messageList"); // NOI18N
        jScrollPane5.setViewportView(messageList);

        logoutButton.setText("Logoff");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout chatRoomPanelLayout = new javax.swing.GroupLayout(chatRoomPanel);
        chatRoomPanel.setLayout(chatRoomPanelLayout);
        chatRoomPanelLayout.setHorizontalGroup(
            chatRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chatRoomPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(chatRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(chatRoomPanelLayout.createSequentialGroup()
                        .addGroup(chatRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userListLabel)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(chatRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(chatRoomPanelLayout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addGroup(chatRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(chatRoomPanelLayout.createSequentialGroup()
                                        .addComponent(messageLabel)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, chatRoomPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(chatRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 737, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(sendButton, javax.swing.GroupLayout.Alignment.TRAILING)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, chatRoomPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(254, 254, 254)
                        .addComponent(logoutButton)))
                .addContainerGap())
        );
        chatRoomPanelLayout.setVerticalGroup(
            chatRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chatRoomPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(chatRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logoutButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(chatRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(chatRoomPanelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(messageLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sendButton))
                    .addGroup(chatRoomPanelLayout.createSequentialGroup()
                        .addComponent(userListLabel)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(chatRoomPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(chatRoomPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        String message = messageField.getText();
        User user;
        try {
            user = chatService.getCurrentUser();
            Date utilDate = new Date();
            //gets time of message being created
            java.sql.Date timeSent = new java.sql.Date(utilDate.getTime());
            //Creates message object and adds it to the servers message list
            Message newMessage = new Message(message, user.getUserName(), false, timeSent, true);
            chatService.addMessage(newMessage);
            messageField.setText("");
            
            //repopulates clients message list
            populateMessageList();
            //resets messageList to have updated list
            messageList.setModel(new javax.swing.AbstractListModel() {
                public int getSize() { return messages.size(); }
                public Object getElementAt(int i) { return messages.get(i).getMessageContent(); 
                }});
            
        } catch (RemoteException ex) {
            Logger.getLogger(Chatroom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_sendButtonActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        User user;
        try{
            user = chatService.getCurrentUser();
            chatService.logoff(user);
            Chatroom chatroom = new Chatroom();
            RMIChatClientInterface thisClient = new RMIChatClientImpl(this);
            chatService.registerForCallback(thisClient);
            this.setVisible(false);
            new Login().setVisible(true);
        } catch (RemoteException ex) {
            Logger.getLogger(Chatroom.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_logoutButtonActionPerformed

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
            java.util.logging.Logger.getLogger(Chatroom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Chatroom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Chatroom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Chatroom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Chatroom().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel chatRoomPanel;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JButton logoutButton;
    private javax.swing.JTextArea messageField;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JList<String> messageList;
    private javax.swing.JButton sendButton;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JList<String> userList;
    private javax.swing.JLabel userListLabel;
    // End of variables declaration//GEN-END:variables

    public void setMessages(ArrayList<Message> messages) {
     
      messageList.setModel(new javax.swing.AbstractListModel() {
                public int getSize() { return messages.size(); }
                public Object getElementAt(int i) { return messages.get(i).getMessageContent(); 
                }});
    }
}
