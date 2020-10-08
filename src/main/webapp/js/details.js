var app = new Vue({
    el : '#box',
    data : {
    	xPage : xPage,
		allclass : ['食品','工具','器械'],
		allstate : ['可用', '使用中', '报废'],

		allId : '',
		lis : [],

		aids : [],
		fids : [],
		pids : [],
		// 添加业务
		a1 : 0,
		a2 : 0,
		a3 : 0,
		a4 : 0,
		a5 : 0,
		a6 : 0,
		a7 : 0,
		a8 : 0,
		assetClass : '',
		productName : '',
		number : '',
		unit : '',
		unitPrice : '',
		spft : '',
		proof : '',
		states : '',
		// 搜索

		// 更改业务
		u1 : 0,
		aid : '',
		fid : '',
		pid : '',
		u1 : 0,
		u2 : 0,
		u3 : 0,
		u4 : 0,
		u5 : 0,
		u6 : 0,
		u7 : 0,
		upassetClass : '',
		upproductName : '',
		upnumber : '',
		upunit : '',
		upunitPrice : '',
		upspft : '',
		upstates : '',

	},
    methods: {
		// 全选
		all : function() {
			if (this.allId) {
				for (var i = 0; i < xPage.dataList.length; i++) {
					this.lis.push(xPage.dataList[i]);
					this.aids.push(xPage.dataList[i].assetsId);
					this.fids.push(xPage.dataList[i].financeId);
					this.pids.push(xPage.dataList[i].productId);
				}
			} else {
				this.lis = [];
				this.aids = [];
				this.fids = [];
				this.pids = [];
			}
			console.log(this.lis.length);
		},
		mycheck : function(li) {
			if (event.target.checked) {
				this.lis.push(li);
				this.aids.push(li.assetsId);
				this.fids.push(li.financeId);
				this.pids.push(li.productId);
			} else {
				this.delone(li, this.lis);
				this.delone(li.assetsId, this.aids);
				this.delone(li.financeId, this.fids);
				this.delone(li.productId, this.pids);
			}
		},
		delone :function(str,arr) {
			var index = arr.indexOf(str);
			arr.splice(index,1);
		},
		delmore: function () {
			if (this.lis.length <= 0 ) {
				console.log(this.lis.length);
				layer.alert("请选择要删除的数据");
			} else {
				var aidstr = this.aids.join(",");
				var fidstr = this.fids.join(",");
				var pidstr = this.pids.join(",");
				this.del(aidstr, fidstr, pidstr);
			}
		},
        del: function (aid, fid, pid) {
			if (xPage.work == 1) {
				layer.confirm('是否要删除？', {
					btn: ['确定','取消'] //按钮
				}, function(){
					$.ajax({
						url : "/details.ajax",
						type : "POST",
						data : {
							assetsId : aid,
							financeId : fid,
							productId : pid,
							str: 'del'
						},
						dataType : "JSON",
						success : function (code) {
							console.log(code);
							console.log(code.acode);
							if (code.acode == "delSuc" && code.fcode == "delSuc" && code.pcode == "delSuc") {
								layer.alert("删除成功！");
								app.allId = false;
								app.lis = [];
								app.aids = [];
								app.fids = [];
								app.pids = [];
								xPage.getData(xPage.pageIndex);
							} else if (code.acode == "delErr") {
								layer.alert("资产信息删除失败！");
							} else if (code.fcode == "delErr") {
								layer.alert("财务信息删除失败！");
							} else if (code.pcode == "delErr") {
								layer.alert("产品信息删除失败！");
							}
						},
						error : function () {
							layer.alert("删除失败，请联系管理员");
						}
					});
				}, function(){
					layer.msg('已取消删除', {icon: 1});
				});
			} else {
				layer.alert("不是保管员无法操作！")
			}
        },
        addMod: function () {
			if (xPage.work == 1) {
				$("#addform")[0].reset();
				$("#adddet").modal(
				    {
				        keyboard:false,
				        backdrop:"static"
				    }
				);
			} else {
				layer.alert("不是保管员无法操作！")
			}
        },
		form : function() {
			$.ajax({
				url : "/finance.ajax",
				type : "POST",
				data : {
					str: 'form',
				},
				dataType : "JSON",
				success : function (code) {
					app.proof = code.code;
				},
				error : function () {
					layer.alert("生成失败，请联系管理员");
				}
			});
		},
		submit : function () {
			if (this.assetClass == '') {
				$("#inputaclass").html("<strong style=\"color: red\">请选择类别</strong>");
			    this.a1 = 0;
			} else {
				$("#inputaclass").html('');
				this.a1 = 1;
			}
			if (this.productName == '') {
				$("#inputname").html("<strong style=\"color: red\">请填写产品名称</strong>");
			    this.a2 = 0;
			} else {
				$("#inputname").html('');
				this.a2 = 1;
			}
			if (this.number == '') {
				$("#inputnumber").html("<strong style=\"color: red\">请填写产品数量</strong>");
			    this.a3 = 0;
			} else {
				$("#inputnumber").html('');
				this.a3 = 1;
			}
			if (this.unit == '') {
				$("#inputunit").html("<strong style=\"color: red\">请填写产品单位</strong>");
			    this.a4 = 0;
			} else {
				$("#inputunit").html('');
				this.a4 = 1;
			}
			if (this.unitPrice == '') {
				$("#inputunitPrice").html("<strong style=\"color: red\">请填写产品单价</strong>");
			    this.a5 = 0;
			} else {
				$("#inputunitPrice").html('');
				this.a5 = 1;
			}
			if (this.spft == '') {
				$("#inputspft").html("<strong style=\"color: red\">请填写产品型号</strong>");
			    this.a6 = 0;
			} else {
				$("#inputspft").html('');
				this.a6 = 1;
			}
			if (this.proof == '') {
				$("#prof").html("<strong style=\"color: red\">请填写产品型号</strong>");
				this.a7 = 0;
			} else {
				$("#prof").html('');
				this.a7 = 1;
			}
			if (this.states == '') {
				$("#inputastate").html("<strong style=\"color: red\">请填写产品状态</strong>");
			    this.a8 = 0;
			} else {
				$("#inputastate").html('');
				this.a8 = 1;
			}
			if(this.a1 + this.a2 + this.a3 + this.a4 + this.a5 + this.a6 + this.a7 + this.a8 != 8) {
				alert(this.a1 + '-' + this.a2 + '-' + this.a3 + '-' + this.a4 + '-' + this.a5 + '-' + this.a6 + '-' + this.a7 );
				alert("请将信息填写正确");
			} else {
			    $("#adddet").modal('hide');
				$.ajax({
				    url : "/details.ajax",
				    type : "POST",
				    data : {
				        str: 'add',
						assetClass : this.assetClass,
						productName : this.productName,
						number : this.number,
						unit : this.unit,
						unitPrice : this.unitPrice,
						specification : this.spft,
						proof : this.proof,
						assetsState : this.states
				    },
				    dataType : "JSON",
				    success : function (code) {
			            if (code.acode == "addSuc" && code.fcode == "addSuc" && code.pcode == "addSuc") {
							layer.alert("数据新增成功");
			            	xPage.getData(xPage.pageIndex);
			            } else if (code.acode == "addErr") {
							layer.alert("资产数据新增失败");
			            } else if (code.fcode == "addErr") {
							layer.alert("财务数据新增失败");
			            } else if (code.pcode == "addErr") {
							layer.alert("产品数据新增失败");
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
				this.aid = li.assetsId;
				this.fid = li.financeId;
				this.pid = li.productId;
				this.upassetClass = li.assetClass;
				this.upproductName = li.productName;
				this.upnumber = li.number;
				this.upunit = li.unit;
				this.upunitPrice = li.unitPrice;
				this.upspft = li.specification;
				this.upstates = li.assetsState;
				$("#updtdet").modal(
					{
						keyboard:false,
						backdrop:"static"
					}
				);
			} else {
				layer.alert("不是保管员无法操作！")
			}
		},
		submit1 : function () {
			if (this.upassetClass == '') {
				$("#upinputaclass").html("<strong style=\"color: red\">请选择类别</strong>");
			    this.u1 = 0;
			} else {
				$("#upinputaclass").html('');
				this.u1 = 1;
			}
			if (this.upproductName == '') {
				$("#upinputname").html("<strong style=\"color: red\">请填写产品名称</strong>");
			    this.u2 = 0;
			} else {
				$("#upinputname").html('');
				this.u2 = 1;
			}
			if (this.upnumber == '') {
				$("#upinputnumber").html("<strong style=\"color: red\">请填写产品数量</strong>");
			    this.u3 = 0;
			} else {
				$("#upinputnumber").html('');
				this.u3 = 1;
			}
			if (this.upunit == '') {
				$("#upinputunit").html("<strong style=\"color: red\">请填写产品单位</strong>");
			    this.u4 = 0;
			} else {
				$("#upinputunit").html('');
				this.u4 = 1;
			}
			if (this.upunitPrice == '') {
				$("#upinputunitPrice").html("<strong style=\"color: red\">请填写产品单价</strong>");
			    this.u5 = 0;
			} else {
				$("#upinputunitPrice").html('');
				this.u5 = 1;
			}
			if (this.upspft == '') {
				$("#upinputspft").html("<strong style=\"color: red\">请填写产品型号</strong>");
			    this.u6 = 0;
			} else {
				$("#upinputspft").html('');
				this.u6 = 1;
			}
			if (this.upstates == '') {
				$("#upinputastate").html("<strong style=\"color: red\">请填写产品状态</strong>");
			    this.u7 = 0;
			} else {
				$("#upinputastate").html('');
				this.u7 = 1;
			}
			if(this.u1 + this.u2 + this.u3 + this.u4 + this.u5 + this.u6 + this.u7 != 7) {
				alert("请将信息填写正确");
			} else {
			    $("#updtdet").modal('hide');
				$.ajax({
				    url : "/details.ajax",
				    type : "POST",
				    data : {
				        str: 'updt',
						assetsId : this.aid,
						financeId : this.fid,
						productId : this.pid,
						assetClass : this.upassetClass,
						productName : this.upproductName,
						number : this.upnumber,
						unit : this.upunit,
						unitPrice : this.upunitPrice,
						specification : this.upspft,
						assetsState : this.upstates
				    },
				    dataType : "JSON",
				    success : function (code) {
				    	console.log(code.acode);
			            if (code.acode == "updtSuc" && code.fcode == "updtSuc" && code.pcode == "updtSuc") {
							layer.alert("数据修改成功");
			            	xPage.getData(xPage.pageIndex);
			            } else if (code.acode == "updtErr") {
							layer.alert("资产数据修改失败");
			            } else if (code.fcode == "updtErr") {
							layer.alert("财务数据修改失败");
			            } else if (code.pcode == "updtErr") {
							layer.alert("产品数据修改失败");
			            }
				    },
				    error : function () {
						layer.alert("添加失败，请联系管理员");
				    }
				});
			}
		}
	}
});
xPage.init("/details.ajax", "保管员");