package drawing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import geometry.Line;
import geometry.Point;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class DlgLine extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtFirstX;
	private JTextField txtFirstY;
	private JTextField txtSecondX;
	private JTextField txtSecondY;

	private Line line = null;
	private Color edgeColor = null;
	private JLabel lblFirstX;
	private JLabel lblFirstY;
	private JLabel lblSecondX;
	private JLabel lblSecondY;
	private JButton btnColor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgLine dialog = new DlgLine();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgLine() {
		setTitle("Dialog for Line");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 170, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			lblFirstX = new JLabel("X coordinate of start point:");
			GridBagConstraints gbc_lblFirstX = new GridBagConstraints();
			gbc_lblFirstX.insets = new Insets(0, 0, 5, 5);
			gbc_lblFirstX.gridx = 0;
			gbc_lblFirstX.gridy = 0;
			contentPanel.add(lblFirstX, gbc_lblFirstX);
		}
		{
			txtFirstX = new JTextField();
			txtFirstX.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
						getToolkit().beep();
						e.consume();
					}
				}
			});
			GridBagConstraints gbc_txtFirstX = new GridBagConstraints();
			gbc_txtFirstX.insets = new Insets(0, 0, 5, 0);
			gbc_txtFirstX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtFirstX.gridx = 1;
			gbc_txtFirstX.gridy = 0;
			contentPanel.add(txtFirstX, gbc_txtFirstX);
			txtFirstX.setColumns(10);
		}
		{
			lblFirstY = new JLabel("Y coordinate of start point:");
			GridBagConstraints gbc_lblFirstY = new GridBagConstraints();
			gbc_lblFirstY.insets = new Insets(0, 0, 5, 5);
			gbc_lblFirstY.gridx = 0;
			gbc_lblFirstY.gridy = 1;
			contentPanel.add(lblFirstY, gbc_lblFirstY);
		}
		{
			txtFirstY = new JTextField();
			txtFirstY.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
						getToolkit().beep();
						e.consume();
					}
				}
			});
			GridBagConstraints gbc_txtFirstY = new GridBagConstraints();
			gbc_txtFirstY.insets = new Insets(0, 0, 5, 0);
			gbc_txtFirstY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtFirstY.gridx = 1;
			gbc_txtFirstY.gridy = 1;
			contentPanel.add(txtFirstY, gbc_txtFirstY);
			txtFirstY.setColumns(10);
		}
		{
			lblSecondX = new JLabel("X coordinate of end point:");
			GridBagConstraints gbc_lblSecondX = new GridBagConstraints();
			gbc_lblSecondX.insets = new Insets(0, 0, 5, 5);
			gbc_lblSecondX.gridx = 0;
			gbc_lblSecondX.gridy = 3;
			contentPanel.add(lblSecondX, gbc_lblSecondX);
		}
		{
			txtSecondX = new JTextField();
			txtSecondX.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
						getToolkit().beep();
						e.consume();
					}
				}
			});
			GridBagConstraints gbc_txtSecondX = new GridBagConstraints();
			gbc_txtSecondX.insets = new Insets(0, 0, 5, 0);
			gbc_txtSecondX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtSecondX.gridx = 1;
			gbc_txtSecondX.gridy = 3;
			contentPanel.add(txtSecondX, gbc_txtSecondX);
			txtSecondX.setColumns(10);
		}
		{
			lblSecondY = new JLabel("Y coordinate of end point:");
			GridBagConstraints gbc_lblSecondY = new GridBagConstraints();
			gbc_lblSecondY.insets = new Insets(0, 0, 5, 5);
			gbc_lblSecondY.gridx = 0;
			gbc_lblSecondY.gridy = 4;
			contentPanel.add(lblSecondY, gbc_lblSecondY);
		}
		{
			txtSecondY = new JTextField();
			txtSecondY.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
						getToolkit().beep();
						e.consume();
					}
				}
			});
			GridBagConstraints gbc_txtSecondY = new GridBagConstraints();
			gbc_txtSecondY.insets = new Insets(0, 0, 5, 0);
			gbc_txtSecondY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtSecondY.gridx = 1;
			gbc_txtSecondY.gridy = 4;
			contentPanel.add(txtSecondY, gbc_txtSecondY);
			txtSecondY.setColumns(10);
		}
		{
			btnColor = new JButton("Color picker");
			btnColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					edgeColor = JColorChooser.showDialog(null, "Choose a color", edgeColor);
					if (edgeColor == null)
						edgeColor = Color.BLACK;
				}
			});
			GridBagConstraints gbc_btnColor = new GridBagConstraints();
			gbc_btnColor.anchor = GridBagConstraints.WEST;
			gbc_btnColor.gridx = 1;
			gbc_btnColor.gridy = 5;
			contentPanel.add(btnColor, gbc_btnColor);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnOk = new JButton("OK");
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							int newFirstX = Integer.parseInt(txtFirstX.getText());
							int newFirstY = Integer.parseInt(txtFirstY.getText());
							int newSecondX = Integer.parseInt(txtSecondX.getText());
							int newSecondY = Integer.parseInt(txtSecondY.getText());

							if (newFirstX < 0 || newFirstY < 0 || newSecondX < 0 || newSecondY < 0) {
								JOptionPane.showMessageDialog(null, "You entered a wrong value.", "Error!",
										JOptionPane.ERROR_MESSAGE);
								return;
							}
							line = new Line(new Point(newFirstX, newFirstY), new Point(newSecondX, newSecondY), false,
									edgeColor);
							dispose();
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "You entered a wrong data type.", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				btnOk.setActionCommand("OK");
				buttonPane.add(btnOk);
				getRootPane().setDefaultButton(btnOk);
			}
			{
				JButton btnCancel = new JButton("Cancel");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}
	}

	public Line getLine() {
		return line;
	}

	public void setStartPoint(Point point) {
		txtFirstX.setText("" + point.getX());
		txtFirstY.setText("" + point.getY());
	}

	public void setEndPoint(Point point) {
		txtSecondX.setText("" + point.getX());
		txtSecondY.setText("" + point.getY());
	}

	public void setLine(Line line) {
		txtFirstX.setText("" + line.getStartPoint().getX());
		txtFirstY.setText("" + line.getStartPoint().getY());
		txtSecondX.setText("" + line.getEndPoint().getX());
		txtSecondY.setText("" + line.getEndPoint().getY());
		edgeColor = line.getColor();
	}

	public void setColors(Color edgeColor) {
		this.edgeColor = edgeColor;
	}

}
