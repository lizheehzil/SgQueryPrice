/**
 * @package com.sg.service
 * @description TODO
 * @author lizhe
 * @modify 2016年9月23日 上午11:19:30 
 */
package com.sg.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sg.model.LoginLog;
import com.sg.model.LoginLogExample;

/**
 *@（#) LoginLogService
 *@description TODO
 *@author lizhe
 *@version
 *@modify 2016年9月23日 
 */
public interface LoginLogService {
	int countByExample(LoginLogExample example);

    int deleteByExample(LoginLogExample example);

    int deleteByPrimaryKey(String id);

    int insert(LoginLog record);

    int insertSelective(LoginLog record);

    List<LoginLog> selectByExample(LoginLogExample example);

    LoginLog selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") LoginLog record, @Param("example") LoginLogExample example);

    int updateByExample(@Param("record") LoginLog record, @Param("example") LoginLogExample example);

    int updateByPrimaryKeySelective(LoginLog record);

    int updateByPrimaryKey(LoginLog record);
}
