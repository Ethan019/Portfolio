package UniversitySystemGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

//AUTHOR: JARED KEKLAK (UNLESS ANNOTATED)
public class DBALogin
{
	private static JButton LOGIN, RETURN;
	private static JLabel USERNAME, PASSWORD, DBAID;
	private static JTextField USER;
	private static JPasswordField PASS, ID;

	public DBALogin()
	{
		JPanel panel = new JPanel();
		panel.setLayout(null);

		USERNAME = new JLabel("UNIVERSITY ID");
		USERNAME.setBounds(150, 85, 90, 20);
		panel.add(USERNAME);

		USER = new JTextField();
		USER.setBounds(150, 105, 193, 28);
		panel.add(USER);

		PASSWORD = new JLabel("PASSWORD");
		PASSWORD.setBounds(150, 130, 70, 20);
		panel.add(PASSWORD);

		PASS = new JPasswordField();
		PASS.setBounds(150, 150, 193, 28);
		panel.add(PASS);

		DBAID = new JLabel("DBA ID");
		DBAID.setBounds(150, 175, 70, 20);
		panel.add(DBAID);

		ID = new JPasswordField();
		ID.setBounds(150, 195, 193, 28);
		panel.add(ID);

		ButtonListener listener = new ButtonListener();

		LOGIN = new JButton("LOGIN");
		LOGIN.setBounds(150, 225, 193, 25);
		LOGIN.setForeground(Color.WHITE);
		LOGIN.setBackground(Color.DARK_GRAY);
		LOGIN.addActionListener(listener);
		panel.add(LOGIN);

		RETURN = new JButton("RETURN");
		RETURN.setBounds(380, 330, 96, 25);
		RETURN.setForeground(Color.WHITE);
		RETURN.setBackground(Color.DARK_GRAY);
		panel.add(RETURN);

		JFrame frame = new JFrame();
		frame.setTitle("UNIVERSITY LOGIN PAGE");
		frame.setLocation(new Point(500, 300));
		frame.add(panel);
		frame.setSize(new Dimension(500, 400));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);

		RETURN.addActionListener(e ->
		{
			frame.dispose();
		});

		LOGIN.addActionListener(e ->
		{

		});

		// frame.setAlwaysOnTop(true);

	}

	public class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event) throws IllegalArgumentException
		{
			//impleented by Ethan
			Object source = event.getSource();
			if (source == LOGIN)
			try {
				{
					String id = USER.getText(); // here is user id
					String pw = PASS.getText(); // here is password
					String dbaid = ID.getText(); // here is dbaid

					 String[] args = { id }; // <-- UNCOMMENT THIS IN FINAL VERSION
					///String[] args =
					///{ "10000000" }; // <-- TEST args, COMMENT THIS OUT IN FINAL VERSION

					//boolean is_user = true; // MAKE THIS BOOLEAN TRUE OR FALSE DEPENDING ON IF THEY CAN LOGIN AS A DBA OR
					// NOT

					String is_user = UserLogin.AdminLogin(id, pw, dbaid);

					//impleented by Ethan
					if (Objects.equals(is_user, null)) {
						JOptionPane.showMessageDialog(null, "Incorrect Username or Password", "ERROR",
								JOptionPane.ERROR_MESSAGE);
						DBAMainWindow.main(args);

					} else {
						if(Objects.equals(is_user, "DBADMIN"))
						DBAMainWindow.main(args);
					}
				}
			}catch (SQLException m){
				System.out.println(m.getMessage());
			}

		}
	}

	public static void main(String args[])
	{
		new DBALogin();
	}

}
