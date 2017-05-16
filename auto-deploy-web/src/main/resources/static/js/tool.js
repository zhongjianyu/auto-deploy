var tool = {
	/**
	 * 分页加载数据
	 */
	loadpage : function(param) {
		param._csrf = $("#_csrf").val();
		param.pageNum = 1;
		tool.post(param.url, param, function(page) {
			console.log(page);
		}, false);
		return;
		layui.laypage({
			cont : param.container,// 分页容器id
			pages : 2,// 总页数
			pageSize : param.pageSize,// 每页记录数
			skip : true,// 是否显示跳转页
			url : param.url,// 数据请求接口
			jump : function(obj, first) {
				// 得到了当前页，用于向服务端请求对应数据
				var curr = obj.curr;
				console.log(obj);
				console.log(first);
			}
		});
	},
	/**
	 * post请求
	 */
	post : function(url, data, callBack, async) {
		$.ajax({
			/* 发送请求的地址 */
			url : url,
			/* 默认为true异步，如果设置为false则同步 */
			async : async,
			/* 请求类型 */
			type : "post",
			/* 服务器返回的数据类型 */
			dataType : "json",
			/* 发送到服务器的数据，键值对 */
			data : data,
			// 回调函数
			success : function(httpResponse) {
				callBack(httpResponse);
			}
		});
	},
	/**
	 * form submit请求
	 */
	submit : function(formId, callBack) {
		$("#" + formId).ajaxSubmit({
			url : $("#" + formId).attr("action"),
			type : "post",
			complete : function(params, status, xhr) {
				if (params.status == 200) {
					callBack(JSON.parse(params.responseText));
				}
			}
		});
	}
};