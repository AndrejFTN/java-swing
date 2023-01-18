package drawing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import geometry.Circle;
import geometry.Donut;
import geometry.Hexagon;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JToggleButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JScrollPane;
import javax.swing.JList;

public class FrmDrawing extends JFrame implements Observer {
	private final int OPERATION_DRAWING = 1;
	private final int OPERATION_EDIT_DELETE = 0;
	
	private int activeOperation = OPERATION_DRAWING;
	
	private PnlDrawing pnlDrawing = new PnlDrawing();
	private JButton btnActionEdit = new JButton("Edit");
	private JButton btnActionDelete = new JButton("Delete");
	private JToggleButton btnShapePoint = new JToggleButton("Point");
	private JToggleButton btnShapeLine = new JToggleButton("Line");
	private JToggleButton btnShapeRectangle = new JToggleButton("Rectangle");
	private JToggleButton btnShapeCircle = new JToggleButton("Circle");
	private JToggleButton btnShapeDonut = new JToggleButton("Donut");
	private JButton btnColorEdge = new JButton("Edge color");
	private JButton btnColorInner = new JButton("Inner color");
	
	private Color edgeColor = Color.BLACK, innerColor = Color.WHITE;
	boolean lineWaitingForEndPoint = false;
	private Point startPoint;
	
	
	private JPanel contentPane;
	private  ButtonGroup btnsOperation = new ButtonGroup();
	private  ButtonGroup btnsShapes = new ButtonGroup();
	private  JButton btnRedo = new JButton("Redo");
	private  JButton btnUndo = new JButton("Undo");

	
	
	private JToggleButton btnHexagon = new JToggleButton("Hexagon");
	
	private DrawingController drawingcontroller;
	private final JPanel panel = new JPanel();
	private final JToggleButton btnOperationDrawing = new JToggleButton("Draw");
	private final JToggleButton btnOperationEditOrDelete = new JToggleButton("Select");
	private final JScrollPane scrollPane = new JScrollPane();
	private final JList list = new JList();
	
