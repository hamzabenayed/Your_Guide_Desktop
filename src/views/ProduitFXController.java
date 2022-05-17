/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import Entities.Categorie;
import Entities.Panier;
import Entities.Produit;
import Services.ImCategorie;
import Services.ImProduit;
import Services.NotificationService;
import Services.PanierService;
import com.esprit.upload.Upload;
import com.esprit.utils.DataSource;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author hedia
 */
public class ProduitFXController implements Initializable {

    @FXML
    private TextArea descp;
    @FXML
    private TextField Nomp;
    @FXML
    private TextField prixp;
    @FXML
    private TableView<Produit> tableP;
    @FXML
    private Button ImageB;
    @FXML
    private Label imagepath;
    @FXML
    private TableColumn<Produit,Integer> ID;
    @FXML
    private TableColumn<Produit,Integer> IDC;
    @FXML
    private TableColumn<Produit,String> nomP;
    @FXML
    private TableColumn<Produit, String> descP;
    @FXML
    private TableColumn<Produit, Integer> PrixP;
    @FXML
    private TableColumn<Produit, String> IMG;
   
    private ImProduit Ip = new ImProduit();
    String pic;
    private File file;
    Text path;
    @FXML
    private ComboBox<String> cat;
    @FXML
    private Button ajouterPanier;
    @FXML
    private Button panier;
     PanierService panierService =new PanierService();
    NotificationService notificationService =new NotificationService();

    int idUser=1;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
                List<Produit> ll = Ip.afficher();
        // TODO
        tableP.setEditable(true);
        ID.setCellValueFactory(new PropertyValueFactory<>("idp"));
        //cat.setCellValueFactory(new PropertyValueFactory<>("category_id"));
        afficherCat();
  
        //col_date.setCellFactory(TextFieldTableCell.forTableColumn());
        
