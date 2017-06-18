package auto.deploy.service.dev.impl;

import auto.deploy.dao.entity.dev.DevProjectGroup;
import auto.deploy.dao.mapper.dev.DevProjectGroupMapper;
import auto.deploy.service.dev.DevProjectGroupService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.StringUtils;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.plugins.Page;
import auto.deploy.dao.config.Where;
import auto.deploy.object.PageBean;

/**
 * 
 * @描述：项目分组表(服务实现类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-06-17
 */
@Service
public class DevProjectGroupServiceImpl extends ServiceImpl<DevProjectGroupMapper, DevProjectGroup> implements DevProjectGroupService {

	@Override
	public Page<DevProjectGroup> list(PageBean pageBean, DevProjectGroup obj) throws Exception {
		Where<DevProjectGroup> where = new Where<DevProjectGroup>();
		if (StringUtils.isNotEmpty(obj.getGroupName())) {
			where.like("group_name", obj.getGroupName());
			where.or("group_desc LIKE {0}", "%" + obj.getGroupName() + "%");
		}
		where.orderBy("create_time", false);
		Page<DevProjectGroup> page = selectPage(new Page<DevProjectGroup>(pageBean.getPageNum(), pageBean.getPageSize()), where);
		return page;
	}	
}
