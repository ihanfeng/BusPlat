var comment_url = '';

function comment_init(comment_url) {
    this.comment_url = comment_url;
}

var comment = $('.comment-a');

/* 控制评论框是否显示 */
comment.on('click', function () {
    // console.log(($(this).parent().parent().parent().parent().siblings('div.handle_comt')).text());
    //.find('.comt_list .repeat_list')
    if ($(this).parent().parent().parent().parent().siblings('div.handle_comt').css('display') == 'none') {
        // console.log('true');
        $(this).parent().parent().parent().parent().siblings('div.handle_comt').fadeIn(200);
    } else {
        $(this).parent().parent().parent().parent().siblings('div.handle_comt').fadeOut(200, function () {
            // $(this).removeAttr('style');
        })
    }
});
/* 控制评论框是否显示 end */

$('.p_opt_btn').on('click', function () {
    var weiboId = $(this).parent().parent().data('wid');
    var content = $(this).parent().siblings('div.p_input').find('textarea').val();
    $.post({
        url: comment_url,
        data: {
            content: content,
            weiboId: weiboId
        },
        success:function (data) {
            console.log(data);
            if (data['login_in'] === true && data['url_err'] === false) {
                // console.log("data['login_in'] === true: " + (data['login_in'] === true));
                if (data['status'] === 1) {
                    window.location.reload(true);
                } else {
                    alert("评论失败");
                }
            } else {
                if (data['login_in'] === true && data['url_err'] === true) {
                    alert("地址错误");
                } else if (data['login_in'] === false) {
                    alert("未登录不能评论哦～");
                }
            }
        }
    });
});