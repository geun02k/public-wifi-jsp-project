
window.onload = function() {
    //$("#deleteGroupBtn").on("click", deleteGroup);
}

function deleteGroup(groupCode) {
    console.log("deleteGroup() groupCode = " + groupCode);

    // validation check
    if(groupCode == null || groupCode.toString().trim() == '' || parseInt(groupCode) <= 0) {
        alert("잘못된 북마크그룹코드(ID)입니다.");
        return false;
    }

    var obj = {
        groupCode: groupCode
    };

    $.ajax({
        type: "DELETE",    // HttpMethod
        url: "/bookmarkGroup",  // 서버 URL
        contentType: "application/json; charset=utf-8",  // request 데이터타입
        dataType: "text",   // response 데이터타입 (실제 서버에서 전달하는 response 타입과 일치해야함.)
        async: true,        //동기,비동기 설정
        data: JSON.stringify(obj),  // requestBody : 서버에 보낼 데이터
        success: function (data, status, xhr) { //통신성공 후 처리로직
            if(data == "SUCCESS") {
                alert("삭제되었습니다.");
                //window.location.reload(); // 재조회를 위한 새로고침
                return true;
            } else if(data == "FAIL_5") {
                alert("필수 입력값 미존재로 삭제불가합니다.");
                return false;
            } else {
                alert("삭제에 실패했습니다.");
                return false;
            }
        },
        error :function (xhr, data) { // 통신실패 후 처리로직
            alert("통신실패");
            return false;
        }
    });
}