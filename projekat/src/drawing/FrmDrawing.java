package drawing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JButton;

import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JToggleButton;
import javax.swing.ButtonGroup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class FrmDrawing extends JFrame {
	private final int OPERATION_DRAWING = 1;
	private final int OPERATION_EDIT_DELETE = 0;
	
	private int activeOperation = OPERATION_DRAWING;
	
	private PnlDrawing pnlDrawing = new PnlDrawing();
	private JToggleButton btnOperationDrawing = new JToggleButton("Draw");
	private JToggleButton btnOperationEditOrDelete = new JToggleButton("Select");
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

	private ArrayList<Command> commands = new ArrayList<Command>();
	private int currentCommand = -1;
	private JToggleButton btnHexagon = new JToggleButton("Hexagon");
	
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
		
		btnActionEdit.addActionListener(btnActionEditClickListener());
		pnlNorth.add(btnActionEdit);
		
		btnActionDelete.addActionListener(btnActionDeleteClickListener());
		pnlNorth.add(btnActionDelete);
		
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentCommand + 1 <= commands.size() - 1) {
					currentCommand++;
					commands.get(currentCommand).Do();	
				}
				
			}
		});
		
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(currentCommand - 1 >= -1) {
					commands.get(currentCommand).Undo();
					currentCommand--;	
				}
				
			}
		});
		
		pnlNorth.add(btnRedo);
		
		pnlNorth.add(btnUndo);
		
		JPanel pnlSouth = new JPanel();
		contentPane.add(pnlSouth, BorderLayout.SOUTH);
		
		btnOperationDrawing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setOperationDrawing();
			}
		});
		
		
		btnsOperation.add(btnOperationDrawing);
		pnlSouth.add(btnOperationDrawing);
		
		btnOperationEditOrDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setOperationEditDelete();
			}
		});
		btnsOperation.add(btnOperationEditOrDelete);
		pnlSouth.add(btnOperationEditOrDelete);
		
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
		
		btnOperationDrawing.setSelected(true);
		setOperationDrawing();
	}
	
	private MouseAdapter pnlDrawingClickListener() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Point mouseClick = new Point(e.getX(), e.getY());
				pnlDrawing.deselect();
				
				if (activeOperation == OPERATION_EDIT_DELETE) {
					pnlDrawing.select(mouseClick);
					return;
				}
				
				
				if (btnShapePoint.isSelected()) {
					DlgPoint dlgPoint = new DlgPoint();
					dlgPoint.setPoint(mouseClick);
					dlgPoint.setColors(edgeColor);
					dlgPoint.setVisible(true);
					if(dlgPoint.getPoint() != null) {
						AddCommand addCommand = new AddCommand(dlgPoint.getPoint(), pnlDrawing);
						commands.add(addCommand);
						currentCommand++;
						addCommand.Do();
						return;
					}
					
					
				}
				
				else if(btnHexagon.isSelected()) {
					DlgHexagon dlgHexagon = new DlgHexagon();
					dlgHexagon.setPoint(mouseClick);
					dlgHexagon.setColors(edgeColor, innerColor);
					dlgHexagon.setVisible(true);
					if(dlgHexagon.getHexagon() != null) {
							AddCommand addCommand = new AddCommand(dlgHexagon.getHexagon(), pnlDrawing);
							commands.add(addCommand);
							currentCommand++;
							addCommand.Do();
							return;
						
					}
				}
				
				else if (btnShapeLine.isSelected()) {
					if(lineWaitingForEndPoint) {
						
						DlgLine dlgLine = new DlgLine();
						Line line = new Line(startPoint,mouseClick);
						dlgLine.setLine(line);
						dlgLine.setColors(edgeColor);
						dlgLine.setVisible(true);
						if(dlgLine.getLine()!= null) {
							AddCommand addCommand = new AddCommand(dlgLine.getLine(), pnlDrawing);
							commands.add(addCommand);
							currentCommand++;
							addCommand.Do();
							lineWaitingForEndPoint=false;
							return;
						}					
					}
					startPoint = mouseClick;
					lineWaitingForEndPoint=true;
					return;
					
		
				} else if (btnShapeRectangle.isSelected()) {
					DlgRectangle dlgRectangle = new DlgRectangle();
					dlgRectangle.setPoint(mouseClick);
					dlgRectangle.setColors(edgeColor, innerColor);
					dlgRectangle.setVisible(true);
					
					if(dlgRectangle.getRectangle() != null) {
						AddCommand addCommand = new AddCommand(dlgRectangle.getRectangle(), pnlDrawing);
						commands.add(addCommand);
						currentCommand++;
						addCommand.Do();
						return;
					}
					
				} else if (btnShapeCircle.isSelected()) {
					DlgCircle dlgCircle = new DlgCircle();
					dlgCircle.setPoint(mouseClick);
					dlgCircle.setColors(edgeColor, innerColor);
					dlgCircle.setVisible(true);
					
					if(dlgCircle.getCircle() != null) {
						AddCommand addCommand = new AddCommand(dlgCircle.getCircle(), pnlDrawing);
						commands.add(addCommand);
						currentCommand++;
						addCommand.Do();
						return;
					}
					return;
				} else if (btnShapeDonut.isSelected()) {
					DlgDonut dlgDonut = new DlgDonut();
					dlgDonut.setPoint(mouseClick);
					dlgDonut.setColors(edgeColor, innerColor);
					dlgDonut.setVisible(true);
					
					if(dlgDonut.getDonut() != null) {
						AddCommand addCommand = new AddCommand(dlgDonut.getDonut(), pnlDrawing);
						commands.add(addCommand);
						currentCommand++;
						addCommand.Do();
						return;
					}
					return;
				}
			}
		};
	}
	
	private ActionListener btnActionEditClickListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = pnlDrawing.getSelected();
				if (index == -1) return;
				
				Shape shape = pnlDrawing.getShape(index);
				
				if (shape instanceof Point) {
					DlgPoint dlgPoint = new DlgPoint();
					dlgPoint.setPoint((Point)shape);
					dlgPoint.setVisible(true);
					
					if(dlgPoint.getPoint() != null) {
						pnlDrawing.setShape(index, dlgPoint.getPoint());
						pnlDrawing.repaint();
					}
				} else if (shape instanceof Line) {
					DlgLine dlgLine = new DlgLine();
					dlgLine.setLine((Line)shape);
					dlgLine.setVisible(true);
					
					if(dlgLine.getLine() != null) {
						pnlDrawing.setShape(index, dlgLine.getLine());
						pnlDrawing.repaint();
					}
				} else if (shape instanceof Rectangle) {
					DlgRectangle dlgRectangle = new DlgRectangle();
					dlgRectangle.setRectangle((Rectangle)shape);
					dlgRectangle.setVisible(true);
					
					if(dlgRectangle.getRectangle() != null) {
						pnlDrawing.setShape(index, dlgRectangle.getRectangle());
						pnlDrawing.repaint();
					}
				
				}else if (shape instanceof Donut) {
						DlgDonut dlgDonut = new DlgDonut();
						dlgDonut.setDonut((Donut)shape);
						dlgDonut.setVisible(true);
						
						if(dlgDonut.getDonut() != null) {
							pnlDrawing.setShape(index, dlgDonut.getDonut());
							pnlDrawing.repaint();
						}
				} else if (shape instanceof Circle) {
					DlgCircle dlgCircle = new DlgCircle();
					dlgCircle.setCircle((Circle)shape);
					dlgCircle.setVisible(true);
					
					if(dlgCircle.getCircle() != null) {
						pnlDrawing.setShape(index, dlgCircle.getCircle());
						pnlDrawing.repaint();
					}
				} 
			}
		};
	}

	private ActionListener btnActionDeleteClickListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pnlDrawing.isEmpty()) return;
				if (JOptionPane.showConfirmDialog(null, "Do you really want to delete selected shape?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) pnlDrawing.removeSelected();
			}
		};
	}
	
	private void setOperationDrawing() {
		activeOperation = OPERATION_DRAWING;
		
		pnlDrawing.deselect();
		
		btnActionEdit.setEnabled(false);
		btnActionDelete.setEnabled(false);
		
		btnShapePoint.setEnabled(true);
		btnShapeLine.setEnabled(true);
		btnShapeRectangle.setEnabled(true);
		btnShapeCircle.setEnabled(true);
		btnShapeDonut.setEnabled(true);
		
		btnColorEdge.setEnabled(true);
		btnColorInner.setEnabled(true);
	}
	
	private void setOperationEditDelete() {
		activeOperation = OPERATION_EDIT_DELETE;
		
		btnActionEdit.setEnabled(true);
		btnActionDelete.setEnabled(true);
		
		btnShapePoint.setEnabled(false);
		btnShapeLine.setEnabled(false);
		btnShapeRectangle.setEnabled(false);
		btnShapeCircle.setEnabled(false);
		btnShapeDonut.setEnabled(false);
		
		btnColorEdge.setEnabled(false);
		btnColorInner.setEnabled(false);
	}
}
