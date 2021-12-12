package com.codegym.trello.repository;

import com.codegym.trello.model.Workspace;
import com.codegym.trello.model.Workspace_Members;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {
    @Query(nativeQuery = true, value = "select * " +
            "from workspace w " +
            "where w.owner_id = ?1 " +
            "   or w.id in " +
            "      (select wm.workspace_id " +
            "       from workspace_members wm " +
            "                join member_workspace mw on wm.members_id = mw.id " +
            "       where mw.user_id = ?1)")
    Iterable<Workspace> findAllByOwnerId(Long id);

    @Query(nativeQuery = true,
            value = "select count(*) " +
                    "from workspace_boards wb " +
                    "where wb.boards_id = ?1")
    Integer isBoardInWorkspace(Long boardId);

    @Query(nativeQuery = true,
            value = "SELECT workspace_id as memberId FROM workspace_members where members_id = ?1")
    List<Workspace_Members> findAllBoardMember(Long userId);

    @Query(nativeQuery = true,
            value = "SELECT id as memberId FROM member_workspace inner join workspace_members on member_workspace.id = workspace_members.members_id where user_id = ?1")
    List<Workspace_Members> findAllBoardMember2(Long userId);

    @Query(nativeQuery = true,
            value = "SELECT workspace_id as memberId FROM workspace_boards where boards_id = ?1")
    Workspace_Members findWorkspaceByBoardId(Long userId);

}
