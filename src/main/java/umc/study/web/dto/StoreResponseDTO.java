package umc.study.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

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

}
