package com.codegym.trello.controller;

import com.codegym.trello.model.Workspace;
import com.codegym.trello.model.Workspace_Members;
import com.codegym.trello.service.workspaces.WorkspaceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/workspaces")
public class WorkspaceController {
    @Autowired
    private WorkspaceServiceImpl workspacesService;

    @GetMapping("")
    public ResponseEntity<Iterable<Workspace>> findAll() {
        return new ResponseEntity<>(workspacesService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/owner/{id}")
    public ResponseEntity<Iterable<Workspace>> findAllByOwnerId(@PathVariable Long id) {
        Iterable<Workspace> workspaces = workspacesService.findAllByOwnerId(id);
        if (workspaces == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(workspacesService.findAllByOwnerId(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Workspace> save(@RequestBody Workspace workspaces) {
        return new ResponseEntity<>(workspacesService.save(workspaces), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Workspace>> findById(@PathVariable Long id) {
        Optional<Workspace> workspacesOptional = workspacesService.findById(id);
        if (!workspacesOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(workspacesOptional, HttpStatus.OK);
    }

    @GetMapping("/getAllBoardMember/{id}")
    public List<Long> getAllBoardMember(@PathVariable Long id) {
        List<Workspace_Members> workspaceMembersList = workspacesService.findAllBoardMember(id);
        List<Workspace_Members> workspaceMembersList2 = workspacesService.findAllBoardMember2(id);
        List<Long> list = new ArrayList<>();

        for (Workspace_Members wm : workspaceMembersList) {
            list.add(wm.getMemberId());
        }

        for (Workspace_Members wm : workspaceMembersList2) {
            list.add(wm.getMemberId());
        }

        return list;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Workspace> updateById(@PathVariable Long id, @RequestBody Workspace workspace) {
        Optional<Workspace> workspacesOptional = workspacesService.findById(id);
        if (!workspacesOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        workspace.setId(workspacesOptional.get().getId());
        workspacesService.save(workspace);
        return new ResponseEntity<>(workspace, HttpStatus.OK);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Workspace> deleteById(@PathVariable Long id) {
        Optional<Workspace> workspacesOptional = workspacesService.findById(id);
        if (!workspacesOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        workspacesService.deleteById(workspacesOptional.get().getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
