package auto.deploy.service.authority.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import auto.deploy.dao.entity.AutUser;
import auto.deploy.dao.mapper.AutUserMapper;
import auto.deploy.service.authority.AutUserService;

/**
 * 
 * @描述：用户表(服务实现类)
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-03
 */
@Service
public class AutUserServiceImpl extends ServiceImpl<AutUserMapper, AutUser> implements AutUserService {
	
}
