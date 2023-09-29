import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Window.Type;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;

public class FirstPage extends JFrame {

	private JPanel contentPane;
	private JTextField matrixSize;
	private double mainMatrix[][];//array of main mtrix
	private double subMatrix[][];//array of submatrix
	private int size;//the size of matrix
	private JLabel res;//label of answer
	private SecondPage obj;
	static FirstPage frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new FirstPage();
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
	public FirstPage() {
		setTitle("Calculate Determinant");
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 205, 382);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// size of matrix
		matrixSize = new JTextField();
		matrixSize.setBackground(new Color(153, 153, 204));
		matrixSize.setFont(new Font("Agency FB", Font.BOLD, 15));
		matrixSize.setBounds(72, 11, 105, 20);
		contentPane.add(matrixSize);
		matrixSize.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 71, 167, 158);
		contentPane.add(scrollPane);

		JTextArea matrix = new JTextArea();
		matrix.setBackground(new Color(153, 153, 204));
		matrix.setFont(new Font("Agency FB", Font.BOLD, 15));
		scrollPane.setViewportView(matrix);

		JButton btnNewButton = new JButton("O K");
		btnNewButton.setForeground(Color.PINK);
		btnNewButton.setBackground(new Color(153, 153, 204));
		btnNewButton.setFont(new Font("Agency FB", Font.BOLD, 21));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// the size of matrix
				size = Integer.parseInt(matrixSize.getText().trim());
				// 2*2 Array for matrix
				mainMatrix = new double[size][size];
				String tmp = matrix.getText();

				for (int i = 0; i < size; i++) {
					for (int j = 0; j < size; j++) {
						mainMatrix[i][j] = Double.parseDouble(tmp.split("\n")[i].split(" ")[j]);
					}
				}

				res.setText(threeDigits(calculateDet(mainMatrix, size)));

			}
		});
		btnNewButton.setBounds(10, 240, 117, 39);
		contentPane.add(btnNewButton);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(255, 175, 175), 2, true), "R e s u l t",
				TitledBorder.LEFT, TitledBorder.TOP, null, Color.PINK));
		panel.setBackground(new Color(153, 153, 204));
		panel.setBounds(10, 290, 167, 42);
		contentPane.add(panel);
		panel.setLayout(null);

		res = new JLabel("");
		res.setFont(new Font("Agency FB", Font.BOLD, 15));
		res.setHorizontalAlignment(SwingConstants.CENTER);
		res.setBounds(0, 0, 167, 42);
		panel.add(res);

		JLabel lblSIZ = new JLabel("S i z e :");
		lblSIZ.setHorizontalAlignment(SwingConstants.LEFT);
		lblSIZ.setFont(new Font("Agency FB", Font.BOLD, 20));
		lblSIZ.setBounds(10, 3, 60, 33);
		contentPane.add(lblSIZ);

		JLabel lblENT = new JLabel("E n t e r  The  M a t r i x : ");
		lblENT.setFont(new Font("Agency FB", Font.BOLD, 20));
		lblENT.setBounds(10, 34, 158, 26);
		contentPane.add(lblENT);

		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				obj.main(null);
				frame.dispose();
			}
		});
		button.setForeground(Color.PINK);
		button.setFont(new Font("Agency FB", Font.BOLD, 18));
		button.setIcon(new ImageIcon("C:\\Users\\user\\Pictures\\2.png"));
		button.setBounds(137, 240, 40, 39);
		contentPane.add(button);
	}

	// make sub matrix
	public double[][] calculateSubMatrix(double[][] matrix, int size, int column) {

		// subMatrix (size-1)*(size-1)
		subMatrix = new double[size - 1][];
		for (int i = 0; i < size - 1; i++)
			subMatrix[i] = new double[size - 1];

		for (int i = 1; i < size; i++) {
			int subColumn = 0;
			for (int j = 0; j < size; j++) {
				if (j != column) {
					subMatrix[i - 1][subColumn] = matrix[i][j];
					subColumn++;
				}
			}
		}

		return subMatrix;
	}

	public double calculateDet(double[][] matrix, int size) {

		double res = 0;

		// 1*1
		if (size == 1) {
			res = matrix[0][0];
		}
		// 2*2
		else if (size == 2) {
			res = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
		}
		// n*n
		else {

			for (int j = 0; j < size; j++) {
				subMatrix = calculateSubMatrix(matrix, size, j);
				res += Math.pow(-1, 1 + (j + 1)) * matrix[0][j] * calculateDet(subMatrix, size - 1);
			}

		}

		return res;
	}

	// up to 3 decimal places
	private String threeDigits(double x) {

		String tmp = String.valueOf(x);// convert double to string
		String tmp2 = "";
		boolean l = false;//find digits after "."
		boolean moreThan3 = false;
		int counter = 0;
		for (int i = 0; i < tmp.length(); i++) {

			if (tmp.charAt(i) != '.') {

				if (l) {//digits after "."
					counter++;
				}

				tmp2 += tmp.charAt(i);

				if (counter == 3) {
					moreThan3 = true;
					break;
				}
			}
			if (tmp.charAt(i) == '.') {
				tmp2 += tmp.charAt(i);
				l = true;
			}
		}

		if (moreThan3)
			return tmp2;

		else {
			for (int i = counter; i < 3; i++) {
				tmp2 += "0";
			}
		}

		return tmp2;

	}
}