	private DefaultListModel<String> defaultListModel = new DefaultListModel<>();
	private final JButton btnMoveUp = new JButton("Move Up");
	private final JButton btnMoveDown = new JButton("Move down");
	private final JButton btnMoveToFront = new JButton("Move to front");
	private final JButton btnMoveToBottom = new JButton("Move to bottom");
	private final JButton btnOutterColor = new JButton("Outter color");
	private final JButton btnInnerColor = new JButton("Inner color");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDrawing frame = new FrmDrawing();
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
	public FrmDrawing() {
		setTitle("Drawing");
		this.drawingcontroller = new DrawingController(pnlDrawing, this);
		this.drawingcontroller.addObserver(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(1200, 600));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		pnlDrawing.addMouseListener(pnlDrawingClickListener());
		contentPane.add(pnlDrawing, BorderLayout.CENTER);
		
		JPanel pnlNorth = new JPanel();
		contentPane.add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		btnActionEdit.addActionListener( 
			 new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					drawingcontroller.editShapes();
				}
			}
		);
		pnlNorth.add(btnActionEdit);
		
		btnActionDelete.addActionListener(btnActionDeleteClickListener());
		pnlNorth.add(btnActionDelete);
		
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawingcontroller.redo();
			}
		});
		
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawingcontroller.undo();

				
			}
		});
		
		btnsOperation.add(btnOperationDrawing);
		btnsOperation.add(btnOperationEditOrDelete);
		
		btnOperationDrawing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setOperationDrawing();
			}
		});
		
		btnOperationEditOrDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setOperationEdit();
			}
		});
		
		
		pnlNorth.add(btnRedo);
		
		pnlNorth.add(btnUndo);
		
		pnlNorth.add(btnMoveUp);
		btnMoveUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawingcontroller.moveUp();
			}
		});
		
		pnlNorth.add(btnMoveDown);	
		btnMoveDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawingcontroller.moveDown();
			}
		});
		
		pnlNorth.add(btnMoveToFront);	
		btnMoveToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawingcontroller.moveToFront();
			}
		});
		
		pnlNorth.add(btnMoveToBottom);
		btnMoveToBottom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawingcontroller.moveToBottom();
			}
		});

		
		pnlNorth.add(btnOutterColor);
		btnOutterColor.setBackground(Color.black);
		btnOutterColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color outterColor = JColorChooser.showDialog(null, "Choose outter color", btnOutterColor.getBackground());
				if(outterColor != null) {
					btnOutterColor.setBackground(outterColor);
				} else {
					btnOutterColor.setBackground(Color.black);
				}
			}
			
		});
		
		pnlNorth.add(btnInnerColor);
		btnInnerColor.setBackground(Color.white);
		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color innerColor = JColorChooser.showDialog(null, "Choose inner color", btnInnerColor.getBackground());
				if(innerColor != null) {
					btnInnerColor.setBackground(innerColor);
				} else {
					btnInnerColor.setBackground(Color.white);
				}
			}
		});
	
		
		JPanel pnlSouth = new JPanel();
		contentPane.add(pnlSouth, BorderLayout.SOUTH);
		pnlSouth.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlWest = new JPanel();
		contentPane.add(pnlWest, BorderLayout.WEST);
		pnlWest.setLayout(new GridLayout(0, 1, 0, 0));
		
		btnsShapes.add(btnShapePoint);
		pnlWest.add(btnShapePoint);
		
		btnsShapes.add(btnHexagon);
		pnlWest.add(btnHexagon);
		
		btnsShapes.add(btnShapeLine);
		pnlWest.add(btnShapeLine);
		
		btnsShapes.add(btnShapeRectangle);
		pnlWest.add(btnShapeRectangle);
		
		btnsShapes.add(btnShapeCircle);
		pnlWest.add(btnShapeCircle);
		
		btnsShapes.add(btnShapeDonut);
		pnlWest.add(btnShapeDonut);
		
		pnlSouth.add(panel, BorderLayout.NORTH);
		btnOperationDrawing.setSelected(true);
		
		panel.add(btnOperationDrawing);
		
		panel.add(btnOperationEditOrDelete);
		
		pnlSouth.add(scrollPane, BorderLayout.SOUTH);
		scrollPane.setViewportView(list);
		list.setModel(defaultListModel);
		
		setOperationDrawing();
		
		btnActionEdit.setEnabled(false);
		btnActionDelete.setEnabled(false);
		btnMoveUp.setEnabled(false);
		btnMoveDown.setEnabled(false);
		btnMoveToFront.setEnabled(false);
		btnMoveToBottom.setEnabled(false);
		btnRedo.setEnabled(false);
		btnUndo.setEnabled(false);
		
	}
	
	
	private MouseAdapter pnlDrawingClickListener() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Point mouseClick = drawingcontroller.makeMouseClick(e.getX(), e.getY());
				
				
				
				System.out.println(activeOperation);
				if (activeOperation == OPERATION_EDIT_DELETE) {
					drawingcontroller.mouseClicked(mouseClick);
					return;
				}
				
				if (btnShapePoint.isSelected()) {
					drawingcontroller.drawPoint(mouseClick);
				}
				
				else if(btnHexagon.isSelected()) {
					drawingcontroller.drawHexagon(mouseClick);
				}
				
				else if (btnShapeLine.isSelected()) {
					drawingcontroller.drawLine(mouseClick);
		
				} else if (btnShapeRectangle.isSelected()) {
					drawingcontroller.drawRectangle(mouseClick);
					
				} else if (btnShapeCircle.isSelected()) {
					drawingcontroller.drawCircle(mouseClick);
					
				} else if (btnShapeDonut.isSelected()) {
					drawingcontroller.drawDonut(mouseClick);
				}
			}
		};
	}
	
	

	private ActionListener btnActionDeleteClickListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawingcontroller.delete();
			}
		};
	}
	
	private void setOperationDrawing() {
		activeOperation = OPERATION_DRAWING;
		
		drawingcontroller.deSelectAll();
	
		
		btnShapePoint.setEnabled(true);
		btnShapeLine.setEnabled(true);
		btnShapeRectangle.setEnabled(true);
		btnShapeCircle.setEnabled(true);
		btnShapeDonut.setEnabled(true);
		btnHexagon.setEnabled(true);
		
		btnColorEdge.setEnabled(true);
		btnColorInner.setEnabled(true);
	}
	
	private void setOperationEdit() {
		activeOperation = OPERATION_EDIT_DELETE;
		
		

		
		btnShapePoint.setEnabled(false);
		btnShapeLine.setEnabled(false);
		btnShapeRectangle.setEnabled(false);
		btnShapeCircle.setEnabled(false);
		btnShapeDonut.setEnabled(false);
		btnHexagon.setEnabled(false);
		
		btnColorEdge.setEnabled(false);
		btnColorInner.setEnabled(false);
	}
	
	public void addLog(String log) {
		defaultListModel.addElement(log);
	}
	
	public Color getGlobalOutterColor() {
		return btnOutterColor.getBackground();
	}
	public Color getGlobalInnerColor() {
		return btnInnerColor.getBackground();
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Update");
		if(arg == null) {
			return;
		}
		ObserverMessage observerMessage = (ObserverMessage)arg;
		btnActionDelete.setEnabled(observerMessage.isEnableDelete());
		btnActionEdit.setEnabled(observerMessage.isEnableEdit());
		btnUndo.setEnabled(observerMessage.isEnableUndo());
		btnRedo.setEnabled(observerMessage.isEnableRedo());
		btnMoveUp.setEnabled(observerMessage.isEnableMoveUp());
		btnMoveDown.setEnabled(observerMessage.isEnableMoveDown());
		btnMoveToFront.setEnabled(observerMessage.isEnableMoveToFront());
		btnMoveToBottom.setEnabled(observerMessage.isEnableMoveToBottom());
		
	}
}
