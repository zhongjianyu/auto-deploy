package auto.deploy.dao.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.enums.FieldStrategy;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * 
 * @描述：实体父类
 *
 * @作者：zhongjy
 *
 * @时间：2017年4月24日 下午12:27:26
 */
public class Entity<T extends Model<T>> extends Model<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4325616953838672659L;

	/**
	 * 主键
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@TableField(validate = FieldStrategy.IGNORED, value = "id")
	private Long id;
	/**
	 * 创建时间
	 */
	@TableField(validate = FieldStrategy.IGNORED, value = "create_time")
	private Date createTime;
	/**
	 * 修改时间
	 */
	@TableField(validate = FieldStrategy.IGNORED, value = "last_update_time")
	private Date lastUpdateTime;
	/**
	 * 版本号
	 */
	@TableField(validate = FieldStrategy.IGNORED, value = "version")
	private Integer version;
	/**
	 * 是否已被删除
	 */
	@TableField(validate = FieldStrategy.IGNORED, value = "is_delete")
	@TableLogic
	private Integer isDelete;
	/**
	 * 最后修改用户ID
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@TableField(validate = FieldStrategy.IGNORED, value = "last_update_user_id")
	private Long lastUpdateUserId;
	/**
	 * 最后修改用户账号
	 */
	@TableField(validate = FieldStrategy.IGNORED, value = "last_update_user_name")
	private String lastUpdateUserName;
	/**
	 * 创建用户ID
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@TableField(validate = FieldStrategy.IGNORED, value = "create_user_id")
	private Long createUserId;
	/**
	 * 创建用户账号
	 */
	@TableField(validate = FieldStrategy.IGNORED, value = "create_user_name")
	private String createUserName;

	/**
	 * 自动创建主键
	 */
	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public String getLastUpdateUserName() {
		return lastUpdateUserName;
	}

	public void setLastUpdateUserName(String lastUpdateUserName) {
		this.lastUpdateUserName = lastUpdateUserName;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public Long getLastUpdateUserId() {
		return lastUpdateUserId;
	}

	public void setLastUpdateUserId(Long lastUpdateUserId) {
		this.lastUpdateUserId = lastUpdateUserId;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	@Override
	public String toString() {
		return "Entity [id=" + id + ", createTime=" + createTime + ", lastUpdateTime=" + lastUpdateTime + ", version=" + version + ", isDelete="
				+ isDelete + ", lastUpdateUserId=" + lastUpdateUserId + ", lastUpdateUserName=" + lastUpdateUserName + ", createUserId="
				+ createUserId + ", createUserName=" + createUserName + "]";
	}

}
