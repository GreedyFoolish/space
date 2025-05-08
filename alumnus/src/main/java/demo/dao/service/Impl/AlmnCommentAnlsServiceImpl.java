package demo.dao.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.bean.MsgException;
import demo.dao.mapper.AlmnCommentAnlsMapper;
import demo.dao.mapper.AlmnCommentMapper;
import demo.dao.model.AlmnComment;
import demo.dao.model.AlmnCommentAnls;
import demo.dao.model.AlmnCommentAnlsExample;
import demo.dao.model.AlmnCommentExample;
import demo.dao.service.AlmnCommentAnlsService;
import demo.dao.service.AlmnCommentService;

@Service
public class AlmnCommentAnlsServiceImpl implements AlmnCommentAnlsService {
	@Autowired
	private AlmnCommentAnlsMapper commentAnlsMapper;

	@Override
	public List<AlmnCommentAnls> getCommentAnls() throws MsgException {
		AlmnCommentAnlsExample example = new AlmnCommentAnlsExample();
		return commentAnlsMapper.selectByExample(example);
	}

	@Override
	public void insert(String anls, Integer flag) throws MsgException {
		AlmnCommentAnls uanls = new AlmnCommentAnls();
		if( flag == 1) {
			uanls.setPraiseSet(anls);
		} else {
			uanls.setNegativeSet(anls);
		}
		commentAnlsMapper.insertSelective(uanls);
	}

	@Override
	public int delCommentAnls(Integer flag) throws MsgException {
		AlmnCommentAnlsExample example = new AlmnCommentAnlsExample();
		AlmnCommentAnlsExample.Criteria criteria = example.createCriteria();
		if(flag == 1) {
			criteria.andPraiseSetIsNotNull();
		} else {
			criteria.andNegativeSetIsNotNull();
		}
		return commentAnlsMapper.deleteByExample(example);
	}
	

}
