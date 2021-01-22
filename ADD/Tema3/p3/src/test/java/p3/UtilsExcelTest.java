package p3;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dam2.add.p3.entities.Pregunta;
import dam2.add.p3.model.UtilsExcel;
import jxl.read.biff.BiffException;

public class UtilsExcelTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void parseFile_correctParse_true() throws BiffException, IOException {
		String path = "src/test/resources/preguntas.xls";
		String text = "test2";
		int correct = 2;

		Pregunta[] p = UtilsExcel.parseFile(path);

		Assert.assertNotNull(p);
		Assert.assertTrue(p.length == 3);
		Assert.assertTrue(p[1].getQuestion().equals(text));
		Assert.assertTrue(p[1].getCorrect() == correct);
	}

}
