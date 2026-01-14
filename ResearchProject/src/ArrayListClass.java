import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.io.*;
import java.util.ArrayList;

public class ArrayListClass extends UnicastRemoteObject implements ArrayListInterface {
    private ArrayList<TVShow> list;

    public ArrayListClass() throws RemoteException {
        super();
        //deserialize
        try {
            FileInputStream fi = new FileInputStream("tvshows.ser");
            ObjectInputStream oi = new ObjectInputStream(fi);
            list = (ArrayList<TVShow>) oi.readObject();
            oi.close();
            fi.close();
            System.out.println("Loaded TV shows from file.");
        } catch (Exception e) {
        	//makes new list if nothing exists yet
            list = new ArrayList<>();

            //creating sample data so list is not empty
            TVShow s1 = new ThrillerShow();
            s1.setTitle("Dark");
            s1.setGenre("Thriller");
            s1.setReleaseYear(2017);
            s1.setSeasons(3);
            list.add(s1);

            TVShow s2 = new FantasyShow();
            s2.setTitle("Locke & Key");
            s2.setGenre("Fantasy");
            s2.setReleaseYear(2020);
            s2.setSeasons(3);
            list.add(s2);

            saveList();
        }
    }

    //add new show
    public void addToList(TVShow show) throws RemoteException {
        list.add(show);
        saveList();
        System.out.println("Added show: " + show.getTitle());
    }

    //delete show by title
    public void deleteFromList(String title) throws RemoteException {
        list.removeIf(s -> s.getTitle().equalsIgnoreCase(title));
        saveList();
        System.out.println("Deleted show: " + title);
    }

    //update existing show by title
    public void updateShow(String title, TVShow updatedShow) throws RemoteException {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getTitle().equalsIgnoreCase(title)) {
                list.set(i, updatedShow);
                saveList();
                System.out.println("Updated show: " + updatedShow.getTitle());
                return;
            }
        }
        System.out.println("Show not found: " + title);
    }

    // return shows
    public ArrayList<TVShow> getList() throws RemoteException {
        return list;
    }

    //serialize
    private void saveList() {
        try {
            FileOutputStream fo = new FileOutputStream("tvshows.ser");
            ObjectOutputStream oo = new ObjectOutputStream(fo);
            oo.writeObject(list);
            oo.close();
            fo.close();
            System.out.println("List saved to file.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
