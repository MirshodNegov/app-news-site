package uz.pdp.appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.appnewssite.entity.Comment;
import uz.pdp.appnewssite.entity.Post;
import uz.pdp.appnewssite.entity.User;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.CommentDTO;
import uz.pdp.appnewssite.repository.CommentRepository;
import uz.pdp.appnewssite.repository.PostRepository;

import java.util.List;
import java.util.Optional;


@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;

    public ApiResponse addComment(CommentDTO commentDTO) {
        Optional<Post> optionalPost = postRepository.findById(commentDTO.getPostId());
        if (!optionalPost.isPresent())
            return new ApiResponse("Post id si xato", false);
        Post post = optionalPost.get();
        Comment comment = new Comment(commentDTO.getText(), post);
        commentRepository.save(comment);
        return new ApiResponse("Comment saqlandi", true);
    }

    public ApiResponse editComment(Long id, String text) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (!optionalComment.isPresent())
            return new ApiResponse("Comment topilmadi !", false);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Comment comment = optionalComment.get();
        User createdBy = comment.getCreatedBy();
        if (user.getId() != createdBy.getId())
            return new ApiResponse("Comment sizga tegishli emas !", false);
        comment.setText(text);
        commentRepository.save(comment);
        return new ApiResponse("Comment o'zgartirildi !", true);
    }

    public ApiResponse deleteComment(Long id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (!optionalComment.isPresent())
            return new ApiResponse("Comment topilmadi !", false);
        commentRepository.deleteById(id);
        return new ApiResponse("Comment o'chirildi", true);
    }

    public ApiResponse deleteMyComment(Long id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (!optionalComment.isPresent())
            return new ApiResponse("Comment topilmadi !", false);
        Comment comment = optionalComment.get();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.getId() != comment.getCreatedBy().getId())
            return new ApiResponse("Comment sizga tegishli emas !", false);
        commentRepository.deleteById(id);
        return new ApiResponse("Sizning Comment o'chirildi", true);
    }

    public List<Comment> getComments() {
        return commentRepository.findAll();
    }
}
