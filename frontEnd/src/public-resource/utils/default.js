'use strict';

var _common = {
    // 网络请求
    request : function(param){
        var _this = this;
        $.ajax({
            type        : param.method  || 'get',
            url         : param.url     || '',
            dataType    : param.type    || 'json',
            data        : param.data    || '',
            success : function(res){
                // 请求成功
                if(0 === res.status){
                    typeof param.success === 'function' && param.success(res.data, res.msg);
                } else if(10 === res.status){  // 没有登录状态，需要强制登录
                    _this.doLogin();
                } else if(1 === res.status){   // 请求数据错误
                    typeof param.error === 'function' && param.error(res.msg);
                }
            },
            error : function(err){
                typeof param.error === 'function' && param.error(err.statusText);
            }
        });
    },
    
    // 成功提示
    successTips : function(msg){
        alert(msg || '操作成功！');
    },
    
    // 错误提示
    errorTips : function(msg){
        alert(msg || '哪里不对了~');
    },
    
    // 字段的验证，支持非空、手机、邮箱的判断
    validate : function(value, type){
        value = $.trim(value);
      
        if('require' === type){
            return !!value;
        }
      
        if('phone' === type){
            return /^1\d{10}$/.test(value);
        }
      
        if('email' === type){
            return /^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/.test(value);
        }
	return null;
    },
    // 统一登录处理
    doLogin : function(){
        window.location.href = './user-login.html?redirect=' + encodeURIComponent(window.location.href);
    },
    goHome : function(){
        window.location.href = './index.html';
    }
};

module.exports = _common;
