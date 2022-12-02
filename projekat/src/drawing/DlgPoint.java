package drawing;

import java.awt.FlowLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JColorChooser;
import geometry.Point;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;

public class DlgPoint extends JDialog {

	private final JPanel pnlContent = new JPanel();
	private JTextField txtX;
	private JTextField txtY;

	private Point point = null;
	private Color edgeColor = null;
	private JLabel lblX;
	private JLabel lblY;
	private JButton btnEdgeColor;

	public DlgPoint() {
		setTitle("Dialog for Point");
		setResizable(false);
		setBounds(100, 100, 400, 200);
		setModal(true);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		pnlContent.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlContent);
		GridBagLayout gbl_pnlContent = new GridBagLayout();
		gbl_pnlContent.columnWidths = new int[] { 130, 66, 0 };
		gbl_pnlContent.rowHeights = new int[] { 45, 55, 0, 0 };
		gbl_pnlContent.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_pnlContent.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		pnlContent.setLayout(gbl_pnlContent);
		{
			lblX = new JLabel("X coordinate:");
			lblX.setHorizontalAlignment(SwingConstants.CENTER);
			lblX.setToolTipText("Enter X coordinate.");
			GridBagConstraints gbc_lblX = new GridBagConstraints();
			gbc_lblX.insets = new Insets(0, 0, 5, 5);
			gbc_lblX.gridx = 0;
			gbc_lblX.gridy = 0;
			pnlContent.add(lblX, gbc_lblX);
		}
		{
			txtX = new JTextField();
			txtX.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
						getToolkit().beep();
						e.consume();
					}
				}
			});
			GridBagConstraints gbc_txtX = new GridBagConstraints();
			gbc_txtX.insets = new Insets(0, 0, 5, 0);
			gbc_txtX.gridx = 1;
			gbc_txtX.gridy = 0;
			pnlContent.add(txtX, gbc_txtX);
			txtX.setColumns(10);
		}
		{
			lblY = new JLabel("Y coordinate:");
			lblY.setHorizontalAlignment(SwingConstants.CENTER);
			lblY.setToolTipText("Enter Y coordinate.");
			GridBagConstraints gbc_lblY = new GridBagConstraints();
			gbc_lblY.insets = new Insets(0, 0, 5, 5);
			gbc_lblY.gridx = 0;
			gbc_lblY.gridy = 1;
			pnlContent.add(lblY, gbc_lblY);
		}
		{
			txtY = new JTextField();
			txtY.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
						getToolkit().beep();
						e.consume();
					}
				}
			});
			GridBagConstraints gbc_txtY = new GridBagConstraints();
			gbc_txtY.insets = new Insets(0, 0, 5, 0);
			gbc_txtY.gridx = 1;
			gbc_txtY.gridy = 1;
			pnlContent.add(txtY, gbc_txtY);
			txtY.setColumns(10);
		}
		{
			btnEdgeColor = new JButton("Color picker");
			btnEdgeColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					edgeColor = JColorChooser.showDialog(null, "Choose a color", edgeColor);
					if (edgeColor == null) edgeColor = Color.BLACK;
				}
			});
			GridBagConstraints gbc_btnEdgeColor = new GridBagConstraints();
			gbc_btnEdgeColor.gridwidth = 2;
			gbc_btnEdgeColor.gridx = 0;
			gbc_btnEdgeColor.gridy = 2;
			pnlContent.add(btnEdgeColor, gbc_btnEdgeColor);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane);
			{
				JButton btnOk = new JButton("OK");
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							int newX = Integer.parseInt(txtX.getText());
							int newY = Integer.parseInt(txtY.getText());

							if (newX < 0 || newY < 0) {
								JOptionPane.showMessageDialog(null, "You entered a wrong value.", "Error!",
										JOptionPane.ERROR_MESSAGE);
								return;
							}
							point = new Point(newX, newY, false, edgeColor);
							dispose();
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "You entered a wrong data type.", "Error!",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
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

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		txtX.setText("" + point.getX());
		txtY.setText("" + point.getY());
		edgeColor = point.getColor();
	}

	public void setColors(Color edgeColor) {
		this.edgeColor = edgeColor;
	}

}
