var app = new Vue({
    el : '#box',
    data : {
        xPage : xPage,
		a1 : 0,
        // 添加业务
		p1 : 0,
		p2 : 0,
		p3 : 0,
		p4 : 0,
		p5 : 0,
		name : '',
		spft : '',
		unit : '',
		number : '',
		unitPrice : '',
		
		// 更改业务
		u1 : 0,
		u2 : 0,
		u3 : 0,
		u4 : 0,
		u5 : 0,
		upid : '',
		upname : '',
		upspft : '',
		upunit : '',
		upnumber : '',
		upunitPrice : ''
	},
    methods: {
		addMod: function () {
			if (xPage.work == 1) {
				$("#addform")[0].reset();
				$("#addprod").modal(
				    {
				        keyboard:false,
				        backdrop:"static"
				    }
				);
			} else {
				layer.alert("不是保管员，无法操作");
			}
		},
		submit : function() {
            if (this.name == '') {
				$("#inputname").html("<strong style=\"color: red\">请输入产品名称</strong>");
                this.p1 = 0;
			} else {
				$("#inputname").html('');
				this.p1 = 1;
			}
			if (this.spft == '') {
				$("#inputspft").html("<strong style=\"color: red\">请输入产品规格</strong>");
				this.p2 = 0;
			} else {
				$("#inputspft").html('');
				this.p2 = 1;
			}
			if (this.unit == '') {
				$("#inputunit").html("<strong style=\"color: red\">请输入产品单位</strong>");
				this.p3 = 0;
			} else {
				$("#inputunit").html('');
				this.p3 = 1;
			}
			if (this.number == '') {
				$("#inputnumber").html("<strong style=\"color: red\">请输入产品数量</strong>");
				this.p4 = 0;
			} else {
				$("#inputnumber").html('');
				this.p4 = 1;
			}
			if (this.unitPrice == '') {
				$("#inputunitPrice").html("<strong style=\"color: red\">请输入产品单价</strong>");
				this.p5 = 0;
			} else {
				$("#inputunitPrice").html('');
				this.p5 = 1;
			}
			if(this.p1 + this.p2 + this.p3 + this.p4 + this.p5 != 5) {
				alert("请正确完成流程");
			} else {
				$("#addprod").modal('hide');
				$.ajax({
				    url : "/product.ajax",
				    type : "POST",
				    data : {
						name : this.name,
						specification : this.spft,
						unit : this.unit,
						number : this.number,
						unitPrice : this.unitPrice,
						str : 'add'
				    },
				    dataType : "JSON",
				    success : function (code) {
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
				this.upname = li.name;
				this.upspft = li.specification;
				this.upunit = li.unit;
				this.upnumber = li.number;
				this.upunitPrice = li.unitPrice;
				$("#updtprodt").modal(
					{
						keyboard:false,
						backdrop:"static"
					}
				);
			} else {
				layer.alert("不是保管员，无法操作");
			}
		},
		submit1 : function () {
			if (this.upname == '') {
				$("#inputupname").html("<strong style=\"color: red\">请输入产品名称</strong>");
				this.p1 = 0;
			} else {
				$("#inputupname").html('');
				this.u1 = 1;
			}
			if (this.upspft == '') {
				$("#inputupspft").html("<strong style=\"color: red\">请输入产品规格</strong>");
				this.u2 = 0;
			} else {
				$("#inputupspft").html('');
				this.u2 = 1;
			}
			if (this.upunit == '') {
				$("#inputupunit").html("<strong style=\"color: red\">请输入产品单位</strong>");
				this.u3 = 0;
			} else {
				$("#inputupunit").html('');
				this.u3 = 1;
			}
			if (this.upnumber == '') {
				$("#inputupnumber").html("<strong style=\"color: red\">请输入产品数量</strong>");
				this.u4 = 0;
			} else {
				$("#inputupnumber").html('');
				this.u4 = 1;
			}
			if (this.upunitPrice == '') {
				$("#inputupunitPrice").html("<strong style=\"color: red\">请输入产品单价</strong>");
				this.u5 = 0;
			} else {
				$("#inputupunitPrice").html('');
				this.u5 = 1;
			}
			if(this.u1 + this.u2 + this.u3 + this.u4 + this.u5 != 5) {
				alert("请正确完成流程");
			} else {
				$("#updtprodt").modal('hide');
				$.ajax({
					url : "/product.ajax",
					type : "POST",
					data : {
						str: 'updt',
						id : this.upid,
						name : this.upname,
						specification : this.upspft,
						unit : this.upunit,
						number : this.upnumber,
						unitPrice : this.upunitPrice,
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
xPage.init("/product.ajax", "保管员");