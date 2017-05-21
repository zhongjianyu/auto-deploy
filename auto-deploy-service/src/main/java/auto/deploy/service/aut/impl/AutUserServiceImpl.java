package auto.deploy.service.aut.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import auto.deploy.dao.config.Where;
import auto.deploy.dao.entity.aut.AutUser;
import auto.deploy.dao.mapper.aut.AutUserMapper;
import auto.deploy.object.PageBean;
import auto.deploy.service.aut.AutUserService;

/**
 * 
 * @描述：用户表(服务实现类)
 * 
 * 				@作者：zhongjy
 * 
 * @时间: 2017-05-21
 */
@Service
public class AutUserServiceImpl extends ServiceImpl<AutUserMapper, AutUser>implements AutUserService {

	@Override
	public Page<AutUser> list(PageBean pageBean) throws Exception {
		Where<AutUser> where = new Where<AutUser>();
		where.orderBy("create_time", false);
		where.eq("is_delete", 0);
		Page<AutUser> page = selectPage(new Page<AutUser>(pageBean.getPageNum(), pageBean.getPageSize()), where);
		return page;
	}
}