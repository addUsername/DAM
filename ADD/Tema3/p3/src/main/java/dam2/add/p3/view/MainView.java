package dam2.add.p3.view;

import java.util.Scanner;

import dam2.add.p3.interfaces.MainViewImpl;

/**
 *
 * @author SERGI
 *
 */
public class MainView implements MainViewImpl {

	private Scanner sc;

	public MainView() {
		super();
		sc = new Scanner(System.in);
	}

	public int showMenu() {
		Print.printMessage(Text.MENU);
		Print.printOptions(Strings.OPTIONS);
		Print.printMessage(Strings.IN);

		return getInt();
	}

	public String[] requestQuestionFile() {
		String[] toReturn = new String[5];
		Print.printMessage(Strings.REQXMLQUESION);
		toReturn[0] = sc.nextLine();
		Print.printMessage(Strings.REQXMLOPTION1);
		toReturn[1] = sc.nextLine();
		Print.printMessage(Strings.REQXMLOPTION2);
		toReturn[2] = sc.nextLine();
		Print.printMessage(Strings.REQXMLOPTION3);
		toReturn[3] = sc.nextLine();
		Print.printMessage(Strings.REQXMLANS);
		toReturn[4] = sc.nextLine();
		return toReturn;
	}

	public String requestImportFile() {
		Print.printMessage(Strings.REQXLSFILEPATH);
		return sc.nextLine();
	}

	public void showQuestion(String question) {
		Print.printMessage(Text.SHOWQUESTION);
		Print.printMessage(question);
		return;
	}

	private int getInt() {
		int i;
		try {
			i = Integer.parseInt(sc.nextLine());
		} catch (Exception e) {
			Print.inputError();
			return getInt();
		}
		return i;
	}

	public void correct() {
		Print.printMessage(Text.TRUE);
		return;
	}

	public void incorrect() {
		Print.printError(Text.FALSE);
		return;
	}

	public int showEndMenu(String[] data) {

		Print.printMessage(Strings.ENDGAME_SCORE + " " + data[0]);
		Print.printMessage(Strings.ENDGAME_ANS + " " + data[1]);
		Print.printOptions(Strings.ENDMENU2);
		Print.printMessage(Strings.IN);

		return getInt();
	}

	public void showPDF() {
		Print.printMessage(Strings.WAIT);
		sc.hasNextLine();
		return;
	}

	public void showRecords(String[] lines) {
		Print.printMessage(Text.RECORDS);
		Print.printOptions(lines);
		Print.printMessage(Strings.WAIT);
		sc.nextLine();
		return;
	}

	public void showWiki(String[] lines) {
		Print.printMessage(Text.INSTRUCTIONS);
		Print.printOptions(lines);
		Print.printMessage(Strings.WAIT);
		sc.nextLine();
		return;
	}

	public String getName() {
		Print.printMessage(Text.END);
		Print.printMessage(Strings.ENDMENU1);
		return sc.nextLine();
	}

	public int showAnswers(String[] posibleAnswers) {
		Print.printOptions(posibleAnswers);
		Print.printMessage(Strings.IN);
		return getInt();
	}

	public void showClose() {
		Print.printMessage(Text.EXIT);
		return;
	}

	public void showInit() {
		Print.printMessage(Text.INIT);
		return;
	}

	public int[] showConfig() {
		int[] toReturn = new int[3];
		Print.printMessage(Text.CONFIG);
		Print.printMessage(Strings.CONFIGMENU1);
		toReturn[0] = getInt();
		Print.printMessage(Strings.CONFIGMENU2);
		toReturn[1] = getInt();
		Print.printMessage(Strings.CONFIGMENU3);
		toReturn[2] = getInt();

		return toReturn;
	}

	public void processError() {
		Print.printError(Strings.PROCESSERROR);
		return;
	}

	public void proccesOK() {
		Print.printMessage(Strings.PROCESSOK);
		return;
	}

	public int overwriteQuestions() {
		// TODO Auto-generated method stub
		Print.printMessage(Strings.OVERRIDEQUESTIONS);
		Print.printOptions(Strings.XLSOPTIONS);
		return getInt();
	}

}
