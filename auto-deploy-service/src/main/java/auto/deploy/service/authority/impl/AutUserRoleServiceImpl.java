package auto.deploy.service.authority.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import auto.deploy.dao.entity.AutUserRole;
import auto.deploy.dao.mapper.AutUserRoleMapper;
import auto.deploy.service.authority.AutUserRoleService;

/**
 * 
 * @描述：用户-角色关联表(服务实现类)
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-14
 */
@Service
public class AutUserRoleServiceImpl extends ServiceImpl<AutUserRoleMapper, AutUserRole> implements AutUserRoleService {
	
}
