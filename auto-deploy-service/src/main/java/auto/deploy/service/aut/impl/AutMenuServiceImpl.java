package auto.deploy.service.aut.impl;

import auto.deploy.dao.entity.aut.AutMenu;
import auto.deploy.dao.mapper.aut.AutMenuMapper;
import auto.deploy.service.aut.AutMenuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.plugins.Page;
import auto.deploy.dao.config.Where;
import auto.deploy.object.PageBean;

/**
 * 
 * @描述：菜单表(服务实现类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-27
 */
@Service
public class AutMenuServiceImpl extends ServiceImpl<AutMenuMapper, AutMenu> implements AutMenuService {

	@Override
	public Page<AutMenu> list(PageBean pageBean, AutMenu obj) throws Exception {
		Where<AutMenu> where = new Where<AutMenu>();
		where.orderBy("create_time", false);
		Page<AutMenu> page = selectPage(new Page<AutMenu>(pageBean.getPageNum(), pageBean.getPageSize()), where);
		return page;
	}	
}
