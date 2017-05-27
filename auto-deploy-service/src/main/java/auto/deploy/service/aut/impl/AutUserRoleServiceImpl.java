package auto.deploy.service.aut.impl;

import auto.deploy.dao.entity.aut.AutUserRole;
import auto.deploy.dao.mapper.aut.AutUserRoleMapper;
import auto.deploy.service.aut.AutUserRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.plugins.Page;
import auto.deploy.dao.config.Where;
import auto.deploy.object.PageBean;

/**
 * 
 * @描述：用户-角色关联表(服务实现类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-27
 */
@Service
public class AutUserRoleServiceImpl extends ServiceImpl<AutUserRoleMapper, AutUserRole> implements AutUserRoleService {

	@Override
	public Page<AutUserRole> list(PageBean pageBean, AutUserRole obj) throws Exception {
		Where<AutUserRole> where = new Where<AutUserRole>();
		where.orderBy("create_time", false);
		Page<AutUserRole> page = selectPage(new Page<AutUserRole>(pageBean.getPageNum(), pageBean.getPageSize()), where);
		return page;
	}	
}
