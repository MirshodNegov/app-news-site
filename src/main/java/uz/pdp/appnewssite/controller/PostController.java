package uz.pdp.appnewssite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appnewssite.entity.Post;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.PostDTO;
import uz.pdp.appnewssite.service.PostService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    PostService postService;

    @PreAuthorize(value = "hasAnyAuthority('ADD_POST')")
    @PostMapping("/add")
    public HttpEntity<?> addPost(@Valid @RequestBody PostDTO postDTO) {
        ApiResponse apiResponse = postService.addPost(postDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyAuthority('EDIT_POST')")
    @PutMapping("/edit/{id}")
    public HttpEntity<?> editPost(@Valid @RequestBody PostDTO postDTO,@PathVariable Long id) {
        ApiResponse apiResponse = postService.editPost(id,postDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyAuthority('DELETE_POST')")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deletePost(@PathVariable Long id) {
        ApiResponse apiResponse = postService.deletePost(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping()
    public HttpEntity<?> getPosts() {
        List<Post> postList = postService.getPosts();
        return ResponseEntity.ok(postList);
    }
}
