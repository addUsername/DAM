package dam2.add.p3.model;

import java.io.File;
import java.io.IOException;

import dam2.add.p3.entities.Pregunta;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * https://www.zamzar.com/es/convert/xlsx-to-xls/
 *
 * @author SERGI
 *
 */
public class UtilsExcel {

	/**
	 * Seleciona las columnas | texto | respuesta1 | respuesta2 | respuesta3 |
	 * correcta | y las mapea a Pregunta[]
	 * 
	 * @param path
	 * @return
	 * @throws BiffException
	 * @throws IOException
	 */
	public static Pregunta[] parseFile(String path) throws BiffException, IOException {

		path = "preguntas.xls";
		Workbook wb = Workbook.getWorkbook(new File(path));
		Sheet sheet = wb.getSheet(0);

		Cell[] text = sheet.getColumn(sheet.findLabelCell("texto").getColumn());
		Cell[] a1 = sheet.getColumn(sheet.findLabelCell("respuesta1").getColumn());
		Cell[] a2 = sheet.getColumn(sheet.findLabelCell("respuesta2").getColumn());
		Cell[] a3 = sheet.getColumn(sheet.findLabelCell("respuesta3").getColumn());
		Cell[] correct = sheet.getColumn(sheet.findLabelCell("correcta").getColumn());

		Pregunta[] preguntas = new Pregunta[text.length - 1];
		Pregunta p = null;
		String[] ops = null;

		for (int i = 1; i < text.length; i++) {
			p = new Pregunta();
			ops = new String[3];

			p.setQuestion(text[i].getContents());
			p.setCorrect(Integer.parseInt(correct[i].getContents()));

			ops[0] = a1[i].getContents();
			ops[1] = a2[i].getContents();
			ops[2] = a3[i].getContents();
			p.setOptions(ops);

			preguntas[i - 1] = p;
		}
		return preguntas;
	}
}
