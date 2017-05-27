package auto.deploy.service.sys.impl;

import auto.deploy.dao.entity.sys.SysDataDict;
import auto.deploy.dao.mapper.sys.SysDataDictMapper;
import auto.deploy.service.sys.SysDataDictService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.plugins.Page;
import auto.deploy.dao.config.Where;
import auto.deploy.object.PageBean;

/**
 * 
 * @描述：数据字典表(服务实现类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-27
 */
@Service
public class SysDataDictServiceImpl extends ServiceImpl<SysDataDictMapper, SysDataDict>implements SysDataDictService {

	@Override
	public Page<SysDataDict> list(PageBean pageBean, SysDataDict obj) throws Exception {
		Where<SysDataDict> where = new Where<SysDataDict>();
		where.orderBy("create_time", false);
		Page<SysDataDict> page = selectPage(new Page<SysDataDict>(pageBean.getPageNum(), pageBean.getPageSize()), where);
		return page;
	}
}
