package auto.deploy.dao.config;

public enum DBTypeEnum {
	def("dataSource_default"), d001("dataSource_biz001");
	private String value;

	DBTypeEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
