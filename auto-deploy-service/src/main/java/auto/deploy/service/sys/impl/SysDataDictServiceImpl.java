package auto.deploy.service.sys.impl;

import auto.deploy.dao.entity.sys.SysDataDict;
import auto.deploy.dao.mapper.sys.SysDataDictMapper;
import auto.deploy.service.sys.SysDataDictService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.StringUtils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		if (StringUtils.isNotEmpty(obj.getDictCode())) {
			where.like("dict_code", obj.getDictCode());
			where.or("dict_name LIKE {0}", "%" + obj.getDictCode() + "%");
		}
		where.setSqlSelect("DISTINCT dict_code,dict_name");
		where.orderBy("create_time", false);
		Page<SysDataDict> page = selectPage(new Page<SysDataDict>(pageBean.getPageNum(), pageBean.getPageSize()), where);
		return page;
	}

	@Override
	@Transactional
	public void update(SysDataDict obj, String[] dictKey1, String[] dictValue1, String[] isActive1, String[] id1) throws Exception {
		List<Long> idList = new ArrayList<Long>();
		for (String s : id1) {
			if (StringUtils.isNotEmpty(s)) {
				idList.add(Long.parseLong(s));
			}
		}
		// 删除
		Where<SysDataDict> where = new Where<SysDataDict>();
		where.eq("dict_code", obj.getDictCode());
		List<SysDataDict> list = selectList(where);
		for (SysDataDict sysDataDict : list) {
			if (!idList.contains(sysDataDict.getId())) {
				deleteById(sysDataDict.getId());
			}
		}
		// 插入或修改
		for (int i = 0; i < id1.length; i++) {
			if (StringUtils.isNotEmpty(id1[i])) {
				// 修改
				SysDataDict dict = selectById(Long.parseLong(id1[i]));
				dict.setDictName(obj.getDictName());
				dict.setDictKey(dictKey1[i]);
				dict.setDictValue(dictValue1[i]);
				dict.setIsActive(Integer.parseInt(isActive1[i]));
				updateById(dict);
			} else {
				// 新增
				SysDataDict dict = new SysDataDict();
				dict.setDictCode(obj.getDictCode());
				dict.setDictName(obj.getDictName());
				dict.setDictKey(dictKey1[i]);
				dict.setDictValue(dictValue1[i]);
				dict.setIsActive(Integer.parseInt(isActive1[i]));
				insert(dict);
			}

		}
	}
}
