package auto.deploy.service.authority.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import auto.deploy.dao.entity.AutUser;
import auto.deploy.dao.mapper.AutUserMapper;
import auto.deploy.object.PageBean;
import auto.deploy.service.authority.AutUserService;

/**
 * 
 * @描述：用户表(服务实现类)
 * 
 * 				@作者：zhongjy
 * 
 * @时间: 2017-05-14
 */
@Service
public class AutUserServiceImpl extends ServiceImpl<AutUserMapper, AutUser>implements AutUserService {

	@Override
	public Page<AutUser> list(PageBean pageBean) throws Exception {
		Page<AutUser> page = selectPage(new Page<AutUser>(pageBean.getPageNum(),pageBean.getPageSize()));
		return page;
	}

}
