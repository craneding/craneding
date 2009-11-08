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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

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
	private static final String title = "免费工具";
	private static final String icon = "011906654.gif";
	private static boolean isSupported = false;
	
	private static final Logger LOGGER = Logger.getLogger(FreeMain.class.getName());
	
	static{
		try {
			FileHandler fileHandler = new FileHandler("FreeMain.log", 0, 1, true);
			fileHandler.setFormatter(new SimpleFormatter());
			fileHandler.setLevel(Level.INFO);
			LOGGER.addHandler(fileHandler);
		} catch (Throwable e) {
			LOGGER.severe("配置日志文件错误\n" + e);
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
			        javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			    }
			    catch (Exception ex)  {
			    	LOGGER.warning("加载皮肤错误.\n" + ex);
			    }
				
				try {
					showFreeFrame();
					
					LOGGER.info("系统启动成功.");
				} catch (Throwable e) {
					LOGGER.severe("系统启动错误.\n" + e);
					System.exit(-1);
				}
				
			}
		});
	}

	private static void showFreeFrame() {
		final FreeFrame freeFrame = new FreeFrame();
		final ImageIcon imageIcon = new ImageIcon(FreeFrame.class.getResource(icon));

		freeFrame.setTitle(title);
		freeFrame.setResizable(false);// 禁止使用最大化按钮
		freeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置关闭动作

		freeFrame.setSize(width, height);// 设置窗口大小
		freeFrame.setLocationRelativeTo(null);// 使窗口显示在屏幕中央
		freeFrame.setIconImage(imageIcon.getImage());

		freeFrame.addWindowStateListener(new WindowStateListener() {
			@Override
			public void windowStateChanged(WindowEvent e) {
				// 最小化时隐藏
				if (isSupported && e.getNewState() == JFrame.ICONIFIED)
					freeFrame.setVisible(false);
			}
		});
		
		freeFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				LOGGER.info("系统正常关闭.");
			}
		});

		freeFrame.setVisible(true);

		if (SystemTray.isSupported())
			try {
				// 显示系统托盘
				showTrayIcon(freeFrame, imageIcon);
				isSupported = true;
			} catch (Exception e) {
				isSupported = false;
				LOGGER.warning("显示系统托盘错误.\n" + e);
			}
	}

	private static void showTrayIcon(final FreeFrame freeFrame, final ImageIcon imageIcon) throws AWTException {
		final PopupMenu popup = new PopupMenu();
		MenuItem defaultItem = new MenuItem("打开");
		defaultItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				freeFrame.setState(JFrame.NORMAL);
				freeFrame.setVisible(true);
			}
		});
		MenuItem exitItem = new MenuItem("退出");
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LOGGER.info("系统正常关闭.");
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
