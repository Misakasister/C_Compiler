package gui;

import java.io.*;
import java.util.ArrayList;



public class FileOperator {
	private ArrayList<String> data;

	public String leadingFile(String filePath) {
		File file = new File(filePath);
		StringBuffer strbuff = new StringBuffer();
		try {
			FileInputStream fis = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			int n;
			while((n=fis.read(buffer,0,buffer.length))!=-1) {
				strbuff.append(new String(buffer));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new String(strbuff);
	}
	
	//保存文件
	public void save(String filePath,Text temp) {
		File f = new File(filePath);
		if(!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			FileOutputStream fos = new FileOutputStream(f);
			fos.write(temp.getText().getBytes());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	//判断文件是否经过了更改
	public boolean isEqual(String path,Text temp) {
		File f  = new File(path);
		if(!f.exists()) {
			return false;
		}
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] buff = new byte[1024];
		int n;
		StringBuffer strbuff = new StringBuffer();
		try {
			while((n = fis.read(buff,0,buff.length))!=-1) {
				strbuff.append(new String(buff));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(new String(strbuff).equals(temp.getText())) {
			return true;
		}
		return false;
	}
	
	//将词法lex分析结果写入文件
	public void writeLexFile(ArrayList<String> lexOut) {
		File f = new File("src/lex.data");
		if(!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataOutputStream dos = new DataOutputStream(fos);
		for(int i = 0; i < lexOut.size();i++) {
			try {
				dos.writeUTF(lexOut.get(i));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			dos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//读出语法分析lex的结果
	public ArrayList<String> readLex(){
		File f = new File("src/lex.data");
		ArrayList<String> data = new ArrayList<String>();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataInputStream dis = new DataInputStream(fis);
		try {
			while(dis.available()!=0) {
				data.add(dis.readUTF());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	//展示语法结果
	public String showLex(){
		File f = new File("src/lex.data");
		StringBuffer buffer = new StringBuffer();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataInputStream dis = new DataInputStream(fis);
		try {
			while(dis.available()!=0) {
				buffer.append(dis.readUTF()+"\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new String(buffer);
	}
	//将语法分析结果按行写入parse.data文件
	public void writeParse(ArrayList<String> parseData) {
		File f = new File("src/parse.data");
		if(!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		FileOutputStream fos=null;
		try {
			fos = new FileOutputStream(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataOutputStream dos = new DataOutputStream(fos);
		for(int i = 0; i < parseData.size(); i++) {
			try {
				dos.writeUTF(parseData.get(i));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//展示语法分析
	public String showParse() {
		File f = new File("src/parse.data");
		FileInputStream fis=null;
		try {
			fis = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataInputStream dis = new DataInputStream(fis);
		StringBuffer buffer = new StringBuffer();
		try {
			while(dis.available()!=0) {
				buffer.append(dis.readUTF()+"\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new String(buffer);
	}
	
	//将程序中的表达式按行写入express.data文件
		public void writeExpress(ArrayList<String> parseData) {
			File f = new File("src/express.data");
			if(!f.exists()) {
				try {
					f.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			FileOutputStream fos=null;
			try {
				fos = new FileOutputStream(f);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DataOutputStream dos = new DataOutputStream(fos);
			for(int i = 0; i < parseData.size(); i++) {
				try {
					dos.writeUTF(parseData.get(i));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		//读出表达式
		public ArrayList<String> readExpress(){
			File f = new File("src/express.data");
			ArrayList<String> data = new ArrayList<String>();
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(f);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DataInputStream dis = new DataInputStream(fis);
			try {
				while(dis.available()!=0) {
					data.add(dis.readUTF());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return data;
		}
}
