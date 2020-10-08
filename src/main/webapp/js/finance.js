var app = new Vue({
    el : '#box',
    data : {
    	xPage : xPage,
        // 添加业务
		a1 : 0,
		a2 : 0,
		a3 : 0,
		proof : '',
		state : '',
		
		// 更改业务
		u1 : 0,
		upid : '',
		upstate : ''
	},
    methods: {
        addMod: function () {
			if (xPage.work == 1) {
				$("#addform")[0].reset();
				$("#addfina").modal(
				    {
				        keyboard:false,
				        backdrop:"static"
				    }
				);
			} else {
				layer.alert("不是管理员无法操作");
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
			// 判断是否成凭证号
            if (this.proof == '请生成凭证号') {
				$("#prof").html("<strong style=\"color: red\">请生成一个凭证号</strong>");
                this.a1 = 0;
			} else {
				$("#inputWork").html('');
				this.a1 = 1;
			}
			if (this.state == '') {
				$("#sta").html("<strong style=\"color: red\">请选择</strong>");
				this.a2 = 0;
			} else {
				$("#sta").html('');
				this.a2 = 1;
			}
			if(this.a1 + this.a2 != 2) {
				alert("请正确完成流程");
			} else {
				$("#addfina").modal('hide');
				$.ajax({
				    url : "/finance.ajax",
				    type : "POST",
				    data : {
				        str: "add",
						proof: this.proof,
						state: this.state
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
				this.upstate = li.state;
				$("#updtfina").modal(
					{
						keyboard:false,
						backdrop:"static"
					}
				);
			} else {
				layer.alert("不是管理员无法操作");
			}
		},
		submit1 : function () {
			// 判断是否选择职位
			if (this.upstate == '') {
				$("#upsta").html("<strong style=\"color: red\">请选择</strong>");
			    this.u1 = 0;
			} else {
				$("#upsta").html('');
				this.u1 = 1;
			}
			if(this.u1 != 1) {
				alert("请将信息填写正确");
			} else {
				$("#updtfina").modal('hide');
				$.ajax({
				    url : "/finance.ajax",
				    type : "POST",
				    data : {
				        str: 'updt',
						id : this.upid,
						state: this.upstate
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
						layer.alert("添加失败，请联系管理员");
				    }
				});
			}
		}
	}
});
xPage.init("/finance.ajax", "保管员");