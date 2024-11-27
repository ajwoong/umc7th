package umc.study.service;

import umc.study.domain.Review;
import umc.study.domain.mapping.MemberMission;
import umc.study.web.dto.StoreRequestDTO;

public interface StoreCommandService {
    Review saveReview(StoreRequestDTO.StoreReviewDTO request, Long storeId);
    MemberMission addMissionProgress(StoreRequestDTO.AddMissionProgressDTO request, Long storeId, Long missionId);
}
