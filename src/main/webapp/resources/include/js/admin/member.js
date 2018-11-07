/**
 * member.js
 */

$(function() {
	go_page(1);
});

//페이징 처리
function go_page(p) {
	console.log("p====="+p);
	document.frm.page.value = p;
	
    $.ajax({
        url:"./member.ajax"  ,
        data :$('#frm').serialize(),
        type: "GET",
        dataType: "json",
        success: callbackMember
   });
}
	
	//document.frm.submit();


function sort(s) {
	document.frm.sort.value = s;
	document.frm.submit();
}

function callbackMember(datas){
	console.log(datas);
	var list = datas.list;
	var paging = datas.paging;
	var html="";
	
	$.each(list,function(idx,data){
		html += ("<tr class='text-center'>"+
				   "<td scope='row'>"+
				   "<label class='custom-control custom-checkbox'>"+
				   "<input type='checkbox' name='_selected_' value='"+data.memberNo+"' class='custom-control-input'>"+
				   "<span class='custom-control-indicator'></span>"+
				   "</label>"+
				   "</td>"+
				   "<td>"+data.memberNo+"</td>"+
				   "<td>"+data.memberName+"</td>"+
				   "<td>"+data.memberNick+"</td>"+
				   "<td>"+data.memberEmail+"</td>"+
				   "<td>"+data.memberPoint+"</td>"+
				   "<td>"+
				   "<fmt:parseDate value='"+data.signupDate+"' var='signupDate_D' pattern='yyyy-MM-dd HH:mm:ss'/>"+
				   "<fmt:formatDate value='${signupDate_D}' var='signupDate_FD' pattern='yyyy.MM.dd'/>"+
				   "${signupDate_FD}"+
				   "</td>"+
				   "<td>"+
				   "<div class='btn-group'>"+
				   "<a href='#' class='btn btn-outline-success btn-sm'>수정</a>"+
				   "<a href='#' class='btn btn-outline-danger btn-sm'>삭제</a>"+
				   "</div>"+
				   "</td>"+
				   "</tr>");
	});
	
	
	var temp;
	var page = "<ul class='pagination justify-content-center'>" +
				"<li class='page-item'>이전";
	
	for(var i=paging.startPage; i<=paging.endPage; i++){
		if(i != paging.page){
			temp = "<li class='page-item'><a class='page-link' href='#' onclick='go_page("+i+")'>"+i+"</a>"
		}
		else{
			temp = "<li class='page-item active'><a class='page-link' href='#' onclick='go_page("+i+")'>"+i+"</a>"
		}
		page += temp;
	}
	
	page += "<li class='page-item'>다음";
	page += "</ul>";
	
	//$('#membertbody').html("");
	//$('#memberPaging').html("");

	$('#membertbody').empty();
	$('#memberPaging').empty();
	
	$('#membertbody').append(html);
	$('#memberPaging').append(page);
	
}