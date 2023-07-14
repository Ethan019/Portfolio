package UniversitySystemGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
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
public class LoginWindow
{
	private static JButton LOGIN, DBA_LOGIN;
	private static JLabel USERNAME, PASSWORD;
	private static JTextField USER;
	private static JPasswordField PASS;

	public LoginWindow()
	{
		JPanel panel = new JPanel();
		panel.setLayout(null);

		USERNAME = new JLabel("UNIVERSITY ID");
		USERNAME.setBounds(150, 125, 90, 20);
		panel.add(USERNAME);

		USER = new JTextField();
		USER.setBounds(150, 145, 193, 28);
		panel.add(USER);

		PASSWORD = new JLabel("PASSWORD");
		PASSWORD.setBounds(150, 170, 70, 20);
		panel.add(PASSWORD);

		PASS = new JPasswordField();
		PASS.setBounds(150, 190, 193, 28);
		panel.add(PASS);

		ButtonListener listener = new ButtonListener();

		LOGIN = new JButton("LOGIN");
		LOGIN.setBounds(150, 225, 193, 25);
		LOGIN.setForeground(Color.WHITE);
		LOGIN.setBackground(Color.DARK_GRAY);
		LOGIN.addActionListener(listener);
		panel.add(LOGIN);

		DBA_LOGIN = new JButton("DBA LOGIN");
		DBA_LOGIN.setBounds(380, 330, 96, 25);
		DBA_LOGIN.setForeground(Color.WHITE);
		DBA_LOGIN.setBackground(Color.DARK_GRAY);
		DBA_LOGIN.addActionListener(listener);
		panel.add(DBA_LOGIN);

		JFrame frame = new JFrame();
		frame.setTitle("UNIVERSITY LOGIN PAGE");
		frame.setLocation(new Point(500, 300));
		frame.add(panel);
		frame.setSize(new Dimension(500, 400));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		// frame.setAlwaysOnTop(true);
		frame.setResizable(false);

		LOGIN.addActionListener(e ->
		{

		});

	}

	public class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event) throws IllegalArgumentException
		{
			Object source = event.getSource();
			if (source == LOGIN)
				try
				{
					Connection conn;
					String id = USER.getText(); // here is user id
					String pw = PASS.getText(); // here is password

					//String[] args = { id  }; // <-- UNCOMMENT THIS IN FINAL VERSION
					//String[] args =
					//{ "10000000" }; // <-- TEST args, COMMENT THIS OUT IN FINAL VERSION

					//boolean is_user = true; // MAKE THIS BOOLEAN TRUE OR FALSE DEPENDING ON IF THEY CAN LOGIN AS A USER OR
					// NOT
					//

					//impleented by Ethan
					String is_user = UserLogin.UserLogin(id, pw);

					String[] args = {id};
					if (Objects.equals(is_user, null))
					{
						JOptionPane.showMessageDialog(null, "Incorrect Username or Password" , "ERROR",
								JOptionPane.ERROR_MESSAGE);

					} else
					//impleented by Ethan
					{
						if (Objects.equals(is_user, "Student")) // PLACE HOLDER: determine if they are a student

							StudentMainWindow.main(args);

						if (Objects.equals(is_user, "Professor")) // PLACE HOLDER: determine if they are a professor
							ProfessorMainWindow.main(args);

						if (Objects.equals(is_user, "SchoolAdmin")) // PLACE HOLDER: determine if they are schooladmin
							SchoolAdministratorMainWindow.main(args);
					}

				} catch (SQLException m){
					System.out.println(m.getErrorCode());
				}

			if (source == DBA_LOGIN)
			{

				DBALogin.main(null);

			}
		}
	}

	public static void main(String args[])
	{
		new LoginWindow();
	}

}
