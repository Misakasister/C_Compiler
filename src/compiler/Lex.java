package compiler;

import java.util.ArrayList;
import gui.FileOperator;

//词法分析程序
public class Lex {
	private String content;
	private String[] key = {"main","if","else","while","for","int","return"};
	ArrayList<String> lexOut = new ArrayList<String>();
	public Lex(String content) {
		this.content = content;
		lexProgram();
	}
	//分析词法
	public void lexProgram() {
		String[] word = content.split("\\s+");//去掉多余的空格
		for(String ss : word) {
			int len = ss.length();
			StringBuffer temp= new StringBuffer();
			for(int i = 0; i < len; i++) {
				//界符
				if(ss.charAt(i)=='{'||ss.charAt(i)=='}'||ss.charAt(i)=='('||ss.charAt(i)==')'||ss.charAt(i)==','||ss.charAt(i)==';') {
					if(temp.length()!=0) {
						judge(temp);
						temp.delete(0, temp.length());
					}
					lexOut.add(new String("(boundary,"+ss.charAt(i)+")"));
				}
				//运算符
				else if(ss.charAt(i)=='+'||ss.charAt(i)=='-'||ss.charAt(i)=='*'||ss.charAt(i)=='/'||ss.charAt(i)=='<'||ss.charAt(i)=='>'||ss.charAt(i)=='=') {
					if(temp.length()!=0) {
						judge(temp);
						temp.delete(0, temp.length());
					}
					if(i+1 < len&&ss.charAt(i+1)=='=') {
						lexOut.add(new String("(operator,"+ss.charAt(i)+ss.charAt(i+1)+")"));
						i++;
					}else {
						lexOut.add(new String("(operator,"+ss.charAt(i)+")"));
					}
				}else {
					temp.append(ss.charAt(i));
				}
			}
			if(temp.length()!=0) {
				judge(temp);
				temp.delete(0, temp.length());
			}
		}
		FileOperator fo = new FileOperator();
		fo.writeLexFile(lexOut);
		fo.readLex();
	}
	private void judge(StringBuffer str) {
		if(str.charAt(0)>='0'&&str.charAt(0)<='9') {
			lexOut.add(new String("(integer,"+new String(str)+")"));
			return;
		}
		for(int i = 0; i < key.length; i++) {
			if(new String(str).equals(key[i])) {
				lexOut.add(new String("(keyword,"+new String(str)+")"));
				return;
			}
		}
		lexOut.add(new String("(identifier,"+new String(str)+")"));
		return;
	}
}
