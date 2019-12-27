package compiler;

import java.io.DataInputStream;
import java.util.ArrayList;



import gui.FileOperator;

//DAG优化
public class DAG {
	private FileOperator fo =null;
	private ArrayList<String> data;//需要分析的表达式
	private String[] saveVar;//需要保存的变量
	private char[][] result;//结果
	private  node[] nodeData;
	private ArrayList<String> finalOutcome;//保存到文件的数据
	private int cnt;
	private boolean[] flag = new boolean[105];
	public DAG(String temp) {
		finalOutcome = new ArrayList<String>();
		fo = new FileOperator();
		data=fo.readExpress();
		result = new char[105][20];
		saveVar =temp.split(";");
		nodeData = new node[105];
		for(int i = 0; i < nodeData.length; i++) {
			nodeData[i]= new node();
		}
		cnt= 0;
		if(saveVar.length!=0) {
			dagMain();
		}
	}
	class node{
		public int l;
		public int r;
		public char id;
		public ArrayList<Character> var;
		public node() {
			l=-1;
			r=-1;
			var = new ArrayList<Character>();
		}
	}
	public void dagMain() {
		for(int i = 0; i < data.size(); i++) {
			int l = add_node(data.get(i).charAt(2));
			int r = add_node(data.get(i).charAt(4));
			add_op(data.get(i).charAt(0),data.get(i).charAt(3),l,r);
		}
		for(int i = 0; i <cnt; i++) {
			if(nodeData[i].l !=-1) {
				result[i][0]=nodeData[i].var.get(0);
				result[i][1]='=';
				node ll = nodeData[nodeData[i].l];
				node rr = nodeData[nodeData[i].r];
				result[i][2]=ll.var.size()>0?ll.var.get(0):ll.id;
				result[i][3]=nodeData[i].id;
				result[i][4]=rr.var.size()>0?rr.var.get(0):rr.id;
				result[i][5]='\0';
			}
		}
		for(int i = 0; i <saveVar.length; i++) {
			for(int j = cnt -1; j>=0; j--) {
				if(String.valueOf(result[j][0]).equals(saveVar[i])) {
					dfs(j);
					break;
				}
			}
		}
		for(int i = 0; i < cnt; i++) {
			if(flag[i]) {
				finalOutcome.add(String.valueOf(result[i]));
			}
		}
		fo.writeDag(finalOutcome);
	}
	
	public boolean find_node(int i,char x) {
		for(int j = 0; j < nodeData[i].var.size();j++) {
			if(nodeData[i].var.get(j)==x) {
				return true;
			}
		}
		return false;
	}
	public int add_node(char x) {
		for(int i = cnt - 1; i>=0;i--) {
			if(nodeData[i].id==x||find_node(i,x)) {
				return i;
			}
		}
		nodeData[cnt].id=x;
		return cnt++;
	}
	public void add_op(char x, char op, int l, int r) {
		for(int i = cnt-1; i >=0; i--) {
			if(nodeData[i].l==l&&nodeData[i].r==r&&nodeData[i].id==op) {
				nodeData[i].var.add(x);
				return;
			}
		}
		nodeData[cnt].id=op;
		nodeData[cnt].var.add(x);
		nodeData[cnt].l=l;
		nodeData[cnt].r=r;
		cnt++;
	}
	
	public void dfs(int i) {
		if(nodeData[i].l!=-1) {
			flag[i]=true;
			dfs(nodeData[i].l);
			dfs(nodeData[i].r);
		}
	}
}
