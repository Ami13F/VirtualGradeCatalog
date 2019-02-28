package Utils;

import com.itextpdf.text.*;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;


public class GeneratePDF {


    public static <K, V> void generatePDF(String fileName, Map<K,V> map){
        OutputStream file = null;
        Stage stage = new Stage ();
        if(fileName.equals("")){
            return;
        }
        try {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Open Resource File");
            File selectedDirectory = directoryChooser.showDialog(stage);
            if(selectedDirectory == null) //daca nu e selectat un director
                return;

            String path = selectedDirectory.toPath().toString().replace( '\\','/');

            File filePath = new File(path+"/"+fileName+".pdf");
            if(filePath.exists()){
                filePath.delete();
            }
            file = new FileOutputStream(filePath);

        Document document = new Document();
        PdfWriter.getInstance(document, file);

        Font f=new Font(Font.FontFamily.TIMES_ROMAN,40.0f,Font.UNDERLINE, WebColors.getRGBColor("#FE3243"));
        Paragraph title = new Paragraph(fileName,f);
        title.setAlignment(Paragraph.ALIGN_CENTER);

        document.open();
        document.add(title);
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));

        document.addTitle("Raport");
        map.forEach((k,v)-> {
            PdfPTable table = new PdfPTable(2);
            PdfPCell cell1 = new PdfPCell(new Paragraph(new Paragraph(k.toString() )) );
            PdfPCell cell2 = new PdfPCell(new Paragraph(new Paragraph(" Media: "+v.toString())));
            String[] rez = v.toString().split(" ");
            if(Float.parseFloat(rez[0]) < 5f){
                cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell2.setBackgroundColor(WebColors.getRGBColor("#FE3243"));
            }else {
                cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell2.setBackgroundColor(WebColors.getRGBColor("#209CB6"));
            }
            table.addCell(cell1);
            table.addCell(cell2);
            try {
                document.add(table);
            } catch (DocumentException e) {
                e.printStackTrace();
            }

        });


        document.close();

        file.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        try {

            OutputStream file = new FileOutputStream(new File("C:/Users/ameli/Desktop/ana.pdf"));


            Document document = new Document();

            PdfWriter.getInstance(document, file);


            document.open();

            document.add(new Paragraph("Hello World, iText"));

            document.add(new Paragraph(new Date().toString()));


            document.close();

            file.close();


        } catch (Exception e) {


            e.printStackTrace();

        }

    }

}