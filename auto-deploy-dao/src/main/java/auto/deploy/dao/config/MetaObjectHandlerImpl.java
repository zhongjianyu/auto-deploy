package auto.deploy.dao.config;

import java.util.Date;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
		/**
		 * 获取当前用户及权限信息
		 */
		JSONObject jo = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			jo = (JSONObject) JSON.toJSON(authentication.getPrincipal());
		}

		/**
		 * 创建时间
		 */
		Object createTime = obj.getValue("createTime");
		/**
		 * 最后修改时间
		 */
		Object lastUpdateTime = obj.getValue("lastUpdateTime");
		/**
		 * 版本号
		 */
		Object version = obj.getValue("version");
		/**
		 * 是否删除
		 */
		Object isDelete = obj.getValue("isDelete");
		/**
		 * 最后修改数据的用户ID
		 */
		Object lastUpdateUserId = obj.getValue("lastUpdateUserId");
		/**
		 * 最后修改数据的用户账号
		 */
		Object lastUpdateUserName = obj.getValue("lastUpdateUserName");
		/**
		 * 创建数据的用户ID
		 */
		Object createUserId = obj.getValue("createUserId");
		/**
		 * 创建数据的用户账号
		 */
		Object createUserName = obj.getValue("createUserName");

		Date now = new Date();
		if (createTime == null) {
			obj.setValue("createTime", now);
		}
		if (lastUpdateTime == null) {
			obj.setValue("lastUpdateTime", now);
		}
		if (version == null) {
			obj.setValue("version", 0);
		}
		if (isDelete == null) {
			obj.setValue("isDelete", 0);
		}
		if (lastUpdateUserId == null) {
			if (jo != null) {
				obj.setValue("lastUpdateUserId", Long.parseLong(jo.get("userId").toString()));
			} else {
				obj.setValue("lastUpdateUserId", -1L);
			}
		}
		if (lastUpdateUserName == null) {
			if (jo != null) {
				obj.setValue("lastUpdateUserName", jo.get("username").toString());
			} else {
				obj.setValue("lastUpdateUserName", "-1");
			}
		}
		if (createUserId == null) {
			if (jo != null) {
				obj.setValue("createUserId", Long.parseLong(jo.get("userId").toString()));
			} else {
				obj.setValue("createUserId", -1L);
			}
		}
		if (createUserName == null) {
			if (jo != null) {
				obj.setValue("createUserName", jo.get("username").toString());
			} else {
				obj.setValue("createUserName", "-1");
			}
		}
	}

	/**
	 * 修改时自动修改字段
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void updateFill(MetaObject obj) {
		/**
		 * 获取原数据信息
		 */
		Object originalObject = obj.getOriginalObject();
		Entity entity = (Entity) originalObject;
		/**
		 * 当前时间
		 */
		Date now = new Date();

		/**
		 * 获取当前用户及权限信息
		 */
		JSONObject jo = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			jo = (JSONObject) JSON.toJSON(authentication.getPrincipal());
		}
		/**
		 * 修改时重新回填修改时间和版本号
		 */
		obj.setValue("lastUpdateTime", now);
		obj.setValue("createTime", entity.getCreateTime());
		obj.setValue("isDelete", entity.getIsDelete());
		obj.setValue("version", entity.getVersion() + 1);

		if (jo != null) {
			obj.setValue("lastUpdateUserId", Long.parseLong(jo.get("userId").toString()));
		} else {
			obj.setValue("lastUpdateUserId", -1L);
		}
		if (jo != null) {
			obj.setValue("lastUpdateUserName", jo.get("username").toString());
		} else {
			obj.setValue("lastUpdateUserName", "-1");
		}
		obj.setValue("createUserId", entity.getCreateUserId());
		obj.setValue("createUserName", entity.getCreateUserName());
	}

}
