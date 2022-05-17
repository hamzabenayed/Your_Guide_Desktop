/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entities.Commande;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

/**
 *
 * @author SeifBS
 */
public class ServicePdf {

    public void commandes(int idUser) throws FileNotFoundException, DocumentException {
        CommandeService sa = new CommandeService();

        String file_name = "src/com/esprit/commandes.pdf";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file_name));
        document.open();
        Paragraph para = new Paragraph("Mes commandes");
        document.add(para);
        List<Commande> commandes = sa.afficher(idUser);
        PdfPTable table = new PdfPTable(3);

        PdfPCell cl = new PdfPCell(new Phrase("Rue"));
        table.addCell(cl);
        PdfPCell cl1 = new PdfPCell(new Phrase("Adresse"));
        table.addCell(cl1);
             PdfPCell cl11 = new PdfPCell(new Phrase("Code postale"));
        table.addCell(cl11);
       
        table.setHeaderRows(1);
        document.add(table);

        int i = 0;
        for (i = 0; i < commandes.size(); i++) {
            table.addCell(commandes.get(i).getRue());
            table.addCell(commandes.get(i).getAdresse());
            table.addCell(commandes.get(i).getCode());


        }
        document.add(table);

        document.close();

    }

   
}
