package auto.deploy.service.authority;

import com.baomidou.mybatisplus.service.IService;

import auto.deploy.dao.entity.User;

public interface UserService extends IService<User> {

	public boolean myInsert(User user);

}
