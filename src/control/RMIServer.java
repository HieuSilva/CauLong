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
import javax.xml.rpc.ParameterMode;
import model.Doi;
import model.NoiDung;
import model.QuocGia;
import model.San;
import model.TranDau;
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
    
    @Override
    public San[] getSanList() throws RemoteException {
        return dao.getSanList();
    }

    @Override
    public boolean themSan(San s) throws RemoteException {
        return dao.themSan(s);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean themDoi(Doi d) throws RemoteException {
        return dao.themDoi(d);
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public TranDau[] getTranDauList() throws RemoteException {
        return dao.getTranDauList();
    }

    @Override
    public boolean themTranDau(TranDau td) throws RemoteException {
        return dao.themTranDau(td);
    }

    @Override
    public VanDongVien[] searchVDVbyQuocGia(int idquocgia, int gioitinh) throws RemoteException {
        return dao.searchVDVbyQuocGia(idquocgia, gioitinh);
    }

    @Override
    public boolean dangKyThiDauCaNhan(TranDau td) throws RemoteException {
        return dao.dangKyThiDauCaNhan(td);
    }

    @Override
    public boolean checkDangKyVanDongVien(TranDau td) throws RemoteException {
        return dao.checkDangKyVanDongVien(td);
    }

    @Override
    public Doi[] getDoiByNoiDung(NoiDung nd) throws RemoteException {
        return dao.getDoiByNoiDung(nd);
    }

    @Override
    public boolean dangKyThiDauDoi(TranDau td) throws RemoteException {
        return dao.dangKyThiDauDoi(td);
    }

    @Override
    public boolean addVanDongVienDoi(VanDongVien v, int maxid) throws RemoteException {
        return dao.addVanDongVienDoi(v, maxid);
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int maxIdDoi() throws RemoteException {
        return dao.maxIdDoi();
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
