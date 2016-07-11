/**
 * Created by vliupro on 16-6-16.
 */

var follow_url = '';
var unfollow_url = '';

function follow_init(follow_url, unfollow_url) {
    this.follow_url = follow_url;
    this.unfollow_url = unfollow_url;
}


$('#foll').on('click', function () {
    var userId = $(this).data('uid');
    if ($(this).data('isf') == -1) {
        $.ajax({
            url: '/api/unfollow',
            data: {'userId': userId},
            success: function (data) {
                console.log(data);
                if (data['login_in'] === true && data['url_err'] === false) {
                    if (data['status'] == 1) {
                        $('#foll').val('添加关注');
                        $('#foll').data('isf', 1);
                    } else {
                        alert(data['error']);
                    }
                } else {
                    if (data['login_in'] === true && data['url_err'] === true) {
                        alert("地址错误");
                    } else if (data['login_in'] === false) {
                        alert("未登录不能关注哦～");
                    }
                }
            }
        });
    } else {
        $.ajax({
            url: '/api/follow',
            data: {userId: userId},
            success: function (data) {
                console.log(data);
                if (data['login_in'] === true && data['url_err'] === false) {
                    if (data['status'] == 1) {
                        $('#foll').val('取消关注');
                        $('#foll').data('isf', -1);
                    } else {
                        alert(data['error']);
                    }
                } else {
                    if (data['login_in'] === true && data['url_err'] === true) {
                        alert("地址错误");
                    } else if (data['login_in'] === false) {
                        alert("未登录不能关注哦～");
                    }
                }
            }
        });
    }

});