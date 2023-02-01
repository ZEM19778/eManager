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
    private List<Dienste> dienstes = new ArrayList<>();
    public UserPDFExporter(List<Dienste> listDienste){
        this.listDienste = listDienste;
    }

    private void writeTableHeader(PdfPTable table){

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.getHSBColor(21,96,87));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
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

        cell.setPhrase(new Phrase("Überstunden", font));
        table.addCell(cell);
    }

    String pattern = "dd.MM.yyyy";
    Float summe = 0f;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    private void writeTableData(PdfPTable table) {
        for (Dienste dienste : listDienste) {
            //Datum
            String date = simpleDateFormat.format(dienste.getDatumvon());
            table.addCell(date);
            //Arbeitsort
            Baustelle b = dienste.getAddresse();
            table.addCell(b.getBezeichnung());
            //Zeit von
            table.addCell(String.valueOf(dienste.getZeitvon()));
            //Zeit bis
            table.addCell(String.valueOf(dienste.getZeitbis()));
            //Dienstdauer
            table.addCell(String.valueOf(dienste.getDauer()+"h"));
            //Überstunden
            table.addCell("   ");
            summe += dienste.getDauer();
            dienstes.add(dienste);
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4.rotate(),0,0,0,0);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(90f);
        table.setWidths(new float[] {1.5f, 2.5f, 1.5f, 1.5f, 1.0f,1.0f});
        table.setSpacingBefore(10);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

        writeTableHeader(table);
        writeTableData(table);

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(25);
        font.setColor(Color.BLACK);

        Font f1 = FontFactory.getFont(FontFactory.HELVETICA);
        f1.setSize(18);

        Image img = Image.getInstance("eManager_demo/src/main/resources/templates/img/Logo.jpg");
        img.setAlignment(Image.RIGHT);
        img.setSpacingBefore(0);
        img.setSpacingAfter(0);

        Phrase ph = new Phrase("Wochenzettel", font);

        Paragraph p = new Paragraph();
        p.add(ph);
        p.setIndentationLeft(43);
        p.setSpacingBefore(0);
        p.setSpacingAfter(0);
        p.setAlignment(Element.ALIGN_LEFT);

        document.add(img);
        document.add(p);

        Dienste d = dienstes.get(0);
        String mitarbeiter = d.getMitarbeiter();
        Paragraph user = new Paragraph("Mitarbeiter: " + mitarbeiter, f1);
        user.setIndentationLeft(43);

        Paragraph stunden = new Paragraph("Stundensumme: " + summe + "h", f1);
        stunden.setAlignment(Element.ALIGN_RIGHT);
        stunden.setIndentationRight(45);

        Font unterschrift = new Font(FontFactory.getFont(FontFactory.HELVETICA));
        unterschrift.setSize(15);
        PdfPTable unterschriften = new PdfPTable(2);
        PdfPCell zelle1 = new PdfPCell(new Phrase("Unterschrift von " + mitarbeiter + ": ", unterschrift));
        zelle1.setHorizontalAlignment(Element.ALIGN_CENTER);
        PdfPCell zelle2 = new PdfPCell(new Phrase("Unterschrift der Geschäftsführung: ", unterschrift));
        zelle2.setHorizontalAlignment(Element.ALIGN_CENTER);
        PdfPCell zelle3 = new PdfPCell(new Phrase("__________________________", unterschrift));
        zelle3.setHorizontalAlignment(Element.ALIGN_CENTER);
        PdfPCell zelle4 = new PdfPCell(new Phrase("__________________________", unterschrift));
        zelle4.setHorizontalAlignment(Element.ALIGN_CENTER);
        PdfPCell leerZelle = new PdfPCell(new Phrase("  "));
        leerZelle.setHorizontalAlignment(Element.ALIGN_CENTER);

        unterschriften.setWidths(new float[] {3.5f,3.5f});
        zelle1.setBorder(Rectangle.NO_BORDER);
        zelle2.setBorder(Rectangle.NO_BORDER);
        zelle3.setBorder(Rectangle.NO_BORDER);
        zelle4.setBorder(Rectangle.NO_BORDER);
        leerZelle.setBorder(Rectangle.NO_BORDER);
        unterschriften.addCell(zelle1);
        unterschriften.addCell(zelle2);
        unterschriften.addCell(leerZelle);
        unterschriften.addCell(leerZelle);
        unterschriften.addCell(zelle3);
        unterschriften.addCell(zelle4);

        Phrase phrase = new Phrase(" ");
        document.add(user);

        document.add(table);

        document.add(stunden);

        document.add(phrase);

        document.add(unterschriften);

        document.close();

    }
}
