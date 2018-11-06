package ui;

import java.awt.Frame;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
	private JMenu menu_showdefendant=new JMenu("显示被告信息");
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
	List<Relation> allrelation=null;
	
	private void reloadCaseTable()
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
	private void reloadDefendantTable(int CaseId)
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
	public FrmMain()
	{
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("knowledgemap");
		dlgLoad=new FrmLoad(this,"加载",true);
	}
}
