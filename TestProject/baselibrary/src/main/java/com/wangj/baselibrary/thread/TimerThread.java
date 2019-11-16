package com.wangj.baselibrary.thread;

import android.os.Handler;
import android.os.Message;

/**
 * 倒计时线程
 * 
 * @author WangJ
 * @perfect 2016.05.16
 */
public class TimerThread extends Thread {

	private int seconds;
	private Handler mHandler;
	
	private String contralStr = "";
	private boolean pauseState;

	/**
	 * 倒计时后台线程
	 * @param mHandler 页面处理handler(msg.what=101-倒计时减一秒，arg1-剩余秒数；102-倒计时暂停；1001-倒计时结束)
	 * @param seconds 总秒数，单位为秒
	 */
	public TimerThread(Handler mHandler, int seconds) {
		this.seconds = seconds;
		this.mHandler = mHandler;
	}

	@Override
	public void run() {
		super.run();
		Message message;
		try {
			for (int i = seconds; i >= 0; i--) {
				synchronized (contralStr) {
					if(pauseState){
						i = i + 1;
						contralStr.wait();
					} else {
						message = new Message();
						message.what = 101;
						message.arg1 = i;
						mHandler.sendMessage(message); // 倒计时减一秒
						Thread.sleep(995);
					}
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		mHandler.sendEmptyMessage(1001); // 倒计时完毕
	}

	/**
	 * 倒计时线程暂停
	 */
	public void pause(){
		if(!pauseState){
			mHandler.sendEmptyMessage(102);
			pauseState = true;
		}
	}
	
	/**
	 * 倒计时线程继续
	 */
	public void continueGo(){
		if(pauseState){
			synchronized (contralStr) {
				pauseState = false;
				contralStr.notifyAll();
			}
		}
	}
	
	/**
	 * 检查当前线程暂停状态
	 * @return
	 */
	public boolean isPauesed(){
		return pauseState;
	}
	
}
