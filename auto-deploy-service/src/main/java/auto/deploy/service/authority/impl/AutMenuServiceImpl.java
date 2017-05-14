package auto.deploy.service.authority.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import auto.deploy.dao.entity.AutMenu;
import auto.deploy.dao.mapper.AutMenuMapper;
import auto.deploy.service.authority.AutMenuService;

/**
 * 
 * @描述：菜单表(服务实现类)
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-14
 */
@Service
public class AutMenuServiceImpl extends ServiceImpl<AutMenuMapper, AutMenu> implements AutMenuService {
	
}
