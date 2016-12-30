package rmichatclient;

import callback_support.RMIChatClientImpl;
import callback_support.RMIChatClientInterface;
import chat_functionality.RMIChatInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * This class is used to set up the chat client by defining the location of the registry,
 * the port number to be used and the object which we are looking for in the registry, it then 
 * makes a new RMIChatInterface object to represent the registry and to allow methods to be called from it,
 * it then creates a new RMIChatClientInterface object and uses the registry to register the user for callback services
 * such as notifications
 * @author Brian
 */
public class RMIChatClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            //Port number to be used
            int portNum = 55555;
            
            //Path to registry
            String registryPath = "rmi://10.102.11.134:" + portNum;
            //Object to be found in registry
            String objectLabel = "/chatService";
            
            //RMIChatInterface object to represent the registry
            RMIChatInterface chatService = (RMIChatInterface) Naming.lookup(registryPath + objectLabel);
            
            //RMIChatClientInterface object to represent the new client
            RMIChatClientInterface thisClient = new RMIChatClientImpl();
            //Register the client for call back services
            chatService.registerForCallback(thisClient);
            
            
        } catch (NotBoundException ex) {
            Logger.getLogger(RMIChatClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(RMIChatClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(RMIChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
