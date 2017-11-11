/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.rpc.ParameterMode;
import model.NoiDung;
import model.QuocGia;
import model.User;
import model.VanDongVien;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

/**
 *
 * @author HIEU
 */
public class RMIServer extends UnicastRemoteObject implements RMIInterface{
    
    private int serverPort = 6666;
    private DAO dao;
    private Registry registry;
    private String rmiService = "rmiService";
    
    public RMIServer() throws RemoteException {
        dao = new DAO();
        try {
            registry = LocateRegistry.createRegistry(serverPort);
            registry.rebind(rmiService, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkLogin(User user) throws RemoteException {
        boolean result = false;
        String endpointURL = "http://localhost:8080/axis/AppWebService.jws";
        Service service = new Service();
        try {
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(new java.net.URL(endpointURL));
            call.setOperationName("checkLogin");
            call.addParameter("username", XMLType.XSD_STRING,ParameterMode.PARAM_MODE_IN);
            call.addParameter("password", XMLType.XSD_STRING,ParameterMode.PARAM_MODE_IN);
            call.setReturnType(XMLType.XSD_BOOLEAN);
            result = (Boolean) call.invoke(new Object[]{user.getUsername(), user.getPassword()});
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
    public static void main(String[] args) {
        try {
            RMIServer server = new RMIServer();
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public NoiDung[] getNoiDungList() throws RemoteException {
        return dao.getNoiDungList();
    }

    @Override
    public QuocGia[] getListQuocGia() throws RemoteException {
        return dao.getListQuocGia();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean themVDV(VanDongVien v) throws RemoteException {
        return dao.themVDV(v);
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
