package com.emanager.emanager_demo.service;

import com.emanager.emanager_demo.model.Dienste;
import com.emanager.emanager_demo.model.User;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class UserPDFExporter {
    private List<Dienste> listDienste;
    public UserPDFExporter(List<Dienste> listDienste){
        this.listDienste = listDienste;
    }

    private void writeTableHeader(PdfPTable table){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.ORANGE);
        cell.setPadding(5);

        Font font = FontFactory.getFont("Helvetica");
        font.setColor(Color.BLACK);

        cell.setPhrase(new Phrase("Datum", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Von", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Bis", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Dauer", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Baustelle", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (Dienste dienste : listDienste) {
            table.addCell(String.valueOf(dienste.getDatumvon()));
            table.addCell(String.valueOf(dienste.getZeitvon()));
            table.addCell(String.valueOf(dienste.getZeitbis()));
            table.addCell(String.valueOf(dienste.getDauer()));
            table.addCell(dienste.getAddresse());
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.ORANGE);

        Paragraph p = new Paragraph("List of Users", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
