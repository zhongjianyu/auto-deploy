package auto.deploy.dao.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;

public class User extends Model<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6699475511751191198L;

	private Long id;

	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	protected Serializable pkVal() {
		return id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}

}
