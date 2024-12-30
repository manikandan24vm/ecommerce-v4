package com.ecommerce.user_service.controller;

import com.ecommerce.user_service.dto.ErrorResponseDto;
import com.ecommerce.user_service.dto.UserDTO;
import com.ecommerce.user_service.entity.User;
import com.ecommerce.user_service.mapper.UserDtoMapper;
import com.ecommerce.user_service.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class UsersController {
    private static final Logger log = LoggerFactory.getLogger(UsersController.class);
    @Autowired
    private UserService userService;

    @Operation(description = "create user", summary = "provides the ability to add a new user")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Http status created"),
            @ApiResponse(responseCode = "500", description = "internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PostMapping("/users")
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO userDTO) {
        User users = UserDtoMapper.DtoToEntity(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserDtoMapper.entityToDto(userService.createUser(users)));

    }

    @Operation(description = "get all available user details",summary = "provides ability to retrieve all available users.")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "users retrieved successfully."),
            @ApiResponse(responseCode = "500",description = "internal server error",content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> usersList = userService.getAllUsers();
        List<UserDTO> userDTOList = usersList.stream().map(UserDtoMapper::entityToDto).toList();
        return ResponseEntity.ok(userDTOList);
    }


    @Operation(description = "update user details",summary = "provides the ability to update the user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "successfully updated user details"),
            @ApiResponse(responseCode = "417", description = "expectation failed"),
            @ApiResponse(responseCode = "500",description = "internal server error",content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))

    })
    @PutMapping("/users/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody @Valid UserDTO userDTO) {
        User users = UserDtoMapper.DtoToEntity(userDTO);
        return ResponseEntity.ok(UserDtoMapper.entityToDto(userService.updateUser(userId, users)));
    }

    @Operation(description = "retrieve user details by user id",summary = "provides the ability to retrieve the user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "successfully retrieved user details"),
            @ApiResponse(responseCode = "417", description = "expectation failed"),
            @ApiResponse(responseCode = "500",description = "internal server error",content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))

    })

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            return ResponseEntity.ok(UserDtoMapper.entityToDto(user));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @Operation(description = "delete user details by user id",summary = "provides the ability to delete the user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "successfully deleted user details"),
            @ApiResponse(responseCode = "417", description = "expectation failed"),
            @ApiResponse(responseCode = "500",description = "internal server error",content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))

    })
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<UserDTO> deleteUserById(@PathVariable Long userId) {
        try {
            userService.deleteUserById(userId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("can't remove user :{}", String.valueOf(e));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
