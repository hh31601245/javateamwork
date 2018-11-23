package ui;

import java.awt.Font;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import scala.reflect.io.File;

public class FrmLineChart {
	/*public boolean a=false;
	public FrmLineChart(boolean a)
	{
		this.a=a;
	}*/
	public static void showFrmLineChart(Map<String,String> list)
	{
		 try {
	            //种类数据集
	            DefaultCategoryDataset ds = new DefaultCategoryDataset();
//	            JFreeChart chart=null;
	            ChartFrame chartFrame = null;
	            String condition="shrs";
	            for(String key:list.keySet())
	            {
	            	ds.setValue(Integer.valueOf(list.get(key)),"minage",key);
	            }

	            Font font = new Font("宋体", Font.BOLD, 20);
	            //创建柱状图
	            JFreeChart chart = ChartFactory.createLineChart("最小年龄发展趋势折线图", "时间", "年龄", ds, PlotOrientation.VERTICAL, true, true, false);

	            //设置整个图片的标题字体
	            chart.getTitle().setFont(font);

	            //设置提示条字体
	            font = new Font("宋体", Font.BOLD, 15);
	            chart.getLegend().setItemFont(font);

	            //得到绘图区
	            CategoryPlot plot = (CategoryPlot) chart.getPlot();
	            //得到绘图区的域轴(横轴),设置标签的字体
	            plot.getDomainAxis().setLabelFont(font);

	            //设置横轴标签项字体
	            plot.getDomainAxis().setTickLabelFont(font);

	            //设置范围轴(纵轴)字体
	            plot.getRangeAxis().setLabelFont(font);
	            //存储成图片
	            //设置chart的背景图片
	           // chart.setBackgroundImage(ImageIO.read(new File("e:/test/1.jpg")));

	            //plot.setBackgroundImage(ImageIO.read(new File("e:/test/cc.png")));

	            plot.setForegroundAlpha(1.0f);
	           // ChartUtilities.saveChartAsJPEG(new File("e:/test/1.png"), chart, 600, 400);

	            chartFrame=new ChartFrame(condition+"",chart);
	            chartFrame.pack();
	            chartFrame.setVisible(true);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//FrmLineChart.showFrmLineChart();
	}

}
