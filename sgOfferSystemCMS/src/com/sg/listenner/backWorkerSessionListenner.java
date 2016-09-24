/**
 * @package com.sg.listenner
 * @description TODO
 * @author lizhe
 * @modify 2016年9月22日 下午10:09:18 
 */
package com.sg.listenner;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.annotation.WebListener;

import com.sg.model.Worker;

/**
 *@（#) backWorkerSessionListenner
 *@description TODO
 *@author lizhe
 *@version
 *@modify 2016年9月22日 
 */
@WebListener
public class backWorkerSessionListenner implements HttpSessionListener{

	/* 
	 * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
	 */
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getSession().getCreationTime());
		System.out.println("lizhe");
	}

	/* 
	 * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		HttpSession session = arg0.getSession();
		Worker worker = (Worker) session.getAttribute("worker");
		System.out.println(worker.getName());
	}

}
