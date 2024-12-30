package com.ecommerce.user_service.controller;

import com.ecommerce.user_service.dto.ErrorResponseDto;
import com.ecommerce.user_service.dto.UserRoleDTO;
import com.ecommerce.user_service.entity.Role;
import com.ecommerce.user_service.mapper.UserRoleMapper;
import com.ecommerce.user_service.service.UserRolesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserRoleController {
    private UserRolesService userRolesService;
    @Operation(description = "create user role", summary = "provides the ability to create the user role")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "successfully created"),
            @ApiResponse(responseCode = "500",description = "internal server error",content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PostMapping("/role")
    public ResponseEntity<String> createUserRole(Long userId, UserRoleDTO role){
        Role roleObj= userRolesService.createUserRole(UserRoleMapper.toRole(role),userId);
        return ResponseEntity.status(HttpStatus.CREATED).body("created");
    }
}
