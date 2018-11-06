package ui;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.URL;

public class FrmLoad extends JDialog implements ActionListener{
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
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnLoad);
		toolBar.add(this.btnOk);
		toolBar.add(this.btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(lableurl);
		workPane.add(edturl);
		this.getContentPane().add(workPane,BorderLayout.CENTER);
		this.setSize(320, 280);
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
		/*	try {                               //测试用
				poi.load.readAndWriterTest3();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
			this.setVisible(false);
		}else if(e.getSource()==this.btnCancel)
		{
			System.exit(0);
		}
	}
	public static void main(String[] args)
	{
		FrmLoad f=new FrmLoad();
		f.setVisible(true);
		
	}
}

