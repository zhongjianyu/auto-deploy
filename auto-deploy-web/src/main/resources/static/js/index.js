/**
 * index.js By Beginner Emain:zheng_jinfan@126.com
 * HomePage:http://www.zhengjinfan.cn
 */

var tab;

layui.config({
	base : 'js/',
	version : new Date().getTime()
}).use([ 'element', 'layer', 'navbar', 'tab' ], function() {
	var element = layui.element(), $ = layui.jquery, layer = layui.layer, navbar = layui.navbar();
	tab = layui.tab({
		elem : '.admin-nav-card' // 设置选项卡容器
		,
		// maxSetting: {
		// max: 5,
		// tipMsg: '只能开5个哇，不能再开了。真的。'
		// },
		contextMenu : true,
		onSwitch : function(data) {
			//console.log(data.id); // 当前Tab的Id
			//console.log(data.index); // 得到当前Tab的所在下标
			//console.log(data.elem); // 得到当前的Tab大容器

			//console.log(tab.getCurrentTabId())
		}
	});
	// iframe自适应
	$(window).on('resize', function() {
		var $content = $('.admin-nav-card .layui-tab-content');
		$content.height($(this).height() - 147);
		$content.find('iframe').each(function() {
			$(this).height($content.height());
		});
	}).resize();
	var navs = null;
	//console.log($("#_csrf").val());
	tool.post("index/menuList", "_csrf=" + $("#_csrf").val(), function(navList) {
		navs = navList;
	}, false);
	//console.log(navs);
	// 设置navbar
	navbar.set({
		spreadOne : true,
		elem : '#admin-navbar-side',
		cached : true,
		data : navs
	/*
	 * cached:true, url: 'datas/nav.json'
	 */
	});
	// 渲染navbar
	navbar.render();
	// 监听点击事件
	navbar.on('click(side)', function(data) {
		tab.tabAdd(data.field);
	});

	$('.admin-side-toggle').on('click', function() {
		var sideWidth = $('#admin-side').width();
		if (sideWidth === 200) {
			$('#admin-body').animate({
				left : '0'
			}); // admin-footer
			$('#admin-footer').animate({
				left : '0'
			});
			$('#admin-side').animate({
				width : '0'
			});
		} else {
			$('#admin-body').animate({
				left : '200px'
			});
			$('#admin-footer').animate({
				left : '200px'
			});
			$('#admin-side').animate({
				width : '200px'
			});
		}
	});
	$('.admin-side-full').on('click', function() {
		var docElm = document.documentElement;
		// W3C
		if (docElm.requestFullscreen) {
			docElm.requestFullscreen();
		}
		// FireFox
		else if (docElm.mozRequestFullScreen) {
			docElm.mozRequestFullScreen();
		}
		// Chrome等
		else if (docElm.webkitRequestFullScreen) {
			docElm.webkitRequestFullScreen();
		}
		// IE11
		else if (elem.msRequestFullscreen) {
			elem.msRequestFullscreen();
		}
		layer.msg('按Esc即可退出全屏');
	});

	// 锁屏
	$(document).on('keydown', function() {
		var e = window.event;
		if (e.keyCode === 76 && e.altKey) {
			// alert("你按下了alt+l");
			lock($, layer);
		}
	});

	// 手机设备的简单适配
	var treeMobile = $('.site-tree-mobile'), shadeMobile = $('.site-mobile-shade');
	treeMobile.on('click', function() {
		$('body').addClass('site-mobile');
	});
	shadeMobile.on('click', function() {
		$('body').removeClass('site-mobile');
	});
});
