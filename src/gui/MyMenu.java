package gui;


import java.awt.BorderLayout;
//菜单 文件操作
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileFilter;


public class MyMenu extends JMenuBar{
	private Text temp;
	private	String fileContent = new String();
	//导入文件事件
	class LeadingFileAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			createFileDialog();
		}
		
	}
	//保存文件事件
	class SaveFileAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			createFileDir();
		}
		
	}
	public MyMenu(Text f) {
		temp = f;
		JMenu startMenu= new JMenu("文件");
		JMenuItem leadingFile= new JMenuItem("导入文件");
		JMenuItem saveFile = new JMenuItem("保存文件");
		startMenu.add(leadingFile);
		startMenu.add(saveFile);
		add(startMenu);
		//给导入文件绑定导入文件的事件
		LeadingFileAction leadingEvent = new LeadingFileAction();
		leadingFile.addActionListener(leadingEvent);
		//给保存文件导入事件
		SaveFileAction saveFileAction = new SaveFileAction();
		saveFile.addActionListener(saveFileAction);
	}
	
	//创建选择文件对话框-->选择文件
	public void createFileDialog() {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		chooser.setVisible(true);
		chooser.setSize(300,200 );
		chooser.showOpenDialog(temp);//展示文件框
		if(chooser.getSelectedFile()==null) {
			return;
		}
		String filename = chooser.getSelectedFile().getPath();
		//将文件信息导入文本域
		FileOperator fo = new FileOperator();
		temp.fileContent(fo.leadingFile(filename));
	}
	
	//创建文件保存对话框-->选择目录保存
	public void createFileDir() {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		chooser.setSize(300,200 );
		chooser.setFileFilter(new FileFilter() {
			
			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return "目录";
			}
			
			@Override
			public boolean accept(File f) {
				// TODO Auto-generated method stub
				return f.isDirectory();
			}
		});
		chooser.showSaveDialog(null);//展示文件框
		//保存文件的绝对路径
		if(chooser.getSelectedFile()!=null) {
			String filename = chooser.getSelectedFile().getAbsolutePath();
			FileOperator fo = new FileOperator();
			//先判断与源文件是否先等
			if(!fo.isEqual(filename,temp)) {
				fo.save(filename,temp);
				MyDialog d = new MyDialog(new JFrame(),"保存成功");
				d.setVisible(true);
			}else {
				MyDialog d = new MyDialog(new JFrame(),"文件没有更改");
				d.setVisible(true);
			}
		}
	}
	
	//保存对话框
	class MyDialog extends JDialog{
		public MyDialog(JFrame frame,String info) {
			super(frame,"提示",true);
			add(new JLabel(info),BorderLayout.CENTER);
			JPanel panel = new JPanel();
			JButton ok = new JButton("确定");
			ok.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					setVisible(false);
				}
			});
			panel.add(ok);
			add(panel,BorderLayout.SOUTH);
			setSize(250, 150);
		}
	}
}
