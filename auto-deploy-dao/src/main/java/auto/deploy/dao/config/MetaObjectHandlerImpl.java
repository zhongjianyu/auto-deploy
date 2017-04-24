package auto.deploy.dao.config;

import java.util.Date;

import org.apache.ibatis.reflection.MetaObject;

import com.baomidou.mybatisplus.mapper.IMetaObjectHandler;

/**
 * 
 * @描述：自动填充字段处理
 *
 * @作者：zhongjy
 *
 * @时间：2017年4月24日 下午12:25:48
 */
public class MetaObjectHandlerImpl implements IMetaObjectHandler {

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
			obj.setValue("version", 1);
		}
		if (isDelete == null) {
			obj.setValue("isDelete", 0);
		}
	}

}
