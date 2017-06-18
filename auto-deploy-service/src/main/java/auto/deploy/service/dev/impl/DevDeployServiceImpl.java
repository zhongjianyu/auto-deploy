package auto.deploy.service.dev.impl;

import auto.deploy.dao.entity.dev.DevDeploy;
import auto.deploy.dao.mapper.dev.DevDeployMapper;
import auto.deploy.service.dev.DevDeployService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.plugins.Page;
import auto.deploy.dao.config.Where;
import auto.deploy.object.PageBean;

/**
 * 
 * @描述：项目部署表(服务实现类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-06-17
 */
@Service
public class DevDeployServiceImpl extends ServiceImpl<DevDeployMapper, DevDeploy> implements DevDeployService {

	@Override
	public Page<DevDeploy> list(PageBean pageBean, DevDeploy obj) throws Exception {
		Where<DevDeploy> where = new Where<DevDeploy>();
		where.orderBy("create_time", false);
		Page<DevDeploy> page = selectPage(new Page<DevDeploy>(pageBean.getPageNum(), pageBean.getPageSize()), where);
		return page;
	}	
}
