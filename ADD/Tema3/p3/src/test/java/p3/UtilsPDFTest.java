package p3;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.itextpdf.text.DocumentException;

import dam2.add.p3.entities.Partida;
import dam2.add.p3.model.UtilsPDF;

public class UtilsPDFTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void generatePdf_isFileCreated_true() throws FileNotFoundException, DocumentException {
		File file = new File(UtilsPDF.TITLEPDF);
		if (file.exists()) {
			file.delete();
		}
		Partida game = new Partida();
		game.setUserName("TEST");
		game.addPoints();

		UtilsPDF.generatePDF(game);
		File file2 = new File(UtilsPDF.TITLEPDF);
		Assert.assertTrue(file2.exists());
		Assert.assertTrue(file2.length() > 1);
	}

	@Test
	public void parseRightCell_goodFormat_notNullTrue() {

		Partida game = new Partida();
		game.addPoints();

		String cellText = UtilsPDF.parseRightCell(game);
		String[] lines = cellText.split("\n");

		Assert.assertNotNull(cellText);
		Assert.assertTrue(lines.length == 5);
		Assert.assertTrue(lines[0].contains("1"));
	}

	@Test
	public void parseSubtitle_goodFormat_notNullTrue() {
		String name = "test";
		Partida game = new Partida();
		game.setUserName(name);

		String cellText = UtilsPDF.parseSubtitle(game);
		String[] lines = cellText.split("\n");

		Assert.assertNotNull(cellText);
		Assert.assertTrue(lines.length == 3);
		Assert.assertTrue(lines[0].contains(name));
		Assert.assertTrue(lines[2].contains(game.getCalendar().getTime().toString()));
	}

}
