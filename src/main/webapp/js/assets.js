Vue.config.devtools = true;
var app = new Vue({
    el : '#box',
    data : {
    	xPage: xPage,
		allclass : ['食品','工具','器械'],
		allstate : ['可用', '使用中', '报废'],

        // 添加业务
		a1 : 0,
		a2 : 0,
		a3 : 0,
		a4 : 0,
		a5 : 0,
		assetClass : '',
		financialId : '',
		productId : '',
		states : '',
		
		// 更改业务
		u1 : 0,
		u2 : 0,
		u5 : 0,
		upid : '',
		upassetClass : '',
		upunitPrice : '',
		upstates : ''
	},
    methods: {
		findfid : function () {
			if (this.financialId == '') {
				$(".inputfid").html("<strong style=\"color: red\">请输入财务编号</strong>");
				this.a3 = 0;
			} else {
				$.ajax({
					url : "/assets.ajax",
					type : "POST",
					data : {
						str: 'ffid',
						id : this.upid,
						ffid : this.financialId
					},
					dataType : "JSON",
					success : function (code) {
						if (code.code == "findfid") {
							$(".inputfid").html('');
							app.a3 = 1;
						}
						if (code.code == "fidUsed") {
							$(".inputfid").html("<strong style=\"color: red\">该财务编号已使用</strong>");
							app.a3 = 0;
						}
						if (code.code == "notfindfid") {
							$(".inputfid").html("<strong style=\"color: red\">该财务编号不存在</strong>");
							app.a3 = 0;
						}
					},
					error : function () {
						alert("验证失败，请联系管理员");
					}
				});
			}
		},
		findpid : function () {
			if (this.productId == '') {
				$(".inputpid").html("<strong style=\"color: red\">请输入产品编号</strong>");
				this.a4 = 0;
			} else {
				$.ajax({
					url : "/assets.ajax",
					type : "POST",
					data : {
						str: 'fpid',
						id : this.upid,
						fpid : this.productId
					},
					dataType : "JSON",
					success : function (code) {
						if (code.code == "findpid") {
							$(".inputpid").html('');
								app.a4 = 1;
						}
						if (code.code == "pidUsed") {
							$(".inputpid").html("<strong style=\"color: red\">该产品编号已使用</strong>");
								app.a4 = 0;
						}
						if (code.code == "notfindpid") {
							$(".inputpid").html("<strong style=\"color: red\">产品编号不存在</strong>");
								app.a4 = 0;
						}
					},
					error : function () {
						layer.alert("验证失败，请联系管理员");
					}
				});
			}
		},
		addMod: function () {
			if (xPage.work == 1) {
				$("#addform")[0].reset();
				$("#addasset").modal(
				    {
				        keyboard:false,
				        backdrop:"static"
				    }
				);
			} else {
				layer.alert("不是保管员无法操作！");
			}
		},
		submit : function () {
            if (this.assetClass == '') {
				$("#inputaclass").html("<strong style=\"color: red\">请选择资产类型</strong>");
                this.a1 = 0;
			} else {
				$("#inputaclass").html('');
				this.a1 = 1;
			}
			if (this.states == '') {
				$("#inputastate").html("<strong style=\"color: red\">请选择资产状态</strong>");
				this.a5 = 0;
			} else {
				$("#inputastate").html('');
				this.a5 = 1;
			}
			if(this.a1 + this.a3 + this.a4 + this.a5 != 4) {
				layer.alert("请正确完成流程");
			} else {
				$("#addasset").modal('hide');
				$.ajax({
				    url : "/assets.ajax",
				    type : "POST",
				    data : {
				        str: 'add',
						assetClass : this.assetClass,
						financeId : this.financialId,
						productId : this.productId,
						state : this.states,
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
		updt : function(li) {
			if (xPage.work == 1) {
				this.upid = li.id;
				this.upassetClass = li.assetClass;
				this.financialId = li.financeId;
				this.productId = li.productId;
				this.upstates = li.state;
				$("#updtasset").modal(
					{
						keyboard:false,
						backdrop:"static"
					}
				);
				app.findpid();
				app.findfid();
			} else {
				layer.alert("不是保管员无法操作！");
			}
		},
		submit1 : function () {
			if (this.upassetClass == '') {
				$("#inputupaclass").html("<strong style=\"color: red\">请选择资产类型</strong>");
			    this.u1 = 0;
			} else {
				$("#inputupaclass").html('');
				this.u1 = 1;
			}
			if (this.upstates == '') {
				$("#inputupastate").html("<strong style=\"color: red\">请选择资产状态</strong>");
				this.u5 = 0;
			} else {
				$("#inputupastate").html('');
				this.u5 = 1;
			}
			if(this.u1 + this.a3 + this.a4 + this.u5 != 4) {
				layer.alert("请正确完成流程"+this.u1 + this.a3 + this.a4 + this.u5);
			} else {
				$("#updtasset").modal('hide');
				$.ajax({
					url : "/assets.ajax",
					type : "POST",
					data : {
						str: 'updt',
						id : this.upid,
						assetClass : this.upassetClass,
						financeId : this.financialId,
						productId : this.productId,
						state : this.upstates,
					},
					dataType : "JSON",
					success : function (code) {
						console.log(code.code);
						if (code.code == "updtSuc") {
							layer.alert("修改成功");
							xPage.getData(xPage.pageIndex);
						} else if(code.code == "updtErr") {
							layer.alert("修改失败");
						}
					},
					error : function () {
						layer.alert("修改失败，请联系管理员");
					}
				});
			}
		}
	}
});
xPage.init("/assets.ajax", "保管员")