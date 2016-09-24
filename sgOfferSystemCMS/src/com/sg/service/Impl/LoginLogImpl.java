/**
 * @package com.sg.service.Impl
 * @description TODO
 * @author lizhe
 * @modify 2016年9月23日 上午11:20:54 
 */
package com.sg.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sg.model.LoginLog;
import com.sg.model.LoginLogExample;
import com.sg.mybatisdao.LoginLogMapper;
import com.sg.service.LoginLogService;

/**
 *@（#) LoginLogImpl
 *@description TODO
 *@author lizhe
 *@version
 *@modify 2016年9月23日 
 */
@Service
@Transactional
public class LoginLogImpl implements LoginLogService {

	@Autowired
	private LoginLogMapper loginLogMapper;
	/* 
	 * @see com.sg.service.LoginLogService#countByExample(com.sg.model.LoginLogExample)
	 */
	@Override
	public int countByExample(LoginLogExample example) {
		// TODO Auto-generated method stub
		return loginLogMapper.countByExample(example);
	}

	/* 
	 * @see com.sg.service.LoginLogService#deleteByExample(com.sg.model.LoginLogExample)
	 */
	@Override
	public int deleteByExample(LoginLogExample example) {
		// TODO Auto-generated method stub
		return loginLogMapper.deleteByExample(example);
	}

	/* 
	 * @see com.sg.service.LoginLogService#deleteByPrimaryKey(java.lang.String)
	 */
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return loginLogMapper.deleteByPrimaryKey(id);
	}

	/* 
	 * @see com.sg.service.LoginLogService#insert(com.sg.model.LoginLog)
	 */
	@Override
	public int insert(LoginLog record) {
		// TODO Auto-generated method stub
		return loginLogMapper.insert(record);
	}

	/* 
	 * @see com.sg.service.LoginLogService#insertSelective(com.sg.model.LoginLog)
	 */
	@Override
	public int insertSelective(LoginLog record) {
		// TODO Auto-generated method stub
		return loginLogMapper.insertSelective(record);
	}

	/* 
	 * @see com.sg.service.LoginLogService#selectByExample(com.sg.model.LoginLogExample)
	 */
	@Override
	public List<LoginLog> selectByExample(LoginLogExample example) {
		// TODO Auto-generated method stub
		return loginLogMapper.selectByExample(example);
	}

	/* 
	 * @see com.sg.service.LoginLogService#selectByPrimaryKey(java.lang.String)
	 */
	@Override
	public LoginLog selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return loginLogMapper.selectByPrimaryKey(id);
	}

	/* 
	 * @see com.sg.service.LoginLogService#updateByExampleSelective(com.sg.model.LoginLog, com.sg.model.LoginLogExample)
	 */
	@Override
	public int updateByExampleSelective(LoginLog record, LoginLogExample example) {
		// TODO Auto-generated method stub
		return loginLogMapper.updateByExampleSelective(record, example);
	}

	/* 
	 * @see com.sg.service.LoginLogService#updateByExample(com.sg.model.LoginLog, com.sg.model.LoginLogExample)
	 */
	@Override
	public int updateByExample(LoginLog record, LoginLogExample example) {
		// TODO Auto-generated method stub
		return loginLogMapper.updateByExample(record, example);
	}

	/* 
	 * @see com.sg.service.LoginLogService#updateByPrimaryKeySelective(com.sg.model.LoginLog)
	 */
	@Override
	public int updateByPrimaryKeySelective(LoginLog record) {
		// TODO Auto-generated method stub
		return loginLogMapper.updateByPrimaryKeySelective(record);
	}

	/* 
	 * @see com.sg.service.LoginLogService#updateByPrimaryKey(com.sg.model.LoginLog)
	 */
	@Override
	public int updateByPrimaryKey(LoginLog record) {
		// TODO Auto-generated method stub
		return loginLogMapper.updateByPrimaryKey(record);
	}

}
