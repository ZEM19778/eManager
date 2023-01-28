package com.emanager.emanager_demo.service;

import com.emanager.emanager_demo.model.Baustelle;
import com.emanager.emanager_demo.model.Dienste;
import com.emanager.emanager_demo.model.User;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class UserPDFExporter {
    private List<Dienste> listDienste = new ArrayList<>();
    public UserPDFExporter(List<Dienste> listDienste){
        this.listDienste = listDienste;
    }

    private void writeTableHeader(PdfPTable table){

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.ORANGE);
        cell.setPadding(5);

        Font font = FontFactory.getFont("Helvetica");
        font.setColor(Color.BLACK);

        //cell.setPhrase(new Phrase("Tag", font));
        //table.addCell(cell);

        cell.setPhrase(new Phrase("Datum", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Arbeitsort", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Von", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Bis", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Dauer", font));
        table.addCell(cell);
    }

    String pattern = "dd.MM.yyyy";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    private void writeTableData(PdfPTable table) {
        for (Dienste dienste : listDienste) {
            //Datum
            String date = simpleDateFormat.format(dienste.getDatumvon());
            table.addCell(date);
            //Arbeitsort
            table.addCell(dienste.getAddresse());
            //Zeit von
            table.addCell(String.valueOf(dienste.getZeitvon()));
            //Zeit bis
            table.addCell(String.valueOf(dienste.getZeitbis()));
            //Dienstdauer
            table.addCell(String.valueOf(dienste.getDauer()));

        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4.rotate(),0,0,0,0);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLACK);

        Image img = Image.getInstance("eManager_demo/src/main/resources/templates/img/Logo_V2.jpg");
        img.setAlignment(Image.RIGHT);

        Phrase ph = new Phrase("Wochenzettel", font);
        Paragraph p = new Paragraph("Wochenzettel", font);
        p.setSpacingBefore(350.8f);

        document.add(img);
        document.add(p);
        document.add(ph);


        //document.add(p);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(90f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
