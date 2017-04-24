package auto.deploy.dao.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldStrategy;

/**
 * 实体父类
 * 
 * @author zhongjy
 *
 * @param <T>
 */
public class Entity<T extends Model<T>> extends Model<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4325616953838672659L;

	/**
	 * 主键
	 */
	@TableField(validate = FieldStrategy.IGNORED)
	private Long id;
	/**
	 * 创建时间
	 */
	@TableField(validate = FieldStrategy.IGNORED)
	private Date createTime;
	/**
	 * 修改时间
	 */
	@TableField(validate = FieldStrategy.IGNORED)
	private Date updateTime;
	/**
	 * 版本号
	 */
	@TableField(validate = FieldStrategy.IGNORED)
	private Integer version;
	/**
	 * 是否已被删除
	 */
	@TableField(validate = FieldStrategy.IGNORED)
	private Integer isDelete;

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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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

	@Override
	public String toString() {
		return "Entity [id=" + id + ", createTime=" + createTime + ", updateTime=" + updateTime + ", version=" + version
				+ ", isDelete=" + isDelete + "]";
	}

}