package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.data.general.DefaultPieDataset;
import model.Defendant;
import util.BaseException;
import begin.Util;

import model.Case;
import mysql.CaseManage;
import mysql.DefendantManage;
/**
 * 
 * @author Administrator
 *统计的包括涉案人数，男女比例，学历，年龄，刑罚种类，*贩卖的毒品的种类，
 */
public class FrmStatistics extends JDialog implements ActionListener {
		private JPanel toolBar=new JPanel();
		private JPanel workPane=new JPanel();
		private JComboBox comboBox=new JComboBox();
		private JLabel lablecondition=new JLabel("统计的项目");
		private JButton btnOK=new JButton("确定");
		private Map<String,Integer> resultmap=new HashMap<String,Integer>();
		private ChartFrame chartFrame =null;
		private JFreeChart chart=null;
		//private ChartPanel workPane=new ChartPanel(chart);
		private DefaultPieDataset dpd=null;
		private String condition="涉案人数";
		private List<Case> cases=new ArrayList<Case>();
		private void Reload(Map<String,Integer> map,String condition)
		{
			 dpd = new DefaultPieDataset(); // 建立一个默认的饼图
			 dpd.clear();
			 for(Map.Entry<String, Integer> entry : map.entrySet())
			 {
				 System.out.println(entry.getKey()+" "+entry.getValue());
				 dpd.setValue(entry.getKey(), entry.getValue());
			 }
			//chartFrame.setLayout(new FlowLayout(FlowLayout.LEFT));
				// 创建主题样式  (可以解决中文乱码问题)
		         StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
		         // 设置标题字体
		         standardChartTheme.setExtraLargeFont(new Font("隶书", Font.BOLD, 20));
		         // 设置图例的字体
		         standardChartTheme.setRegularFont(new Font("宋书", Font.PLAIN, 15));
		         // 设置轴向的字体
		         standardChartTheme.setLargeFont(new Font("宋书", Font.PLAIN, 15));
		         // 应用主题样式
		         ChartFactory.setChartTheme(standardChartTheme);
		         
		         chart = ChartFactory.createPieChart(condition+"统计表", dpd, true,
		                 true, false);
		      // 可以查具体的API文档,第一个参数是标题，第二个参数是一个数据集，第三个参数表示是否显示Legend，第四个参数表示是否显示提示，第五个参数表示图中是否存在URL	
					chartFrame = new ChartFrame(condition+"统计表",chart);
			         // chart要放在Java容器组件中，ChartFrame继承自java的Jframe类。该第一个参数的数据是放在窗口左上角的，不是正中间的标题。
			         chartFrame.pack(); // 以合适的大小展现图形
			         chartFrame.setVisible(true);// 图形是否可见
		}
		public FrmStatistics(Frame f,String s,boolean b,List<Case> caselist)
		//public FrmStatistics(List<Case> caselist)
		{
			super(f,s,b);
			cases=caselist;
			System.out.println(cases.get(0).getNumberPeople());
			toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
			JPanel kongge0=new JPanel();
			kongge0.setPreferredSize(new Dimension(200,0));
			toolBar.add(kongge0);
			toolBar.add(this.lablecondition);
			JPanel kongge=new JPanel();
			kongge.setPreferredSize(new Dimension(20,0));
			toolBar.add(kongge);
			toolBar.add(this.comboBox);
			JPanel kongge1=new JPanel();
			kongge1.setPreferredSize(new Dimension(20,0));
			toolBar.add(kongge1);
			toolBar.add(this.btnOK);
			JPanel kongge2=new JPanel();
			kongge2.setPreferredSize(new Dimension(200,0));
			toolBar.add(kongge2);
			comboBox.addItem("涉案人数");
			comboBox.addItem("性别");
			comboBox.addItem("学历");
			comboBox.addItem("年龄");
			comboBox.addItem("刑罚种类");
			//comboBox.addItem("毒品种类");
			comboBox.setEditable(true); 
			 
		    comboBox.addActionListener(new ActionListener() { 
		      public void actionPerformed(ActionEvent e) { 
		        System.out.println("Selected =" + ((JComboBox)e.getSource()).getSelectedItem());
		        condition=((JComboBox)e.getSource()).getSelectedItem().toString();
		      } 
		    }); 
			//workPane.add(toolBar);
			//workPane.add(dpd);
		    for(int i=0;i<cases.size();i++)
			{
				resultmap.put(cases.get(i).getCid(),cases.get(i).getNumberPeople());
				
			}
	         workPane.add(toolBar);
	         this.btnOK.addActionListener(this);
	         this.getContentPane().add(workPane,BorderLayout.CENTER);
	 		this.setSize(850,300);
	 		double width=Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	 		double heigh=Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	 		this.setLocation((int)(width-this.getWidth())/2,(int)(heigh-this.getHeight())/2);
	 		this.validate();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==this.btnOK)
			{
				resultmap.clear();
				if(condition.equals("涉案人数"))
				{
					for(int i=0;i<cases.size();i++)
					{
						resultmap.put(cases.get(i).getCid(),cases.get(i).getNumberPeople());
						
					}
					this.setVisible(false);
					this.Reload(resultmap,"涉案人数");
				}else if(condition.equals("性别"))
				{
					resultmap.clear();
					Integer count=0;
					for(int i=0;i<cases.size();i++)
					{
						Map<String,Integer> map=new HashMap<String,Integer>();
						System.out.println(cases.get(i).getCid());
						map=begin.Util.defendantmanage.StatisticsDefendantSex(cases.get(i));
						//resultmap.putAll(map);
						for (String key :map.keySet()) {  //通过foreach方法来遍历
							   if(resultmap.containsKey(key))
							   {
								   count=resultmap.get(key)+map.get(key);
								   resultmap.put(key,count);
							   }
							   else
							   {
								   resultmap.put(key,map.get(key));
							   }
						       System.out.println("key= "+ key + " and value= " + resultmap.get(key));
						 }
					}
					this.setVisible(false);
					this.Reload(resultmap,"性别");
				}else if(condition.equals("学历"))
				{
					resultmap.clear();
					Integer count=0;
					for(int i=0;i<cases.size();i++)
					{
						Map<String,Integer> map=new HashMap<String,Integer>();
						map=begin.Util.defendantmanage.StatisticsDefendantEducation(cases.get(i));
						for (String key :map.keySet()) {  //通过foreach方法来遍历
							   if(resultmap.containsKey(key))
							   {
								   count=resultmap.get(key)+map.get(key);
								   resultmap.put(key,count);
							   }
							   else
							   {
								   resultmap.put(key,map.get(key));
							   }
						       System.out.println("key= "+ key + " and value= " + resultmap.get(key));
						 }
					}
					this.setVisible(false);
					this.Reload(resultmap,"学历");
				}else if(condition.equals("年龄"))
				{
					resultmap.clear();
					String[] age={"<18","18~30岁","30~40岁","40~50岁",">=50岁"};
					Integer[] count= {0,0,0,0,0};
					for(int i=0;i<cases.size();i++)
					{
						try {
							List<Defendant> defendantlist=begin.Util.defendantmanage.loadDefendant(cases.get(i));
							for(Defendant d:defendantlist)
							{
								if(d.getAge()>=50)
								{
									count[4]++;
								}else if(d.getAge()>=40)
								{
									count[3]++;
								}else if(d.getAge()>=30)
								{
									count[2]++;
								}else if(d.getAge()>=18)
								{
									count[1]++;
								}else
								{
									count[0]++;
								}
							}
							for(int j=0;j<5;j++)
							{
								resultmap.put(age[j],count[j]);
							}
							
							
						} catch (BaseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					this.setVisible(false);
					this.Reload(resultmap,"年龄");
				}else if(condition.equals("刑罚种类"))
				{
					resultmap.clear();
					Integer count=0;
					for(int i=0;i<cases.size();i++)
					{
						Map<String,Integer> map=new HashMap<String,Integer>();
						map=begin.Util.defendantmanage.StatisticsDefendantPenaltyType(cases.get(i));
						for (String key :map.keySet()) {  //通过foreach方法来遍历
							   if(resultmap.containsKey(key))
							   {
								   count=resultmap.get(key)+map.get(key);
								   resultmap.put(key,count);
							   }
							   else
							   {
								   resultmap.put(key,map.get(key));
							   }
						       System.out.println("key= "+ key + " and value= " + resultmap.get(key));
						 }
					}
					this.setVisible(false);
					this.Reload(resultmap,"刑罚种类");
				}
			}
		}
		public static void main(String[] args)
		{
			//FrmStatistics f=new FrmStatistics(Case.caselist);
			//f.setVisible(true);
			
		}
}
