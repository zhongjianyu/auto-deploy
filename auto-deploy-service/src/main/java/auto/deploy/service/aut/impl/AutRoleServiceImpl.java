package auto.deploy.service.aut.impl;

import auto.deploy.dao.entity.aut.AutRole;
import auto.deploy.dao.mapper.aut.AutRoleMapper;
import auto.deploy.service.aut.AutRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.StringUtils;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;

import auto.deploy.activiti.service.ActivitiService;
import auto.deploy.dao.config.Where;
import auto.deploy.object.PageBean;
import auto.deploy.object.RetMsg;

/**
 * 
 * @描述：角色表(服务实现类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-27
 */
@Service
public class AutRoleServiceImpl extends ServiceImpl<AutRoleMapper, AutRole> implements AutRoleService {

	@Resource
	private ActivitiService activitiService;

	@Override
	public Page<AutRole> list(PageBean pageBean, AutRole obj) throws Exception {
		Where<AutRole> where = new Where<AutRole>();
		if (StringUtils.isNotEmpty(obj.getRoleName())) {
			where.like("role_name", obj.getRoleName());
			where.or("role_code LIKE {0}", "%" + obj.getRoleName() + "%");
		}
		where.orderBy("create_time", false);
		Page<AutRole> page = selectPage(new Page<AutRole>(pageBean.getPageNum(), pageBean.getPageSize()), where);
		return page;
	}

	@Override
	@Transactional
	public RetMsg add(AutRole obj) throws Exception {
		RetMsg retMsg = new RetMsg();
		// 检查角色名称和角色代码是否已经存在
		Where<AutRole> where = new Where<AutRole>();
		where.eq("role_name", obj.getRoleName());
		where.or("role_code = {0}", obj.getRoleCode());
		if (selectCount(where) > 0) {
			retMsg.setCode(1);
			retMsg.setMessage("角色名称或代码已存在");
		} else {
			insert(obj);
			retMsg.setCode(0);
			retMsg.setMessage("操作成功");
			// 同步activiti分组
			activitiService.addGroup(obj.getId());
		}
		return retMsg;
	}

	@Override
	@Transactional
	public RetMsg delete(AutRole obj) throws Exception {
		RetMsg retMsg = new RetMsg();
		deleteById(obj.getId());
		// 同步activiti分组
		activitiService.delGroup(obj.getId());
		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}
}
