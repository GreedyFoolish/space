package demo.dao.service;

import java.util.List;

import org.springframework.stereotype.Service;

import demo.bean.MsgException;
import demo.dao.model.AlmnRole;

@Service
public interface AlmnRoleService {

	List<AlmnRole> getAllRole() throws MsgException;

	void updateRoleInfo(Integer id, String roleName);
	
}
