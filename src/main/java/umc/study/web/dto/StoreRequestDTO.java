package umc.study.web.dto;

import lombok.Getter;

public class StoreRequestDTO {

    @Getter
    public static class StoreReviewDTO{
        Long memberId;
        String title;
        Float score;
        String body;
    }

    @Getter
    public static class AddMissionProgressDTO{
        Long memberId;
    }

}
