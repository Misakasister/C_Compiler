package gui;

import java.io.*;

public class FileOperator {
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
}
