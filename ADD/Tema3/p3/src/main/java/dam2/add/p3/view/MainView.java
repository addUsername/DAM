package dam2.add.p3.view;

import java.io.InputStream;
import java.util.Scanner;

import dam2.add.p3.interfaces.MainControllerViewImpl;
import dam2.add.p3.interfaces.MainViewImpl;

/**
 *
 * @author SERGI
 *
 */
public class MainView implements MainViewImpl {

	private MainControllerViewImpl controller;
	private UploadService uploadService;

	public MainView(MainControllerViewImpl controller) {
		super();
		this.controller = controller;
	}

	public void showMenu() {

		Print.printMessage(Text.MENU);
		Print.printOptions(Strings.OPTIONS);

		int i = getInput();
		controller.mainMenuOptionSelected(i);
	}

	private int getInput() {
		// TODO Auto-generated method stub
		String option = new Scanner(System.in).nextLine();
		int toReturn = 0;
		try {
			toReturn = Integer.parseInt(option);
		} catch (NumberFormatException e) {

			Print.inputError();
			Print.printMessage(Strings.IN);
			toReturn = getInput();
		}
		return toReturn;
	}

	public void requestQuestionFile() {
		// TODO UploadFileService xml
		Scanner sc = new Scanner(System.in);
		Print.printMessage(Strings.REQXMLFILEPATH);
		InputStream in = uploadService.getUpload(sc.nextLine());
		controller.uploadQuestion(in);
	}

	public void requestImportFile() {
		// TODO UploadFileService xls
		Scanner sc = new Scanner(System.in);
		Print.printMessage(Strings.REQXMLFILEPATH);
		InputStream in = uploadService.getUpload(sc.nextLine());
		controller.importFile(in);
	}

}
