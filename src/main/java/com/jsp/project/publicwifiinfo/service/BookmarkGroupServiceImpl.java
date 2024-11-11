package com.jsp.project.publicwifiinfo.service;

import com.jsp.project.publicwifiinfo.model.BookmarkGroup;
import com.jsp.project.publicwifiinfo.repository.BookmarkGroupRepository;

import java.util.List;

public class BookmarkGroupServiceImpl implements BookmarkGroupService {
    BookmarkGroupRepository groupRepository = new BookmarkGroupRepository();

    @Override
    public List<BookmarkGroup> getGroupList() {
        return groupRepository.selectBookmarkGroupList();
    }

    @Override
    public int saveGroup(BookmarkGroup group) {
        return 0;
    }

    @Override
    public int updateGroup(BookmarkGroup group) {
        return 0;
    }

    @Override
    public int deleteGroup(BookmarkGroup group) {
        return 0;
    }
}
