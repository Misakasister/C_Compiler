package compiler;

import java.io.DataInputStream;
import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.*;

import gui.*;

//�м���Ԫʽ�����ɳ���
public class SimpleCode {
	private ArrayList<String> result;
	private ArrayList<String> express;//���ʽ
	private ArrayList<String> ni;//��׺���ʽ
	public SimpleCode() {
		result = new ArrayList<String>();
		express = new ArrayList<String>();
		ni = new ArrayList<String>();
		FileOperator fo = new FileOperator();
		express = fo.readExpress();
		Parse(express);
		fo.writeMid(result);
	}
	
	//����Ϊ��׺���ʽ
	public void Parse(ArrayList<String> express) {
		StringBuilder temp = new StringBuilder();
		Stack<String> var = new Stack<String>();//����ջ
		Stack<String> op = new Stack<String>();//�����ջ
		for(int i = 0; i < express.size(); i++) {
			String str = express.get(i);
			String[] data = str.split("");
			for(int j = 2; j < data.length; j++) {
			if(!(data[j].equals("+")||data[j].equals("*")||data[j].equals("="))) {
				temp.append(data[j]);
			}else {
				if(!op.empty()) {
					if(data[j].equals("=")) {
						op.push(data[j]);
					}else if(data[j].equals("*")) {//���ȼ���ֱ����ջ
						if(op.peek().equals("+")) {
							op.push(data[j]);
						}else {
							while(!op.empty()&&op.peek().equals("*")) {
								String s = op.peek();
								temp.append(s);
								op.pop();
							}
							op.push(data[j]);
						}
					}else {//+�����ȶ����
						while(!op.empty()&&op.peek().equals("*")) {
							String s = op.peek();
							temp.append(s);
							op.pop();
						}
						op.push(data[j]);
					}
				}else {
					op.push(data[j]);
				}
			}
		}
			while(!op.empty()) {
				String s = op.peek();
				temp.append(s);
				op.pop();
			}
			temp.insert(0, data[1]);
			temp.insert(0, data[0]);
			ni.add(temp.toString());
			temp.delete(0,temp.length());
		}
		midCode(ni);
	}
	
	//��׺���ʽװ���м����
	public void midCode(ArrayList<String> ni) {
		int num = 0;
		int n = 0;
		Stack<String> var = new Stack<String>();//����ջ
		StringBuffer temp = new StringBuffer();
		for(int i = 0; i < ni.size(); i++) {
			String str = ni.get(i);
			String[] data = str.split("");
			for(int j = 2; j <str.length(); j++ ) {
				if(data[j].equals("+")||data[j].equals("*")) {//���������ʼ��ջ
					if(data[j].equals("+")) {
						String x = var.peek();
						var.pop();
						String y = var.peek();
						var.pop();
						String t = "t"+n;
						n++;
//						System.out.println((num++)+"(+,"+x+","+y+","+t+")");
//						temp.append(new String((num++)+"(+,"+x+","+y+","+t+")"));
						result.add(new String((num++)+"(+,"+x+","+y+","+t+")"));
						var.push(t);
					}else if(data[j].equals("*")) {
						String x = var.peek();
						var.pop();
						String y = var.peek();
						var.pop();
						String t = "t"+n;
						n++;
//						System.out.println((num++)+"(*,"+x+","+y+","+t+")");
//						temp.append(new String((num++)+"(*,"+x+","+y+","+t+")"));
						result.add(new String((num++)+"(*,"+x+","+y+","+t+")"));
						var.push(t);
					}
				}else {
					var.push(data[j]);
				}
			}
			String x = data[0];//�Ⱥ���ߵı���
			String t = "t"+(n-1);
//			System.out.println();
//			temp.append(new String((num++)+"(=,"+" "+","+x+","+t+")"));
			result.add(new String((num++)+"(=,"+" "+","+x+","+t+")"));
			temp.delete(0, temp.length());
		}
	}
}
