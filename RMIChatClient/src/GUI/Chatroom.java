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
import callback_support.RMIChatClientInterface;
import java.awt.Color;
import java.awt.Component;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * This class is used to create a new chatroom form for the user to see
 * @author Megatronus
 */
public class Chatroom extends javax.swing.JFrame{

    private User user = new User();
    private String recipient;
    private RMIChatClientInterface client;
    private static ArrayList<User> users;
    private static ArrayList<Message> messages;
    private static ArrayList<String> messageSenderList;
    
    /**
     * Creates new chatRoom form with no parameters, it initalises the components on the panel
     * and sets a listener to listen for any click on the user list in order to send a private
     * message to the user thats clicked.
     */
    public Chatroom() {
        //Initalises the components on the chatroom form
        initComponents();
        //Set the listener to listen for the users click
        setListener();
        //Set up the colors verifying if a user is logged in or not
        setLoggedUsersColors();
    }
    
    /**
     * Creates new chatRoom form with a user object supplied as a parameter, 
     * it initalises the components on the panel and sets a listener to listen 
     * for any click on the user list in order to send a private message to the 
     * user thats clicked, it then sets the global variable user to the user taken
     * in so it can be used in other instances.
     * @param user 
     */
    Chatroom(User user) {
        //Set the current user as the user passed into the chatroom
        this.user = user;
        //Initalises the components on the chatroom form
        initComponents();
        //Set the listener to listen for the users click
        setListener();
        //Set up the colors verifying if a user is logged in or not
        setLoggedUsersColors();
    }
    
    
    /**
     * This method is used to populate the user list in the chatroom GUI, it uses 
     * the global variable 'users' and makes it a new arrayList, it then populates the arrayList
     * by calling on the chatService to return all the users in the database in the form of  
     * user objects, it then returns the arrayList of user objects.
     * @return users, an arrayList of user objects.
     */
    public static ArrayList<User> populateUserList()
    {
        //Create a new arraylist to store all the users registered
        users = new ArrayList();
        try {
            //Use the chatService to get all the users registered
            users = chatService.getAllUsers();
            
            
        } catch (RemoteException ex) {
            Logger.getLogger(Chatroom.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }
    
    /**
     * This method is used to populate the message list in the chatroom GUI, it uses
     * the global variable 'messages' and makes it a new arrayList, it then populates 
     * the arrayList by calling on the chatService to return all the messages in the database
     * that where sent through the forum in the form of message objects which are then returned.
     * @return messages, an arrayList of message objects.
     */
    public static ArrayList<Message> populateMessageList(){
        //Create a new array to store all the messages that are in the forum 
        messages = new ArrayList();
        
         //Create a new arraylist to store the username of the user that submit 
         //each message to the forum page 
         messageSenderList = new ArrayList();
        try{
            //Use the chatService to get all the messages posted to the forum
            messages = chatService.getAllForumMessages();
            //Use the chat service to get all the senders of the messages
            messageSenderList = chatService.getAllForumSenderNames();
            
            
        } catch (RemoteException ex){
            Logger.getLogger(Chatroom.class.getName()).log(Level.SEVERE, null, ex);
        }
        return messages;
    }
    
    
    public ArrayList<User> populateUnreadMessageList(){
        //Create a new Array to store all the users that sent the unread mail
        ArrayList<User> senders = new ArrayList<User>();
        //Create a new array to store all the messages that the user has not read yet 
        ArrayList<Message> unreadMessages = new ArrayList();
        try{
            
            //Use the chatService to get all the unread messages
            unreadMessages = chatService.getUnreadMessages(user.getUserId());
            
            //Use the chatService to get the senders of the new messages
            senders = chatService.getAllMessageSenders(unreadMessages);
            
            
        } catch (RemoteException ex){
            Logger.getLogger(Chatroom.class.getName()).log(Level.SEVERE, null, ex);
        }
        return senders;
    }
    
    /**
     * This method adds a listener to the user list to listen for user clicks, upon the click 
     * of a user from the list is takes the value of the user selected and stores it, it then creates
     * a new ChatWindow form that takes the user and recipient as parameters to carry the information 
     * across, it then sets the chatroom panel to be invisible and brings the ChatWindow form the be 
     * visible.
     */
    private void setListener()
    {
        //Add a listener to the list of users on the chatroom form
        userList.addListSelectionListener(new ListSelectionListener()
            {
                @Override
                //If an event happens invoke the method
                public void valueChanged(ListSelectionEvent e) {
                    if(e.getValueIsAdjusting())
                    {
                        if(userList != null)
                        {
                        //Set the recipient of the private message as the one clicked by the user
                        recipient = userList.getSelectedValue().toString();
                        //Create a new chatWindow form passing the user and intended recipient to it
                        ChatWindow chatwindow = new ChatWindow(user,recipient);
                        
                        //Make the chatWindow form visable to the user
                        Chatroom.this.setVisible(false);
                        chatwindow.setVisible(true);
                        }
                       
                    }
                }
                
            });
        
        //Add a listener to the list of users on the chatroom form
        unreadMailList.addListSelectionListener(new ListSelectionListener()
            {
                @Override
                //If an event happens invoke the method
                public void valueChanged(ListSelectionEvent e) {
                    if(e.getValueIsAdjusting())
                    {
                        if(unreadMailList != null)
                        {
                        //Set the recipient of the private message as the one clicked by the user
                        recipient = unreadMailList.getSelectedValue().toString();
                        //Create a new chatWindow form passing the user and intended recipient to it
                        ChatWindow chatwindow = new ChatWindow(user,recipient);
                        
                        //Make the chatWindow form visable to the user
                        Chatroom.this.setVisible(false);
                        chatwindow.setVisible(true);
                        }
                       
                    }
                }
                
            });
    }
    
    public void setLoggedUsersColors()
    {        
        userList.setCellRenderer(new DefaultListCellRenderer() {

                     @Override
                     public Component getListCellRendererComponent(JList list, Object value, int index,
                               boolean isSelected, boolean cellHasFocus) {
                          Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                          if(value != null)
                          {
                          String username = value.toString();
                          User u;
                         try {
                             u = chatService.getUser(username);
                             
                             if (u != null && u.isLoggedIn()) 
                          {
                                setBackground(Color.GREEN);
                          } else 
                          {
                                setBackground(Color.RED);
                          }
                         } catch (RemoteException ex) {
                             Logger.getLogger(Chatroom.class.getName()).log(Level.SEVERE, null, ex);
                         }
                          }
                          
                          
                          return c;
                     }
                });
    }
    
    /**
     * This method is used to refresh the message list for the chatroom, It takes in an ArrayList of messages
     * as a parameter and binds this list to the message list model displaying all the objects message content
     * attributes.
     * @param messages is an ArrayList of Message objects
     */
    public void setMessages(ArrayList<Message> messages) {
     
      messageList.setModel(new javax.swing.AbstractListModel() {
                public int getSize() { return messages.size(); }
                public Object getElementAt(int i) { return messageSenderList.get(i)+": "+ messages.get(i).getMessageContent(); 
                }});
    }
    
    public void setUsers(ArrayList<User> nUserList) {
        userList.setModel(new javax.swing.AbstractListModel() {
                public int getSize() { return nUserList.size(); }
                public Object getElementAt(int i) { 
                    if(nUserList.get(i).getUserName().equals(user.getUserName()))
                    {
                        return null;
                    }
                    else
                    {
                        return nUserList.get(i).getUserName();
                    }
                }});
    }
    
    public void setUnreadMessageSenders() {
     ArrayList<User> senders = populateUnreadMessageList();
     
      unreadMailList.setModel(new javax.swing.AbstractListModel() {
                public int getSize() { return senders.size(); }
                public Object getElementAt(int i) { return senders.get(i).getUserName(); 
                }});
    }
    
    
    /**
     * This method is used to pass the client from the login to the chatroom by taking the client that
     * is passed to the method and setting the client variable equal to the client passed.
     * @param newClient is the clients details passed to the method
     */
    public void setClient(RMIChatClientInterface newClient){
    this.client = newClient;
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
        userList = new javax.swing.JList<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        messageList = new javax.swing.JList<>();
        logoutButton = new javax.swing.JButton();
        unreadMailLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        unreadMailList = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titleLabel.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        titleLabel.setText("CHATROOM");

        userListLabel.setText("User list");

        messageField.setColumns(20);
        messageField.setRows(5);
        messageField.setName("mesageField"); // NOI18N
        jScrollPane3.setViewportView(messageField);

        messageLabel.setText("Please enter a message:");

        sendButton.setText("Send");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        userList.setModel(new javax.swing.AbstractListModel<String>() {
            ArrayList<User> users = populateUserList();
            public int getSize() { return users.size(); }
            public String getElementAt(int i) { if(users.get(i).getUserName().equals(user.getUserName()))
                {
                    return null;
                }
                else
                {
                    return users.get(i).getUserName();
                } }
            });
            userList.setName("userList"); // NOI18N
            jScrollPane4.setViewportView(userList);

            messageList.setModel(new javax.swing.AbstractListModel() {
                ArrayList<Message> messageList = populateMessageList();
                public int getSize() { return messageList.size(); }
                public Object getElementAt(int i) { return messageSenderList.get(i)+": "+ messageList.get(i).getMessageContent(); }
            });
            messageList.setName("messageList"); // NOI18N
            jScrollPane5.setViewportView(messageList);

            logoutButton.setText("Logoff");
            logoutButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    logoutButtonActionPerformed(evt);
                }
            });

