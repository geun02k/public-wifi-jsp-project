package com.jsp.project.publicwifiinfo.service;

import com.jsp.project.publicwifiinfo.model.Bookmark;

import java.util.List;

public interface BookmarkService {
    // 북마크목록 조회
    List<Bookmark> getBookmarkList();

    // 북마크 단건 조회
    Bookmark getBookmark(Bookmark bookmark);

    // 북마크 저장
    int saveBookmark(Bookmark bookmark);

    // 북마크그룹 삭제
    int deleteBookmark(Bookmark bookmark);
}
