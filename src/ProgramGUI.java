import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ProgramGUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProgramGUI window = new ProgramGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ProgramGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 897, 567);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea textAreaOutput = new JTextArea();
		textAreaOutput.setBounds(511, 117, 307, 332);
		frame.getContentPane().add(textAreaOutput);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(58, 85, 48, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel typeLabel = new JLabel("Type:");
		typeLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		typeLabel.setBounds(499, 42, 66, 39);
		frame.getContentPane().add(typeLabel);
		
		JLabel resultLabel = new JLabel("null");
		resultLabel.setForeground(Color.RED);
		resultLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		resultLabel.setBounds(569, 28, 217, 70);
		frame.getContentPane().add(resultLabel);
		
		JButton takePictureBtn = new JButton("Take Picture");
		takePictureBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Camera.capture();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		takePictureBtn.setBounds(58, 318, 114, 23);
		frame.getContentPane().add(takePictureBtn);
		
		JButton scanPictureBtn = new JButton("Scan Picture");
		scanPictureBtn.setBounds(276, 318, 119, 23);
		frame.getContentPane().add(scanPictureBtn);
		scanPictureBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
				ArrayList<String> result = DataAnalysis.analyzeLabels(VisionScanner.scan());
				if(result.get(0).isEmpty()) {
					resultLabel.setForeground(Color.RED);
					resultLabel.setText("No Recyclable Material Recognized");
					
				}
				resultLabel.setForeground(Color.BLUE);
				resultLabel.setText(result.get(0));
				String textAreaFormat = "";
				for(int i = 1; i<result.size(); i++) {
					textAreaFormat += "\n" + result.get(i);
					
				}
				textAreaOutput.setText(textAreaFormat);
					
					
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		

	}
}
