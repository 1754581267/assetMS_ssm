var app = new Vue({
    el : '#box',
    data : {
    	xPage : xPage,
        maxSize: 0,
        nowPage: 1,
        list: [],
        pageList: [],
        url: '',

        // 添加业务
		a1 : 0,
		a2 : 0,
		a3 : 0,
		assetId : '',
		state : '',
		
		// 更改业务
		u1 : 0,
		upid : '',
		upstate : ''
	},
    methods: {
		ret : function(li) {
			layer.confirm('是否申请归还？', {
				btn: ['确定','取消'] //按钮
			}, function(){
				$.ajax({
					url : "/app.ajax",
					type : "POST",
					data : {
						str: 'ret',
						assetsId : li.assetsId,
						id : li.id,
						staffId : li.staffId
					},
					dataType : "JSON",
					success : function (code) {
						if (code.code == "retok") {
							layer.alert("归还成功");
							xPage.getData(xPage.pageIndex);
						}
						if (code.code == "notsid") {
							layer.alert("请本人申请归还");
						}
						if (code.code == "reterr") {
							layer.alert("申请归还失败");
						}
					},
					error : function () {
						layer.alert("申请归还失败，请联系管理员");
					}
				});
			}, function(){
				layer.msg('已取消申请', {icon: 1});
			});
		},
		scrap : function(li) {
			layer.confirm('是否申请报废？', {
					btn: ['确定','取消'] //按钮
			}, function(){
				$.ajax({
					url : "/app.ajax",
					type : "POST",
					data : {

						str : 'scrap',
						assetsId : li.assetsId,
						id : li.id,
						staffId : li.staffId
					},
					dataType : "JSON",
					success : function (code) {
						if (code.code == "scrapok") {
							layer.alert("申请报废成功");
							xPage.getData(xPage.pageIndex);
						}
						if (code.code == "notsid") {
							layer.alert("请本人申请");
						}
						if (code.code == "scraperr") {
							layer.alert("申请报废失败");
						}
					},
					error : function () {
						layer.alert("申请报废失败，请联系管理员");
					}
				});
			}, function(){
				layer.msg('已取消申请', {icon: 1});
			});
		},
		addMod: function () {
			$("#addform")[0].reset();
			$("#addapp").modal(
				{
					keyboard:false,
					backdrop:"static"
				}
			);
		},
		findas : function() {
			if (this.assetId == '') {
				$("#ass").html("<strong style=\"color: red\">请输入资产编号</strong>");
				this.a1 = 0;
			} else {
				$.ajax({
					url : "/app.ajax",
					type : "POST",
					data : {
						str: 'finda',
						assetsId : this.assetId
					},
					dataType : "JSON",
					success : function (code) {
						if (code.code == "isok") {
							$("#ass").html('');
							app.a1 = 1;
						}
						if (code.code == "isnot") {
							$("#ass").html("<strong style=\"color: red\">该资产不可用</strong>");
							app.a1 = 0;
						}
						if (code.code == "notfinda") {
							$("#ass").html("<strong style=\"color: red\">该资产编号不存在</strong>");
							app.a1 = 0;
						}
					},
					error : function () {
						layer.alert("验证失败，请联系管理员");
					}
				});
			}
		},
		submit : function () {
			$("#addapp").modal('hide');
			if(this.a1 != 1) {
				layer.alert("请正确完成流程");
			} else {
				$.ajax({
				    url : "/app.ajax",
				    type : "POST",
				    data : {
				        str: 'add',
						assetsId: this.assetId,
				    },
				    dataType : "JSON",
				    success : function (code) {
                        console.log(code.code);
				        if (code.code == "addSuc") {
							layer.alert("添加成功");
							xPage.getData(xPage.pageIndex);
				        } else if(code.code == "addErr") {
							layer.alert("添加失败");
				        }
				    },
				    error : function () {
						layer.alert("添加失败，请联系管理员");
				    }
				});
			}
		},
	}
});
xPage.init("/app.ajax", "保管员");