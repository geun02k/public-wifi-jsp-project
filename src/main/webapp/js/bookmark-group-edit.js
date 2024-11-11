
window.onload = function() {
    $("#updateGroupBtn").on("click", updateGroup);
}

function updateGroup() {
    var groupCode = $("#groupCode").val();
    var groupNm = $("#groupName").val();
    var groupSeq = $("#groupSeq").val();

    // validation check
    if(groupCode == null || groupCode.trim() == '' || groupCode <= 0) {
        alert("잘못된 북마크그룹코드입니다.");
        return;
    }
    if(groupNm == null || groupNm.trim() == '') {
        alert("북마크 이름을 입력해주세요.");
        $("#groupName").focus();
        return;
    }
    if(groupSeq < 0 || groupSeq.trim() == '') {
        alert("순서를 입력해주세요.");
        $("#groupSeq").focus();
        return;
    }
    if($("#preGroupName").val() == groupNm && $("#preGroupSeq").val() == groupSeq) {
        alert("변경사항이 없습니다.");
        return;
    }

    var obj = {
        groupCode: groupCode,
        groupName: groupNm,
        groupSeq: groupSeq
    };

    $.ajax({
        type: "PUT",    // HttpMethod
        url: "/bookmarkGroup",  // 서버 URL
        contentType: "application/json; charset=utf-8",  // request 데이터타입
        dataType: "text",   // response 데이터타입 (실제 서버에서 전달하는 response 타입과 일치해야함.)
        async: true,        //동기,비동기 설정
        data: JSON.stringify(obj),  // requestBody : 서버에 보낼 데이터
        success: function (data, status, xhr) { //통신성공 후 처리로직
            if(data == "SUCCESS") {
                alert("수정되었습니다.");
                location.href = "/bookmark/bookmark-group.jsp";
            } else if(data == "FAIL_5") {
                alert("필수 입력값 미존재로 수정불가합니다.");
            } else if(data = "FAIL_10") {
                alert("순서 중복으로 수정 불가합니다.");
            } else {
                alert("수정에 실패했습니다.");
            }
        },
        error :function (xhr, data) { // 통신실패 후 처리로직
            alert("통신실패");
        }
    });
}