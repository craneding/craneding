/**
 * 
 */
package com.craneding.javatail;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author crane.ding
 *
 */
public class JavaTail {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String osName = System.getProperty("os.name");
		String cmd = "";
		
		String logFile = "javatail.txt";
		String outFile = "error.txt";
		
		Process exec = null;
		
		try {
			if (osName.matches("^(?i)Windows.*$")) {// Window ϵͳ
				// ����֧��Linux�����PATH
				// ��ʽ set PATH=.;/%PATH%
				cmd = "  cmd /c set PATH=" + new File(".").getAbsolutePath() + ";/%PATH%";
				Runtime.getRuntime().exec(cmd);
				// ���˴������־������ļ�
				cmd = "  cmd /c cat " + logFile + " | grep 'ERROR' > " + outFile;
				Runtime.getRuntime().exec(cmd);
				// ��ӡ�����ļ�
				cmd = "  cmd /c more " + outFile;
				exec = Runtime.getRuntime().exec(cmd);
			} else {// Linux ϵͳ(�ݲ���������ϵͳ)
				// ���˴������־������ļ�
				cmd = "  cat " + logFile + " | grep 'ERROR' > " + outFile;
				Runtime.getRuntime().exec(cmd);
				// ��ӡ�����ļ�
				cmd = "  more " + outFile;
				exec = Runtime.getRuntime().exec(cmd);
			}
			
			if(exec != null){
				BufferedReader reader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
				
				String line = null;
				while( (line = reader.readLine())!=null ){
					System.out.println(line);
				}
				
				reader.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

}
