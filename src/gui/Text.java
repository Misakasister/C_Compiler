package gui;

import java.awt.Font;

import javax.swing.*;
//代码输入框组件
public class Text extends JTextArea {
	public Text(int lines, int columns) {
		super("int main() {\n\n    return 0;\n}",lines,columns);
		setLineWrap(true);
		Font  f  = new Font(Font.SERIF,Font.PLAIN, 14);
		this.setFont(f);
	}
	
	//将文件内容显示在文本域
	public void fileContent(String content) {
		//先清空内容
		String str = getText();
		replaceRange("", 0, str.length());
		append(content);
	}
}
