package com.example.BusBookingApp.service;

import com.example.BusBookingApp.dto.CreateUserRequest;
import com.example.BusBookingApp.dto.PageResponse;
import com.example.BusBookingApp.dto.UserResponse;
import com.example.BusBookingApp.entity.UserInfo;
import com.example.BusBookingApp.entity.UserRole;
import com.example.BusBookingApp.mapper.UserInfoMapper;
import com.example.BusBookingApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserInfoMapper userInfoMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserInfoMapper userInfoMapper)
    {
        this.userRepository = userRepository;
        this.userInfoMapper = userInfoMapper;
    }

    @Transactional(readOnly = true)
    public PageResponse<UserResponse> getAllUsers(int page, int size)
    {
        Pageable pageRequest = PageRequest.of(page, size);
        Page<UserInfo> userInfoPage = userRepository.findAll(pageRequest);
        List<UserResponse> userResponseList = userInfoMapper.toUserResponse(userInfoPage.getContent());
        return new PageResponse<>(userResponseList, userInfoPage.getNumber(), userInfoPage.getSize(),
                userInfoPage.getTotalElements(), userInfoPage.getTotalPages());
    }

    @Transactional
    public UserResponse addUser(CreateUserRequest createUserRequest)
    {
        Optional<UserInfo> byUsername = userRepository.findByUsername(createUserRequest.name());
        if(!byUsername.isEmpty())
        {
            throw new IllegalStateException("Username already exist.");
        }
        Optional<UserInfo> byEmail = userRepository.findByEmail(createUserRequest.email());
        if(!byEmail.isEmpty())
        {
            throw new IllegalStateException("Email ID already exists.");
        }
        UserInfo userInfo = userInfoMapper.toUserInfo(createUserRequest);
        userInfo.setRole(UserRole.PASSENGER.toString());
        UserInfo newUserInfo = userRepository.save(userInfo);
        return userInfoMapper.toUserResponse(newUserInfo);
    }
}
