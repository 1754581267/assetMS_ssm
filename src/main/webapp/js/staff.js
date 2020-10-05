var app = new Vue({
    el : '#box',
    data : {
    	xPage : xPage,
        url: '',

        // 添加业务
		a1 : 0,
		a2 : 0,
		a3 : 0,
		a4 : 0,
		a5 : 0,
		a6 : 0,
		a7 : 0,
		allWorks : ['员工','管理员','保管员'],
		works: '',
		name: '',
		uname : '',
		pwd : '',
		power : '已锁定',
		sex: '',
		age: 0,
		phone: '',
        // 修改业务
        u1: 0,
        u2: 0,
        u3: 0,
        u4: 0,
        u5: 0,
        u6: 0,
        u7: 0,
		upid: '',
		upwork: '',
		upname: '',
		upuname: '',
		uppwd: '',
		uppower: '',
		upsex: '',
		upage: '',
		upphone: ''
	},
    methods: {
    	emMore : function() {
			if (xPage.ids.length <= 0 ) {
				layer.alert("请选择要解锁的数据");
			} else {
				var idstr = xPage.ids.join(",");
				app.empower(idstr);
			}
		},
		empower: function(ids) {
			if (xPage.work === 1) {
				$.ajax({
					url : "/staff.ajax",
					type : "POST",
					data : {
						str: 'empower',
						id: ids
					},
					dataType : "JSON",
					success : function (code) {
						console.log(code.code);
						if (code.code === "updtSuc") {
							layer.alert("已解锁");
							xPage.ids = [];
							xPage.getData(xPage.pageIndex);
						} else if(code.code == "updtErr") {
							layer.alert("解锁失败");
						}
					},
					error : function () {
						layer.alert("解锁失败，请联系管理员");
					}
				});
			} else {
				layer.alert("不是管理员，无法操作")
			}
		},
		reMore : function() {
			if (xPage.ids.length <= 0 ) {
				layer.alert("请选择要锁定的数据");
			} else {
				var idstr = xPage.ids.join(",");
				app.revoke(idstr);
			}
		},
		revoke: function(ids) {
			if (xPage.work == 1) {
				$.ajax({
					url : "/staff.ajax",
					type : "POST",
					data : {
						str: 'revoke',
						id: ids
					},
					dataType : "JSON",
					success : function (code) {
						console.log(code.code);
						if (code.code == "updtSuc") {
							layer.alert("已锁定");
							xPage.ids = [];
							xPage.getData(xPage.pageIndex);
						} else if(code.code == "myself") {
							layer.alert("你的账户被锁定");
							location.reload();
						} else if(code.code == "updtErr") {
							layer.alert("锁定失败");
						}
					},
					error : function () {
						layer.alert("锁定失败，请联系管理员");
					}
				});
			} else {
				layer.alert("不是管理员，无法操作")
			}
		},
        addMod: function () {
			if (xPage.work == 1) {
				$("#addform")[0].reset();
				// document.getElementById("addform").reset();
				$("#addStaff").modal(
					{
						keyboard:false,
						backdrop:"static"
					}
				);
			} else {
				layer.alert("不是管理员，无法操作")
			}
        },
		submit : function (str) {
			// 判断是否选择职位
            if (this.works == '') {
				$("#inputWork").html("<strong style=\"color: red\">请选择一个职位</strong>");
                this.a1 = 0;
			} else {
				$("#inputWork").html('');
				this.a1 = 1;
			}
			// 判断是否填写姓名
			if (this.name == '') {
				$("#inputName").html("<strong style=\"color: red\">请填写姓名</strong>");
                this.a2 = 0;
			} else {
				$("#inputName").html('');
				this.a2 = 1;
			}
			if (this.uname == '') {
				$("#inputUName").html("<strong style=\"color: red\">请输入用户名</strong>");
				this.a3 = 0;
			} else {
				$("#inputUName").html("");
				this.a3 = 1;
			}
			if (this.pwd == '') {
				$("#inputPwd").html("<strong style=\"color: red\">请输入密码</strong>");
				this.a4 = 0;
			} else {
				$("#inputPwd").html("");
				this.a4 = 1;
			}
			// 是否选择性别
			if (this.sex == '') {
				$("#inputSex").html("<strong style=\"color: red\">请选择性别</strong>");
                this.a5 = 0;
			} else {
				$("#inputSex").html('');
				this.a5 = 1;
			}
			// 判断年龄是否正确
			if (this.age == 17) {
				$("#inputAge").html("<strong style=\"color: red\">请输入正确的年龄</strong>");
                this.a6 = 0;
			} else {
				$("#inputAge").html('');
				this.a6 = 1;
			}
			// 判断手机号是否正确
			if (this.phone.length != 11) {
				$("#inputPhone").html("<strong style=\"color: red\">请输入正确的手机号码</strong>");
                this.a7 = 0;
			} else {
				$("#inputPhone").html('');
				this.a7 = 1;
			}
			if(this.a1 + this.a2 + this.a3 + this.a4 + this.a5 + this.a6 + this.a7 != 7) {
				layer.alert("请将信息填写正确");
			} else {
                $("#addStaff").modal('hide');
				$.ajax({
				    url : "/staff.ajax",
				    type : "POST",
				    data : {
				        str: str,
				        work: this.works,
						name: this.name,
						uname: this.uname,
						pwd: this.pwd,
						powers: this.power,
						sex: this.sex,
						age: this.age,
						phone: this.phone
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
				this.upwork = li.work;
				this.upname = li.name;
				this.upuname = li.uname;
				this.uppwd = li.pwd;
				this.uppower = li.powers;
				this.upsex = li.sex;
				this.upage = li.age;
				this.upphone = li.phone;
				$("#updStaff").modal(
					{
						keyboard:false,
						backdrop:"static"
					}
				);
			} else {
				layer.alert("不是管理员，无法操作")
			}
		},
		submit1 : function (str) {
			// 判断是否选择职位
			if (this.upwork == '') {
				$("#inputupWork").html("<strong style=\"color: red\">请选择一个职位</strong>");
			    this.u1 = 0;
			} else {
				$("#inputupWork").html('');
				this.u1 = 1;
			}
			// 判断是否填写姓名
			if (this.upname == '') {
				$("#inputupname").html("<strong style=\"color: red\">请填写姓名</strong>");
			    this.u2 = 0;
			} else {
				$("#inputupname").html('');
				this.u2 = 1;
			}
			if (this.upuname == '') {
				$("#inputUName").html("<strong style=\"color: red\">请输入用户名</strong>");
				this.u3 = 0;
			} else {
				$("#inputUName").html("");
				this.u3 = 1;
			}
			if (this.uppwd == '') {
				$("#inputupPwd").html("<strong style=\"color: red\">请输入密码</strong>");
				this.u4 = 0;
			} else {
				$("#inputupPwd").html("");
				this.u4 = 1;
			}
			// 是否选择性别
			if (this.upsex == '') {
				$("#inputupSex").html("<strong style=\"color: red\">请选择性别</strong>");
			    this.u5 = 0;
			} else {
				$("#inputupSex").html('');
				this.u5 = 1;
			}
			// 判断年龄是否正确
			if (this.upage == 17) {
				$("#inputupAge").html("<strong style=\"color: red\">请输入正确的年龄</strong>");
			    this.u6 = 0;
			} else {
				$("#inputupAge").html('');
				this.u6 = 1;
			}
			// 判断手机号是否正确
			if (this.upphone.length != 11) {
				$("inputupPhone").html("<strong style=\"color: red\">请输入正确的手机号码</strong>");
			    this.u7 = 0;
			} else {
				$("#inputupPhone").html('');
				this.u7 = 1;
			}
			if(this.u1 + this.u2 + this.u3 + this.u4 + this.u5 + this.u6 + this.u7 != 7) {
				layer.alert("请将信息填写正确");
			} else {
				$("#updStaff").modal('hide');
				$.ajax({
				    url : "/staff.ajax",
				    type : "POST",
				    data : {
				        str: str,
						id : this.upid,
						work: this.upwork,
						name: this.upname,
						uname: this.upuname,
						pwd: this.uppwd,
						powers: this.uppower,
						sex: this.upsex,
						age: this.upage,
						phone: this.upphone
				    },
				    dataType : "JSON",
				    success : function (code) {
			            console.log(code.code);
				        if (code.code == "uptSuc") {
				            layer.alert("修改成功");
							xPage.getData(xPage.pageIndex);
				        } else if(code.code == "uptErr") {
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
app.xPage.init("/staff.ajax", "管理员");
