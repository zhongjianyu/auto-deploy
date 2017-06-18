package auto.deploy.service.dev.impl;

import auto.deploy.dao.entity.dev.DevServer;
import auto.deploy.dao.mapper.dev.DevServerMapper;
import auto.deploy.service.dev.DevServerService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.StringUtils;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.plugins.Page;
import auto.deploy.dao.config.Where;
import auto.deploy.object.PageBean;

/**
 * 
 * @描述：服务器表(服务实现类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-06-17
 */
@Service
public class DevServerServiceImpl extends ServiceImpl<DevServerMapper, DevServer> implements DevServerService {

	@Override
	public Page<DevServer> list(PageBean pageBean, DevServer obj) throws Exception {
		Where<DevServer> where = new Where<DevServer>();
		if (StringUtils.isNotEmpty(obj.getUserName())) {
			where.like("server_ip", obj.getUserName());
			where.or("user_name LIKE {0}", "%" + obj.getUserName() + "%");
			where.or("server_desc LIKE {0}", "%" + obj.getUserName() + "%");
		}
		where.orderBy("create_time", false);
		Page<DevServer> page = selectPage(new Page<DevServer>(pageBean.getPageNum(), pageBean.getPageSize()), where);
		return page;
	}
}
