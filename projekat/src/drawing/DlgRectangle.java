package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Point;
import geometry.Rectangle;

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
import java.awt.GridLayout;
import javax.swing.SwingConstants;

public class DlgRectangle extends JDialog {

	private final JPanel pnlContent = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtHeight;
	private JTextField txtWidth;

	private JLabel lblX;
	private JLabel lblY;
	private JLabel lblHeight;
	private JLabel lblWidth;

	private Rectangle rectangle = null;
	private Color edgeColor = null, innerColor = null;

	public DlgRectangle() {
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Dialog for Rectangle");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 400, 200);
		getContentPane().setLayout(new BorderLayout(0, 0));
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
			lblHeight = new JLabel("Height:");
			lblHeight.setHorizontalAlignment(SwingConstants.CENTER);
			pnlContent.add(lblHeight);
		}
		{
			txtHeight = new JTextField();
			txtHeight.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
						getToolkit().beep();
						e.consume();
					}
				}
			});
			pnlContent.add(txtHeight);
			txtHeight.setColumns(10);
		}
		{
			lblWidth = new JLabel("Width:");
			lblWidth.setHorizontalAlignment(SwingConstants.CENTER);
			pnlContent.add(lblWidth);
		}
		{
			txtWidth = new JTextField();
			txtWidth.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
						getToolkit().beep();
						e.consume();
					}
				}
			});
			pnlContent.add(txtWidth);
			txtWidth.setColumns(10);
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
							int newHeight = Integer.parseInt(txtHeight.getText());
							int newWidth = Integer.parseInt(txtWidth.getText());

							if (newX < 0 || newY < 0 || newHeight < 1 || newWidth < 1) {
								JOptionPane.showMessageDialog(null, "You entered a wrong value.", "Error!",
										JOptionPane.ERROR_MESSAGE);
								return;
							}
							rectangle = new Rectangle(new Point(newX, newY), newHeight, newWidth, false, edgeColor,
									innerColor);
							dispose();
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "You entered a wrong data type.", "Error!",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				});
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
				pnlButtons.add(btnCancel);
			}
		}
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setPoint(Point point) {
		txtX.setText("" + point.getX());
		txtY.setText("" + point.getY());
	}

	public void setColors(Color edgeColor, Color innerColor) {
		this.edgeColor = edgeColor;
		this.innerColor = innerColor;
	}

	public void setRectangle(Rectangle rect) {
		txtX.setText("" + rect.getUpperLeftPoint().getX());
		txtY.setText("" + rect.getUpperLeftPoint().getY());
		txtHeight.setText("" + rect.getHeight());
		txtWidth.setText("" + rect.getWidth());
		edgeColor = rect.getColor();
		innerColor = rect.getInnerColor();
	}
}
