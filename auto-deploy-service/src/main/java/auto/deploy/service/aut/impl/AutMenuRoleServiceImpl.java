package auto.deploy.service.aut.impl;

import auto.deploy.dao.entity.aut.AutMenuRole;
import auto.deploy.dao.mapper.aut.AutMenuRoleMapper;
import auto.deploy.service.aut.AutMenuRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.plugins.Page;
import auto.deploy.dao.config.Where;
import auto.deploy.object.PageBean;

/**
 * 
 * @描述：菜单-角色表(服务实现类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-27
 */
@Service
public class AutMenuRoleServiceImpl extends ServiceImpl<AutMenuRoleMapper, AutMenuRole> implements AutMenuRoleService {

	@Override
	public Page<AutMenuRole> list(PageBean pageBean, AutMenuRole obj) throws Exception {
		Where<AutMenuRole> where = new Where<AutMenuRole>();
		where.orderBy("create_time", false);
		Page<AutMenuRole> page = selectPage(new Page<AutMenuRole>(pageBean.getPageNum(), pageBean.getPageSize()), where);
		return page;
	}	
}
