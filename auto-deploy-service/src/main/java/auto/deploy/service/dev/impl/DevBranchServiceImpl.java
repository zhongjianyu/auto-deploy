package auto.deploy.service.dev.impl;

import auto.deploy.dao.entity.dev.DevBranch;
import auto.deploy.dao.mapper.dev.DevBranchMapper;
import auto.deploy.service.dev.DevBranchService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import auto.deploy.dao.config.Where;
import auto.deploy.object.PageBean;

/**
 * 
 * @描述：项目分支表(服务实现类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-06-17
 */
@Service
public class DevBranchServiceImpl extends ServiceImpl<DevBranchMapper, DevBranch> implements DevBranchService {

	@Override
	public Page<DevBranch> list(PageBean pageBean, DevBranch obj) throws Exception {
		Where<DevBranch> where = new Where<DevBranch>();
		where.orderBy("create_time", false);
		Page<DevBranch> page = selectPage(new Page<DevBranch>(pageBean.getPageNum(), pageBean.getPageSize()), where);
		return page;
	}

	@Override
	@Transactional
	public void add(DevBranch obj) {
		//新建远程分支
		//插入分支表
		
	}	
}
