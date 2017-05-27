package auto.deploy.service.aut.impl;

import auto.deploy.dao.entity.aut.AutWidgetRole;
import auto.deploy.dao.mapper.aut.AutWidgetRoleMapper;
import auto.deploy.service.aut.AutWidgetRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.plugins.Page;
import auto.deploy.dao.config.Where;
import auto.deploy.object.PageBean;

/**
 * 
 * @描述：控件-角色表(服务实现类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-27
 */
@Service
public class AutWidgetRoleServiceImpl extends ServiceImpl<AutWidgetRoleMapper, AutWidgetRole> implements AutWidgetRoleService {

	@Override
	public Page<AutWidgetRole> list(PageBean pageBean, AutWidgetRole obj) throws Exception {
		Where<AutWidgetRole> where = new Where<AutWidgetRole>();
		where.orderBy("create_time", false);
		Page<AutWidgetRole> page = selectPage(new Page<AutWidgetRole>(pageBean.getPageNum(), pageBean.getPageSize()), where);
		return page;
	}	
}
