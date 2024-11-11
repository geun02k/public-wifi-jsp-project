package com.jsp.project.publicwifiinfo.service;

import com.jsp.project.publicwifiinfo.model.BookmarkGroup;

import java.util.List;

public interface BookmarkGroupService {
    // 북마크그룹 조회
    List<BookmarkGroup> getGroupList();

    // 북마크그룹 생성
    int saveGroup(BookmarkGroup group);

    // 북마크그룹 수정
    int updateGroup(BookmarkGroup group);

    // 북마크그룹 삭제
    int deleteGroup(BookmarkGroup group);
}
