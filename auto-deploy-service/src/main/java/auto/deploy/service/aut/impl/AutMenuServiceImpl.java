package auto.deploy.service.aut.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import auto.deploy.dao.entity.aut.AutMenu;
import auto.deploy.dao.mapper.aut.AutMenuMapper;
import auto.deploy.service.aut.AutMenuService;

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
