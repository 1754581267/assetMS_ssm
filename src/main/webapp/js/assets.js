Vue.config.devtools = true;
var app = new Vue({
    el : '#box',
    data : {
		page : {
			index : 1,
			max : 0,
			pager : []
		},
        list: [],
        pageList: [],
        url: '',
		allclass : ['食品','工具','器械'],
		allstate : ['可用', '使用中', '报废'],
		e1 : 0,
		sd:{
			assetClass : '',
			state : '',
		},
		allId : '',
		ids : [],

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
    	all: function() {
    		if (this.allid) {
    			for (var i = 0; i < this.list.length; i++) {
    				this.ids.push(this.list[i].id);
				}
			} else {
    			this.ids = [];
			}
		},
        nextPage: function () {
            if (this.page.index < this.page.max) {
            	this.page.index = this.page.index + 1;
            	this.getData(this.page.index);
			}
        },
        upPage: function () {
            if (this.page.index > 1) {
            	this.page.index = this.page.index - 1;
            	this.getData(this.page.index);
            }
        },
		runpage : function(max) {
			this.page.max = max;
			app.page.pager = [];
			for (var i = 1; i <= max; i++) {
				app.page.pager.push(i);
			}
		},
        getData: function(indexPage) {
            $.ajax({
                url : "/assets.ajax",
                type : "POST",
                data : {
					str : 'paging',
                    index : indexPage,
					assetClass : this.sd.assetClass,
					state : this.sd.state
                },
                dataType : "JSON",
                success : function (data) {
					app.page.index = indexPage;
					app.list = data.dataList;
					app.runPage(data.pageCount);
                },
                error : function () {
                    alert("翻页失败，请联系管理员");
                }
            });
        },
		delmore : function() {
    		if (this.ids.length <= 0) {
    			alert("请选择要删除的数据");
			} else {
    			var idstr = this.ids.join(",");
    			this.del(idstr);
			}
		},
        del: function (ids) {
			if (app.e1 == 1) {
				if (confirm("是否要删除?")) {
					$.ajax({
						url : "/assets.ajax",
						type : "POST",
						data : {
							id : ids,
							str: 'del'
						},
						dataType : "JSON",
						success : function (code) {
							console.log(code.code);
							if (code.code == "delSuc") {
								alert("删除成功");
								location.reload();
							} else if (code.code == "delErr") {
								alert("删除失败");
							}
						},
						error : function () {
							alert("删除失败，请联系管理员");
						}
					});
				} else {
					alert("已取消删除");
				}
			} else {
				alert("不是保管员无法操作！");
			}
        },
		exist: function () {
			$.ajax({
				url : "/assets.ajax",
				type : "POST",
				data : {
					str: 'exist',
				},
				dataType : "JSON",
				success : function (code) {
					if (code.code == "notkep") {
							app.e1 = 0;
					}
					if (code.code == "iskep") {
							app.e1 = 1;
					}
				},
				error : function () {
					alert("验证失败，请联系管理员");
				}
			});
		},
		findfid : function (str) {
			if (this.financialId == '') {
				$(".inputfid").html("<strong style=\"color: red\">请输入财务编号</strong>");
				this.a3 = 0;
			} else {
				$.ajax({
					url : "/assets.ajax",
					type : "POST",
					data : {
						str: str,
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
		findpid : function (str) {
			if (this.productId == '') {
				$(".inputpid").html("<strong style=\"color: red\">请输入产品编号</strong>");
				this.a4 = 0;
			} else {
				$.ajax({
					url : "/assets.ajax",
					type : "POST",
					data : {
						str: str,
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
						alert("验证失败，请联系管理员");
					}
				});
			}
		},
		addMod: function () {
			if (app.e1 == 1) {
				$("#addform")[0].reset();
				$("#addasset").modal(
				    {
				        keyboard:false,
				        backdrop:"static"
				    }
				);
			} else {
				alert("不是保管员无法操作！");
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
				alert("请正确完成流程");
			} else {
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
				            alert("添加成功");
				            location.reload();
				        } else if(code.code == "addErr") {
				            alert("添加失败");
				        }
				    },
				    error : function () {
				        alert("添加失败，请联系管理员");
				    }
				});
			}
		},
		updt : function(li) {
			if (app.e1 == 1) {
				this.upid = li.id;
				this.upassetClass = li.assetClass;
				this.financeId = li.financeId;
				this.productId = li.productId;
				this.upstates = li.state();
				$("#updtasset").modal(
					{
						keyboard:false,
						backdrop:"static"
					}
				);
			} else {
				alert("不是保管员无法操作！");
			}
		},
		submit1 : function (str) {
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
				alert("请正确完成流程");
			} else {
				$.ajax({
					url : "/assets.ajax",
					type : "POST",
					data : {
						str: str,
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
							alert("修改成功");
							location.reload();
						} else if(code.code == "updtErr") {
							alert("修改失败");
						}
					},
					error : function () {
						alert("修改失败，请联系管理员");
					}
				});
			}
		}
	}
});
app.getData(1);
app.exist();