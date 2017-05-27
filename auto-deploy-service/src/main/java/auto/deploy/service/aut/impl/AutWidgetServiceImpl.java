package auto.deploy.service.aut.impl;

import auto.deploy.dao.entity.aut.AutWidget;
import auto.deploy.dao.mapper.aut.AutWidgetMapper;
import auto.deploy.service.aut.AutWidgetService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.plugins.Page;
import auto.deploy.dao.config.Where;
import auto.deploy.object.PageBean;

/**
 * 
 * @描述：控件表(服务实现类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-27
 */
@Service
public class AutWidgetServiceImpl extends ServiceImpl<AutWidgetMapper, AutWidget> implements AutWidgetService {

	@Override
	public Page<AutWidget> list(PageBean pageBean, AutWidget obj) throws Exception {
		Where<AutWidget> where = new Where<AutWidget>();
		where.orderBy("create_time", false);
		Page<AutWidget> page = selectPage(new Page<AutWidget>(pageBean.getPageNum(), pageBean.getPageSize()), where);
		return page;
	}	
}
