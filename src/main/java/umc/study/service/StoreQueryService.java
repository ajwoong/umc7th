package umc.study.service;

import org.springframework.data.domain.Page;
import umc.study.domain.Review;

public interface StoreQueryService {
    Page<Review> getReviewList(Long storeId, Integer page);
    Page<Review> getMyReviewList(Long storeId, Long memberId, Integer page);
}
