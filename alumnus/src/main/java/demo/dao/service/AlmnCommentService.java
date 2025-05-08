package demo.dao.service;

import java.util.List;

import org.springframework.stereotype.Service;

import demo.bean.MsgException;
import demo.dao.model.AlmnComment;

@Service
public interface AlmnCommentService {
	
	List<AlmnComment> selectCommentByDailyId(String dailyId) throws MsgException ;
	
	void insert(AlmnComment commt) throws MsgException ;
	
	
}
