package ui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Case;
import model.Judgment;
import poi.LoadWord;
import regularexpression.Leading_in;
import util.BaseException;
import mysql.JudgmentManage;
import begin.Util;
public class FrmLoad extends JDialog implements ActionListener{
	private JPanel toolBar=new JPanel();
	private JPanel workPane=new JPanel();
	private JButton btnLoad=new JButton("载入");
	private JButton btnCancel=new JButton("取消");
	private JButton btnOk=new JButton("确定");
	private JButton btnAllSelect=new JButton("全选进入");
	private JButton btnDelete=new JButton("删除");
	private JList<String> list=new JList<String>();
	int i=0;
	private JScrollPane gd;    //滚动条
	private void Reload()
	{
		int i=0;
		if(begin.Util.judgmentmanage.loadAll()!=null)
		{
		String[] titles=new String[begin.Util.judgmentmanage.loadAll().size()];
		for(String title:begin.Util.judgmentmanage.loadAll())
		{
			
			titles[i]=title;
			i++;
		}
        list.setListData(titles);// 设置选项数据（内部将自动封装成 ListModel ）
		}
		else
		{
			String[] titiles=new String[1];
			titiles[0]="null";
			list.setListData(titiles);
		}
	}
	public FrmLoad(Frame f,String s,boolean b)
	//public FrmLoad()//测试用
	{
		super(f,s,b);
		//super();
		toolBar.setLayout(new FlowLayout(FlowLayout.CENTER));
		toolBar.add(this.btnLoad);
		JPanel kongge=new JPanel();
		kongge.setPreferredSize(new Dimension(100,0));
		toolBar.add(kongge);
		toolBar.add(this.btnAllSelect);
		JPanel kongge0=new JPanel();
		kongge0.setPreferredSize(new Dimension(100,0));
		toolBar.add(kongge0);
		toolBar.add(this.btnDelete);
		JPanel kongge1=new JPanel();
		kongge1.setPreferredSize(new Dimension(100,0));
		toolBar.add(kongge1);
		toolBar.add(this.btnOk);
		JPanel kongge2=new JPanel();
		kongge2.setPreferredSize(new Dimension(100,0));
		toolBar.add(kongge2);
		toolBar.add(this.btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		list.setPreferredSize(new Dimension(800,1000));// 设置一下首选大小
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);// 允许可间断的多选
		this.Reload();
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // 获取所有被选中的选项索引
                int[] indices = list.getSelectedIndices();
                // 获取选项数据的 ListModel
                ListModel<String> listModel = list.getModel();
                // 输出选中的选项
                for (int index : indices) {
                    System.out.println("选中: " + index + " = " + listModel.getElementAt(index));
                }
                System.out.println();
            }
        });
        list.setSelectedIndex(1);//默认选择第一项
        gd=new JScrollPane(list);
        gd.setPreferredSize(new Dimension(800,800));
        workPane.add(gd);
        workPane.setSize(800,800);
		this.getContentPane().add(workPane,BorderLayout.CENTER);
		this.setSize(850,900);
		double width=Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double heigh=Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int)(width-this.getWidth())/2,(int)(heigh-this.getHeight())/2);
		this.validate();
		btnLoad.addActionListener(this);
		btnAllSelect.addActionListener(this);
		btnDelete.addActionListener(this);
		btnOk.addActionListener(this);
		btnCancel.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		}
				
				);
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==this.btnLoad)
		{
		    JFileChooser jf=new JFileChooser();
		    jf.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
		    jf.setMultiSelectionEnabled(true);
		    jf.showOpenDialog(this); 
		    File f=jf.getSelectedFile();
		    String jurl=f.getAbsolutePath();
		    String jtitle=null;
		    String content=null;
		    System.out.println(jurl);
		    	 File file = new File(jurl);
		    if (file.isDirectory()) {
				         System.out.println("文件夹");
				         String[] filelist = file.list();
				         for (int i = 0; i < filelist.length; i++) {
				        	 Judgment j=new Judgment();
				        	 String jurl1=jurl+"\\"+filelist[i];
				        	 //System.out.println(filelist[i]);
				        	 System.out.println(jurl1);
				        	 try {
				 				content=LoadWord.LoadAllWord(jurl1);
				 			} catch (IOException e1) {
				 				// TODO Auto-generated catch block
				 				e1.printStackTrace();
				 			}
				        	 Pattern p=Pattern.compile("浙\\d+刑初\\d+号"); //括号里写正则表达式
							 Matcher m= p.matcher(jurl1);  //括号里是目标字符串
							 while(m.find())
								{
									//System.out.println(m.group());
									jtitle=m.group();
									break;
								}
							System.out.println(jtitle);
							j.setTitle(jtitle);
						    j.setContent(content);
						    Util.judgmentmanage.addJudgment(j);
						    Leading_in.Leading_in_Case(jurl1,jtitle);
						    Leading_in.Leading_in_Defendant(jurl1,jtitle);

				         }

			}
		    else
		    {
		    Judgment j=new Judgment();
		    try {
				content=LoadWord.LoadAllWord(jurl);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    //s=s.split("\\")[s.split("\\").length-1];
				Pattern p=Pattern.compile("浙\\d+刑初\\d+号"); //括号里写正则表达式
				Matcher m= p.matcher(jurl);  //括号里是目标字符串
				while(m.find())
				{
					//System.out.println(m.group());
					jtitle=m.group();
					break;
				}
				System.out.println(jurl);
		    j.setTitle(jtitle);
		    j.setContent(content);
		    Util.judgmentmanage.addJudgment(j);
		    Leading_in.Leading_in_Case(jurl,jtitle);
		    Leading_in.Leading_in_Defendant(jurl,jtitle);		    
		    }
		    this.Reload();
		}else if(e.getSource()==this.btnOk)
		{
			System.out.println(123);
			int[] indices = list.getSelectedIndices();
            // 获取选项数据的 ListModel
            ListModel<String> listModel = list.getModel();
            // 输出选中的选项
            for (int index : indices) {
            	Case caseitem=null;
				try {
					caseitem = Util.casemanage.searchCase(listModel.getElementAt(index));
					Case.caselist.add(caseitem);
				} catch (BaseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                
            }
		//	Leading_in.Leading_in_Case();
		//	Leading_in.Leading_in_Defendant();
		//	Leading_in.Leading_in_Relation();
			this.setVisible(false);
		}else if(e.getSource()==this.btnCancel)
		{
			System.exit(0);
		}/*else if(e.getSource()==this.btnALLSelect)
		{
			list.getSelectedIndices();
		}*/
		else if(e.getSource()==this.btnDelete)
		{
			int[] indices = list.getSelectedIndices();
            // 获取选项数据的 ListModel
            ListModel<String> listModel = list.getModel();
            // 输出选中的选项
            for (int index : indices) {
                Util.judgmentmanage.deleteJudgment(listModel.getElementAt(index));
            }
            this.Reload();
            System.out.println();
		}
		else if(e.getSource()==this.btnAllSelect)
		{
			 List<String> titles=Util.judgmentmanage.loadAll();
			 for(String ti:titles)
			 {
	            	Case caseitem=null;
					try {
						caseitem = Util.casemanage.searchCase(ti);
						Case.caselist.add(caseitem);
					} catch (BaseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	                
	            }
				this.setVisible(false);
		}
	}
	public static void main(String[] args)
	{
		//FrmLoad f=new FrmLoad();
		//f.setVisible(true);
		
	}
}
/*public class FrmLoad extends JDialog implements ActionListener{
	private JPanel toolBar=new JPanel();
	private JPanel workPane=new JPanel();
	private JButton btnLoad=new JButton("载入");
	private JButton btnCancel=new JButton("取消");
	private JButton btnOk=new JButton("确定");
	private JLabel lableurl=new JLabel("文件url:");
	private JTextField edturl=new JTextField(50);
	public FrmLoad(Frame f,String s,boolean b)
	//public FrmLoad()//测试用
	{
		super(f,s,b);
		//super();
		toolBar.setLayout(new FlowLayout(FlowLayout.CENTER));
		toolBar.add(this.btnLoad);
		toolBar.add(this.btnOk);
		toolBar.add(this.btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(lableurl);
		workPane.add(edturl);
		this.getContentPane().add(workPane,BorderLayout.CENTER);
		this.setSize(700,150);
		double width=Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double heigh=Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int)(width-this.getWidth())/2,(int)(heigh-this.getHeight())/2);
		this.validate();
		btnLoad.addActionListener(this);
		btnOk.addActionListener(this);
		btnCancel.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		}
				
				);
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==this.btnLoad)
		{
		    JFileChooser jf=new JFileChooser();
		    jf.showOpenDialog(this);
		    File f=jf.getSelectedFile();
		    String s=f.getAbsolutePath();
		    this.edturl.setText(s);
		}else if(e.getSource()==this.btnOk)
		{
			URL.WordURL=edturl.getText();
		//	Leading_in.Leading_in_Case();
		//	Leading_in.Leading_in_Defendant();
		//	Leading_in.Leading_in_Relation();
			this.setVisible(false);
		}else if(e.getSource()==this.btnCancel)
		{
			System.exit(0);
		}
	}
	public static void main(String[] args)
	{
		//FrmLoad f=new FrmLoad();
		//f.setVisible(true);
		
	}
}*/

