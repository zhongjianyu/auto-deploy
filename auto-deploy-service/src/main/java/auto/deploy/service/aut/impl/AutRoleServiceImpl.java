package auto.deploy.service.aut.impl;

import auto.deploy.dao.entity.aut.AutRole;
import auto.deploy.dao.mapper.aut.AutRoleMapper;
import auto.deploy.service.aut.AutRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.StringUtils;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.plugins.Page;
import auto.deploy.dao.config.Where;
import auto.deploy.object.PageBean;

/**
 * 
 * @描述：角色表(服务实现类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-27
 */
@Service
public class AutRoleServiceImpl extends ServiceImpl<AutRoleMapper, AutRole>implements AutRoleService {

	@Override
	public Page<AutRole> list(PageBean pageBean, AutRole obj) throws Exception {
		Where<AutRole> where = new Where<AutRole>();
		if (StringUtils.isNotEmpty(obj.getRoleName())) {
			where.like("role_name", obj.getRoleName());
			where.or("role_code LIKE {0}", "%" + obj.getRoleName() + "%");
		}
		where.orderBy("create_time", false);
		Page<AutRole> page = selectPage(new Page<AutRole>(pageBean.getPageNum(), pageBean.getPageSize()), where);
		return page;
	}
}
