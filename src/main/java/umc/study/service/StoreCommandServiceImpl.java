package umc.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.ExceptionHandler;
import umc.study.converter.MemberMissionConverter;
import umc.study.converter.ReviewConverter;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.domain.mapping.MemberMission;
import umc.study.repository.MemberMissionRepository;
import umc.study.repository.MemberRepository;
import umc.study.repository.MissionRepository;
import umc.study.repository.StoreRepository;
import umc.study.web.dto.StoreRequestDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreCommandServiceImpl implements StoreCommandService{

    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    // 해당 가게에 멤버가 리뷰 작성하는 로직
    @Override
    @Transactional
    public Review saveReview(StoreRequestDTO.StoreReviewDTO request, Long storeId) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new ExceptionHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ExceptionHandler(ErrorStatus.STORE_NOT_FOUND));
        Review review = ReviewConverter.toReview(request, member, store);

        //해당 가게에 리뷰추가
        store.addReviewList(review);

        //해당 가게의 별점 최신화
        store.updateScoreByReviewScore(review.getScore());

        //해당 멤버의 리뷰추가
        member.addReviewList(review);
        return review;
    }

    // 멤버가 해당 가게의 미션을 본인 미션 리스트에 추가하는 로직(도전하는 로직)
    @Override
    @Transactional
    public MemberMission addMissionProgress(StoreRequestDTO.AddMissionProgressDTO request, Long storeId, Long missionId) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new ExceptionHandler(ErrorStatus.MISSION_NOT_FOUND));
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new ExceptionHandler(ErrorStatus.MEMBER_NOT_FOUND));

        MemberMission memberMissionProgress = MemberMissionConverter.toMemberMissionProgress(member, mission);

        List<MemberMission> memberMissionList = memberMissionRepository.findByMemberIdAndMissionId(member.getId(), mission.getId());
        if(!memberMissionList.isEmpty()){
            throw new ExceptionHandler(ErrorStatus.MISSION_ALREADY_CHALLENGING);
        }


        member.addMemberMissionList(memberMissionProgress);
        mission.addMemberMissionList(memberMissionProgress);

        return memberMissionProgress;
    }


}
