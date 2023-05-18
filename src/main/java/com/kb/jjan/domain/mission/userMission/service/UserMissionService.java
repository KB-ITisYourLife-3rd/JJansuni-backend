package com.kb.jjan.domain.mission.userMission.service;

import com.kb.jjan.domain.mission.mission.Mission;
import com.kb.jjan.domain.mission.mission.repository.MissionRepository;
import com.kb.jjan.domain.mission.userMission.UserMission;
import com.kb.jjan.domain.mission.userMission.dto.UserMissionRequest;
import com.kb.jjan.domain.mission.userMission.repository.UserMissionRepository;
import com.kb.jjan.domain.user.User;
import com.kb.jjan.domain.user.repository.UserRepository;
import com.kb.jjan.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class UserMissionService {

    private final UserMissionRepository userMissionRepository;
    private final MissionRepository missionRepository;
    private final UserRepository userRepository;
    private final UserService userService;


    public void registerUserMission(@RequestBody UserMissionRequest userMissionRequest) throws Exception {
        Mission solvedMission = missionRepository.getReferenceById(userMissionRequest.getSolvedMissionId());
        User solvedUser = userRepository.getReferenceById(userMissionRequest.getSolvedUserId());
        userService.updateUser(userMissionRequest.getSolvedUserId());

        UserMission userMission = userMissionRequest.toEntity(solvedMission, solvedUser);
        userMissionRepository.save(userMission);
    }


}
