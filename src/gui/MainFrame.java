package gui;
import java.awt.BorderLayout;
import compiler.*;


import java.awt.Dimension;
import java.awt.Menu;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
//主界面
public class MainFrame extends JFrame{
	public MainFrame() {
		//初始化屏幕大小
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;
		setSize(width/2,height/2);
		setTitle("小C语言编译器");
		//布局 3个panel竖着排
		Box box = Box.createVerticalBox();
		//编译按钮-->上方
		JButton compilerButton = new JButton("编译");
		JLabel daginfo = new JLabel("请输入要保存的变量信息，用分号隔开");
		JTextField Daginput = new JTextField(20);
		JPanel northPanel = new JPanel();
		northPanel.add(compilerButton);
		northPanel.add(daginfo);
		northPanel.add(Daginput);
		box.add(northPanel);
		//添加文本域-->中间
		Text textArea = new Text(22, 100);
		JPanel textPanel = new JPanel();
		textPanel.add(textArea);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setRowHeaderView(new LineNumberHeaderView());
		textPanel.add(scrollPane);
		box.add(textPanel);
		compilerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame showInfo = new JFrame("具体信息展示");
				FileOperator fo = new FileOperator();
				Lex lex = new Lex(textArea.getText());
				JPanel lexPanel = new JPanel();
				JTextArea lexTextArea = new JTextArea(fo.showLex());
				JScrollPane lexScrollPane = new JScrollPane(lexTextArea);
				lexPanel.setSize(400,200);
				lexPanel.add(lexTextArea);
				showInfo.add(lexPanel,BorderLayout.NORTH);
				showInfo.add(lexScrollPane);
				//语法
				Parse p = new Parse();
				JPanel parsePanel = new JPanel();
				JTextArea parseTextArea = new JTextArea(fo.showParse());
				JScrollPane parseScrollPane = new JScrollPane(parseTextArea);
				parsePanel.setSize(400,200);
				parsePanel.add(parseTextArea);
				showInfo.add(parsePanel,BorderLayout.WEST);
				showInfo.add(parseScrollPane);
				//DAG
				DAG dag = new DAG(Daginput.getText());
				showInfo.setSize(900,1000);
				showInfo.setVisible(true);
			}
		});
		//菜单
		MyMenu menuBar = new MyMenu(textArea);
		setJMenuBar(menuBar);
		setVisible(true);
		//错误信息
		JPanel errorPanel = new JPanel();
		JLabel errorInfo = new JLabel("错误信息:");
		errorPanel.add(errorInfo,BorderLayout.NORTH);
		box.add(errorPanel);
		add(box);
		//关闭事件
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				MyDialog d = new MyDialog(new JFrame(),menuBar);
				d.setVisible(true);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}

		});
	}
	//保存对话框
		class MyDialog extends JDialog{
			public MyDialog(JFrame frame,MyMenu m) {
				super(frame,"提示",true);
				add(new JLabel("是否保存文件"),BorderLayout.CENTER);
				JPanel panel = new JPanel();
				JButton ok = new JButton("保存");
				JButton no = new JButton("不保存");
				no.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						setVisible(false);
					}
				});
	            ok.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						m.createFileDir();
						setVisible(false);
					}
				});
				panel.add(ok);
				panel.add(no);
				add(panel,BorderLayout.SOUTH);
				setSize(250, 150);
			}
		}
}
