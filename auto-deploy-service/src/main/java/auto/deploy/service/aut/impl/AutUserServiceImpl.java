package auto.deploy.service.aut.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.StringUtils;

import auto.deploy.dao.config.Where;
import auto.deploy.dao.entity.aut.AutUser;
import auto.deploy.dao.mapper.aut.AutUserMapper;
import auto.deploy.object.PageBean;
import auto.deploy.service.aut.AutUserService;

/**
 * 
 * @描述：用户表(服务实现类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-21
 */
@Service
public class AutUserServiceImpl extends ServiceImpl<AutUserMapper, AutUser>implements AutUserService {

	@Override
	public Page<AutUser> list(PageBean pageBean, AutUser obj) throws Exception {
		Where<AutUser> where = new Where<AutUser>();
		if (StringUtils.isNotEmpty(obj.getUserName())) {
			where.like("user_name", obj.getUserName());
			where.or("nick_name LIKE {0}", "%" + obj.getUserName() + "%");
			where.or("user_email LIKE {0}", "%" + obj.getUserName() + "%");
		}
		where.orderBy("create_time", false);
		Page<AutUser> page = selectPage(new Page<AutUser>(pageBean.getPageNum(), pageBean.getPageSize()), where);
		return page;
	}
}
