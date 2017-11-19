package auto.deploy.service.dev.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import auto.deploy.activiti.service.ActivitiService;
import auto.deploy.dao.config.Where;
import auto.deploy.dao.entity.dev.DevBranch;
import auto.deploy.dao.entity.dev.DevProject;
import auto.deploy.dao.mapper.dev.DevBranchMapper;
import auto.deploy.gitlab.service.GitlabService;
import auto.deploy.object.PageBean;
import auto.deploy.service.dev.DevBranchService;
import auto.deploy.service.dev.DevProjectService;

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

	@Resource
	private DevProjectService devProjectService;
	@Resource
	private GitlabService gitlabService;
	@Resource
	private ActivitiService activitiService;

	@Override
	public Page<DevBranch> list(PageBean pageBean, DevBranch obj) throws Exception {
		Where<DevBranch> where = new Where<DevBranch>();
		where.orderBy("create_time", false);
		Page<DevBranch> page = selectPage(new Page<DevBranch>(pageBean.getPageNum(), pageBean.getPageSize()), where);
		return page;
	}

	@Override
	@Transactional
	public void add(DevBranch obj) throws Exception {
		// 插入分支表
		DevProject project = devProjectService.selectById(obj.getProjectId());
		DevBranch branch = selectById(obj.getParentBranchId());
		obj.setProjectName(project.getProjectName());
		obj.setIsDeploySuccess(0);
		obj.setProjectStatus(0);
		obj.setIsActive(1);
		obj.setParentBranchName(branch.getBranchName());
		insert(obj);
		// 新建远程分支
		gitlabService.addBranch(project.getGitlabProjectId(), obj.getBranchName(), branch.getBranchName());
		// 启动流程
		activitiService.startProcess("it_project_develop_cycle");

	}
}
