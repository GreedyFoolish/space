package demo.dao.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.bean.MsgException;
import demo.dao.mapper.AlmnCommentMapper;
import demo.dao.model.AlmnComment;
import demo.dao.model.AlmnCommentExample;
import demo.dao.service.AlmnCommentService;

@Service
public class AlmnCommentServiceImpl implements AlmnCommentService {
	@Autowired
	private AlmnCommentMapper commentMapper;

	@Override
	public List<AlmnComment> selectCommentByDailyId(String dailyId) throws MsgException {
		AlmnCommentExample example = new AlmnCommentExample();
		AlmnCommentExample.Criteria criteria = example.createCriteria();
		criteria.andDailysIdEqualTo(dailyId);
		List<AlmnComment> comments = commentMapper.selectByExample(example);
		return comments;
	}
	@Override
	public void insert(AlmnComment commt) throws MsgException {
		commentMapper.insertSelective(commt);
	}

}
