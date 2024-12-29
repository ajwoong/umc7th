package umc.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.ExceptionHandler;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.repository.MemberRepository;
import umc.study.repository.ReviewRepository;
import umc.study.repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class StoreQueryServiceImpl implements StoreQueryService {

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    @Override
    public Page<Review> getReviewList(Long storeId, Integer page) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ExceptionHandler(ErrorStatus.STORE_NOT_FOUND));

        return reviewRepository.findAllByStore(store, PageRequest.of(page, 10));
    }

    @Override
    public Page<Review> getMyReviewList(Long storeId, Long memberId, Integer page) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ExceptionHandler(ErrorStatus.STORE_NOT_FOUND));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ExceptionHandler(ErrorStatus.MEMBER_NOT_FOUND));

        return reviewRepository.findAllByStoreAndMember(store, member, PageRequest.of(page, 10));
    }
}
