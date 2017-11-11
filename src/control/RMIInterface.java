/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.rmi.Remote;
import java.rmi.RemoteException;
import model.NoiDung;
import model.User;

/**
 *
 * @author HIEU
 */
public interface RMIInterface extends Remote{
    
    public boolean checkLogin(User user) throws RemoteException;
    public NoiDung [] getNoiDungList() throws RemoteException;
    
}
