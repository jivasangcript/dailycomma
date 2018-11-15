/*좋아요 처리*/
function likeOn(data) {
    $('.likey').find('span').attr("class","a1");
    $('.bt_detail').find('span').attr("class","a1");

    $('[name=jsonLikeCount]').text(data.totalLike);
}
function likeOff(data) {
    $('.likey').find('span').attr("class","a0");
    $('.bt_detail').find('span').attr("class","a0");

    $('[name=jsonLikeCount]').text(data.totalLike);
}
$(document).on('click', '.likey', function () {
    var tourId = $(this).attr('id');
    var liId = $(this).find('span').attr('class');

    if(liId == 'a0'){
        $.ajax({
            url: path + "detailCastInsert/" + tourId,
            dataType: "json",
            error: function (data, status, msg) {
                //alert("상태값 :" + status + " Http에러메시지 :"+msg);
            },
            success:likeOn
        })
    }else if(liId == 'a1'){
        $.ajax({
            url: path + "detailCastDelete/" + tourId,
            dataType: "json",
            error: function (data, status, msg) {
                //alert("상태값 :" + status + " Http에러메시지 :"+msg);
            },
            success:likeOff
        })
    }
})

/*캐스트 상세 덧글 처리*/
function detailCastPostList(data){
    var html = "";

    console.log(data);

    $('.postContents').remove();

    $.each(data,function(idx,item) {
        html +=
        "<div class='con_post bord_b postContents'>"+
            "<div class='count'>"+
                "<span class='day'>"+item.commentDate+" "+item.cmemberNick+"</span>"+
            "<div class='content_post'>"+
                "<span style='color: #454545'>내용 :"+item.commentContent+"</span>"+
            "</div>"+
            "</div>"+
            "</div>";
    })
    $('.addPostList').append(html);
}
/*캐스트 상세 페이지 버튼*/
function detailCastPostPage(data){
    console.log(data);
    console.log(data.totalPage);

    var idx = data.totalPage;
    var html = "";

    for(var i = 1 ;i <= idx ; i++){
        html += '<li class="page-item"><a class="page-link" onclick="postContent('+ i +')">'+ i +'</a></li>';
    }
    $('.paging').after(html);


}
/*덧글 리스트 호출*/
function postContent(paging){
    var tourId = $('.likey').attr('id');
    console.log(tourId);

    $.ajax({
        url: path + "detailCastPostList/" + tourId + "/" + paging,
        dataType: "json",
        error: function (data, status, msg) {
            //alert("상태값 :" + status + " Http에러메시지 :"+msg);
        },
        success:detailCastPostList
    })
}

/*덧글 페이지 버튼*/
function postButton(){
    $.ajax({
        url: path + "detailCastPostPage",
        dataType: "json",
        error: function (data, status, msg) {
            //alert("상태값 :" + status + " Http에러메시지 :"+msg);
        },
        success:detailCastPostPage
    })
}
/*페이지가 로드되면 ajax 호출*/
$(function () {
    var paging = 1;
    postContent(paging);
    postButton();
})
