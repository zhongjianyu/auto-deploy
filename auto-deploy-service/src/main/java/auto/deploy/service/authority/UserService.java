package auto.deploy.service.authority;

import com.baomidou.mybatisplus.service.IService;

import auto.deploy.dao.entity.User;

/**
 * 
 * @描述：用户service接口类
 *
 * @作者：zhongjy
 *
 * @时间：2017年4月24日 下午12:30:24
 */
public interface UserService extends IService<User> {

	public boolean myInsert(User user);

}
