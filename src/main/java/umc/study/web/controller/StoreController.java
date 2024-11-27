package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.MemberMissionConverter;
import umc.study.converter.ReviewConverter;
import umc.study.domain.Review;
import umc.study.domain.mapping.MemberMission;
import umc.study.service.StoreCommandService;
import umc.study.web.dto.StoreRequestDTO;
import umc.study.web.dto.StoreResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

    private final StoreCommandService storeCommandService;

    @PostMapping("{storeId}/review")
    public ApiResponse<StoreResponseDTO.SaveReviewResultDTO> saveReview(@RequestBody @Valid StoreRequestDTO.StoreReviewDTO request,
                                                                        @PathVariable("storeId") Long storeId) {
        Review review = storeCommandService.saveReview(request, storeId);
        return ApiResponse.onSuccess(ReviewConverter.toSaveReviewResultDTO(review));
    }

    @PostMapping("/{storeId}/mission/{missionId}")
    public ApiResponse<StoreResponseDTO.AddMissionProgressResultDTO> addMissionProgress(@RequestBody @Valid StoreRequestDTO.AddMissionProgressDTO request,
                                                                                        @PathVariable("storeId") Long storeId,
                                                                                        @PathVariable("missionId") Long missionId){
        MemberMission memberMission = storeCommandService.addMissionProgress(request, storeId, missionId);
        return ApiResponse.onSuccess(MemberMissionConverter.toAddMissionProgressResultDTO(memberMission));
    }
}
