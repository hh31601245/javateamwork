package ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import model.Case;
import model.Defendant;
import util.BaseException;

public class FrmSearch  extends JDialog implements ActionListener{
	public JTextField textField=null;
	public JButton btn=null;
	public JList<String> list=null;
	public FrmSearch(Frame f,String s,boolean b)
	{
		
		super(f,s,b);
		System.out.println("789");
//		jf.setSize(500, 500);                       // 设置窗口大小
      //  jf.setLocationRelativeTo(null);             // 把窗口位置设置到屏幕中心
        
        JPanel panel = new JPanel();//创建内容面板1，流式布局
        JPanel panel2 = new JPanel();//创建内容面板2，流式布局
        
        
        JLabel label01 = new JLabel();//文本标签
        label01.setText("输入：");
        label01.setFont(new Font(null, Font.PLAIN, 25));  // 设置字体，null 表示使用默认字体
        panel.add(label01);//并添加到 面板容器 中

        textField = new JTextField(8);//创建文本框
        textField.setFont(new Font(null, Font.PLAIN, 20));
        panel.add(textField);
 
        btn = new JButton("确定");// 创建一个按钮  
        btn.setFont(new Font(null, Font.PLAIN, 20));
        btn.addActionListener(this);
        panel.add(btn);
        
    
        list = new JList<String>();// 创建一个 JList 实例
        list.setFont(new Font(null, Font.PLAIN, 25));
        list.setPreferredSize(new Dimension(5000, 1000));// 设置一下首选大小     
        list.setListData(new String[]{"没有内容"});// 设置选项数据（内部将自动封装成 ListModel ）
        list.setSelectedIndex(0);// 设置默认选中项
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);// 允许可间断的多选
//        scrollPane.add(list);
//        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(
        		list//,
            //    ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
             //   ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );

        Box vBox = Box.createVerticalBox();// 创建一个垂直盒子容器, 把上面2 个 JPanel 串起来作为内容面板添加到窗口
        vBox.add(panel);
        vBox.add(scrollPane);
        //this.setSize(850,300);
        this.setContentPane(vBox);
        
       // jf.pack();
        this.setSize(850,300);
 		double width=Toolkit.getDefaultToolkit().getScreenSize().getWidth();
 		double heigh=Toolkit.getDefaultToolkit().getScreenSize().getHeight();
 		this.setLocation((int)(width-this.getWidth())/2,(int)(heigh-this.getHeight())/2);
 		this.validate();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.btn)
		{
			String name=textField.getText();
			List<Defendant> result=new ArrayList<Defendant>();
			try {
				for(Case c:Case.caselist)
				{
					System.out.println(c.getCid());
					List<Defendant> resultitem=new ArrayList<Defendant>();
					resultitem=begin.Util.defendantmanage.vagueSearch(name,c.getCid());
					result.addAll(resultitem);
				}
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String[] item= {"没找到符合条件的对象"};
			if(result.size()>0)
			{
				int i=0;
				for(Defendant d:result)
				{
					item[i]="姓名："+d.getDname()
							+";  性别"+d.getSex()
					        +";  年龄："+d.getAge()
					        +";  生日："+d.getDateOfBrith()
					        +";  学历："+d.getEducation()
							+";  罪行："+d.getCrime()
							+";  刑罚："+d.getPenaltyType()
							+";  刑期："+d.getSentence()
							+";  经济刑："+d.getPropertyPenaltyType()
							+";  罚金"+d.getPropertyPenaltyAmount();
					i++;
				}
			}
			list.setListData(item);// 设置选项数据（内部将自动封装成 ListModel ）
		}
	}

}
