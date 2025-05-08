package demo.dao.service;

import java.util.List;

import org.springframework.stereotype.Service;

import demo.bean.MsgException;
import demo.dao.model.AlmnInfoCheck;

@Service
public interface AlmnInfoCheckService {
	
	int countInsertUser(AlmnInfoCheck user) throws MsgException ;
	
	List<AlmnInfoCheck> getAllUserInfo() throws MsgException ;
	
}
