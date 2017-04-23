package auto.deploy.service.authority.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import auto.deploy.dao.entity.User;
import auto.deploy.dao.mapper.UserMapper;
import auto.deploy.service.authority.UserService;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>implements UserService {

	@Override
	public boolean myInsert(User user) {
		return user.insert();
	}

}
