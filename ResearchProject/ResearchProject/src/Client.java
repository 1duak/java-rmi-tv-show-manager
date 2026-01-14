import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Client extends JFrame implements ActionListener {

    private JLabel label = new JLabel("TV Show Information:");
    private TextArea textBox = new TextArea();
    private JButton showButton = new JButton("Show All");
    private JButton hideButton = new JButton("Hide All");
    private JButton addButton = new JButton("Add Show");
    private JButton deleteButton = new JButton("Delete Show");
    private JButton editButton = new JButton("Edit Show");

    private ArrayListInterface server;

    public Client() {
        try {
        	//connect to RMI server
            Registry r = LocateRegistry.getRegistry("localhost", 1234);
            server = (ArrayListInterface) r.lookup("listclass");
            System.out.println("Connected to server.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //grid layout
        getContentPane().setLayout(new GridLayout());
        getContentPane().add(label);
        getContentPane().add(textBox);
        getContentPane().add(showButton);
        getContentPane().add(hideButton);
        getContentPane().add(addButton);
        getContentPane().add(deleteButton);
        getContentPane().add(editButton);
        
        //buttons
        showButton.addActionListener(this);
        hideButton.addActionListener(this);
        addButton.addActionListener(this);
        deleteButton.addActionListener(this);
        editButton.addActionListener(this);

        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
        	//show all shows
            if (e.getSource().equals(showButton)) {
                textBox.setText("");
                ArrayList<TVShow> list = server.getList();
                for (TVShow s : list) {
                    textBox.append(s.toString() + "\n");
                }
            }
            //hide shows
            if (e.getSource().equals(hideButton)) {
                textBox.setText("");
            }
            //add a show
            if (e.getSource().equals(addButton)) {
                String title = JOptionPane.showInputDialog("Enter title:");
                String genre = JOptionPane.showInputDialog("Enter genre (Thriller/Fantasy):");
                int year = Integer.parseInt(JOptionPane.showInputDialog("Enter release year:"));
                int seasons = Integer.parseInt(JOptionPane.showInputDialog("Enter number of seasons:"));
                
                //create show using factory
                TVShow newShow = Factory.createShow(genre);
                
                //set all the user input
                newShow.setTitle(title);
                newShow.setGenre(genre);
                newShow.setReleaseYear(year);
                newShow.setSeasons(seasons);
                
                //server adds and saves
                server.addToList(newShow);
                JOptionPane.showMessageDialog(null, "Show added successfully!");
            }
            
            //delete show
            if (e.getSource().equals(deleteButton)) {
            	//get show by title to delete
                String title = JOptionPane.showInputDialog("Enter the title to delete:");
                
                //server deletes
                server.deleteFromList(title);
                JOptionPane.showMessageDialog(null, "Show deleted successfully!");
            }
            
            //edit show
            if (e.getSource().equals(editButton)) {
            	//get show by title to update
                String title = JOptionPane.showInputDialog("Enter the title to edit:");
                String newTitle = JOptionPane.showInputDialog("Enter new title:");
                String genre = JOptionPane.showInputDialog("Enter new genre (Thriller/Fantasy):");
                int year = Integer.parseInt(JOptionPane.showInputDialog("Enter new release year:"));
                int seasons = Integer.parseInt(JOptionPane.showInputDialog("Enter new number of seasons:"));

                //create updated show
                TVShow updated = Factory.createShow(genre);

                //set new info
                updated.setTitle(newTitle);
                updated.setGenre(genre);
                updated.setReleaseYear(year);
                updated.setSeasons(seasons);

                server.updateShow(title, updated);
                JOptionPane.showMessageDialog(null, "Show updated successfully!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client();
    }
}
