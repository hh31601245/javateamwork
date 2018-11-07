package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import begin.Util;
import util.BaseException;
import model.Case;
import model.Defendant;
import model.Relation;

public class FrmMain extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JMenuBar menubar=new JMenuBar();
	private JMenu menu_add=new JMenu("添加新案例");
	private JMenu menu_showcase=new JMenu("显示案例信息");
	//private JMenu menu_showdefendant=new JMenu("显示被告信息");
	private JMenu menu_deletecase=new JMenu("删除案例");
	private JMenu menu_deletedefendant=new JMenu("删除被告");
	private JMenu menu_deleterelation=new JMenu("删除联系");
	private JMenu menu_showrelation=new JMenu("显示关系");
	private JMenu menu_neo4j=new JMenu("生成Neo4j");
	private FrmLoad dlgLoad=null;
	private JPanel statusBar=new JPanel();
	
	private Object tblCaseTitle[]=Case.TABLE_TITLE;
	private Object tblCaseData[][];
	DefaultTableModel tblCaseModel=new DefaultTableModel();  //这个要查一下
	private JTable dataTableCase=new JTable(tblCaseModel);
	
	private Object tblDefendantTitle[]=Defendant.TABLE_TITLE;
	private Object tblDefendantData[][];
	DefaultTableModel tblDefendantModel=new DefaultTableModel();
	private JTable dataTableDefendant=new JTable(tblDefendantModel);
	
	private Object tblRelationTitle[]=Relation.TABLE_TITLE;
	private Object tblRelationData[][];
	DefaultTableModel tblRalationModel=new DefaultTableModel();
	private JTable dataTableRelation=new JTable(tblRalationModel);
	
	private Case curCase=null;
	private Defendant curDfendant=null;
	private Relation curRelation=null;
	
	List<Case> allCase=null;
	List<Defendant> allDefendant=null;
	List<Relation> allRelation=null;
	
	private void reloadCaseTable()  //相当于刷新案件
	{
		try
		{
			allCase=Util.casemanage.loadAll();
			
		}catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblCaseData=new Object[allCase.size()][Case.TABLE_TITLE.length];
		for(int i=0;i<allCase.size();i++)
		{
			for(int j=0;j<Case.TABLE_TITLE.length;j++)
			{
				tblCaseData[i][j]=allCase.get(i).getCell(j);
			}
		}
		tblCaseModel.setDataVector(tblCaseData,tblCaseTitle);
		this.dataTableCase.validate();
		this.dataTableCase.repaint();
	}
	
	
	private void reloadDefendantTable(int CaseId)  //相当于刷新
	{
		if(CaseId<0)
		{
			return;
		}
		curCase=allCase.get(CaseId);
		try
		{
			allDefendant=Util.defendantmanage.loadDefendant(curCase);
		}catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblDefendantData=new Object[allDefendant.size()][Defendant.TABLE_TITLE.length];
		for(int i=0;i<allDefendant.size();i++)
		{
			for(int j=0;j<Defendant.TABLE_TITLE.length;j++)
			{
				tblDefendantData[i][j]=allDefendant.get(i).getCell(j);
			}
		}
		tblDefendantModel.setDataVector(tblDefendantData,tblDefendantTitle);
		this.dataTableDefendant.validate();
		this.dataTableDefendant.repaint();
	}
	private void reloadRelation()
	{
		try
		{
			allRelation=Util.relationmanage.loadAll();
		}catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblRelationData=new Object[allRelation.size()][Relation.TABLE_TITLE.length];
		for(int i=0;i<allRelation.size();i++)
		{
			for(int j=0;j<Relation.TABLE_TITLE.length;j++)
			{
				tblRelationData[i][j]=allRelation.get(i).getCell(j);
			}
		}
		tblCaseModel.setDataVector(tblRelationData, tblRelationTitle);
		this.dataTableRelation.validate();
		this.dataTableRelation.repaint();
	}
	
	public FrmMain()
	{
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("knowledgemap");
		dlgLoad=new FrmLoad(this,"加载",true);
		dlgLoad.setVisible(true);
		this.menu_add.addActionListener(this);
		this.menu_showcase.addActionListener(this);
		this.menu_deletecase.addActionListener(this);
		this.menu_deletedefendant.addActionListener(this);
		this.menu_deleterelation.addActionListener(this);
		this.menu_showrelation.addActionListener(this);
		//this.menu_showdefendant.addActionListener(this);
		this.menu_neo4j.addActionListener(this);
		menubar.add(menu_add);
		menubar.add(menu_showcase);
		menubar.add(menu_showrelation);
		menubar.add(menu_deletecase);
		menubar.add(menu_deletedefendant);
		menubar.add(menu_deleterelation);
		//menubar.add(menu_showdefendant);
		menubar.add(menu_neo4j);
		
		this.getContentPane().add(new JScrollPane(this.dataTableCase),BorderLayout.NORTH);
		this.dataTableCase.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e)
			{
				int i=FrmMain.this.dataTableCase.getSelectedRow();
				if(i<0)
				{
					return;
				}
				FrmMain.this.reloadDefendantTable(i);
			}
		}
		);
		
		this.getContentPane().add(new JScrollPane(this.dataTableDefendant),BorderLayout.SOUTH);
		this.reloadCaseTable();
		statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel jlable=new JLabel("knowledgemap");
		statusBar.add(jlable);
		this.getContentPane().add(statusBar,BorderLayout.SOUTH);
		this.addWindowListener(new WindowAdapter(){   
	    	public void windowClosing(WindowEvent e){ 
	    		System.exit(0);
             }
        });
	    this.setVisible(true);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.menu_add)
		{
			dlgLoad=new FrmLoad(this,"加载",true);
			dlgLoad.setVisible(true);
		}else if(e.getSource()==this.menu_neo4j)
		{
			
		}else if(e.getSource()==this.menu_showcase)
		{
			
		}
		else if(e.getSource()==this.menu_showrelation)
		{
			
		}
		else if(e.getSource()==this.menu_deletecase)
		{
			
		}
		else if(e.getSource()==this.menu_deletedefendant)
		{
			
		}
		else if(e.getSource()==this.menu_deleterelation)
		{
			
		}
		
	}
}
