package compiler;

import java.util.ArrayList;


import gui.FileOperator;
//表达式语法分析
public class Parse {
	private ArrayList<String> data;
	private int current;
	private int num;
	private FileOperator fo;
	private ArrayList<String> parseData;
	private ArrayList<String> expressData;
	private ArrayList<String> errorDate;
	public Parse() {
		fo = new FileOperator();
		errorDate = new ArrayList<String>();
		parseData= new ArrayList<String>();
		expressData = new ArrayList<String>();
		this.data=fo.readLex();
		parseFind();
		fo.writeParse(parseData);
		fo.writeExpress(expressData);
		fo.writeError(errorDate);
	}
	
	//在词法分析结果中找到表达式
	public void parseFind() {
		ArrayList<String> temp = new ArrayList<String>();
		for(int i = 3; i < data.size(); i++) {
			if(data.get(i).startsWith("(keyword,if)")||data.get(i).startsWith("(keyword,while)")) {//是if跳过括号的
				int j = i;
				for(j = i; j < data.size(); j++) {
					if(data.get(j).endsWith("))")) {
						break;
					}
				}
				i=j;
				continue;
			}
			if(data.get(i).endsWith("int)")) {//跳过定义的式子到分号
				int j = i;
				for(j = i; j < data.size(); j++) {
					if(data.get(j).endsWith(";)")) {
						break;
					}
				}
				i = j;
				continue;
			}
			if(data.get(i).endsWith("})")||data.get(i).endsWith("{)")){
					continue;//是括号就跳过去
			}
			if(data.get(i).endsWith("else)")) {
				continue;
			}
			//剩下的就是表达式
			temp.add(data.get(i));
		}
		parseLex(temp);
	}
	
	//对表达式进行语法分析->找出表达式
	public  void parseLex(ArrayList<String> express) {
		StringBuffer strbuff = new StringBuffer();
		StringBuffer input = new StringBuffer();
		for(int i = 0; i < express.size(); i++) {
			if(express.get(i).endsWith(";)")) {
			    parseData.add(new String(input));
			    expressData.add(new String(input));
				parseString(strbuff);
				strbuff.delete(0, strbuff.length());
				input.delete(0, input.length());
				continue;
			}
			if(express.get(i).startsWith("(o")) {
				strbuff.append(express.get(i).substring(10,express.get(i).length()-1));
				input.append(express.get(i).substring(10,express.get(i).length()-1));
			}else if(express.get(i).startsWith("(iden")){
				strbuff.append("i");
				input.append(express.get(i).substring(12,express.get(i).length()-1));
			}else if(express.get(i).startsWith("(int")) {
				strbuff.append("i");
				input.append(express.get(i).substring(9,express.get(i).length()-1));
			}else if(express.get(i).startsWith("(b")) {
				strbuff.append(express.get(i).substring(10,express.get(i).length()-1));
				input.append(express.get(i).substring(10,express.get(i).length()-1));
			}
		}
	}
	
	//分析表达式
	public void parseString(StringBuffer strbuff) {
		strbuff.append("#");
		current=0;
		num=0;
		E(strbuff);
		if(strbuff.charAt(current)=='#') {
			parseData.add("accept");
		}else {
			parseData.add("error");
			errorDate.add("表示式不正确");
		}
	}
	
	public void E(StringBuffer strbuff) {
		if(strbuff.charAt(current)=='i'&&strbuff.charAt(current+1)=='=') {
			parseData.add(new String((num++)+"E->i=E"));
			current++;
			current++;
			E(strbuff);
		}else {
			parseData.add(new String((num++)+"E->TE'"));
			T(strbuff);
			EE(strbuff);
		}
	}
	public void EE(StringBuffer strbuff) {
		if(strbuff.charAt(current)=='+') {
			parseData.add(new String((num++)+"E'->+TE'"));
			current++;
			T(strbuff);
			EE(strbuff);
		}else {
			parseData.add(new String((num++)+"E'->&"));
		}
	}
	public void T(StringBuffer strbuff) {
		parseData.add(new String((num++)+"T->FT'"));
		F(strbuff);
		TT(strbuff);
	}
	public void TT(StringBuffer strbuff) {
		if(strbuff.charAt(current)=='*') {
			parseData.add(new String((num++)+"T'->*FT'"));
			current++;
			F(strbuff);
			TT(strbuff);
		}else {
			parseData.add(new String((num++)+"T'->&"));
		}
	}
	public void F(StringBuffer  strbuff) {
		if(strbuff.charAt(current)=='i') {
			current++;
			parseData.add(new String((num++)+"F->i"));
		}else if(strbuff.charAt(current)=='(') {
				current++;
				parseData.add(new String((num++)+"F->(E)"));
				E(strbuff);
				if(strbuff.charAt(current)==')') {
					current++;
				}else {
					parseData.add("error");
					errorDate.add("括号匹配错误");
				}
		}else {
			parseData.add("error");
			errorDate.add("表达式错误");
		}
	}
}
