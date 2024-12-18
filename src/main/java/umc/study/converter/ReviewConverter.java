package umc.study.converter;

import org.springframework.data.domain.Page;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.web.dto.StoreRequestDTO;
import umc.study.web.dto.StoreResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {


    public static StoreResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review){
        return StoreResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }
    public static StoreResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList){

        List<StoreResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(ReviewConverter::reviewPreViewDTO).collect(Collectors.toList());

        return StoreResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }

    public static StoreResponseDTO.SaveReviewResultDTO toSaveReviewResultDTO(Review review){
        return StoreResponseDTO.SaveReviewResultDTO.builder()
                .storeId(review.getStore().getId())
                .memberId(review.getMember().getId())
                .title(review.getTitle())
                .body(review.getBody())
                .score(review.getScore())
                .build();
    }

    public static Review toReview(StoreRequestDTO.StoreReviewDTO request, Member member, Store store){

        return Review.builder()
                .store(store)
                .member(member)
                .body(request.getBody())
                .title(request.getTitle())
                .score(request.getScore()).build();
    }

}
