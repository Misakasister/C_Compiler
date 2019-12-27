package gui;
import java.awt.BorderLayout;
import compiler.*;


import java.awt.Dimension;
import java.awt.Menu;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
//������
public class MainFrame extends JFrame{
	public MainFrame() {
		//��ʼ����Ļ��С
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;
		setSize(width/2,height/2);
		setTitle("СC���Ա�����");
		//���� 3��panel������
		Box box = Box.createVerticalBox();
		//���밴ť-->�Ϸ�
		JButton compilerButton = new JButton("����");
		JLabel daginfo = new JLabel("������Ҫ����ı�����Ϣ���÷ֺŸ���");
		JTextField Daginput = new JTextField(20);
		JRadioButton c1 = new JRadioButton("dag�Ż�");
		JPanel northPanel = new JPanel();
		northPanel.add(compilerButton);
		northPanel.add(c1);
		northPanel.add(daginfo);
		northPanel.add(Daginput);
		box.add(northPanel);
		//����ı���-->�м�
		Text textArea = new Text(22, 100);
		JPanel textPanel = new JPanel();
		textPanel.add(textArea);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setRowHeaderView(new LineNumberHeaderView());
		textPanel.add(scrollPane);
		box.add(textPanel);
		//�˵�
		MyMenu menuBar = new MyMenu(textArea);
		setJMenuBar(menuBar);
		setVisible(true);
		//������Ϣ
		JPanel errorPanel = new JPanel();
		JLabel errorInfo = new JLabel("������Ϣ:");
		errorPanel.add(errorInfo,BorderLayout.NORTH);
		box.add(errorPanel);
		add(box);
        compilerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame showInfo = new JFrame("������Ϣչʾ");
				FileOperator fo = new FileOperator();
				Box b1=Box.createHorizontalBox(); 
				Lex lex = new Lex(textArea.getText());
				JPanel lexPanel = new JPanel();
				JTextArea lexTextArea = new JTextArea(fo.showLex(),100,30);
				JScrollPane lexScrollPane = new JScrollPane(lexTextArea);
				lexPanel.setSize(200,200);
				lexPanel.add(lexTextArea);
				b1.add(lexPanel);
				showInfo.add(lexScrollPane);
				//�﷨
				Parse p = new Parse();
				JPanel parsePanel = new JPanel();
				JTextArea parseTextArea = new JTextArea(fo.showParse(),100,30);
				JScrollPane parseScrollPane = new JScrollPane(parseTextArea);
				parsePanel.setSize(200,200);
				parsePanel.add(parseTextArea);
				b1.add(parsePanel);
				showInfo.add(parseScrollPane);
				//DAG
				if(c1.isSelected()) {
					DAG dag = new DAG(Daginput.getText());
					JTextArea dagInfo = new JTextArea(fo.showDag(),100,30);
					JPanel dagPanel = new JPanel();
					dagPanel.add(dagInfo);
					dagPanel.setSize(200,200);
					b1.add(dagPanel);
				}
				//�м����
				SimpleCode sc = new SimpleCode();
				JPanel midPanel = new JPanel();
				JTextArea midTextArea = new JTextArea(fo.showMid(),100,30);
				JScrollPane midScrollPane = new JScrollPane(midTextArea);
				midPanel.setSize(200,200);
				midPanel.add(midTextArea);
				b1.add(midPanel);
				showInfo.add(b1);
				//������Ϣ
				errorInfo.setText(fo.showError());
				showInfo.setSize(900,1000);
				showInfo.setVisible(true);
			}
		});
		//�ر��¼�
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				MyDialog d = new MyDialog(new JFrame(),menuBar);
				d.setVisible(true);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
	}
	//����Ի���
		class MyDialog extends JDialog{
			public MyDialog(JFrame frame,MyMenu m) {
				super(frame,"��ʾ",true);
				add(new JLabel("�Ƿ񱣴��ļ�"),BorderLayout.CENTER);
				JPanel panel = new JPanel();
				JButton ok = new JButton("����");
				JButton no = new JButton("������");
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
