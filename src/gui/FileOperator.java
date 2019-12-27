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
	
	//�����ļ�
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
	
	//�ж��ļ��Ƿ񾭹��˸���
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
	
	//���ʷ�lex�������д���ļ�
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
	
	//�����﷨����lex�Ľ��
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
	//չʾ�﷨���
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
	//���﷨�����������д��parse.data�ļ�
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
	
	//չʾ�﷨����
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
	
	//�������еı��ʽ����д��express.data�ļ�
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
		
		//�������ʽ
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
		
		//����dag�Ż����
		public void writeDag(ArrayList<String> parseData) {
			File f = new File("src/dag.data");
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
		//չʾdag�Ż��������
		public String showDag() {
			File f = new File("src/dag.data");
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
		
		//д����
		public void writeError(ArrayList<String> parseData) {
			File f = new File("src/error.data");
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
		
		public String showError() {
			File f = new File("src/error.data");
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
		
		//д�м���ʽ
		public void writeMid(ArrayList<String> parseData) {
			File f = new File("src/mid.data");
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
		
		public String showMid() {
			File f = new File("src/mid.data");
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
}
