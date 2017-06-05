package auto.deploy.service.sys.impl;

import auto.deploy.dao.entity.sys.SysOperateLog;
import auto.deploy.dao.mapper.sys.SysOperateLogMapper;
import auto.deploy.service.sys.SysOperateLogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.StringUtils;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.plugins.Page;
import auto.deploy.dao.config.Where;
import auto.deploy.object.PageBean;

/**
 * 
 * @描述：操作日志表(服务实现类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-06-05
 */
@Service
public class SysOperateLogServiceImpl extends ServiceImpl<SysOperateLogMapper, SysOperateLog>implements SysOperateLogService {

	@Override
	public Page<SysOperateLog> list(PageBean pageBean, SysOperateLog obj) throws Exception {
		Where<SysOperateLog> where = new Where<SysOperateLog>();
		if (StringUtils.isNotEmpty(obj.getOperateUserName())) {
			where.like("operate_user_name", obj.getOperateUserName());
			where.or("operate_user_ip LIKE {0}", "%" + obj.getOperateUserName() + "%");
			where.or("operate_log_name LIKE {0}", "%" + obj.getOperateUserName() + "%");
		}

		where.orderBy("create_time", false);
		Page<SysOperateLog> page = selectPage(new Page<SysOperateLog>(pageBean.getPageNum(), pageBean.getPageSize()), where);
		return page;
	}
}
