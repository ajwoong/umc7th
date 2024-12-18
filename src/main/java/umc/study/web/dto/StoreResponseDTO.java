package umc.study.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

public class StoreResponseDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class SaveReviewResultDTO {
        Long memberId;
        Long storeId;
        String title;
        Float score;
        String body;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class AddMissionProgressResultDTO{
        Long memberId;
        Long storeId;
        Long missionId;
        Integer reward;
        LocalDate deadline;
        String missionSpec;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class ReviewPreViewListDTO {
        List<ReviewPreViewDTO> reviewList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class ReviewPreViewDTO {
        String ownerNickname;
        Float score;
        String body;
        LocalDate createdAt;
    }

}
