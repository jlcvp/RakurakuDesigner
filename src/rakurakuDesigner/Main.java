package rakurakuDesigner;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;

public class Main {

	private JFrame frame;
	private JPanel panel;
	JToggleButton[][] buttons;
	JTextPane textPane;

	private final Action resetAction = new ResetAction();
	private final Action generateAction = new GenerateAction();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
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
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1158, 713);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		addButtons(panel);

		JButton btnNewButton = new JButton("RESET");
		btnNewButton.setAction(resetAction);
		btnNewButton.setBounds(12, 636, 98, 26);
		panel.add(btnNewButton);

		textPane = new JTextPane();
		textPane.setBounds(876, 47, 254, 403);
		panel.add(textPane);
		
		JButton button = new JButton("Generate");
		button.setBounds(876, 12, 254, 23);
		button.setAction(generateAction);
		panel.add(button);

	}

	private void reset()
	{
		for (int i = 0; i < 16; i++) {
			for(int j=0; j<19; j++){

				buttons[i][j].setSelected(false);
			}
		}
	}

	public void generate() {
		//		byte[][] dino1 = {
		//				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		//				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		//				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		//				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		//				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		//				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		//				{0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0},
		//				{0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0},
		//				{0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
		//				{0,0,0,0,1,0,1,0,0,1,0,1,0,0,0,0,0,0,0},
		//				{0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
		//				{0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
		//				{0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0},
		//				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		//				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		//				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		//		};

		String strBase = "byte[][] dino1 = {\n";
		StringBuilder sb = new StringBuilder(strBase);
		for (int i = 0; i < 16; i++) {
			sb.append("{");
			
			for(int j=0; j<19; j++){
				if(buttons[i][j].isSelected())
				{
					sb.append("1");
				}
				else
				{
					sb.append("0");
				}
				
				if(j!=18)
				{
					sb.append(",");
				}
			}
			
			sb.append("}");
			if(i!=15)
			{
				sb.append(",\n");
			}
			else
			{
				sb.append("\n};");
			}
		}
		
		textPane.setText(sb.toString());


	}

	private void addButtons(JPanel panel2) {
		buttons = new JToggleButton[16][19];
		for (int i = 0; i < 16; i++) {
			for(int j=0; j<19; j++){
				if(i==0)
				{
					buttons[i][j] = new JToggleButton(""+(j+1));
				}
				else if(j==0){
					buttons[i][j] = new JToggleButton(""+(i+1));
				}
				else
				{
					buttons[i][j] = new JToggleButton("");
				}
				buttons[i][j].setFont(Font.decode("Arial"));
				buttons[i][j].setBounds(10+j*38, 10+i*38, 35, 35);
				buttons[i][j].setBorderPainted(false);
				buttons[i][j].setMargin(new Insets(0, 0, 0, 0));
				buttons[i][j].setBackground(new Color(200,200,200));
				buttons[i][j].setUI(new MyUI(Color.BLACK));;
				//buttons[i][j].setFocusPainted(false);
				//buttons[i][j].setContentAreaFilled(false);
				panel2.add(buttons[i][j]);
			}

		}

	}	

	class MyUI extends javax.swing.plaf.metal.MetalToggleButtonUI
	{
		Color color;
		public MyUI(Color c){color = c;}
		public Color getSelectColor(){return color;}
	}

	private class ResetAction extends AbstractAction {
		public ResetAction() {
			putValue(NAME, "Reset");
			//putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			reset();
		}
	}

	private class GenerateAction extends AbstractAction {
		public GenerateAction() {
			putValue(NAME, "Generate");
		}

		public void actionPerformed(ActionEvent e) {
			generate();
		}
	}
}
