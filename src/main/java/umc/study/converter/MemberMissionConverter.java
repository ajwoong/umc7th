package umc.study.converter;

import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;
import umc.study.web.dto.StoreResponseDTO;

public class MemberMissionConverter {

    public static StoreResponseDTO.AddMissionProgressResultDTO toAddMissionProgressResultDTO(MemberMission membermission){
        return StoreResponseDTO.AddMissionProgressResultDTO
                .builder()
                .memberId(membermission.getMember().getId())
                .missionId(membermission.getMission().getId())
                .storeId(membermission.getMission().getStore().getId())
                .missionSpec(membermission.getMission().getMissionSpec())
                .reward(membermission.getMission().getReward())
                .deadline(membermission.getMission().getDeadline())
                .build();
    }

    public static MemberMission toMemberMissionProgress(Member member, Mission mission){

        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.CHALLENGING)
                .build();
    }

}
