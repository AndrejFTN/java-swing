package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;

import geometry.Donut;
import geometry.Point;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class DlgDonut extends JDialog {
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtRadius;
	private JTextField txtInnerRadius;

	private JLabel lblX;
	private JLabel lblY;
	private JLabel lblRadius;
	private JLabel lblInnerRadius;

	private Donut donut = null;
	private Color edgeColor = null, innerColor = null;

	public DlgDonut() {
		setResizable(false);
		setTitle("Dialog for Donut");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 400, 200);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));
		{
			JPanel pnlContent = new JPanel();
			getContentPane().add(pnlContent, BorderLayout.CENTER);
			pnlContent.setLayout(new GridLayout(5, 2, 0, 0));
			{
				lblX = new JLabel("X coordinate:", SwingConstants.CENTER);
				pnlContent.add(lblX);
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
						if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
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
						if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
							getToolkit().beep();
							e.consume();
						}
					}
				});
				pnlContent.add(txtRadius);
				txtRadius.setColumns(10);
			}
			{
				lblInnerRadius = new JLabel("Inner radius:");
				lblInnerRadius.setHorizontalAlignment(SwingConstants.CENTER);
				pnlContent.add(lblInnerRadius);
			}
			{
				txtInnerRadius = new JTextField();
				txtInnerRadius.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						char c = e.getKeyChar();
						if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
							getToolkit().beep();
							e.consume();
						}
					}
				});
				pnlContent.add(txtInnerRadius);
				txtInnerRadius.setColumns(10);
			}
			{
				JButton btnEdgeColor = new JButton("Pick Edge color");
				btnEdgeColor.setHorizontalAlignment(SwingConstants.CENTER);
				btnEdgeColor.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						edgeColor = JColorChooser.showDialog(null, "Choose edge color", edgeColor);
						if (edgeColor == null)
							edgeColor = Color.BLACK;
					}
				});
				pnlContent.add(btnEdgeColor);
			}
			{
				JButton btnInnerColor = new JButton("Pick Inner Color");
				btnInnerColor.setHorizontalAlignment(SwingConstants.CENTER);
				btnInnerColor.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						innerColor = JColorChooser.showDialog(null, "Choose inner color", innerColor);
						if (innerColor == null)
							innerColor = Color.WHITE;
					}
				});
				pnlContent.add(btnInnerColor);
			}
		}
		{
			JPanel pnlButtons = new JPanel();
			getContentPane().add(pnlButtons, BorderLayout.SOUTH);
			pnlButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JButton btnOk = new JButton("OK");
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							int newX = Integer.parseInt(txtX.getText());
							int newY = Integer.parseInt(txtY.getText());
							int newRadius = Integer.parseInt(txtRadius.getText());
							int newInnerRadius = Integer.parseInt(txtInnerRadius.getText());

							if (newX < 0 || newY < 0 || newRadius < 1 || newInnerRadius < 1
									|| newInnerRadius >= newRadius) {
								JOptionPane.showMessageDialog(null, "You entered a wrong value.", "Error!",
										JOptionPane.ERROR_MESSAGE);
								return;
							}
							donut = new Donut(new Point(newX, newY), newRadius, newInnerRadius, false, edgeColor,
									innerColor);
							dispose();
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "You entered a wrong data type.", "Error!",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				pnlButtons.add(btnOk);
			}
			{
				JButton btnCancel = new JButton("Cancel");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				pnlButtons.add(btnCancel);
			}
		}
	}

	public Donut getDonut() {
		return donut;
	}

	public void setPoint(Point point) {
		txtX.setText("" + point.getX());
		txtY.setText("" + point.getY());
	}

	public void setColors(Color edgeColor, Color innerColor) {
		this.edgeColor = edgeColor;
		this.innerColor = innerColor;
	}

	public void setDonut(Donut donut) {
		txtX.setText("" + donut.getCenter().getX());
		txtY.setText("" + donut.getCenter().getY());
		txtRadius.setText("" + donut.getRadius());
		txtInnerRadius.setText("" + donut.getInnerRadius());
		edgeColor = donut.getColor();
		innerColor = donut.getInnerColor();
	}
}
