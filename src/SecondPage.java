import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Window.Type;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;

public class SecondPage extends JFrame {

	private JPanel contentPane;
	private String result2 = "";//final answer
	private JTextArea textArea_1;//field of answer
	private FirstPage obj;
	static SecondPage frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame  = new SecondPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SecondPage() {
		setTitle("Compute Sum Of Nums");
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 213, 352);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 37, 179, 50);
		contentPane.add(scrollPane);

		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Agency FB", Font.BOLD, 15));
		textArea.setBackground(new Color(153, 153, 204));
		scrollPane.setViewportView(textArea);

		JButton btnNewButton = new JButton("O K");
		btnNewButton.setBackground(new Color(153, 153, 204));
		btnNewButton.setForeground(Color.PINK);
		btnNewButton.setFont(new Font("Agency FB", Font.BOLD, 21));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				textArea_1.setText("");
				result2 = "";
				textArea_1.setText(returnResult(textArea.getText()));
				
			}
		});
		btnNewButton.setBounds(10, 263, 129, 39);
		contentPane.add(btnNewButton);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 125, 179, 127);
		contentPane.add(scrollPane_1);

		textArea_1 = new JTextArea();
		textArea_1.setFont(new Font("Agency FB", Font.BOLD, 15));
		textArea_1.setBackground(new Color(153, 153, 204));
		scrollPane_1.setViewportView(textArea_1);

		JLabel lblEnterHere = new JLabel("E n t e r   H E R E : ");
		lblEnterHere.setFont(new Font("Agency FB", Font.BOLD, 20));
		lblEnterHere.setBounds(10, 6, 179, 24);
		contentPane.add(lblEnterHere);

		JLabel lblRES = new JLabel("R e s u l t : ");
		lblRES.setFont(new Font("Agency FB", Font.BOLD, 20));
		lblRES.setBounds(10, 90, 179, 24);
		contentPane.add(lblRES);

		JButton button_15 = new JButton("");
		button_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				obj.main(null);
				frame.dispose();
			}
		});
		button_15.setIcon(new ImageIcon("C:\\Users\\user\\Pictures\\2.png"));
		button_15.setBounds(149, 263, 40, 39);
		contentPane.add(button_15);
	}

	private int computeSum(String s) {
		String tmp = "";
		String subSet = "";//internal set
		int result = 0;//sum of numbers in each sub
		int open = 0;//counter of "{"
		int close = 0;//counter of "}"

		for (int i = 1; i < s.length() - 1; i++) {
			
			if (s.charAt(i) == '{') {
				tmp = "";
				open++;
			}
			
			if (s.charAt(i) == '}') {
				tmp = "";
				close++;
				if (close == open) {
					subSet += "}";
					result += computeSum(subSet);
					subSet = "";
					continue;
				}
			}
			
			//find subset
			if (open != close) {
				subSet += s.charAt(i);
			} else {// open == close

				if (s.charAt(i) != ',' && s.charAt(i) != '{') {
					tmp += s.charAt(i);
				} else if (!tmp.equals("")) {
					result += Double.parseDouble(tmp);
					tmp = "";
				}
			}
		}
		
		//for last Num
		if (!tmp.equals("")) {
			result += Integer.parseInt(tmp);
			tmp = "";
		}

		result2 += result + "\r" + "\n";
		return (int) result;

	}

	//method of print result2
	private String returnResult(String s) {

		computeSum(s);
		return result2;
	}
}
