package auto.deploy.service.dev.impl;

import javax.annotation.Resource;

import org.gitlab.api.models.GitlabGroup;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.StringUtils;

import auto.deploy.dao.config.Where;
import auto.deploy.dao.entity.dev.DevProjectGroup;
import auto.deploy.dao.mapper.dev.DevProjectGroupMapper;
import auto.deploy.gitlab.service.GitlabService;
import auto.deploy.object.PageBean;
import auto.deploy.object.RetMsg;
import auto.deploy.service.dev.DevProjectGroupService;

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

	@Resource
	private GitlabService gitlabService;

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

	@Override
	public RetMsg add(DevProjectGroup obj) throws Exception {
		RetMsg retMsg = new RetMsg();
		Where<DevProjectGroup> where = new Where<DevProjectGroup>();
		where.eq("group_name", obj.getGroupName());
		// 判断分组是否存在
		if (selectCount(where) > 0) {
			retMsg.setCode(1);
			retMsg.setMessage("该项目分组已经存在");
		} else {
			// 新增gitlab分组
			GitlabGroup gitlabGroup = gitlabService.createGroup(obj);
			// 返回gitlab的分组ID回填到DevProjectGroup
			obj.setGitlabGroupId(gitlabGroup.getId());
			insert(obj);
			retMsg.setCode(0);
			retMsg.setMessage("操作成功");
		}
		return retMsg;
	}

	@Override
	public RetMsg del(DevProjectGroup obj) throws Exception {
		RetMsg retMsg = new RetMsg();
		obj = selectById(obj.getId());
		// 先删除gitlab的项目分组
		gitlabService.delGroup(obj);
		// 删除本地分组
		deleteById(obj.getId());
		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}
}
