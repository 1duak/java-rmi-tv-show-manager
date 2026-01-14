import java.rmi.*;
import java.util.ArrayList;

public interface ArrayListInterface extends Remote {
    //add new show
    public void addToList(TVShow show) throws RemoteException;
    //delete a show
    public void deleteFromList(String title) throws RemoteException;
    //update a show
    public void updateShow(String title, TVShow updatedShow) throws RemoteException;
    //return list
    public ArrayList<TVShow> getList() throws RemoteException;
}