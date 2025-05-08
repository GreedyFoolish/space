package demo.dao.service;

import java.util.List;

import org.springframework.stereotype.Service;

import demo.bean.MsgException;
import demo.dao.model.AlmnCoinLog;
import demo.dao.model.AlmnCommentAnls;

@Service
public interface AlmnCommentAnlsService {
	
	List<AlmnCommentAnls> getCommentAnls() throws MsgException ;
	/**
	 * 	flag=1代表设置好评关键字
	 * @param anls
	 * @param flag
	 * @throws MsgException
	 */
	void insert(String anls, Integer flag) throws MsgException ;
	
	int delCommentAnls(Integer flag) throws MsgException ;
	
}
