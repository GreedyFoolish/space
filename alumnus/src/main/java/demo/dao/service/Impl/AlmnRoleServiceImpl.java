package demo.dao.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.bean.MsgException;
import demo.dao.mapper.AlmnRoleMapper;
import demo.dao.model.AlmnRole;
import demo.dao.model.AlmnRoleExample;
import demo.dao.service.AlmnRoleService;

@Service
public class AlmnRoleServiceImpl implements AlmnRoleService {
	@Autowired
	private AlmnRoleMapper roleMapper;

	@Override
	public List<AlmnRole> getAllRole() throws MsgException {
		AlmnRoleExample example = new AlmnRoleExample();
		AlmnRoleExample.Criteria criteria = example.createCriteria();
		return roleMapper.selectByExample(example);
	}

	@Override
	public void updateRoleInfo(Integer id, String roleName) {
		AlmnRole role = new AlmnRole();
		role.setId(id);
		role.setRoleName(roleName);
		roleMapper.updateRoleInfo(role);
	}



}
