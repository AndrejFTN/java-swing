package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import geometry.Circle;
import geometry.Hexagon;
import geometry.Point;

public class DlgHexagon extends JDialog{
	
	private final JPanel pnlContent = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtRadius;

	private JLabel lblX;
	private JLabel lblY;
	private JLabel lblRadius;
	
	private Hexagon hexagon = null;
	private Color edgeColor = null, innerColor = null;
	
	public DlgHexagon() {
		setResizable(false);
		setTitle("Dialog for Hexagon");
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 200);
		getContentPane().setLayout(new BorderLayout());
		pnlContent.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlContent, BorderLayout.CENTER);
		pnlContent.setLayout(new GridLayout(0, 2, 0, 0));
		{
			lblX = new JLabel("X coordinate:");
			lblX.setHorizontalAlignment(SwingConstants.CENTER);
			pnlContent.add(lblX);
		}
		{
			txtX = new JTextField();
			txtX.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE)
							|| (c == KeyEvent.VK_DELETE))) {
						getToolkit().beep();
						e.consume();
					}
				}
			});
			pnlContent.add(txtX);
			txtX.setColumns(10);
		}
		{
			lblY = new JLabel("Y coordinate:");
			lblY.setHorizontalAlignment(SwingConstants.CENTER);
			pnlContent.add(lblY);
		}
		{
			txtY = new JTextField();
			txtY.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE)
							|| (c == KeyEvent.VK_DELETE))) {
						getToolkit().beep();
						e.consume();
					}
				}
			});
			pnlContent.add(txtY);
			txtY.setColumns(10);
		}
		{
			lblRadius = new JLabel("Radius:");
			lblRadius.setHorizontalAlignment(SwingConstants.CENTER);
			pnlContent.add(lblRadius);
		}
		{
			txtRadius = new JTextField();
			txtRadius.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE)
							|| (c == KeyEvent.VK_DELETE))) {
						getToolkit().beep();
						e.consume();
					}
				}
			});
			pnlContent.add(txtRadius);
			txtRadius.setColumns(10);
		}
		{
			JButton btnEdgeColor = new JButton("Pick Edge color");
			btnEdgeColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					edgeColor = JColorChooser.showDialog(null, "Choose edge color", edgeColor);
					if (edgeColor == null)
						edgeColor = Color.BLACK;
				}
			});
				pnlContent.add(btnEdgeColor);
		}
		{
			JButton btnInnerColor = new JButton("Pick Inner color");
			btnInnerColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					innerColor = JColorChooser.showDialog(null, "Choose inner color", innerColor);
					if (innerColor == null)
						innerColor = Color.WHITE;
					
				}
			});
			pnlContent.add(btnInnerColor);
		}
		{
			JPanel pnlButtons = new JPanel();
			pnlButtons.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(pnlButtons, BorderLayout.SOUTH);
			{
				JButton btnOk = new JButton("OK");
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							int newX = Integer.parseInt(txtX.getText());
							int newY = Integer.parseInt(txtY.getText());
							int newRadius = Integer.parseInt(txtRadius.getText());

							if (newX < 0 || newY < 0 || newRadius < 1) {
								JOptionPane.showMessageDialog(null, "You entered a wrong value.", "Error",
										JOptionPane.ERROR_MESSAGE);
								return;
							}
							hexagon = new Hexagon(new Point(newX, newY), newRadius, false, edgeColor, innerColor);
							dispose();
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "You entered a wrong data type.", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				btnOk.setActionCommand("OK");
				pnlButtons.add(btnOk);
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
				pnlButtons.add(btnCancel);
			}
		}
		
	}
	public Hexagon getHexagon() {
		return hexagon;
	}

	public void setPoint(Point point) {
		txtX.setText("" + point.getX());
		txtY.setText("" + point.getY());
	}
	
	public void setColors(Color edgeColor, Color innerColor) {
		this.edgeColor = edgeColor;
		this.innerColor = innerColor;
	}

	public void setHexagon(Hexagon hexagon) {
		txtX.setText("" + hexagon.getCenter().getX());
		txtY.setText("" + hexagon.getCenter().getY());
		txtRadius.setText("" + hexagon.getRadius());
		edgeColor = hexagon.getColor();
		innerColor = hexagon.getInnerColor();
	}

		
}