            unreadMailLabel.setText("Unread Mail");

            unreadMailList.setModel(new javax.swing.AbstractListModel() {
                ArrayList<User> unreadList = populateUnreadMessageList();
                public int getSize() { return unreadList.size(); }
                public Object getElementAt(int i) { return unreadList.get(i).getUserName(); }
            });
            jScrollPane1.setViewportView(unreadMailList);

            javax.swing.GroupLayout chatRoomPanelLayout = new javax.swing.GroupLayout(chatRoomPanel);
            chatRoomPanel.setLayout(chatRoomPanelLayout);
            chatRoomPanelLayout.setHorizontalGroup(
                chatRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(chatRoomPanelLayout.createSequentialGroup()
                    .addGroup(chatRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(chatRoomPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(chatRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(unreadMailLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1)
                                .addComponent(jScrollPane4)))
                        .addGroup(chatRoomPanelLayout.createSequentialGroup()
                            .addGap(53, 53, 53)
                            .addComponent(userListLabel)))
                    .addGroup(chatRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(chatRoomPanelLayout.createSequentialGroup()
                            .addGap(382, 382, 382)
                            .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 250, Short.MAX_VALUE)
                            .addComponent(logoutButton))
                        .addGroup(chatRoomPanelLayout.createSequentialGroup()
                            .addGap(104, 104, 104)
                            .addGroup(chatRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane3)
                                .addComponent(jScrollPane5)
                                .addGroup(chatRoomPanelLayout.createSequentialGroup()
                                    .addGroup(chatRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(messageLabel)
                                        .addComponent(sendButton))
                                    .addGap(0, 0, Short.MAX_VALUE)))))
                    .addContainerGap())
            );
            chatRoomPanelLayout.setVerticalGroup(
                chatRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(chatRoomPanelLayout.createSequentialGroup()
                    .addGroup(chatRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(chatRoomPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(chatRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(logoutButton)
                                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(36, 36, 36)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE))
                        .addGroup(chatRoomPanelLayout.createSequentialGroup()
                            .addGap(46, 46, 46)
                            .addComponent(userListLabel)
                            .addGap(18, 18, 18)
                            .addComponent(jScrollPane4)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(chatRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(messageLabel)
                        .addComponent(unreadMailLabel))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(chatRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(chatRoomPanelLayout.createSequentialGroup()
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(35, 35, 35)
                            .addComponent(sendButton))
                        .addComponent(jScrollPane1))
                    .addGap(40, 40, 40))
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

    /**
     * this method is invoked once a user clicks the logout button on the GUI,
     * once clicked the method calls on the chatService to log the user out of 
     * the application and unregister the client from the callback service to 
     * close the port the client was on to free it for other users, it then shuts 
     * down the application.
     * @param evt is the event that happens on the logout button e.g. user click
     */
    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        try{
            //Log the user out of the application
            chatService.logoff(user);
            //Unregister the user for callback services
            chatService.unregisterForCallback(client);
            //Close the application
            System.exit(0);
        } catch (RemoteException ex) {
            Logger.getLogger(Chatroom.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_logoutButtonActionPerformed

    /**
     * This method is invoked once a user clicks the send button on the GUI
     * in order to send a message the the forum, it creates a message variable 
     * to store the text taken from the message text area on the GUI, 
     * to record the time the message was sent a new java.util.Date object, once
     * created it creates a java.sql.Date object taking the java.util.Date 
     * object as a parameter, it then creates a new message object taking in 
     * the relevant variables as parameters while setting the isRead attribute as false
     * as the users have not seen this message yet, it also sets the inFourm
     * attribute as true as the message  is being posted to the forum for all
     * users to see, once created the chatService is called on to add the message 
     * to the forum message list to be added to the database at a later stage, 
     * it then clears the message text area of any ext so the user can send another
     * message instantly, it then re-populates the message list to show the new message
     * in the forum.
     * @param evt 
     */
    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        String message = messageField.getText();
        try {
            Date utilDate = new Date();
            //gets time of message being created
            java.sql.Date timeSent = new java.sql.Date(utilDate.getTime());
            //Creates message object and adds it to the servers message list
            Message newMessage = new Message(message, "Forum", false, timeSent, true);
            //Use the chatService to add the message to the relevant lists
            chatService.addMessage(newMessage, user);
            //clear the message field
            messageField.setText("");

            //repopulates clients message list
            populateMessageList();
            //resets messageList to have updated list
            messageList.setModel(new javax.swing.AbstractListModel() {
                public int getSize() { return messages.size(); }
                public Object getElementAt(int i) { return messageSenderList.get(i)+": "+ messages.get(i).getMessageContent();
                }});

            } catch (RemoteException ex) {
                Logger.getLogger(Chatroom.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_sendButtonActionPerformed

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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JButton logoutButton;
    private javax.swing.JTextArea messageField;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JList<String> messageList;
    private javax.swing.JButton sendButton;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel unreadMailLabel;
    private javax.swing.JList<String> unreadMailList;
    private javax.swing.JList<String> userList;
    private javax.swing.JLabel userListLabel;
    // End of variables declaration//GEN-END:variables

    
}
