package umc.study.converter;

import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.web.dto.StoreRequestDTO;
import umc.study.web.dto.StoreResponseDTO;

public class ReviewConverter {


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
