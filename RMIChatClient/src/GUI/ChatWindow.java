package GUI;

import static GUI.Login.chatService;
import business.Message;
import business.User;
import callback_support.RMIChatClientInterface;
import java.awt.Color;
import java.awt.Component;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 * This class is used to create a GUI form to display a private message window to the user
 * @author Brian
 */
public class ChatWindow extends javax.swing.JFrame {

    private static User user;
    private static String recipient;
    private RMIChatClientInterface client;
    private Chatroom chatroom;
    private ArrayList<Message> privateMessages = new ArrayList<Message>();

    /**
     * Creates a new ChatWindow form that 
     * carries the current user and the recipient of the private message.
     * It then populates the relevant text fields with the senders and recipient's information
     * so the user does not have to. It then populates the message list with the past messages 
     * that have been sent between these two individuals.
     * 
     * @param user is the current user sending the private message
     * @param recipient is the intended recipient of the private message
     * 
     * 
     */
    ChatWindow(User user, String recipient) {
        //Initalises the components to be placed on the chatWindow form
        initComponents();
        
        //Stores the recipient passed into the class and places it into the recipient field on the GUI
        this.recipient = recipient;
        RecipientField.setText(recipient);
        
        //Stores the user passed into the class and places its username attribute in teh sender field on the GUI
        this.user = user;
        SenderField.setText(user.getUserName());
        
       //Populates the message area with all messages sent between the sender and receiver
       populateMessageList();
       
       //Sets the colour of the mesages depending on who sent them
       setColorsForUserMessages();
       
       
        try {
            chatService.setMessagesAsRead(privateMessages, user.getUserName());
        } catch (RemoteException ex) {
            Logger.getLogger(ChatWindow.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    ChatWindow(User user, String recipient, Chatroom chatroom) {
        //Initalises the components to be placed on the chatWindow form
        initComponents();
        
        //Stores the recipient passed into the class and places it into the recipient field on the GUI
        this.recipient = recipient;
        RecipientField.setText(recipient);
        
        //Stores the user passed into the class and places its username attribute in teh sender field on the GUI
        this.user = user;
        SenderField.setText(user.getUserName());
        
       //Populates the message area with all messages sent between the sender and receiver
       populateMessageList();
       
       //Sets the colour of the mesages depending on who sent them
       setColorsForUserMessages();
       
       this.chatroom = chatroom;

       
       
       
        try {
            chatService.setMessagesAsRead(privateMessages, user.getUserName());
        } catch (RemoteException ex) {
            Logger.getLogger(ChatWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * This method is used to populate the message list of a gui by calling on the chatService to 
     * return all the messages that has been sent between the sender and recipient in the form of 
     * message objects which are held within an arrayList, it then creates a model and populates 
     * it with the content of the returned message objects, once done it attaches the now populated 
     * model to the private message list. 
     */
    public void populateMessageList(){

        try{
            if(user != null && recipient != null)
            {
                //Use the chatService to get all messages sent between the sender and recipient 
            privateMessages = chatService.getAllPrivateMessages(user.getUserName(), recipient);
            
            if(privateMessages!= null)
            {
                Collections.sort(privateMessages, new Comparator<Message>() {
                @Override
                public int compare(Message m1, Message m2) {
                    return m1.getTimeSent().compareTo(m2.getTimeSent());
                }
                });
            }
            
            //Create a new model to store the messages retrieved and attach it to the list on the GUI
            DefaultListModel<String> model = new DefaultListModel<String>();
            for(Message m:privateMessages){
                model.addElement(m.getMessageContent());
            }
            privateMessageList.setModel(model);
            }

            
        } catch (RemoteException ex){
            Logger.getLogger(Chatroom.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void setClient(RMIChatClientInterface client) {
        this.client = client;
    }
    
    public void setColorsForUserMessages() {
        privateMessageList.setCellRenderer(new DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value != null) {
                    String m = value.toString();
                    
                    for(int i = 0; i<privateMessages.size(); i++)
                        {
                            if(m.equalsIgnoreCase(privateMessages.get(i).getMessageContent()))
                            {

                                if (privateMessages.get(i).getReceiver().equalsIgnoreCase(user.getUserName())) 
                                {
                                setBackground(Color.CYAN);
                                } 
                                else 
                                {
                                setBackground(Color.yellow);
                                }
                            }
                        }
                        
                    
                }

                return c;
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TitleLabel = new javax.swing.JLabel();
        SenderLabel = new javax.swing.JLabel();
        SenderField = new javax.swing.JTextField();
        RecipientLabel = new javax.swing.JLabel();
        RecipientField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        MessageTextArea = new javax.swing.JTextArea();
        SendButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        privateMessageList = new javax.swing.JList<>();
        logOutButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TitleLabel.setFont(new java.awt.Font("Tahoma", 1, 21)); // NOI18N
        TitleLabel.setText("Private Message");

        SenderLabel.setText("Sender:");

        SenderField.setEditable(false);

        RecipientLabel.setText("Recipient:");

        RecipientField.setEditable(false);

        MessageTextArea.setColumns(20);
        MessageTextArea.setRows(5);
        jScrollPane1.setViewportView(MessageTextArea);

        SendButton.setText("Send");
        SendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SendButtonActionPerformed(evt);
            }
        });

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        privateMessageList.setModel(new javax.swing.AbstractListModel<String>() {
            ArrayList<Message> messageList = new ArrayList();
            public int getSize() { return messageList.size(); }
            public String getElementAt(int i) { return messageList.get(i).getMessageContent(); }
        });
        jScrollPane2.setViewportView(privateMessageList);

        logOutButton.setText("Log Out");
        logOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(backButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(SendButton)
                .addGap(50, 50, 50))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(SenderLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SenderField, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(RecipientLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(RecipientField, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(logOutButton)
                        .addGap(13, 13, 13))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TitleLabel)
                    .addComponent(logOutButton))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SenderLabel)
                    .addComponent(SenderField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RecipientLabel)
                    .addComponent(RecipientField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(backButton)
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(SendButton)
                        .addGap(39, 39, 39))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * This method listens for the back button on the GUI to be pushed, in doing so a new
     * chatRoom object is created taking a user object containing the current users information,
     * it then sets the chatWindow panel to be invisible and hen brings up the chatroom panel again.
     * @param evt is the event that happened on the back button  
     */
    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        try {
            chatService.unregisterForCallback(client);
        } catch (RemoteException ex) {
            Logger.getLogger(ChatWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
                //Make the chatroom visable to the user
                this.setVisible(false);
                //chatroom.setVisible(true);
    }//GEN-LAST:event_backButtonActionPerformed

    /**
     * This method is used to send a message from the sender to the recipient and inform the user
     * if it was successfully sent or not, it stores the current users id from the user object that 
     * was taken in on the creation of the chat window, it takes the message content from the message 
     * text area and stores it, it also takes the recipient from the recipient text field, the date is 
     * created as an java.util.date, then it creates a java.util.date taking the java.util.date as a 
     * parameter, a new message object is then created with the stored information, with the isRead 
     * which states if the message is read or not and the inForum attribute which states if the message was 
     * sent in the forum or not, both being stated as false in this case
     * 
     * The method checks for null values in the message object and the user id, if it passes,
     * it asks the chatService to send a private message from the user relevant to the user id
     * to the receiver held within the message object, this returns a boolean value specifying 
     * if the message was sent successfully or not
     * 
     * If successful the method alerts the user that it was successful, adds the message to the private
     * message arraylist and re-populates the list with the new message appearing to the user, it then wipes
     * the text from the typing area so a new message can be written with ease
     * 
     * if unsuccessful the user will be alerted that the message was not sent and will be allowed to try again
     * with their message text still available to them.
     * 
     * @param evt is the event that happened on the send button  
     */
    private void SendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SendButtonActionPerformed
        //Get the current users user id
        int userId = user.getUserId();
        //Store the message content entered by the user
        String messageContent = MessageTextArea.getText();
        //Get the receiver information from the appropriate text field
        String receiver = RecipientField.getText();
        //Create a new date object to record when the message was sent
        Calendar calendar = Calendar.getInstance();
        java.sql.Timestamp timeSent = new java.sql.Timestamp(calendar.getTimeInMillis());
        //Create a new message object
        Message message = new Message(messageContent, receiver, false, timeSent, false);
        boolean valid = false;
        
        if(!message.equals("") && userId >0)
        {
            try {
                //Use chatService to send the message and record if it was successful or not
                valid = chatService.sendPrivateMessage(userId, message);
                
                //If successful
                if(valid)
                {
                    //Let the user know the message was sent to the intended recipient
                    System.out.println("You sent a message to: " + receiver);
                    //Add the message to the messsage list containing the mesages already sent
                    privateMessages.add(message);
                    //Repopulate the message list so the new message is visable
                    populateMessageList();
                    //Clear the text area 
                    MessageTextArea.setText("");
                    
                    client.repopulatePrivateMessages();

                }
                else
                {
                    //Let the user know that the message was not sent
                    System.out.println("Sorry your message was not sent to: " + receiver);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(ChatWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }//GEN-LAST:event_SendButtonActionPerformed

    private void logOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutButtonActionPerformed
        try {
            //Log the user out of the application
            chatService.logoff(user);
            //Unregister the user for callback services
            chatService.unregisterForCallback(client);
            //Close the application
            System.exit(0);
        } catch (RemoteException ex) {
            Logger.getLogger(Chatroom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_logOutButtonActionPerformed

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
            java.util.logging.Logger.getLogger(ChatWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChatWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChatWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChatWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea MessageTextArea;
    private javax.swing.JTextField RecipientField;
    private javax.swing.JLabel RecipientLabel;
    private javax.swing.JButton SendButton;
    private javax.swing.JTextField SenderField;
    private javax.swing.JLabel SenderLabel;
    private javax.swing.JLabel TitleLabel;
    private javax.swing.JButton backButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton logOutButton;
    private javax.swing.JList<String> privateMessageList;
    // End of variables declaration//GEN-END:variables

    
}
