/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.User;

import Services.UserService;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * FXML Controller class
 *
 * @author Takwa
 */
public class ListController implements Initializable {
    UserService cs = new UserService();
public static User connectedUser;
    @FXML
    private TableView<User> tableview;
    @FXML
    private TableColumn<User, String> nom;
    @FXML
    private TableColumn<User, String> email;
   
 public ObservableList<User> list;
    @FXML
    private TextField inputRech;
    @FXML
    private Button supp;
    @FXML
    private Button supp1;
    @FXML
    private Button Ajouter;
    @FXML
    private Label User;
    @FXML
    private TableColumn<?, ?> roles;
    @FXML
    private Button pdf2;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         User.setText(LoginController.connectedUser.getNom());
    UserService pss = new UserService();
        ArrayList<User> c = new ArrayList<>();
        try {
            c = (ArrayList<User>) pss.AfficherAllUsers();
        } catch (SQLException ex) {
            System.out.println("erreurrrrrrrrrrrrr");
        }
        ObservableList<User> obs2 = FXCollections.observableArrayList(c);
        tableview.setItems(obs2);
 nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
       
        roles.setCellValueFactory(new PropertyValueFactory<>("roles"));
                email.setCellValueFactory(new PropertyValueFactory<>("login"));
            try {
            list = FXCollections.observableArrayList(
                    pss.AfficherAllUsers()
            );      
            FilteredList<User> filteredData = new FilteredList<>(list, e -> true);
            inputRech.setOnKeyReleased(e -> {
                inputRech.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super User>) Utilisateurs -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();
                        if (Utilisateurs.getNom().toLowerCase().contains(lower)) {
                            return true;
                        }

                        return false;
                    });
                });
                SortedList<User> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(tableview.comparatorProperty());
                tableview.setItems(sortedData);
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        
       
      
    }

    @FXML
    private void supp(ActionEvent event) throws SQLException {
         if (event.getSource() == supp) {
            User user = new User();
            user.setId(tableview.getSelectionModel().getSelectedItem().getId());  
            UserService cs = new UserService();
            cs.SupprimerUser(user);
            resetTableData(); 

        
        
    }


    
    
}
  public void resetTableData() throws SQLDataException, SQLException {

        List<User> listuser = new ArrayList<>();
        listuser = cs.AfficherAllUsers();
        ObservableList<User> data = FXCollections.observableArrayList(listuser);
        tableview.setItems(data);
    }

  
  

    @FXML
    private void Modif(ActionEvent event) throws IOException {
        
    // String login, String roles, String mdp, String nom, String image)
           UserService ps = new UserService();
        User c = new User(tableview.getSelectionModel().getSelectedItem().getId(),
                tableview.getSelectionModel().getSelectedItem().getLogin(),
                 tableview.getSelectionModel().getSelectedItem().getRoles(),
                tableview.getSelectionModel().getSelectedItem().getMdp(),
               tableview.getSelectionModel().getSelectedItem().getNom(),
                tableview.getSelectionModel().getSelectedItem().getImage()
               
                );
        ListController.connectedUser = c;
        
             Parent page1 = FXMLLoader.load(getClass().getResource("EditUser.fxml"));
        Scene scene = new Scene(page1, 1144, 741);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Ajouter(ActionEvent event) throws IOException {
        
            Parent page1 = FXMLLoader.load(getClass().getResource("Ajout.fxml"));
        Scene scene = new Scene(page1, 1236, 785);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Liste des Users");
        stage.setScene(scene);
        stage.show();
        
    }

       private void printPDF() throws FileNotFoundException, DocumentException, IOException {
        ;
        Document d = new Document();
        PdfWriter.getInstance(d, new FileOutputStream("C:\\Users\\GEOPHYSIQUE-DGRE\\Desktop\\ListEvents.pdf"));
        d.open();
        d.add(new Paragraph("Liste des Users"));
        
        PdfPTable pTable = new PdfPTable(1);
       
     //   pTable.addCell("NomEvent");
     
        
        tableview.getItems().forEach((t) -> {
            pTable.addCell(String.valueOf(t.getNom()));
            //pTable.addCell(t.getNomEvent());
          //  pTable.addCell(t.getDescriptionEvent());
            
            try {
                d.add(pTable);
            } catch (DocumentException ex) {
                System.out.println(ex);
            }
        });
        
        
        d.close();
        Desktop.getDesktop().open(new File("C:\\Users\\GEOPHYSIQUE-DGRE\\Desktop\\ListEvents.pdf"));

    } 
    
    
    
    
    
    @FXML
    private void pdf2(ActionEvent event) throws FileNotFoundException, DocumentException, IOException {
         if (event.getSource() == pdf2) {
            
             printPDF();
            

        }
        
        
        
    }

    @FXML
    private void dashhhh(ActionEvent event) throws IOException {
                     Stage XX = new Stage() ;
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/DashboardAdmin.fxml"));
        Scene scene = new Scene(root);
        XX.setScene(scene);
        XX.show();
        
    }
  
 

}