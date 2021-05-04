package uz.pdp.appnewssite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appnewssite.entity.Comment;
import uz.pdp.appnewssite.entity.Post;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.CommentDTO;
import uz.pdp.appnewssite.payload.PostDTO;
import uz.pdp.appnewssite.service.CommentService;
import uz.pdp.appnewssite.service.PostService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/add")
    public HttpEntity<?> addComment(@Valid @RequestBody CommentDTO commentDTO) {
        ApiResponse apiResponse = commentService.addComment(commentDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/edit/{id}")
    public HttpEntity<?> editComment(@Valid @RequestBody String text,@PathVariable Long id) {
        ApiResponse apiResponse = commentService.editComment(id,text);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyAuthority('DELETE_COMMENT')")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteComment(@PathVariable Long id) {
        ApiResponse apiResponse = commentService.deleteComment(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/deleteMyComment/{id}")
    public HttpEntity<?> deleteMyComment(@PathVariable Long id) {
        ApiResponse apiResponse = commentService.deleteMyComment(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping()
    public HttpEntity<?> getComments() {
        List<Comment> comments = commentService.getComments();
        return ResponseEntity.ok(comments);
    }
}
