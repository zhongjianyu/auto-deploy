package auto.deploy.dao.config;

import java.util.Date;

import org.apache.ibatis.reflection.MetaObject;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;

import auto.deploy.dao.entity.Entity;

/**
 * 
 * @描述：自动填充字段处理
 *
 * @作者：zhongjy
 *
 * @时间：2017年4月24日 下午12:25:48
 */
public class MetaObjectHandlerImpl extends MetaObjectHandler {

	/**
	 * 插入时自动填充字段
	 */
	@Override
	public void insertFill(MetaObject obj) {
		Object createTime = obj.getValue("createTime");
		Object updateTime = obj.getValue("updateTime");
		Object version = obj.getValue("version");
		Object isDelete = obj.getValue("isDelete");

		Date now = new Date();
		if (createTime == null) {
			obj.setValue("createTime", now);
		}
		if (updateTime == null) {
			obj.setValue("updateTime", now);
		}
		if (version == null) {
			obj.setValue("version", 0);
		}
		if (isDelete == null) {
			obj.setValue("isDelete", 0);
		}
	}

	/**
	 * 修改时自动修改字段
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void updateFill(MetaObject obj) {
		Object originalObject = obj.getOriginalObject();
		Entity entity = (Entity) originalObject;
		Date now = new Date();
		obj.setValue("updateTime", now);
		obj.setValue("createTime", entity.getCreateTime());
		obj.setValue("isDelete", entity.getIsDelete());
		obj.setValue("version", entity.getVersion() + 1);
	}

}
