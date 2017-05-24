package auto.deploy.web.tag;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import auto.deploy.service.aut.AutWidgetRoleService;
import freemarker.core.Environment;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 
 * @描述：自定义freemarker的select标签(根据code自动获取数据字典的值).
 *
 * @作者：zhongjy
 * 
 * @时间：2017年5月24日 下午5:17:42
 */
public class Select implements TemplateDirectiveModel {

	private AutWidgetRoleService autWidgetRoleService;

	public Select(AutWidgetRoleService autWidgetRoleService) {
		this.autWidgetRoleService = autWidgetRoleService;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Environment environment, Map map, TemplateModel[] model, TemplateDirectiveBody body) throws TemplateException, IOException {
		Writer out = environment.getOut();
		// 获取标签属性
		Object codeObj = map.get("code");
		Object idObj = map.get("id");
		Object nameObj = map.get("name");
		Object defObj = map.get("def");

		String code = codeObj == null ? null : ((SimpleScalar) codeObj).getAsString();
		String id = idObj == null ? null : ((SimpleScalar) idObj).getAsString();
		String name = nameObj == null ? null : ((SimpleScalar) nameObj).getAsString();
		String def = defObj == null ? null : ((SimpleScalar) defObj).getAsString();

		// 构造页面需要显示的元素
		StringBuffer select = new StringBuffer();
		select.append("<div class=\"layui-input-inline\">");
		select.append(" <select name=\"" + name + "\" id=\"" + id + "\">");
		select.append("	 <option value=\"\">请选择</option>");
		select.append("	 <option value=\"西湖区\">西湖区</option>");
		select.append("	 <option value=\"余杭区\">余杭区</option>");
		select.append("	 <option value=\"拱墅区\">临安市</option>");
		select.append(" </select>");
		select.append("</div>");

	}

}
