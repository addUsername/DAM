package dam2.add.p3.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Calendar;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import dam2.add.p3.entities.Partida;

public class UtilsPDF {

	private static final String nameFont = FontFamily.HELVETICA.name();
	public static final String TITLEPDF = "partida.pdf";
	public static final String HEADER = "Autogenerated";

	private static Paragraph setHeader() {
		Paragraph p = new Paragraph(HEADER, headerFont());
		p.setAlignment(Element.ALIGN_RIGHT);
		return p;
	}

	public static void generatePDF(Partida game) throws FileNotFoundException, DocumentException {

		String fileName = TITLEPDF;

		Document pdf = new Document();
		PdfWriter.getInstance(pdf, new FileOutputStream(new File(fileName)));
		pdf.open();

		pdf.addTitle(game.getUserName());
		pdf.addAuthor("addUsername");
		pdf.addCreationDate();

		pdf.add(setHeader());
		Paragraph title = new Paragraph("INFORME PARTIDA", titleFont());
		title.setAlignment(Element.ALIGN_CENTER);
		pdf.add(title);
		pdf.add(new Paragraph("\n"));

		float[] size = new float[2];
		size[0] = 2;
		size[1] = 1;
		PdfPTable table = new PdfPTable(size);
		table.setWidthPercentage(95);

		PdfPCell cell = new PdfPCell(new Phrase(parseSubtitle(game), subtitleTableFont()));
		cell.setBorderColor(BaseColor.WHITE);
		table.addCell(cell);

		PdfPCell cellRight = new PdfPCell(new Phrase(parseRightCell(game), subtitleTableFont()));
		cell.setBorderColor(BaseColor.WHITE);
		table.addCell(cellRight);

		pdf.add(table);
		pdf.add(new Paragraph("\n"));
		Paragraph p = new Paragraph("Detalles", sectionFont());
		p.setAlignment(Element.ALIGN_CENTER);
		pdf.add(p);
		pdf.add(new Paragraph("\n"));

		PdfPTable detailsTable = new PdfPTable(3);

		// 2 rows Q and As
		for (int i = 0; i < game.getUserAns().size(); i++) {
			detailsTable.addCell(questionCell(game.getPreguntas().get(i).getQuestion()));

			// true#1#2
			String[] line = game.getUserAns().get(i).split("#");
			detailsTable.addCell(emptyCell());
			detailsTable.addCell(
					userInputCell(game.getPreguntas().get(i).getOptions()[Integer.parseInt(line[1])], line[0]));

			detailsTable.addCell(correctCell(game.getPreguntas().get(i).getOptions()[Integer.parseInt(line[2])]));
		}

		pdf.add(detailsTable);
		pdf.close();
	}

	/*
	 * Celdas de la tabla
	 */

	private static PdfPCell correctCell(String string) {
		PdfPCell cell = new PdfPCell(new Phrase(string, userFont(true)));
		return cell;
	}

	private static PdfPCell userInputCell(String string, String bol) {
		Boolean correct = Boolean.parseBoolean(bol);
		PdfPCell cell = new PdfPCell(new Phrase(string, userFont(correct)));
		if (correct) {
			cell.setBackgroundColor(BaseColor.GREEN);
		} else {
			cell.setBackgroundColor(BaseColor.RED);
		}
		return cell;
	}

	private static PdfPCell questionCell(String question) {

		PdfPCell cell = new PdfPCell(new Phrase(question, questionFont()));
		cell.setColspan(3);
		cell.setBorderColor(BaseColor.WHITE);
		return cell;
	}

	private static PdfPCell emptyCell() {

		PdfPCell cell = new PdfPCell(new Phrase("\n"));
		cell.setBorder(0);
		return cell;
	}

	/*
	 * Textos
	 */
	public static String parseRightCell(Partida game) {

		String text = "";
		text += "SCORE: " + game.getScore() + "/" + game.MAXPREGUNTAS * game.POINTSCORRECTANS;
		text += "\nTotal Ans: " + game.getUserAns().size();
		text += "\nAccepted Ans: " + game.POINTSCORRECTANS;
		text += "\nWrong Ans: " + game.POINTSWRONGANS;
		text += "\nDuration (s): "
				+ (Calendar.getInstance().getTimeInMillis() - game.getCalendar().getTimeInMillis()) / 1000;

		return text;
	}

	public static String parseSubtitle(Partida game) {

		String text = "";
		text += "User: " + game.getUserName();
		text += "\nId match: " + game.getId();
		text += "\nStarted at: " + game.getCalendar().getTime().toString();

		return text;
	}

	/*
	 * Fuentes de texto
	 */

	private static Font questionFont() {
		Font f = new Font();
		f.setFamily(nameFont);
		f.setSize(15);
		return f;
	}

	private static Font titleFont() {
		Font f = new Font();
		f.setFamily(nameFont);
		f.setSize(30);
		f.setColor(159, 168, 218);
		return f;
	}

	private static Font subtitleTableFont() {
		Font f = new Font();
		f.setFamily(nameFont);
		f.setSize(15);
		f.setColor(159, 168, 218);
		return f;
	}

	private static Font sectionFont() {
		Font f = new Font();
		f.setFamily(nameFont);
		f.setSize(25);
		f.setStyle(Font.BOLD);
		f.setColor(159, 168, 218);
		return f;
	}

	private static Font userFont(boolean b) {
		Font f = new Font();
		f.setFamily(nameFont);
		f.setSize(11);
		if (b) {
			f.setColor(BaseColor.BLACK);
		} else {
			f.setColor(BaseColor.WHITE);
		}
		return f;
	}

	private static Font headerFont() {
		Font f = new Font();
		f.setFamily(nameFont);
		f.setSize(10);
		f.setStyle(Font.ITALIC);
		return f;
	}

}
