/**
 * @package com.sg.test
 * @description TODO
 * @author lizhe
 * @modify 2016年9月23日 上午11:26:39 
 */
package com.sg.test;

import java.util.Date;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sg.model.LoginLog;
import com.sg.service.LoginLogService;

/**
 * @（#) Test
 * @description TODO
 * @author lizhe
 * @version
 * @modify 2016年9月23日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
public class Test {
	@Autowired
	private LoginLogService loginLogService;
	@org.junit.Test
	public void testMybatis() {
		LoginLog log = new LoginLog();
		log.setId("123");
		log.setLogintime(new Date());
		log.setWorkerid(1);
		log.setPostionid(2);
		
		loginLogService.insert(log);
	}
}
