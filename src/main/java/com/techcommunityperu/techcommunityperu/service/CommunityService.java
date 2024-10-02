package com.techcommunityperu.techcommunityperu.service;

import com.techcommunityperu.techcommunityperu.dto.CommunityDTO;

import java.util.List;

public interface CommunityService {
    CommunityDTO createCommunity(CommunityDTO communityDTO, Long userId);
    CommunityDTO updateCommunity(CommunityDTO communityDTO, Long communityId);
    void deleteCommunity(Long communityId);
    List<CommunityDTO> getAllCommunities();
}


