package com.example.BusBookingApp.mapper;

import com.example.BusBookingApp.dto.CreateUserRequest;
import com.example.BusBookingApp.dto.UserResponse;
import com.example.BusBookingApp.entity.UserInfo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserInfoMapper {

    UserResponse toUserResponse(UserInfo user);

    List<UserResponse> toUserResponse(List<UserInfo> userList);

    UserInfo toUserInfo(CreateUserRequest createUserRequest);
}
