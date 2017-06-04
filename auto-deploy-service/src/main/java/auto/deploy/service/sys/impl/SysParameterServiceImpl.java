package auto.deploy.service.sys.impl;

import auto.deploy.dao.entity.sys.SysParameter;
import auto.deploy.dao.mapper.sys.SysParameterMapper;
import auto.deploy.service.sys.SysParameterService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.plugins.Page;
import auto.deploy.dao.config.Where;
import auto.deploy.object.PageBean;

/**
 * 
 * @描述：系统参数表(服务实现类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-06-04
 */
@Service
public class SysParameterServiceImpl extends ServiceImpl<SysParameterMapper, SysParameter> implements SysParameterService {

	@Override
	public Page<SysParameter> list(PageBean pageBean, SysParameter obj) throws Exception {
		Where<SysParameter> where = new Where<SysParameter>();
		where.orderBy("create_time", false);
		Page<SysParameter> page = selectPage(new Page<SysParameter>(pageBean.getPageNum(), pageBean.getPageSize()), where);
		return page;
	}	
}
