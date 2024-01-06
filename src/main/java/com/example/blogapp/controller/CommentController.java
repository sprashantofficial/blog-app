package com.example.blogapp.controller;

import com.example.blogapp.bean.Comment;
import com.example.blogapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/posts/{id}/addComment")
    public ResponseEntity<String> addComment(@RequestBody Comment comment, @PathVariable Long id) {
        Comment response = commentService.addComment(comment, id);

        return new ResponseEntity<>("Comment created successfully. Id -> " + response.getId(), HttpStatus.CREATED);
    }

    @GetMapping("/comment/{id}")
    public ResponseEntity<Comment> getCommentByCommentId(@PathVariable Long id) {
        Comment comment = commentService.getCommentByCommentId(id);

        return ResponseEntity.ok(comment);
    }

    @GetMapping("/post/{id}/getComments")
    public List<Comment> getCommentByPostId(@PathVariable Long id) {
        return commentService.getCommentByPostId(id);
    }

    @PutMapping("/post/{postId}/comment/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable Long postId, @PathVariable Long commentId
            , @RequestBody Comment comment) {
        commentService.updateCommentByCommentId(commentId, postId, comment);

        return new ResponseEntity<>("Comment updated successfully.", HttpStatus.OK);
    }

    @DeleteMapping("/deleteComment/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id) {
        commentService.deleteCommentByCommentId(id);

        return new ResponseEntity<>("Comment deleted successfully.", HttpStatus.OK);
    }

}
