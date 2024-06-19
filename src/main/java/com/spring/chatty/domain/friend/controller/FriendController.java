package com.spring.chatty.domain.friend.controller;

import com.spring.chatty.domain.friend.domain.Friend;
import com.spring.chatty.domain.friend.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/friends")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    @PostMapping("/add")
    public ResponseEntity<String> addFriend(@RequestParam String friendEmail) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails == null) {
            return ResponseEntity.badRequest().body("사용자가 인증되지 않았습니다.");
        }
        friendService.addFriend(userDetails.getUsername(), friendEmail);
        return ResponseEntity.ok("친구가 성공적으로 추가되었습니다.");
    }

    @GetMapping("/list")
    public ResponseEntity<List<Friend>> getFriends() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails == null) {
            return ResponseEntity.badRequest().body(null);
        }
        List<Friend> friends = friendService.getFriends(userDetails.getUsername());
        return ResponseEntity.ok(friends);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteFriend(@RequestParam String friendEmail) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails == null) {
            return ResponseEntity.badRequest().body("사용자가 인증되지 않았습니다.");
        }
        friendService.deleteFriend(userDetails.getUsername(), friendEmail);
        return ResponseEntity.ok("친구가 성공적으로 삭제되었습니다.");
    }
}
