package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.MemberMissionConverter;
import umc.study.converter.ReviewConverter;
import umc.study.domain.Review;
import umc.study.domain.mapping.MemberMission;
import umc.study.service.StoreCommandService;
import umc.study.service.StoreQueryService;
import umc.study.web.dto.StoreRequestDTO;
import umc.study.web.dto.StoreResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

    private final StoreCommandService storeCommandService;
    private final StoreQueryService storeQueryService;

    @GetMapping("{storeId}/review")
    public ApiResponse<StoreResponseDTO.ReviewPreViewListDTO> getReviewList(@PathVariable("storeId") Long storeId,
                                                                            @RequestParam(name = "page") Integer page){
        Page<Review> reviewList = storeQueryService.getReviewList(storeId, page);
        return ApiResponse.onSuccess(ReviewConverter.reviewPreViewListDTO(reviewList));
    }

    @GetMapping("{storeId}/review/my")
    public ApiResponse<StoreResponseDTO.ReviewPreViewListDTO> getMyReviewList(@PathVariable("storeId") Long storeId,
                                                                              @RequestParam(name = "page") Integer page,
                                                                              @RequestParam(name = "memberId") Long memberId)
                                                                             {
        Page<Review> myReviewList = storeQueryService.getMyReviewList(storeId,memberId, page);
        return ApiResponse.onSuccess(ReviewConverter.reviewPreViewListDTO(myReviewList));
    }


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
