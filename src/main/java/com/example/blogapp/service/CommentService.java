package com.example.blogapp.service;

import com.example.blogapp.bean.Comment;
import com.example.blogapp.bean.Post;
import com.example.blogapp.dao.CommentDAO;
import com.example.blogapp.dao.PostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private PostDAO postDAO;

    @Autowired
    private CommentDAO commentDAO;

    public Comment addComment(Comment comment, Long postId) {
        Post post = postDAO.findById(postId)
                .orElseThrow(() -> new RuntimeException(postId + " -> This post id doesn't exists"));
        comment.setPost(post);

        return commentDAO.save(comment);
    }

    public Comment getCommentByCommentId(Long id) {
        return commentDAO.findById(id)
                .orElseThrow(() -> new RuntimeException(id + " -> This id doesn't exists"));
    }

    public List<Comment> getCommentByPostId(Long postId) {
        return commentDAO.findByPostId(postId);
    }

    public void updateCommentByCommentId(Long commentId, Long postId, Comment comment) {
        Post post = postDAO.findById(postId)
                .orElseThrow(() -> new RuntimeException(postId + " -> This post id doesn't exists"));

        comment.setId(commentId);
        comment.setPost(post);

        commentDAO.save(comment);
    }

    public void deleteCommentByCommentId(Long id) {
        if(commentDAO.findById(id).isPresent()) {
            commentDAO.deleteById(id);
        } else {
            throw new RuntimeException(id + " -> This id doesn't exists");
        }
    }

}
