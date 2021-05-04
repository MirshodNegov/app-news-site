package uz.pdp.appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appnewssite.entity.Post;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.PostDTO;
import uz.pdp.appnewssite.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public ApiResponse addPost(PostDTO postDTO) {
        boolean exists = postRepository.existsByTitle(postDTO.getTitle());
        if (exists)
            return new ApiResponse("Bunaqa nomli post mavjud !",false);
        Post post=new Post(postDTO.getTitle(), postDTO.getText(), postDTO.getUrl());
        postRepository.save(post);
        return new ApiResponse("Post qo'shildi",true);
    }

    public ApiResponse editPost(Long id, PostDTO postDTO) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (!optionalPost.isPresent())
            return new ApiResponse("Bunday id li post topilmadi !",false);
        Post post = optionalPost.get();
        post.setTitle(postDTO.getTitle());
        post.setText(postDTO.getText());
        post.setUrl(postDTO.getUrl());
        postRepository.save(post);
        return new ApiResponse("Post o'zgartirildi !",true);
    }

    public ApiResponse deletePost(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (!optionalPost.isPresent())
            return new ApiResponse("Bunday id li post topilmadi !",false);
        postRepository.deleteById(id);
        return new ApiResponse("Post deleted !",true);
    }

    public List<Post> getPosts() {
        return postRepository.findAll();
    }
}
