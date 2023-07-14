package UniversitySystemGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseListener;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
//AUTHOR: JARED KEKLAK (UNLESS ANNOTATED)
public class DBATerminal
{

	private static JFrame terminal;
	private static JInternalFrame internal_frame_input;
	private static JInternalFrame internal_frame_output;
	private static JTextArea text_area_input;
	private static JTextArea text_area_output;
	private static JButton send_command, clear;

	public static void main()
	{
		terminal = new JFrame();

		terminal.setLayout(null);
		terminal.setSize(450, 350);
		terminal.setLocation(new Point(500, 300));
		terminal.setTitle("DBA: TERMINAL MODE");
		terminal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		terminal.setVisible(true);
		terminal.setResizable(false);

		text_area_input = new JTextArea(50, 40);
		text_area_input.setBackground(Color.gray);
		text_area_input.setForeground(Color.BLACK);
		Font i = new Font("Consolas", Font.PLAIN, 12);
		text_area_input.setFont(i);
		text_area_input.setEditable(true);
		text_area_input.setSize(410, 220);
		text_area_input.setLocation(10, 50);

		text_area_output = new JTextArea(50, 40);
		text_area_output.setBackground(Color.BLACK);
		text_area_output.setForeground(Color.GREEN);
		Font o = new Font("Consolas", Font.PLAIN, 12);
		text_area_output.setFont(o);
		text_area_output.setEditable(false);
		text_area_output.setSize(410, 220);
		text_area_output.setLocation(10, 50);

		PrintStream printStream = new PrintStream(new CustomOutputStream(text_area_output, false));
		System.setOut(printStream);
		System.setErr(printStream);

		internal_frame_input = new JInternalFrame("Enter Command");
		internal_frame_input.setResizable(false);
		internal_frame_input.setVisible(true);
		internal_frame_input.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
		internal_frame_input.setSize(410, 100);
		internal_frame_input.setResizable(false);
		internal_frame_input.setLocation(10, 10);

		JScrollPane scroll_panel_input = new JScrollPane(text_area_input);
		scroll_panel_input.setBackground(Color.darkGray);

		internal_frame_input.add(scroll_panel_input);

		internal_frame_output = new JInternalFrame("Terminal Output");
		internal_frame_output.setResizable(false);
		internal_frame_output.setVisible(true);
		internal_frame_output.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
		internal_frame_output.setSize(410, 160);
		internal_frame_output.setResizable(false);
		internal_frame_output.setLocation(10, 115);

		JScrollPane scroll_panel_output = new JScrollPane(text_area_output);
		scroll_panel_output.setBackground(Color.darkGray);

		internal_frame_output.add(scroll_panel_output);

		// prevent user from moving internal frame, disable mouse listener for frame
		for (MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) internal_frame_input.getUI())
				.getNorthPane().getMouseListeners())
		{
			((javax.swing.plaf.basic.BasicInternalFrameUI) internal_frame_input.getUI()).getNorthPane()
					.removeMouseListener(listener);
		}

		// prevent user from moving internal frame, disable mouse listener for frame
		for (MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) internal_frame_output.getUI())
				.getNorthPane().getMouseListeners())
		{
			((javax.swing.plaf.basic.BasicInternalFrameUI) internal_frame_output.getUI()).getNorthPane()
					.removeMouseListener(listener);
		}

		send_command = new JButton("Send Command");
		send_command.setBounds(240, 280, 130, 25);
		send_command.setForeground(Color.WHITE);
		send_command.setBackground(Color.DARK_GRAY);
		send_command.addActionListener(e ->
		{
			String command = text_area_input.getText();
			// ========================================
			/*
			 * run sql query here, use system.out to print results information designed to
			 * be displayed using System.print... Console Output it directed to new internal
			 * frame created. ========================================
			 */

			System.out.println("!!!EXAMPLE OUTPUT!!!");
			System.out.println(command);

		});

		clear = new JButton("Clear");
		clear.setBounds(80, 280, 130, 25);
		clear.setForeground(Color.WHITE);
		clear.setBackground(Color.DARK_GRAY);
		clear.addActionListener(e ->
		{
			text_area_output.setText("");
		});

		terminal.add(send_command);
		terminal.add(clear);

		terminal.add(internal_frame_input);
		terminal.add(internal_frame_output);

	}

}
