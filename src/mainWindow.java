import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class mainWindow {

	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		startingWindow sw = new startingWindow();
		sw.setVisible(true);
		

	}

}
