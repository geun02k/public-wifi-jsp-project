window.onload = function() {
    $("#addGroupBtn").on("click", saveGroup);
}

function saveGroup() {
    var groupNm = $("#groupName").val();
    var groupSeq = $("#groupSeq").val();
    console.log("groupSeq=" + groupSeq);
    // validation check
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

    var obj = {
        groupName: groupNm,
        groupSeq: groupSeq
    };

    $.ajax({
        type: "POST",    // HttpMethod
        url: "/bookmarkGroup",  // 서버 URL
        contentType: "application/json; charset=utf-8",  // request 데이터타입
        dataType: "text",   // response 데이터타입 (실제 서버에서 전달하는 response 타입과 일치해야함.)
        async: true,        //동기,비동기 설정
        data: JSON.stringify(obj),  // requestBody : 서버에 보낼 데이터
        success: function (data, status, xhr) { //통신성공 후 처리로직
            if(data == "SUCCESS") {
                alert("저장되었습니다.");
                window.location.reload(); // 재조회를 위한 새로고침
            } else if(data == "FAIL_5") {
                alert("필수 입력값 미존재로 저장에 실패했습니다.");
            } else if(data = "FAIL_10") {
                alert("순서 중복으로 저장에 실패했습니다.");
            } else {
                alert("저장에 실패했습니다.");
            }
        },
        error :function (xhr, data) { // 통신실패 후 처리로직
            alert("통신실패");
        }
    });
}