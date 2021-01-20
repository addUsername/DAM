package p3;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;

import dam2.add.p3.model.MainModel;

public class MainModelTest {

	private MainModel mm;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Mock
	public MainModel mmMock;

	@Before
	public void setUp() throws Exception {
		mm = new MainModel();
		// MockitoAnnotations.initMocks(this);
	}

	@Test
	public void setRecord_writeFileInOrder_true() {

	}

	@Test
	public void chooseQuestions_returnsOK_true() {

		/*
		 * Pregunta p = new Pregunta(); long id = p.getId(); p.setCorrect(5);
		 *
		 * Pregunta[] preguntas = { new Pregunta(), p, new Pregunta(), new Pregunta(),
		 * new Pregunta(), };
		 *
		 * HashMap<Long, Pregunta> pregs = mm.chooseQuestions(preguntas, 5);
		 * Assert.assertNotNull(pregs); Assert.assertTrue(pregs.size() > 0);
		 */
	}

}
