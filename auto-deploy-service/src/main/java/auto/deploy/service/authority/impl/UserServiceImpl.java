package auto.deploy.service.authority.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import auto.deploy.dao.entity.User;
import auto.deploy.dao.mapper.UserMapper;
import auto.deploy.service.authority.UserService;

/**
 * 
 * @描述：用户service接口实现类
 *
 * @作者：zhongjy
 *
 * @时间：2017年4月24日 下午12:30:50
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>implements UserService {

	@Override
	public boolean myInsert(User user) {
		return user.insert();
	}

}
