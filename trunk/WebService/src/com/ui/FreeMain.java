/**
 * 
 */
package com.ui;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * @author crane.ding
 *
 */
public class FreeMain {
	
	private static final int width = 600;
	private static final int height = 340;
	private static final String title = "��ѹ���";
	private static final String icon = "011906654.gif";
	private static boolean isSupported = false;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
			        javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			    }
			    catch (Exception ex)  {
			    	ex.printStackTrace();
			    }
				
				showFreeFrame();
			}
		});
	}

	private static void showFreeFrame() {
		final FreeFrame freeFrame = new FreeFrame();
		final ImageIcon imageIcon = new ImageIcon(FreeFrame.class.getResource(icon));

		freeFrame.setTitle(title);
		freeFrame.setResizable(false);// ��ֹʹ����󻯰�ť
		freeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ���ùرն���

		freeFrame.setSize(width, height);// ���ô��ڴ�С
		freeFrame.setLocationRelativeTo(null);// ʹ������ʾ����Ļ����
		freeFrame.setIconImage(imageIcon.getImage());

		freeFrame.addWindowStateListener(new WindowStateListener() {
			@Override
			public void windowStateChanged(WindowEvent e) {
				// ��С��ʱ����
				if (isSupported && e.getNewState() == JFrame.ICONIFIED)
					freeFrame.setVisible(false);
			}
		});

		freeFrame.setVisible(true);

		if (SystemTray.isSupported())
			try {
				// ��ʾϵͳ����
				showTrayIcon(freeFrame, imageIcon);
				isSupported = true;
			} catch (Exception e) {
				isSupported = false;
				e.printStackTrace();
			}
	}

	private static void showTrayIcon(final FreeFrame freeFrame, final ImageIcon imageIcon) throws AWTException {
		final PopupMenu popup = new PopupMenu();
		MenuItem defaultItem = new MenuItem("��");
		defaultItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				freeFrame.setState(JFrame.NORMAL);
				freeFrame.setVisible(true);
			}
		});
		MenuItem exitItem = new MenuItem("�˳�");
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		popup.add(defaultItem);
		popup.add(exitItem);  
		
		final SystemTray systemTray = SystemTray.getSystemTray();
		final TrayIcon trayIcon = new TrayIcon(imageIcon.getImage(), title, popup);
		systemTray.add(trayIcon);
	}
	
}