        nomP.setCellValueFactory(new PropertyValueFactory<Produit,String>("nom"));
        descP.setCellValueFactory(new PropertyValueFactory<Produit,String>("descrip"));
        PrixP.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("prix"));
        IMG.setCellValueFactory(new PropertyValueFactory<Produit,String>("image"));

        nomP.setEditable(true);
        nomP.setCellFactory(TextFieldTableCell.forTableColumn());
         descP.setCellFactory(TextFieldTableCell.forTableColumn());
         PrixP.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        tableP.getItems().setAll(ll);
        
            nomP.setOnEditCommit(new EventHandler<CellEditEvent<Produit, String>>() {
          
            public void handle(CellEditEvent<Produit, String> event) {
                Produit tt = event.getRowValue();
                tt.setNom(event.getNewValue());
              
               Ip.modifier(tt);

            }});
             descP.setOnEditCommit(new EventHandler<CellEditEvent<Produit, String>>() {
          
            public void handle(CellEditEvent<Produit, String> event) {
                Produit tt = event.getRowValue();
                tt.setDescrip(event.getNewValue());
              
               Ip.modifier(tt);

            }});
             
              PrixP.setOnEditCommit(new EventHandler<CellEditEvent<Produit, Integer>>() {
          
            public void handle(CellEditEvent<Produit, Integer> event) {
                Produit tt = event.getRowValue();
                tt.setPrix(event.getNewValue());
              
               Ip.modifier(tt);

            }});
              
    }    

    @FXML
    private void ADDP(ActionEvent event) {
        ImCategorie ks = new ImCategorie();
             String s ="1";
        if(Nomp.getText().equals("") || Nomp.getText().contains(s) || descp.getText().equals("") ||prixp.getText().equals("") || Integer.parseInt(prixp.getText().toString())<=0||prixp.getText().length()<=0)
        {
                    Notifications notifications=Notifications.create();
                    notifications.text("Hello please fill the required fields");
                    notifications.title("Failed Message");
                    notifications.show();

    }
        else{
            
            Ip.ajouter(new Produit (Nomp.getText(),
                    descp.getText(),
                    Integer.parseInt(prixp.getText()),
                    pic
                            
            
            
            ));
             Notifications notifications=Notifications.create();
            notifications.text("Ajoute");
            notifications.title("Suceess");
            notifications.show();
            initialize(null,null);
        }
    }

    @FXML
    private void DELP(ActionEvent event) {
         Produit C = new Produit();
            System.out.println("****");
        C=tableP.getSelectionModel().getSelectedItems().get(0);
        Ip.supprimer(C);
         initialize(null,null);
    }

    @FXML
    private void upload(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
            file= fileChooser.showOpenDialog(null);
             FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (.jpg)", ".JPG");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (.png)", ".PNG");
            fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

            imagepath.setText("File:"+file);
            //pic=(file.toURI().toString());
            pic=new Upload().upload(file,"\\UploadFile");
            System.out.println(pic);
    }
  private void afficherCat() {
        ImCategorie cs = new ImCategorie();
        List<Categorie> fcts = cs.afficher();
        ObservableList<String> nom = FXCollections.observableArrayList();
        for(int i = 0; i<fcts.size();i++){
            nom.add(fcts.get(i).getNom());
        }
       cat.setItems(nom); //pour remplir le combo box   
    }
  @FXML
    private void CreatePDF(ActionEvent event) throws SQLException, IOException, DocumentException {

        try {
           
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream("pdffg.pdf"));
            doc.open();
            doc.add(new Paragraph(" "));
            Font font = new Font(Font.FontFamily.TIMES_ROMAN, 28, Font.UNDERLINE, BaseColor.BLACK);
            Paragraph p = new Paragraph("----------produit-------- ", font);
            p.setAlignment(Element.ALIGN_CENTER);
            doc.add(p);
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));

            PdfPTable tabpdf = new PdfPTable(3);
            tabpdf.setWidthPercentage(100);

            PdfPCell cell;
            cell = new PdfPCell(new Phrase("id", FontFactory.getFont("Times New Roman")));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);

            cell = new PdfPCell(new Phrase("category_id", FontFactory.getFont("Times New Roman")));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);

            cell = new PdfPCell(new Phrase("nom", FontFactory.getFont("Times New Roman")));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);


           Connection cnx;
            cnx= DataSource.getInstance().getCnx();
            String req = "SELECT * FROM produit";
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                cell = new PdfPCell(new Phrase(rs.getString("id"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                tabpdf.addCell(cell);

                cell = new PdfPCell(new Phrase(rs.getString("category_id"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                tabpdf.addCell(cell);

                cell = new PdfPCell(new Phrase(rs.getString("nom"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                tabpdf.addCell(cell);

            }
            doc.add(tabpdf);
            JOptionPane.showMessageDialog(null, "Fichier PDF crée !");
            doc.close();
            Desktop.getDesktop().open(new File("pdf.pdf"));
        } catch (DocumentException e) {
            System.out.println("ERROR PDF");
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println(e.getMessage());
        }

    }

    @FXML
    private void ajouterPanier(ActionEvent event) {
                Produit produit=tableP.getSelectionModel().getSelectedItems().get(0);
                Panier panier=new Panier(produit,idUser);                
                Boolean exist=panierService.productExist(produit, idUser);
                if(exist)
                {

                 notificationService.alert("Panier","Produit existe deja ");
                }
                else {
                                    panierService.ajouter(panier);

                                    notificationService.notification("Panier", "Produit ajouté");

                }
                    

        
        
    }

    @FXML
    private void Panier(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Panier.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(ProduitFXController.class.getName()).log(Level.SEVERE, null, ex);
        }

        PanierController HomeScene = loader.getController();
                HomeScene.idUser = this.idUser;

        HomeScene.initializeFxml();

        Stage window = (Stage) panier.getScene().getWindow();

        window.setScene(new Scene(root, 1000, 718));
        
    }
}
