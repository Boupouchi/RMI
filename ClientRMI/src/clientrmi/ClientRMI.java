/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientrmi;

import dao.IDao;
import entities.Machine;
import entities.Salle;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author boupouchi
 */
public class ClientRMI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            IDao<Machine> dao = (IDao<Machine>) Naming.lookup("rmi://localhost:1099/dao");
            IDao<Salle> dao2 = (IDao<Salle>) Naming.lookup("rmi://localhost:1099/dao2");
            Salle s=new Salle("B2");
            dao2.create(s);
            
            for(Salle sS:dao2.findAll()){
                System.out.println(sS);
            }

            dao.create(new Machine("jimmy1", "kali", 200,s));
            dao.create(new Machine("jimmy2", "ubuntu", 300,s));
            dao.create(new Machine("jimmy3", "kubuntu", 500,s));
            dao.create(new Machine("jimmy4", "parot", 400,s));
            dao.create(new Machine("jimmy5", "mint", 300,s));
            dao.create(new Machine("jimmy6", "red hat", 600,s));
               for(Machine m: dao.findAll()){
            System.out.println(m);
        }
        } catch (NotBoundException ex) {
            Logger.getLogger(ClientRMI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ClientRMI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientRMI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
