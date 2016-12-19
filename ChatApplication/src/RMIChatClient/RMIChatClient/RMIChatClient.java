/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMIChatClient.RMIChatClient;


import DTO.Message;
import DTO.User;
import RMIChatClient.CallbackSupport.RMIChatClientImpl;
import RMIChatClient.CallbackSupport.RMIChatClientInterface;
import RMIChatClient.ChatFunctionality.RMIChatInterface;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author labadmin
 */
public class RMIChatClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            int portNum = 55555;
            
            String registryPath = "rmi://localhost:" + portNum;
            String objectLabel = "/quoteService";
            
            RMIChatInterface messageService = (RMIChatInterface) Naming.lookup(registryPath + objectLabel);
            
            RMIChatClientInterface thisClient = new RMIChatClientImpl();
            messageService.registerForCallback(thisClient);
            
            Message m = new Message("I'll be back", "Ahnawld");
            boolean added = messageService.addMessage(m);
            
            System.out.println("Quote added? " + added);
            
            Message newMessage = messageService.getMessage();
            System.out.println(newMessage);
            
            User u = new User("Michelle", "password");
            
            System.out.println("Registered: " + messageService.register(u));
            
        } catch (NotBoundException ex) {
            Logger.getLogger(RMIChatClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(RMIChatClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(RMIChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
