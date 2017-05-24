package auto.deploy.service.aut;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import auto.deploy.dao.entity.aut.AutUser;
import auto.deploy.object.PageBean;

/**
 * 
 * @描述：用户表(服务类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-21
 */
public interface AutUserService extends IService<AutUser> {

	public Page<AutUser> list(PageBean pageBean) throws Exception;
}
