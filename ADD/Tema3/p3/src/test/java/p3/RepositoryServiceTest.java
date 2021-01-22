/**
 *
 */
package p3;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dam2.add.p3.model.RepositoryService;

/**
 * @author SERGI
 *
 */
public class RepositoryServiceTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// MockitoAnnotations.initMocks(this);

	}

	@Test
	public void getLines_getWikiGoodPath_NotNullNotEmpty() {

		String path = "src/test/resources/wiki.txt";

		String[] lines = RepositoryService.getLines(path, true);

		Assert.assertNotNull(lines);
		Assert.assertTrue(lines.length > 0);
	}

	@Test
	public void getLines_getWikiBadPath_null() {

		String path = "src/test/resources/wiki2.txt";

		String[] lines = RepositoryService.getLines(path, true);

		Assert.assertNull(lines);
	}

	@Test
	public void getLines_getRecordsGoodFormat_true() {

		String path = "src/test/resources/records.txt";
		String[] lines = RepositoryService.getLines(path, true);

		Assert.assertNotNull(lines);
		Assert.assertTrue(lines.length == 3);
		Assert.assertTrue(lines[0].split("#")[0].equals("Juan"));
	}

	@Test
	public void getLines_getRecordsGoodPath_correctFileNotNullNotEmpty_true() {

		String path = "src/test/resources/records.txt";

		String[] lines = RepositoryService.getLines(path, false);

		Assert.assertNotNull(lines);
		Assert.assertTrue(lines.length > 0);
	}

	@Test
	public void getLines_getRecordsBadPath_null() {

		String path = "src/test/resources/records2.txt";

		String[] lines = RepositoryService.getLines(path, false);

		Assert.assertNull(lines);
	}
}
