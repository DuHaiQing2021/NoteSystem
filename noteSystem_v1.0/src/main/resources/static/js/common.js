//这个文件放一些公共代码
//加上一个逻辑，通过GET/Login这个接口来获取当前的登陆状态
function getUserInfo(pathName){
    jQuery.ajax({
        type:'get',
        url:'user/prove',
        success: function(body){
            //判断此处的body是不是一个有效的user对象（userId 是否为0）
            if(body.userId && body.userId > 0){
                //登录成功！
                // console.log("当前用户登录成功！用户名："+body.username);
                if(pathName == "blog_list"){
                    //在左边显示登录信息
                    setCardName(body.username);
                }
            }else{
                //登录失败！
                //让前端页面，跳转到 login.html
                alert("当前您尚未登录！请登录后再访问博客页面！");
                location.assign('blog_login.html');
            }
        },
        error: function(){
            location.assign('blog_login.html');
        }

    })
}


function setCardName(username){
    let h3=document.querySelector(".card h2");
    h3.innerHTML=username;
}