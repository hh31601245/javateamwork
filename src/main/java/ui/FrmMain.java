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
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import begin.Util;
import util.BaseException;
import model.Case;
import model.Defendant;
import csv.Lead_out;
//import model.Relation;

public class FrmMain extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JMenuBar menubar=new JMenuBar();
	//private JMenu menu_add=new JMenu("添加新案例");
	//private JMenu menu_deletecase=new JMenu("删除案例");
	//private JMenu menu_deletedefendant=new JMenu("删除被告");
	private JMenu menu_statistics =new JMenu("统计");
	private JMenuItem menuitem_statistics=new JMenuItem("统计");
	private JMenu menu_load_out=new JMenu("导出");
	private JMenuItem menuitem_load_out=new JMenuItem("导出");
	private JMenu menu_neo4j=new JMenu("生成Neo4j");
	private JMenuItem menuitem_neo4j=new JMenuItem("生成Neo4j");
	private FrmLoad dlgLoad=null;
	private FrmStatistics dlgStatistics=null;
	private JPanel statusBar=new JPanel();
	
	private Object tblCaseTitle[]=Case.TABLE_TITLE;
	private Object tblCaseData[][];
	DefaultTableModel tblCaseModel=new DefaultTableModel();  //这个要查一下
	private JTable dataTableCase=new JTable(tblCaseModel);
	
	private Object tblDefendantTitle[]=Defendant.TABLE_TITLE;
	private Object tblDefendantData[][];
	DefaultTableModel tblDefendantModel=new DefaultTableModel();
	private JTable dataTableDefendant=new JTable(tblDefendantModel);
	
//	private Object tblRelationTitle[]=Relation.TABLE_TITLE;
	private Object tblRelationData[][];
	DefaultTableModel tblRalationModel=new DefaultTableModel();
	private JTable dataTableRelation=new JTable(tblRalationModel);
	
	private Case curCase=null;
	private Defendant curDfendant=null;
//	private Relation curRelation=null;
	
	List<Case> allCase=null;
	List<Defendant> allDefendant=null;
//	List<Relation> allRelation=null;
	
	int Caseid=0;
	private void reloadCaseTable()  //相当于刷新案件
	{
		allCase=Case.caselist;
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
		System.out.println(curCase.getCid());
		try
		{
			allDefendant=Util.defendantmanage.loadDefendant(curCase);
			System.out.println(allDefendant.get(0).getDname());
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
				//System.out.println(allDefendant.get(i).getCell(j));
			}
		}
		tblDefendantModel.setDataVector(tblDefendantData,tblDefendantTitle);
		this.dataTableDefendant.validate();
		this.dataTableDefendant.repaint();
	}
/*	private void reloadRelation()
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
	*/
	public FrmMain()
	{
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("knowledgemap");
		dlgLoad=new FrmLoad(this,"加载",true);
		dlgLoad.setVisible(true);
		//this.menu_add.addActionListener(this);
		//this.menu_deletecase.addActionListener(this);
		//this.menu_deletedefendant.addActionListener(this);		
		//menubar.add(menu_add);
		//menubar.add(menu_deletecase);
		//menubar.add(menu_deletedefendant);
		//menubar.add(menu_showdefendant);
		this.menu_statistics.add(menuitem_statistics);
		this.menu_load_out.add(menuitem_load_out);
		this.menu_neo4j.add(menuitem_neo4j);
		menubar.add(menu_statistics);
		menubar.add(menu_load_out);
		menubar.add(menu_neo4j);
		this.menuitem_statistics.addActionListener(this);
		this.menuitem_load_out.addActionListener(this);
		this.menuitem_neo4j.addActionListener(this);
		this.setJMenuBar(menubar);
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
				Caseid=i;
			}
		}
		);
		
		this.getContentPane().add(new JScrollPane(this.dataTableDefendant),BorderLayout.CENTER);
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
		if(e.getSource()==this.menuitem_statistics)
		{
			System.out.println(123);
			//this.setVisible(false);
			this.dlgStatistics=new FrmStatistics(this,"统计",true,Case.caselist);
			this.dlgStatistics.setVisible(true);
			//this.setVisible(true);
		}
		else if(e.getSource()==this.menuitem_load_out)
		{
			Lead_out.write();
		}
		else if(e.getSource()==this.menuitem_neo4j)
		{
			neo4j.createNeo4j.create();
		}
		/*if(e.getSource()==this.menu_add)
		{
			dlgLoad=new FrmLoad(this,"加载",true);
			dlgLoad.setVisible(true);
		}else if(e.getSource()==this.menu_neo4j)
		{
			
		}else if(e.getSource()==this.menu_showcase)
		{
			try {
				Util.casemanage.loadAll();
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==this.menu_showrelation)
		{
			
		}
		else if(e.getSource()==this.menu_deletecase)
		{
			if(this.curCase==null)
			{
				JOptionPane.showMessageDialog(null, "请选择计划", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try
			{
				Util.casemanage.deleteCase(this.curCase);
				this.reloadCaseTable();
			}catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if(e.getSource()==this.menu_deletedefendant)
		{
			if(this.curDfendant==null)
			{
				JOptionPane.showMessageDialog(null,"请选择计划","错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try
			{
				Util.defendantmanage.deleteDefendant(this.curDfendant);
				this.reloadDefendantTable(Caseid);
			}catch(BaseException e1)
			{
				JOptionPane.showMessageDialog(null,e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(e.getSource()==this.menu_deleterelation)
		{
			
		}*/
		
	}
}
