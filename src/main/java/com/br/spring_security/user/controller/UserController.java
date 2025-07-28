package com.br.spring_security.user.controller;

import com.br.spring_security.user.dto.request.UserRoleRequestDTO;
import com.br.spring_security.user.dto.response.UserWithRoleResponse;
import com.br.spring_security.user.entity.User;
import com.br.spring_security.user.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController
{
    private final UserService userService;

    @PatchMapping()
    public ResponseEntity<?> updateUserRole(@RequestParam @NotNull UUID id,
                                            @RequestParam @NotNull @Positive Integer oldRoleId,
                                            @RequestParam @NotNull @Positive Integer newRoleId)
    {
        userService.updateUserRole(id, oldRoleId, newRoleId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/role/all")
    public ResponseEntity<?> findAll()
    {
        List<User> userList = userService.findAll();
        List<UserWithRoleResponse> response = userList.stream().map(UserWithRoleResponse::new).toList();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/role/{id}")
    public ResponseEntity<?> findByIdWithRole(@PathVariable @NotNull UUID id)
    {
        User user = userService.findByIdWithRole(id);
        UserWithRoleResponse response = new UserWithRoleResponse(user);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/role")
    public ResponseEntity<?> addRoleToUser(@RequestBody @Valid UserRoleRequestDTO request)
    {
        userService.addRoleToUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
