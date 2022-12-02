package stack;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Rectangle;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmStack extends JFrame {

	private JPanel contentPane;
	private JList<Rectangle> lstRectangle = new JList<Rectangle>();
	private DefaultListModel<Rectangle> dlm = new DefaultListModel<Rectangle>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmStack frame = new FrmStack();
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
	public FrmStack() {
		setTitle("Stack");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel pnlCenter = new JPanel();
		contentPane.add(pnlCenter, BorderLayout.CENTER);
		pnlCenter.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JScrollPane scrlPnlRectangle = new JScrollPane(lstRectangle);
		scrlPnlRectangle.setSize(400,300);
		pnlCenter.add(scrlPnlRectangle);

		lstRectangle.setModel(dlm);
		lstRectangle.setVisibleRowCount(12);
		scrlPnlRectangle.setViewportView(lstRectangle);

		JPanel pnlSouth = new JPanel();
		contentPane.add(pnlSouth, BorderLayout.SOUTH);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 0;
				DlgStack dlgStack = new DlgStack();
				dlgStack.setVisible(true);

				if (dlgStack.getRectangle() != null) {
					dlm.add(i, dlgStack.getRectangle());
					i++;
				}
			}
		});
		pnlSouth.add(btnAdd);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dlm.isEmpty() == false) {
					DlgStack dlgStack = new DlgStack();
					dlgStack.setRectangle(dlm.getElementAt(0));
					dlgStack.setVisible(true);
					dlm.removeElementAt(0);
				} else {
					JOptionPane.showMessageDialog(null, "Stack is empty!", "ERROR", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		pnlSouth.add(btnDelete);
	}

}
