package auto.deploy.service.dev.impl;

import auto.deploy.dao.entity.dev.DevProject;
import auto.deploy.dao.mapper.dev.DevProjectMapper;
import auto.deploy.service.dev.DevProjectService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.StringUtils;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.plugins.Page;
import auto.deploy.dao.config.Where;
import auto.deploy.object.PageBean;

/**
 * 
 * @描述：项目表(服务实现类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-06-17
 */
@Service
public class DevProjectServiceImpl extends ServiceImpl<DevProjectMapper, DevProject> implements DevProjectService {

	@Override
	public Page<DevProject> list(PageBean pageBean, DevProject obj) throws Exception {
		Where<DevProject> where = new Where<DevProject>();
		if (StringUtils.isNotEmpty(obj.getProjectName())) {
			where.like("project_name", obj.getProjectName());
			where.or("project_desc LIKE {0}", "%" + obj.getProjectName() + "%");
		}
		where.orderBy("create_time", false);
		Page<DevProject> page = selectPage(new Page<DevProject>(pageBean.getPageNum(), pageBean.getPageSize()), where);
		return page;
	}	
}
