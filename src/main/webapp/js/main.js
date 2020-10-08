var app = new Vue({
	el : '#box',
	data : {
		work : '',
		name : '',
		staff : '管理员',
		asset : '保管员',
		mysrc : 'detail.html',
	},
	methods:{
		myif : function(url) {
			this.mysrc = url;
		},
		forwork: function() {
			$.ajax({
			    url : "/change",
			    type : "POST",
			    data : {
					str : 'work'
			    },
			    dataType : "JSON",
			    success : function (data) {
					app.work = data.work;
					app.name = data.name;
			    },
			    error : function () {
			        alert("职位获取失败，请联系管理员");
			    }
			});
		}
	}
});
app.forwork();

function mod1() {
    $("#mod").modal(
        {
            keyboard:false,
            backdrop:"static"
        }
    );
}

var cha = new Vue({
	el : '.modal',
	data : {
		f1 : 0,
		f2 : 0,
		f3 : 0,
		pwd1 : '',
		pwd2 : '',
		pwd3 : '',
	},
	methods:{
		inputpwd1 : function() {
			if (this.pwd1 == "") {
			    $("#pwd1").html("<strong style=\"color: red\">  不能为空</strong>");
			    this.f1 = 0;
			} else {
			    $.ajax({
			        url : "/change",
			        type : "POST",
			        data : {
			            password : this.pwd1,
						str : 'pwd'
			        },
			        dataType : "JSON",
			        success : function (data) {
			            if (data.code == "not") {
			                $("#pwd1").html("<strong style=\"color: red\">与原密码不相同</strong>");
			                cha.f1 = 0;
			            } else if(data.code == "same") {
			                $("#pwd1").html("<strong style=\"color: greenyellow\"> OK</strong>");
			                cha.f1 = 1;
			            }
			        },
			        error : function () {
			            alert("模态框出现异常");
			        }
			    })
			}
		},
		inputpwd2 : function() {
			if (this.pwd2 == "") {
			    $("#pwd2").html("<strong style=\"color: red\">  不能为空</strong>");
			    this.f2 = 0;
			}else if (this.pwd2 == this.pwd1) {
			    $("#pwd2").html("<strong style=\"color: red\">  不能与原密码相同</strong>");
			    this.f2 = 0;
			} else {
			    $("#pwd2").html("<strong style=\"color: greenyellow\">  OK</strong>");
			    this.f2 = 1;
			}
		},
		inputpwd3 : function() {
			if (this.pwd3 !== this.pwd2) {
			    $("#pwd3").html("<strong style=\"color: red\">  和第一次输入不相同</strong>");
			    this.f3 = 0;
			} else {
			    $("#pwd3").html("<strong style=\"color: greenyellow\">  OK</strong>");
			    this.f3 = 1;
			}
		},
		plan1 : function() {
			if (this.pwd1 == "" || this.pwd2 == "" || this.pwd3 == "") {
			    $("#end").html("<strong style=\"color: red\">  不能为空</strong>");
			} else if ((this.f1 + this.f2 + this.f3) !== 3) {
			    $("#end").html("<strong style=\"color: red\">  请填写正确的密码</strong>");
			} else if (this.pwd2 === this.pwd1) {
			    $("#end").html("<strong style=\"color: red\">  与原密码不能相同</strong>");
			} else if (this.pwd2 !== this.pwd3) {
			    $("#end").html("<strong style=\"color: red\">  两次密码不相同</strong>");
			} else {
				$.ajax({
				    url : "/change",
				    type : "POST",
				    data : {
				        newPwd : this.pwd2,
						str : 'change'
				    },
				    dataType : "JSON",
					success : function (data) {
					    if (data.code == "change") {
					        alert("修改成功, 请重新登录");
							location.reload();
					    } else if(data.code == "notchange") {
					        alert("修改失败");
					    }
					},
				    error : function () {
				        alert("修改失败, 请联系管理员");
				    }
				});
			}
		}
	}
});
