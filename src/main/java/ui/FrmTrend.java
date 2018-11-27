package ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import model.Case;
import trendJudge.agetrendjudge;
public class FrmTrend   extends JDialog implements ActionListener{
	public  JButton btn=null;
	public JButton btn2=null;
	public Map<String,String> result=new TreeMap();
	//public FrmLineChart dlgLineChart=null;
	public FrmTrend(Frame f,String s,boolean b)
	{
		super(f,s,b);
		System.out.println("456");
		JPanel panel = new JPanel();//创建内容面板1，流式布局
        JPanel panel2 = new JPanel();//创建内容面板2，流式布局
        result=agetrendjudge.judgetrend(Case.caselist);
        
        JLabel label01 = new JLabel();//文本标签
        label01.setText("结论："+agetrendjudge.conclusion(result).split("a")[0]);
        label01.setFont(new Font(null, Font.PLAIN, 25));  // 设置字体，null 表示使用默认字体
        panel.add(label01);//并添加到 面板容器 中
        JLabel label02 = new JLabel();//文本标签
        label02.setText(""+agetrendjudge.conclusion(result).split("a")[1]);
        label02.setFont(new Font(null, Font.PLAIN, 25));  // 设置字体，null 表示使用默认字体
        panel.add(label02);//并添加到 面板容器 中
        btn = new JButton("查看折线图");// 创建一个按钮  
        btn.setFont(new Font(null, Font.PLAIN, 20));
        btn.addActionListener(this);
        panel2.add(btn);
        JPanel kongge=new JPanel();
		kongge.setPreferredSize(new Dimension(100,0));
		panel2.add(kongge);
        btn2 = new JButton("退出");// 创建一个按钮  
        btn2.setFont(new Font(null, Font.PLAIN, 20));
        btn2.addActionListener(this);
        panel2.add(btn2);
        
        Box vBox = Box.createVerticalBox();// 创建一个垂直盒子容器, 把上面2 个 JPanel 串起来作为内容面板添加到窗口
        vBox.add(panel);
        vBox.add(panel2);
        
        this.setContentPane(vBox);
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
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==this.btn)
		{
			this.setVisible(false);
			FrmLineChart.showFrmLineChart(result);
			
		}
		else if(arg0.getSource()==this.btn2)
		{
			this.setVisible(false);
		}
	}

}
