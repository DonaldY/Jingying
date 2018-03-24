'use strict';

var _common = {
    // ��������
    request : function(param){
        var _this = this;
        $.ajax({
            type        : param.method  || 'get',
            url         : param.url     || '',
            dataType    : param.type    || 'json',
            data        : param.data    || '',
            success : function(res){
                // ����ɹ�
                if(0 === res.status){
                    typeof param.success === 'function' && param.success(res.data, res.msg);
                } else if(10 === res.status){  // û�е�¼״̬����Ҫǿ�Ƶ�¼
                    _this.doLogin();
                } else if(1 === res.status){   // �������ݴ���
                    typeof param.error === 'function' && param.error(res.msg);
                }
            },
            error : function(err){
                typeof param.error === 'function' && param.error(err.statusText);
            }
        });
    },
    
    // �ɹ���ʾ
    successTips : function(msg){
        alert(msg || '�����ɹ���');
    },
    
    // ������ʾ
    errorTips : function(msg){
        alert(msg || '���ﲻ����~');
    },
    
    // �ֶε���֤��֧�ַǿա��ֻ���������ж�
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
    // ͳһ��¼����
    doLogin : function(){
        window.location.href = './user-login.html?redirect=' + encodeURIComponent(window.location.href);
    },
    goHome : function(){
        window.location.href = './index.html';
    }
};

module.exports = _common;
