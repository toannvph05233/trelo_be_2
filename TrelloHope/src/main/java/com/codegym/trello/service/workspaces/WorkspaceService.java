package com.codegym.trello.service.workspaces;

import com.codegym.trello.model.Workspace;
import com.codegym.trello.model.Workspace_Members;
import com.codegym.trello.service.GeneralService;

import java.util.List;

public interface WorkspaceService extends GeneralService<Workspace> {
    Iterable<Workspace> findAllByOwnerId (Long id);
    Boolean isBoardInWorkspace(Long boardId);
    List<Workspace_Members> findAllBoardMember(Long userId);
    Workspace_Members findWorkspaceByBoardId(Long userId);

}
